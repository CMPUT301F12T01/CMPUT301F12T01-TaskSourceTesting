package ca.ualberta.cs.c301f12t01.test;

import static org.junit.Assert.*;
import ca.ualberta.cs.c301f12t01.common.MediaType;
import ca.ualberta.cs.c301f12t01.common.Request;
import ca.ualberta.cs.c301f12t01.common.Task;
import ca.ualberta.cs.c301f12t01.util.DualIndexedObservableCollection;
import ca.ualberta.cs.c301f12t01.util.Message;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import org.junit.Test;

/**
 * Tests for DualIndexedObservableCollection and its parent ObservableCollection
 * to make sure all the methods are working properly since this class is very
 * important in the grand scheme of things.
 * 
 * @author padlesky
 * 
 */
public class ObservableCollectionTests {

    /*
     * TODO: unimplemented tests:
     * 
     * - removeKey
     * - grabIndex
     * 
     * Actually, there are a whole bunch more to test, but I couldn't keep track
     * of 'em.
     */

    /**
     * Dummy class to extend from DualIndexedObservableCollection so that we can
     * test its methods to see if it works.
     */
    public class Dummy extends DualIndexedObservableCollection<UUID, Task> {
        public UUID getKey(Task element) {
            return element.getId();
        }
    }

    /**
     * Simple check to see if adding a task goes through.
     */
    @Test
    public void addNoNotify() {
        Dummy testCase = new Dummy();
        Task element = new Task(UUID.randomUUID());
        assertTrue(testCase.add(element));
    }

    /**
     * checks to see if size reads the size of the HashMap right.
     */
    @Test
    public void size() {
        Dummy testCase = new Dummy();
        Task element = new Task(UUID.randomUUID());
        testCase.add(element);
        assertTrue(testCase.size() == 1);
    }

    /**
     * checks to see if something is empty reads properly.
     */
    @Test
    public void isEmpty() {
        Dummy testCase = new Dummy();
        assertTrue(testCase.isEmpty());
    }

    /**
     * Checks to see if the object at position matches the object that is there.
     */
    @Test
    public void getAt() {
        Dummy testCase = new Dummy();
        Task element = new Task(UUID.randomUUID());
        testCase.add(element);
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
        testCase.add(element1);
        Task element2 = new Task(UUID.randomUUID());
        testCase.add(element2);
        assertTrue(testCase.getAt(1).equals(element2));
    }

    /**
     * This test makes sure that it is element2 that is in the last position by
     * using the fact that it fails for element 1.
     */
    @Test
    public void newestLast2() {
        Dummy testCase = new Dummy();
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        Task element2 = new Task(UUID.randomUUID());
        testCase.add(element2);
        assertFalse(testCase.getAt(1).equals(element1));
    }

    /**
     * Checks to see if you can grab the index of an element once it is added.
     */
    @Test
    public void indexOf() {
        Dummy testCase = new Dummy();
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        assertTrue(testCase.grabIndex(element1) == 0);
    }

    /**
     * Simple check to see if replaceNoNotify succeeds.
     */
    @Test
    public void replaceNoNotify1() {
        Dummy testCase = new Dummy();
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        Request request = new Request(MediaType.TEXT);
        element1.addRequest(request);
        Task element2 = element1;
        assertTrue(testCase.replace(element1, element2));
    }

    /**
     * Proves the replaced object is at the same position, as well as that the
     * element replaced is actually there.
     */
    @Test
    public void replaceNoNotify2() {
        Dummy testCase = new Dummy();
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        Request request = new Request(MediaType.TEXT);
        element1.addRequest(request);
        Task element2 = element1;
        testCase.replace(element1, element2);
        assertTrue(testCase.getAt(0).equals(element2));
    }

    /**
     * Check to see if removeNoNotify Succeeds.
     */
    @Test
    public void removeNoNotify1() {
        Dummy testCase = new Dummy();
        Task element = new Task(UUID.randomUUID());
        assertTrue(testCase.removeElement(element));
    }

    /**
     * Checks to see if once something is removed the position in the list is
     * readjusted to account for it.
     */
    @Test
    public void removeNoNotify2() {
        Dummy testCase = new Dummy();
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        Task element2 = new Task(UUID.randomUUID());
        testCase.add(element2);
        testCase.removeElement(element1);
        assertTrue(testCase.grabIndex(element2) == 0);
    }

    /**
     * Checks to see if the observers are notified of changes.
     */
    @Test
    public void addWithNotify() {
        Observer test = new Observer() {
            @Override
            public void update(Observable obs, Object obj) {
                assertNotNull(obj);
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
    }

    /**
	 * 
	 */
    @Test
    public void replaceWithNotify() {
        Observer test = new Observer() {
            @Override
            public void update(Observable obs, Object obj) {
                assertNotNull(obj);
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        Request request = new Request(MediaType.TEXT);
        element1.addRequest(request);
        Task element2 = element1;
        testCase.replace(element1, element2);
    }

    /**
	 * 
	 */
    @Test
    public void removeWithNotify() {
        Observer test = new Observer() {
            @Override
            public void update(Observable obs, Object obj) {
                assertNotNull(obj);
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        testCase.removeElement(element1);
    }

    /**
     * So returns the proper message when you add a Task.
     */
    @Test
    public void addWithNotifyMessage() {
        Observer test = new Observer() {
            public void update(Observable obs, Object obj) {
                Message message = (Message) obj;
                assertTrue(message.getAction().equals(
                        Message.MessageAction.ADDED));
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
    }

    /**
     * Returns the proper message when you modify a Task.
     */
    @Test
    public void replaceWithNotifyMessage() {
        Observer test = new Observer() {
            public void update(Observable obs, Object obj) {
                Message message = (Message) obj;
                assertTrue(message.getAction().equals(
                        Message.MessageAction.MODIFIED));
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        Task element2 = new Task(UUID.randomUUID());
        testCase.replace(element1, element2);
    }

    /**
     * Returns the proper message when you remove a Task.
     */
    @Test
    public void removeWithNotifyMessage() {
        Observer test = new Observer() {
            @Override
            public void update(Observable obs, Object obj) {
                Message message = (Message) obj;
                assertTrue(message.getAction().equals(
                        Message.MessageAction.REMOVED)
                        || message.getAction().equals(
                                Message.MessageAction.ADDED));
            }
        };
        Dummy testCase = new Dummy();
        testCase.addObserver(test);
        Task element1 = new Task(UUID.randomUUID());
        testCase.add(element1);
        testCase.removeElement(element1);
    }
}
