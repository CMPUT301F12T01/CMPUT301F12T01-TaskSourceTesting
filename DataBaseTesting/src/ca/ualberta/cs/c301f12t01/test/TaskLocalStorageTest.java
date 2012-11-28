package ca.ualberta.cs.c301f12t01.test;

import java.util.HashMap;
import java.util.UUID;

import android.test.AndroidTestCase;
import ca.ualberta.cs.c301f12t01.common.Request;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.localStorage.DeviceStorage;
import ca.ualberta.cs.c301f12t01.test.TestUtils;

/**
 * Test class from the DatabaseTesting android project
 * 
 * This class attempts to test all of the functionality 
 * of storing and retrieving Tasks and Responses from 
 * the sqlite database
 * 
 * IMPORTANT!!!!!!
 * Because this class is from another project for testing
 * it should not run. It is only here to demonstrate that
 * testing was performed for the database
 * 
 * @author nborle
 *
 */

public class TaskLocalStorageTest extends AndroidTestCase
{
	
	/**
	 * Initial setup that removes a remaining database
	 */
	public void setup() {
		getContext().deleteDatabase("TaskSourceDB.db");
	}

	/**
	 * Check if a task can be stored into the database
	 */
	public void test_store_localtask() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
	}
	
	/**
	 * Test if a local task that was stored can
	 * be retrieved
	 */
	public void test_retrieve_localtask() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		assertEquals(1, taskHash.size());
		
	}
	
	/**
	 * Test if a task stored in the database can be
	 * removed from the database
	 */
	public void test_remove_localtask() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		ds.removeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		assertEquals(0, taskHash.size());
		
	}
	
	/**
	 * Test if a task stored in the database can 
	 * be updated in the database
	 */
	public void test_update_task() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		task.setGlobal();
		ds.updateTask(task);
		HashMap<UUID,Task> taskHash = ds.getGlobalTasks();
		HashMap<UUID,Task> taskHash2 = ds.getOwnTasks(task.getUser());
		assertEquals(taskHash.get(task.getId()).getId(), taskHash2.get(task.getId()).getId());
		
	}
	
	/**
	 * Test if a global task can be retrieved 
	 * from the database
	 */
	public void test_retrieve_globaltask() 
	{
		setup();
		
		// put in 1 global task
		Task task = TestUtils.makeSimpleTask();
		task.setGlobal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getGlobalTasks();
		assertEquals(1, taskHash.size());
		
	}
	
	/**
	 * Again retrieve a task but be 
	 * rigorous about it being the same task
	 * that was initially stored
	 */
	public void test_retrieve_samelocaltask() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		
		Task returnedTask = taskHash.get(task.getId());
		assertEquals(task.getId().toString(), 
				returnedTask.getId().toString());
	}
	
	/**
	 * When storing a task that has requests,
	 * make sure that you get get back the same
	 * reconstructed task that contains the 
	 * same requests
	 */
	public void test_retrieve_requests() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		//ADDED BY MITCHELL
		Request r = TestUtils.makeSimpleRequest();
		r.setDescription("I've got a new description");
		task.addRequest(r);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		
		Task returnedTask = taskHash.get(task.getId());
		
		for (Request req : returnedTask) {
			//System.out.println(req.getDescription());
			assertEquals(req.getDescription(), r.getDescription());
		}
	}

}
