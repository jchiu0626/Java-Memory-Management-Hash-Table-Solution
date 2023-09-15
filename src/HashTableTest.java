import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the HashTable class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/
public class HashTableTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */    
    public void setUp() {
        // Nothing here
    }


    /**
     * Test constructor without parameter
     */
    @Test
    public void testConstructorWithoutParameter() {
        HashTable hashTable = new HashTable();
        assertNotNull(hashTable);
    }
    
    /**
     * Test constructor with parameter
     */
    @Test
    public void testConstructorWithParameter() {
        HashTable hashTable = new HashTable(4);
        assertNotNull(hashTable);
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
     * Test insert in a tombstone slot
     */
    @Test
    public void testInsertInTombstoneSlot() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        assertNull(hashTable.getArray()[1]);
        hashTable.insert(1, handle);
        hashTable.delete(1);
        assertNotNull(hashTable.getArray()[1]);
        assertFalse(hashTable.getArray()[1].getKey() != -1);
        hashTable.insert(1, handle);
        assertNotNull(hashTable.getArray()[1]);
        assertTrue(hashTable.getArray()[1].getKey() != -1);
        assertNotNull(hashTable.getArray()[1].getHandle());
    }
    
    /**
     * Test insert in a non-empty slot
     */
    @Test
    public void testInsert() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        assertNotNull(hashTable.getArray()[1]);
        hashTable.insert(5, handle);
        assertNotNull(hashTable.getArray()[1]);
        assertTrue(hashTable.getArray()[(hashTable.h1(5) + hashTable.h2(5))
                                        % hashTable.getSize()] != null);
        assertTrue(hashTable.getArray()[1].getKey() != -1);
    }
    
    
    /**
     * Test delete a key not in the hash table
     */ 
    @Test
    public void testDeleteNull() {
        HashTable hashTable = new HashTable(4);
        assertNull(hashTable.search(1));
        hashTable.delete(1);
    }
    
    /**
     * delete not null
     */ 
    @Test
    public void testDelete() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        hashTable.insert(5, handle);
        assertNotNull(hashTable.search(1));
        assertNotNull(hashTable.search(5));
        assertTrue(hashTable.getArray()[hashTable.h1(5)].getKey() != 5);
        assertFalse(hashTable.getArray()[(hashTable.h1(5) + 
                hashTable.h2(5)) % hashTable.getSize()].getKey() != 5);
        assertEquals(hashTable.getCount(), 2);
        hashTable.delete(5);
        assertNull(hashTable.search(5));
        assertTrue(hashTable.getArray()[(hashTable.h1(5) + 
                hashTable.h2(5)) % hashTable.getSize()].getKey() != 5);
        assertEquals(hashTable.getCount(), 1);
        hashTable.delete(1);
        assertEquals(hashTable.getCount(), 0);
    }
    
    /**
     * Test delete a tombstone
     */ 
    @Test
    public void testDeleteTombstone() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        assertNotNull(hashTable.search(1));
        hashTable.delete(1);
        assertNull(hashTable.search(1));
        hashTable.delete(1);
        assertNull(hashTable.search(1));
    }
    
    /**
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testEmptyTableSearchDelete() {
        HashTable hashTable = new HashTable(4);
        hashTable.delete(1);
        assertNull(hashTable.search(1));
    }
    
    /**
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testSearchNotFound() {
        HashTable hashTable = new HashTable(4);
        assertNull(hashTable.search(1));
    }
    
    /**
     * Test the resize method
     */ 
    @Test
    public void testResize() {
        HashTable hashTable = new HashTable(4);
        Handle[] handles = new Handle[7];
        for (int i = 0; i < 7; i++) {
            Handle handle = new Handle();
            handles[i] = handle;
            hashTable.insert(i, handle);
        }
        assertEquals(hashTable.getSize(), 16);
        for (int i = 0; i < 7; i++) {
            Handle handle = hashTable.search(i);
            assertNotNull(handle);
            assertTrue(handles[i] == handle);
        }
        for (int i = 7; i < 16; i++) {
            assertNull(hashTable.search(i));
        }
    }
    
    /**
     * Test the resize method with tombstone
     */
    @Test
    public void testResizeWithTombstone() {
        HashTable hashTable = new HashTable(4);
        for (int i = 0; i < 2; i++) {
            hashTable.insert(i, new Handle());
        }
        hashTable.delete(1);
        hashTable.resize(8);
        assertEquals(8, hashTable.getSize());
        assertNotNull(hashTable.search(0));
        assertNull(hashTable.search(1));
        for (int i = 0; i < 8; i++) {
            if (hashTable.search(i) != null) {
                assertTrue(hashTable.getArray()[i].getKey() != -1);
            }
        }
    }
    
    /**
     * Test the print method
     */
    @Test
    public void testPrint() {
        HashTable hashTable = new HashTable(4);
        for (int i = 0; i < 4; i++) {
            hashTable.insert(i, new Handle());
        }
        hashTable.delete(3);
        assertNotNull(hashTable.search(0));
        assertNotNull(hashTable.search(1));
        assertNotNull(hashTable.search(2));
        assertNull(hashTable.search(3));
        String output = hashTable.print();
        assertTrue(output.contains("0: 0"));
        assertTrue(output.contains("1: 1"));
        assertTrue(output.contains("2: 2"));
        assertTrue(output.contains("3: TOMBSTONE"));
        assertTrue(output.contains("total records: 3"));
    }
    
    /**
     * Test the h2 method with different keys
     */
    @Test
    public void testH2() {
        HashTable hashTable = new HashTable(4);
        int result1 = hashTable.h2(3);
        int result2 = hashTable.h2(4);
        int result3 = hashTable.h2(5);
        assertEquals(1, result1);
        assertEquals(3, result2);
        assertEquals(3, result3);
    }
    
    /**
     * Test the h2 method with different sizes
     */
    @Test
    public void testH2WithDifferentSizes() {
        HashTable hashTable = new HashTable(4);
        int result1 = hashTable.h2(3);  
        assertEquals(1, result1);
        hashTable = new HashTable(8);
        result1 = hashTable.h2(3);
        assertEquals(1, result1);
        hashTable = new HashTable(2);
        result1 = hashTable.h2(3);
        assertEquals(1, result1);
    }
}

