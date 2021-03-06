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

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.UUID;

import org.junit.Test;

import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.model.TaskCollection;
import ca.ualberta.cs.c301f12t01.util.DualIndexedObservableCollection;

/**
 * Testing for TaskCollection
 * @author home
 *
 */
public class TaskCollectionTest {

	@Test
	public void check_collection_size() {
		DualIndexedObservableCollection<UUID, Task> tc = new TaskCollection();
		Task t = TestUtils.makeSimpleTask();
		tc.add(t);
		assertTrue(tc.size() == 1);
	}
	
	@Test
	public void check_iterator(){
		DualIndexedObservableCollection<UUID, Task> tc = new TaskCollection();
		Task t = TestUtils.makeSimpleTask();
		tc.add(t);
		Iterator<Task> iter = tc.iterator();
		assertTrue(iter.next().equals(t));
		
	}

}
