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

import ca.ualberta.cs.c301f12t01.common.Report;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.serverStorage.ReportServerStorage;
import ca.ualberta.cs.c301f12t01.serverStorage.TaskServerRetrieval;
import ca.ualberta.cs.c301f12t01.serverStorage.TaskServerStorage;

/**
 * 
 * @author home
 *
 */
public class InitialBaseServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testReport();
		//testTask();
		testGetAll();
	}
	
	public static void testGetAll(){
		/*Task t = TestUtils.makeSimpleTask();
		TaskServerStorage.storeTask(t);
		t = TestUtils.makeSimpleTask();
		t.setDescription("d1");
		TaskServerStorage.storeTask(t);
		t = TestUtils.makeSimpleTask();
		t.setDescription("d2");
		TaskServerStorage.storeTask(t);
		t = TestUtils.makeSimpleTask();
		t.setDescription("d3");
		TaskServerStorage.storeTask(t);
		*/
		TaskServerRetrieval.getAllTasks();
		
	}
	public static void testReport(){
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		ReportServerStorage.storeReport(report);
	}
	public static void testTask(){
		Task task = TestUtils.makeSimpleTask();
		TaskServerStorage.storeTask(task);
	}

}

