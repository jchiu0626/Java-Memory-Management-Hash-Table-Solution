import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the MemManager class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/

public class MemManagerTest extends TestCase {
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
        MemManager memManager = new MemManager(4, false);
        assertNotNull(memManager);
    }
    
    /**
     * Test insert
     */
    @Test
    public void testInsert() {
        MemManager memManager = new MemManager(4, false);
        byte[] space = {1, 2, 3, 4};
        Handle handle = memManager.insert(space, space.length);
        byte[] retrievedSpace = memManager.getBytes(
                handle.getPosition(), handle.getLength());
        for (int i = 0; i < space.length; i++) {
            assertEquals(space[i], retrievedSpace[i]);
        }
        assertNotNull(handle);
        assertEquals(4, handle.getLength());
        assertEquals(0, handle.getPosition());
        assertTrue(handle.getPosition() >= 0);
    }

    
    /**
     * Test get length
     */
    @Test
    public void testGetLength() {
        MemManager memManager = new MemManager(4, false);
        byte[] space = {1, 2, 3, 4};
        Handle handle = memManager.insert(space, space.length);
        assertEquals(space.length, memManager.getLength(handle));
    }

    
    /**
     * Test remove
     */
    @Test
    public void testRemove() {
        MemManager memManager = new MemManager(4, false);
        byte[] space = {1, 2, 3, 4};
        Handle handle1 = memManager.insert(space, space.length);
        memManager.remove(handle1);
        Handle handle2 = memManager.insert(space, space.length);
        assertNotNull(handle2);
        memManager.remove(handle2);
    }
    
    /**
     * Test resize
     */
    @Test
    public void testResize() {
        MemManager memManager = new MemManager(4, false);
        byte[] space = {1, 2, 3, 4, 5, 6, 7, 8};
        memManager.resize(8);
        Handle handle = memManager.insert(space, space.length);
        assertNotNull(handle);
    }
    
    /**
     * Test print
     */
    @Test
    public void testPrint() {
        MemManager memManager = new MemManager(4, false);
        memManager.print();
        String output = systemOut().getHistory();
        assertTrue(output.contains("4: 0"));
        byte[] space = {1, 2, 3, 4};
        Handle handle = memManager.insert(space, space.length);
        memManager.print();
        output = systemOut().getHistory();
        assertTrue(output.contains("There are no"
                + " freeblocks in the memory pool"));
    }
    
    /**
     * Test get bytes
     */
    @Test
    public void testGetBytes() {
        MemManager memManager = new MemManager(4, false);
        byte[] space = {1, 2, 3, 4};
        memManager.insert(space, space.length);
        byte[] retrievedSpace = memManager.getBytes(0, 4);
        for (int i = 0; i < space.length; i++) {
            assertEquals(space[i], retrievedSpace[i]);
        }
    }

    
}
