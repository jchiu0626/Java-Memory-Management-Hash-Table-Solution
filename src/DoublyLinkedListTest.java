import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the DoublyLinkedList class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/
public class DoublyLinkedListTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */    
    public void setUp() {
        // Nothing here
    }


    /**
     * Test constructor
     */
    @Test
    public void testConstructor() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertNotNull(doublyLinkedList);
    }

    /**
     * Test add at head
     */
    @Test
    public void testAddAtHead() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertEquals(doublyLinkedList.getSize(), 0);
        doublyLinkedList.addAtHead(1);
        assertEquals(doublyLinkedList.getSize(), 1);
        doublyLinkedList.addAtHead(2);
        assertEquals(doublyLinkedList.getSize(), 2);
    }
    
    /**
     * Test add at tail
     */
    @Test
    public void testAddAtTail() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertEquals(doublyLinkedList.getSize(), 0);
        doublyLinkedList.addAtTail(1);
        assertEquals(doublyLinkedList.getSize(), 1);
        doublyLinkedList.addAtTail(2);
        assertEquals(doublyLinkedList.getSize(), 2);
    }
    
    /**
     * Test remove head
     */
    @Test
    public void testRemoveAtHead() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertEquals(doublyLinkedList.getSize(), 0);  
        doublyLinkedList.removeHead();
        assertEquals(doublyLinkedList.getSize(), 0);  
        doublyLinkedList.addAtTail(1);
        assertEquals(doublyLinkedList.getSize(), 1);
        doublyLinkedList.removeHead();
        assertEquals(doublyLinkedList.getSize(), 0);        
    }
    
    /**
     * Test remove tail
     */
    @Test
    public void testRemoveAtTail() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertEquals(doublyLinkedList.getSize(), 0);  
        doublyLinkedList.removeTail();
        assertEquals(doublyLinkedList.getSize(), 0);  
        doublyLinkedList.addAtTail(1);
        assertEquals(doublyLinkedList.getSize(), 1);
        doublyLinkedList.removeTail();
        assertEquals(doublyLinkedList.getSize(), 0);        
    }
    
    /**
     * Test add in order
     */
    @Test
    public void testAddInOrder() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addInOrder(4);
        assertEquals(doublyLinkedList.getSize(), 1);
        assertEquals(doublyLinkedList.getHeadValue(), 4);
        doublyLinkedList.addInOrder(2);
        assertEquals(doublyLinkedList.getSize(), 2);
        assertEquals(doublyLinkedList.getHeadValue(), 2);
        doublyLinkedList.addInOrder(3);
        assertEquals(doublyLinkedList.getSize(), 3);
        assertEquals(doublyLinkedList.getHeadValue(), 2);
        doublyLinkedList.addInOrder(1);
        assertEquals(doublyLinkedList.getSize(), 4);
        assertEquals(doublyLinkedList.getHeadValue(), 1);
    }
    
    /**
     * Test print
     */
    @Test
    public void testPrint() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addInOrder(4);
        assertEquals(" 4\n", doublyLinkedList.print());
        doublyLinkedList.addInOrder(2);
        assertEquals(" 2 4\n", doublyLinkedList.print());
        doublyLinkedList.addInOrder(3);
        assertEquals(" 2 3 4\n", doublyLinkedList.print());
        doublyLinkedList.addInOrder(1);
        assertEquals(" 1 2 3 4\n", doublyLinkedList.print());
    }
    
    /**
     * Test remove value
     */
    @Test
    public void testRemoveValue() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addInOrder(4);
        doublyLinkedList.addInOrder(2);
        doublyLinkedList.addInOrder(3);
        doublyLinkedList.addInOrder(1);
        assertEquals(doublyLinkedList.getSize(), 4);
        doublyLinkedList.removeValue(3);
        assertEquals(doublyLinkedList.getSize(), 3);
    }
    
    /**
     * Test remove value but not found
     */
    @Test
    public void testRemoveValueNotFound() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addInOrder(4);
        doublyLinkedList.addInOrder(2);
        doublyLinkedList.addInOrder(3);
        doublyLinkedList.addInOrder(1);
        assertEquals(doublyLinkedList.getSize(), 4);
        doublyLinkedList.removeValue(5);
        assertEquals(doublyLinkedList.getSize(), 4);
    }
    
    /**
     * Test find value
     */
    @Test
    public void testFindValue() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addInOrder(4);
        doublyLinkedList.addInOrder(2);
        doublyLinkedList.addInOrder(3);
        doublyLinkedList.addInOrder(1);
        assertEquals(doublyLinkedList.findValue(3), true);
        assertEquals(doublyLinkedList.findValue(5), false);
    }
}

