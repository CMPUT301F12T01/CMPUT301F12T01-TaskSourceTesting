package ca.ualberta.cs.c301f12t01.test;
import static org.junit.Assert.*;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.util.DualIndexedObservableCollection;
import java.util.UUID;

import org.junit.Test;

public class ObservableCollectionTests{
	
	public class Dummy extends DualIndexedObservableCollection<UUID, Task> {
		public UUID getKey(Task element)	{
			return element.getId();
		}
	}
	
	@Test
	public void addNoNotify() {
		Dummy testCase = new Dummy();
		Task element = new Task(UUID.randomUUID());
		assertTrue(testCase.addNoNotify(element));
	}
	
	@Test
	public void size() {
		Dummy testCase = new Dummy();
		Task element = new Task(UUID.randomUUID());
		testCase.addNoNotify(element);
		assertTrue(testCase.size() == 1);
	}
	
	@Test
	public void isEmpty() {
		Dummy testCase = new Dummy();
		assertTrue(testCase.isEmpty());
	}
	
	@Test
	public void getAt() {
		Dummy testCase = new Dummy();
		Task element = new Task(UUID.randomUUID());
		testCase.addNoNotify(element);
		assertTrue(testCase.getAt(0).equals(element));
	}
	
	/**
	 * This test checks to see if element2 is in the last position by checking
	 * to see if the element2 equals the one at position 1 in the arrayList.
	 */
	@Test
	public void newestLast1() {
		Dummy testCase = new Dummy();
		Task element1 = new Task(UUID.randomUUID());
		testCase.addNoNotify(element1);
		Task element2 = new Task(UUID.randomUUID());
		testCase.addNoNotify(element2);
		assertTrue(testCase.getAt(1).equals(element2));
	}
	
	/**
	 * This test makes sure that it is element2 that is in the last position
	 * by using the fact that it fails for element 1.
	 */
	@Test
	public void newestLast2() {
		Dummy testCase = new Dummy();
		Task element1 = new Task(UUID.randomUUID());
		testCase.addNoNotify(element1);
		Task element2 = new Task(UUID.randomUUID());
		testCase.addNoNotify(element2);
		assertTrue(testCase.getAt(1).equals(element1));
	}
}
