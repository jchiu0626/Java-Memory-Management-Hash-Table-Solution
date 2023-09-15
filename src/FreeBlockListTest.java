import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the FreeBlockList class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/
public class FreeBlockListTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */    
    public void setUp() {
        // Nothing here
    }

    
    /**
     * Test constructor with resize true
     */
    @Test
    public void testConstructorWithResizeTrue() {
        FreeBlockList freeBlockList = new FreeBlockList(256, true);
        assertNotNull(freeBlockList);
        assertEquals(0, freeBlockList.getDoublyLinkedList()
                [freeBlockList.getLength() - 1].getSize());
    }
    
    /**
     * Test constructor with resize false
     */
    @Test
    public void testConstructorWithResizeFalse() {
        FreeBlockList freeBlockList = new FreeBlockList(256, false);
        assertNotNull(freeBlockList);
        assertEquals(1, freeBlockList.getDoublyLinkedList()
                [freeBlockList.getLength() - 1].getSize());
    }

    /**
     * Test insert in empty slot
     */
    @Test
    public void testInsertInEmptySlot() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        assertNull(hashTable.getArray()[1]);
        hashTable.insert(1, handle);
        assertNotNull(hashTable.getArray()[1]);
        assertTrue(hashTable.getArray()[1].getKey() != -1);
    }
    
    /**
     * Test there's no free block
     * 
     */
    @Test
    public void testMaxSizeFreeBlockNotExists() {
        FreeBlockList freeBlockList = new FreeBlockList(256, true);
        assertEquals(freeBlockList.maxSizeFreeBlock(), -1);
    }
    
    /**
     * Test there exists free block
     * 
     */
    @Test
    public void testMaxSizeFreeBlockExists() {
        FreeBlockList freeBlockList = new FreeBlockList(256, false);
        assertEquals(freeBlockList.maxSizeFreeBlock(), 256);
    }
    
    /**
     * Test there exists free block in the middle doubly linked list
     * 
     */
    @Test
    public void testMaxSizeFreeBlockWhenMiddleFree() {
        FreeBlockList freeBlockList = new FreeBlockList(256, true);
        freeBlockList.getDoublyLinkedList()[4].addAtHead(128);
        assertEquals(freeBlockList.maxSizeFreeBlock(), 32); 
    }

}

