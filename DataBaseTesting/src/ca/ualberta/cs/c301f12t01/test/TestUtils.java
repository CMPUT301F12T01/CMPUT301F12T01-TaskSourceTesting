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

import java.sql.Timestamp;
import java.util.Date;

import ca.ualberta.cs.c301f12t01.common.MediaType;
import ca.ualberta.cs.c301f12t01.common.Report;
import ca.ualberta.cs.c301f12t01.common.Request;
import ca.ualberta.cs.c301f12t01.common.Response;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.common.TextResponse;

/**
 * Provides utilities for testing
 * 
 * @author home
 *
 */
public class TestUtils {
	
    static final String TEST_USER = "Test User";
    
	/**
	 * @return A simple global task meant for testing
	 */
	public static Task makeSimpleTask(){
		Task t = new Task(TEST_USER);
		t.setDescription("This is a longer description");
		t.setSummary("A short summary");
		t.addRequest(TestUtils.makeSimpleRequest());
		return t;		
	}	
	
	public static Request makeSimpleRequest() {
		
		MediaType type = MediaType.TEXT;
		Request r = new Request(type, "THIS IS A DESCRIPTION YO", 1);
		
		return r;
	}
	
	public static Report makeSimpleReport(Task t) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		Report r = new Report(t, timestamp);
		return r;
	}
	
	public static Response makeSimpleTextResponse() {
		Response textResponse = new TextResponse("This is a text response.");
		return textResponse;
	}
}
