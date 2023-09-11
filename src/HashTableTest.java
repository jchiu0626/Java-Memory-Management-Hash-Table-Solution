import org.junit.Test;

import student.TestCase;

/**
 * @author Yung-Lin Chiu
 * @version September 2023
 */
public class HashTableTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */    
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    @Test
    public void testConstructorWithoutParameter() {
        HashTable hashTable = new HashTable();
        assertNotNull(hashTable);
    }
    
    /**
     * Get code coverage of the class declaration.
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
        assertTrue(hashTable.getArray()[1] == null);
        hashTable.insert(1, handle);
        assertTrue(hashTable.getArray()[1] != null);
        assertTrue(hashTable.getArray()[1].getKey() != -1);
    }
    
    /**
     * Test insert in a tombstone slot
     */
    @Test
    public void testInsertInTombstoneSlot() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        assertTrue(hashTable.getArray()[1] == null);
        hashTable.insert(1, handle);
        hashTable.delete(1);
        assertTrue(hashTable.getArray()[1] != null);
        assertFalse(hashTable.getArray()[1].getKey() != -1);
        hashTable.insert(1, handle);
        assertTrue(hashTable.getArray()[1] != null);
        assertTrue(hashTable.getArray()[1].getKey() != -1);
        assertTrue(hashTable.getArray()[1].getHandle() != null);
    }
    
    /**
     * Test insert in a non-empty slot
     */
    @Test
    public void testInsert() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        assertTrue(hashTable.getArray()[1] != null);
        hashTable.insert(5, handle);
        assertTrue(hashTable.getArray()[1] != null);
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
        Handle handle = new Handle();
        assertTrue(hashTable.search(1) == null);
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
        assertFalse(hashTable.search(1) == null);
        assertFalse(hashTable.search(5) == null);
        assertTrue(hashTable.getArray()[hashTable.h1(5)].getKey() != 5);
        assertFalse(hashTable.getArray()[(hashTable.h1(5) + 
                hashTable.h2(5)) % hashTable.getSize()].getKey() != 5);
        assertEquals(hashTable.getCount(), 2);
        hashTable.delete(5);
        assertTrue(hashTable.search(5) == null);
        assertTrue(hashTable.getArray()[(hashTable.h1(5) + 
                hashTable.h2(5)) % hashTable.getSize()].getKey() != 5);
        assertEquals(hashTable.getCount(), 1);
        hashTable.delete(1);
        assertEquals(hashTable.getCount(), 0);
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
            if (hashTable.search(i) != null){
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
        hashTable.print();
    }
}

