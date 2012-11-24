package ca.ualberta.cs.c301f12t01.test;

import java.util.HashMap;
import java.util.UUID;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import ca.ualberta.cs.c301f12t01.common.Request;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.localStorage.DBInstance;
import ca.ualberta.cs.c301f12t01.localStorage.DeviceStorage;
import ca.ualberta.cs.c301f12t01.testing.TestUtils;

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

	public void test_store_localtask() 
	{
		DBInstance instance = new DBInstance(getContext());
		SQLiteDatabase db = instance.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS Tasks");
	    db.execSQL("DROP TABLE IF EXISTS Reports");
	    db.execSQL("DROP TABLE IF EXISTS Request");
	    db.execSQL("DROP TABLE IF EXISTS Response");
	    //instance.onCreate(db);
		db.close();
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
	}
	
	public void test_retrieve_localtask() 
	{
		getContext().deleteDatabase("TaskSourceDB.db");
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		assertNotNull(taskHash);
		
	}
	
	public void test_retrieve_globaltask() 
	{
		getContext().deleteDatabase("TaskSourceDB.db");
		
		// put in 1 global task
		Task task = TestUtils.makeSimpleTask();
		task.setGlobal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getGlobalTasks();
		assertEquals(0, taskHash.size());
		
	}
	
	public void test_retrieve_samelocaltask() 
	{
		getContext().deleteDatabase("TaskSourceDB.db");
		
		Task task = TestUtils.makeSimpleTask();
		task.setLocal();
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeTask(task);
		HashMap<UUID,Task> taskHash = ds.getLocalTasks();
		
		Task returnedTask = taskHash.get(task.getId());
		assertEquals(task.getId().toString(), 
				returnedTask.getId().toString());
	}
	
	public void test_retrieve_requests() 
	{
		getContext().deleteDatabase("TaskSourceDB.db");
		
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
