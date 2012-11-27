package ca.ualberta.cs.c301f12t01.test;

import java.util.ArrayList;
import java.util.Iterator;

import android.test.AndroidTestCase;
import ca.ualberta.cs.c301f12t01.common.Report;
import ca.ualberta.cs.c301f12t01.common.Response;
import ca.ualberta.cs.c301f12t01.common.Sharing;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.common.TextResponse;
import ca.ualberta.cs.c301f12t01.localStorage.DeviceStorage;
import ca.ualberta.cs.c301f12t01.test.TestUtils;

/**
 * Test class from the DatabaseTesting android project
 * 
 * This class attempts to test all of the functionality
 * of storing and retrieving Reports and Requests from 
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

public class ReportLocalStorageTest extends AndroidTestCase
{
	public void setup() {
		getContext().deleteDatabase("TaskSourceDB.db");
	}
	
	public void test_store_localreport() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
	}
	
	public void test_retrieve_localreport() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		ArrayList<Report> reportList = ds.getLocalReports(task);
		assertEquals(1, reportList.size());
		
	}
	
	public void test_remove_localreport() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		ds.removeReport(report);
		ArrayList<Report> reportList = ds.getLocalReports(task);
		assertEquals(0, reportList.size());
		
	}
	
	public void test_update_report() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		report.setSharing(Sharing.TASK_CREATOR);
		ds.updateReport(report);
		ArrayList<Report> reportList = ds.getTaskCreatorReports(task);
		ArrayList<Report> reportList2 = ds.getReports(task);
		assertEquals(reportList2.get(0).getId(), reportList.get(0).getId());
		
	}
	
	public void test_retrieve_globalreport() 
	{
		setup();
		
		// Put in 1 global report
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		ArrayList<Report> reportList = ds.getGlobalReports(task);
		assertEquals(0, reportList.size());
		
	}
	
	public void test_retrieve_samelocalreport() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		ArrayList<Report> reportList = ds.getLocalReports(task);
		Iterator<Report> iter = reportList.iterator();
		
		while (iter.hasNext()){
			Report r = iter.next();
			if (r.getId().toString().equals(report.getId().toString())) {
				assertEquals(report.getId().toString(), r.getId().toString());
			}
		}
	}
	
	public void test_retrieve_responses() 
	{
		setup();
		
		Task task = TestUtils.makeSimpleTask();
		Report report = TestUtils.makeSimpleReport(task);
		Response res = new TextResponse("RESPONSE YAY");
		report.addResponse(res);
		DeviceStorage ds = new DeviceStorage(getContext());
		
		ds.storeReport(report);
		ArrayList<Report> reportList = ds.getLocalReports(task);
		Iterator<Report> iter = reportList.iterator();
		
		while (iter.hasNext()){
			Report r = iter.next();
			if (r.getId().toString().equals(report.getId().toString())) {
				
				for (Response response : r) {
					//System.out.println(response.getResponseData());
					assertEquals(response.getResponseData(), "RESPONSE YAY");
					
				}
			}
		}
		
	}

}
