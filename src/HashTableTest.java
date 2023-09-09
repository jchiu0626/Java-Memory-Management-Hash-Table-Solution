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
     * Get code coverage of the class declaration.
     */
    @Test
    public void testInsert() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        assertEquals(handle, hashTable.search(1));
    }
    
    /**
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testDeleteNull() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.delete(1);
        assertNotSame(handle, hashTable.search(1));
    }
    
    /**
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testDelete() {
        HashTable hashTable = new HashTable(4);
        Handle handle = new Handle();
        hashTable.insert(1, handle);
        hashTable.delete(1);
        assertNotSame(handle, hashTable.search(1));
    }
    
    /**
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testDeleteOnEmptyHashTable() {
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
     * Get code coverage of the class declaration.
     */ 
    @Test
    public void testResize() {
        HashTable hashTable = new HashTable(4);
        for (int i = 0; i < 7; i++) {
            Handle handle = new Handle();
            hashTable.insert(i, handle);
        }
        assertEquals(hashTable.getSize(), 16);
    }
}

