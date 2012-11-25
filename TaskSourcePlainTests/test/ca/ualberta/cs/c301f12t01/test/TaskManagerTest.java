/**
 * Copyright 2012 Neil Borle, Mitchell Home, Bronte Lee, Aaron
 * Padlesky, Eddie Santos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package ca.ualberta.cs.c301f12t01.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.model.TaskCollection;
import ca.ualberta.cs.c301f12t01.model.TaskManager;

/**
 * Contains test for testing the TaskManager class *
 * 
 * These are all very terribly thought out tests.
 * 
 * NOTE TO SELF: Singletons are hard to test.
 * 
 * @author home
 */
public class TaskManagerTest {

    /** Create a new, empty TaskManager, for testing. */
    private TaskManager emptyTaskManager() {
        Collection<Task> empytCollection = new ArrayList<Task>();

        TaskManager empty = TaskManager.initializeTaskMananger(empytCollection,
                empytCollection);

        return empty;
    }

    /** Tests the creation of a new, empty TaskManager. */
    @Test
    public void testEmptyTaskManager() {
        TaskManager manager = emptyTaskManager();

        assertTrue(manager.getGlobalTaskCollection().isEmpty()
                && manager.getLocalTaskCollection().isEmpty());
    }

    @Test
    public void add_global_task() {

        TaskManager tm = emptyTaskManager();

        Task original = TestUtils.makeSimpleTask();
        tm.addTask(original); // now we should have one global task

        TaskCollection globalTasks = tm.getGlobalTaskCollection();
        Task t2 = globalTasks.getAt(0);
        assertTrue(original.equals(t2));
    }

    @Test
    public void add_local_task() {
        TaskManager tm = TaskManager.getInstance();
        Task t1 = TestUtils.makeSimpleTask();
        t1.setLocal();
        tm.addTask(t1); // now we should have one local task
        TaskCollection l = tm.getLocalTaskCollection();
        Task t2 = l.getAt(0);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void get_global_id() {
        TaskManager tm = TaskManager.getInstance();
        Task t1 = TestUtils.makeSimpleTask();
        tm.addTask(t1);
        Task t2 = tm.get(t1.getId());
        assertTrue(t1.equals(t2));
    }

    @Test
    public void get_local_id() {
        TaskManager tm = TaskManager.getInstance();
        Task t1 = TestUtils.makeSimpleTask();
        t1.setLocal();
        tm.addTask(t1);
        Task t2 = tm.get(t1.getId());
        assertTrue(t1.equals(t2));
    }

    @Test
    public void get_invalid_id() {
        TaskManager tm = TaskManager.getInstance();
        Task t1 = TestUtils.makeSimpleTask();
        t1.setLocal();
        tm.addTask(t1);
        Task t2 = tm.get(UUID.randomUUID());
        assertTrue(t2 == null);
    }
}
