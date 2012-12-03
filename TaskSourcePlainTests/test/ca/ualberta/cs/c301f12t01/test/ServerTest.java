package ca.ualberta.cs.c301f12t01.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import ca.ualberta.cs.c301f12t01.common.Report;
import ca.ualberta.cs.c301f12t01.common.Sharing;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.serverStorage.Server;
import ca.ualberta.cs.c301f12t01.serverStorage.ServerStorage;

public class ServerTest {

	@Test
	public void test_nukeServer() {
		List<BasicNameValuePair> nvp = new ArrayList<BasicNameValuePair>();
		nvp.add(new BasicNameValuePair("action", "nuke"));
		nvp.add(new BasicNameValuePair("key", "judgedredd"));
		Server server = new Server();
		server.post(nvp);
		assertNotNull(server);
	}

	// add a global task and get it
	@Test
	public void test_add_retrieve_global_task() {
		Task t1 = TestUtils.makeSimpleTask();
		t1.setGlobal();
		ServerStorage ss = new ServerStorage();
		ss.storeTask(t1);
		HashMap<UUID, Task> hm = ss.getGlobalTasks();
		assertNotNull(hm);
		Task t2 = hm.get(t1.getId());
		assertEquals(t1.toString(), t2.toString());
	}

	// add a local task and get it
	@Test
	public void test_add_retrieve_local_task() {
		Task t1 = TestUtils.makeSimpleTask();
		t1.setLocal();
		ServerStorage ss = new ServerStorage();
		ss.storeTask(t1);
		HashMap<UUID, Task> hm = ss.getLocalTasks();
		assertNotNull(hm);
		Task t2 = hm.get(t1.getId());
		assertEquals(t1.toString(), t2.toString());
	}

	// add a global report and get it
	@Test
	public void test_add_retrieve_global_report() {
		Task t = TestUtils.makeSimpleTask();
		Report r1 = TestUtils.makeSimpleReport(t);
		r1.setSharing(Sharing.GLOBAL);
		ServerStorage ss = new ServerStorage();
		ss.storeReport(r1);
		ArrayList<Report> rList = ss.getGlobalReports(t);
		assertNotNull(rList);
		Report r2 = rList.get(0);
		assertEquals(r1.toString(), r2.toString());
	}

	// add a local report and get it
	@Test
	public void test_add_retrieve_local_report() {
		Task t = TestUtils.makeSimpleTask();
		Report r1 = TestUtils.makeSimpleReport(t);
		r1.setSharing(Sharing.LOCAL);
		ServerStorage ss = new ServerStorage();
		ss.storeReport(r1);
		ArrayList<Report> rList = ss.getLocalReports(t);
		assertNotNull(rList);
		Report r2 = rList.get(0);
		assertEquals(r1.toString(), r2.toString());
	}
	
	//get all reports
	@Test
	public void test_get_all_reports(){
		ServerStorage ss = new ServerStorage();
		ArrayList<Report> rList = ss.getAllReports();
		assertNotNull(rList);
	}
	
	//update task
	@Test
	public void test_update_task(){
		String newDescription = "A modified description";
		Task t = TestUtils.makeSimpleTask();
		ServerStorage ss = new ServerStorage();
		ss.storeTask(t);
		t.setDescription(newDescription);
		ss.updateTask(t);
		HashMap<UUID,Task> hm = ss.getGlobalTasks();
		Task t1 = hm.get(t.getId());
		assertEquals(t1.getDescription(),newDescription);
	}
}
