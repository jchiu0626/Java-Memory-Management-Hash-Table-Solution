import java.util.Scanner;

import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the Parser class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/
public class ParserTest extends TestCase {
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
        Parser parser = new Parser();
        assertNotNull(parser);
    }
    
    /**
     * Test constructor with parameter
     */
    @Test
    public void testConstructorWithParameter() {
        Parser parser = new Parser("emptyTest.txt");
        assertNotNull(parser);
    }
    
    /**
     * Test parser
     */
    @Test
    public void testParse() {
        Parser parser = new Parser("test.txt");
        HashTable hashTable = new HashTable(4);
        MemManager memManager = new MemManager(4, false);
        parser.parse(hashTable, memManager);
        assertNotNull(hashTable.search(1));
        assertNull(hashTable.search(999));
    }
    
    /**
     * Test inserting an existing ID
     */
    @Test
    public void testInsertExistingID() {
        Parser parser = new Parser("test.txt");
        HashTable hashTable = new HashTable(4);
        MemManager memManager = new MemManager(4, false);
        parser.parse(hashTable, memManager);
        String output = systemOut().getHistory();
        assertTrue(output.contains("Insert FAILED"
                + " - There is already a record with ID 3"));
    }
    
    /**
     * Test searching an non-existing ID
     */
    @Test
    public void testSearchNonExistingID() {
        Parser parser = new Parser("test.txt");
        HashTable hashTable = new HashTable(4);
        MemManager memManager = new MemManager(4, false);
        parser.parse(hashTable, memManager);
        String output = systemOut().getHistory();
        assertTrue(output.contains("Search FAILED"
                + " -- There is no record with ID 5"));
    }
    
    /**
     * Test deleting an non-existing ID
     */
    @Test
    public void testDeleteNonExistingID() {
        Parser parser = new Parser("test.txt");
        HashTable hashTable = new HashTable(4);
        MemManager memManager = new MemManager(4, false);
        parser.parse(hashTable, memManager);
        String output = systemOut().getHistory();
        assertTrue(output.contains("Delete FAILED"
                + " -- There is no record with ID 2"));
    }

    /**
     * Test print in both cases
     */
    @Test
    public void testPrint() {
        Parser parser = new Parser("test.txt");
        HashTable hashTable = new HashTable(4);
        MemManager memManager = new MemManager(4, false);
        parser.parse(hashTable, memManager);
        String output = systemOut().getHistory();
        assertTrue(output.contains("Hashtable: "));
        assertTrue(output.contains("Freeblock List:"));
    }

}

