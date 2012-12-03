package ca.ualberta.cs.c301f12t01.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.http.message.BasicNameValuePair;

import android.test.AndroidTestCase;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.serverStorage.Server;
import ca.ualberta.cs.c301f12t01.serverStorage.ServerStorage;
import ca.ualberta.cs.c301f12t01.serverStorage.TaskServerRetrieval;
import ca.ualberta.cs.c301f12t01.serverStorage.TaskServerStorage;

public class TaskServerTest extends AndroidTestCase {
	
	//first nuke the server
	/**public void test_nukeServer(){
		List<BasicNameValuePair> nvp = new ArrayList<BasicNameValuePair>();
		nvp.add(new BasicNameValuePair("action", "nuke"));
		nvp.add(new BasicNameValuePair("key", "judgedredd"));
		Server server = new Server();
		server.post(nvp);
		assertNotNull(server);
	}*/
	
	public void test_getTasks(){
		ArrayList<Task> tasks = TaskServerRetrieval.getAllTasks();
		assertNotNull(tasks);
	}
	
	//add a task and get it back	
	/*public void test_addTask(){
		Task t1 = TestUtils.makeSimpleTask();
		ServerStorage ss = new ServerStorage();
		ss.storeTask(t1);
		HashMap<UUID, Task> hm = ss.getLocalTasks();
		Task t2 = hm.get(t1.getId());
		assertEquals(t1,t2);		
	}*/	
}
