package ca.ualberta.cs.c301f12t01.test;

import java.util.HashMap;
import java.util.UUID;

import android.test.AndroidTestCase;
import android.widget.Gallery;
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
		
		assertEquals(returnedTask.getRequest(1).getDescription(), r.getDescription());
		
	}
	
	public void test_globalTasksDoesNotCoerceSharingMode() {
		setup();
		
		Task localTask = TestUtils.makeSimpleTask();
		localTask.setSummary("this is local");
		localTask.setLocal();

		assert localTask.isLocal();
		
		Task globalTask = TestUtils.makeSimpleTask();
		globalTask.setSummary("this is global");
		globalTask.setGlobal();

		assert localTask.isGlobal();

		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(globalTask);
		ds.storeTask(localTask);
		
		HashMap <UUID, Task> globalTasks = ds.getGlobalTasks();

		assertTrue("Global tasks returns more tasks than is neccessary", globalTasks.size() == 1);
		
		
	}
	
	
	public void test_GetGlobalOnlyReturnsGlobalTasks() {
		setup();
		
		Task localTask = TestUtils.makeSimpleTask();
		localTask.setSummary("this is local");
		localTask.setLocal();
		assert localTask.isLocal();
		
		Task globalTask = TestUtils.makeSimpleTask();
		globalTask.setSummary("ths is global");
		globalTask.setGlobal();

		assert localTask.isGlobal();

		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(globalTask);
		ds.storeTask(localTask);
		
		HashMap <UUID, Task> globalTasks = ds.getGlobalTasks();
		
		
		boolean allGlobal = true; 
		for (Task task : globalTasks.values()) {
			if (task.isLocal()) 
				allGlobal = false;
		}
		
		assertTrue("Found local task in global tasks", allGlobal);
		
		
	}
	
	public void test_GetLocalOnlyReturnsLocalTasks() {
		setup();
		
		Task localTask = TestUtils.makeSimpleTask();
		localTask.setSummary("this is local");
		localTask.setLocal();
		assert localTask.isLocal();
		
		Task globalTask = TestUtils.makeSimpleTask();
		globalTask.setSummary("ths is global");
		globalTask.setGlobal();

		assert localTask.isGlobal();

		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(globalTask);
		ds.storeTask(localTask);
		
		HashMap <UUID, Task> localTasks = ds.getLocalTasks();
		
		boolean allLocal = true; 
		for (Task task : localTasks.values()) {
			if (task.isGlobal()) {
				allLocal = false;
			}
		}
		
		System.err.println(allLocal);
		
		assertTrue("Found global task in local tasks", allLocal);
		
		
	}

}
