import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Parser class with Parse/Insert/Search/Delete/Print support. 
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class Parser {
    private Scanner scanner; // Scanner object to read the command file

    // ----------------------------------------------------------
    /**
     * Dummy Parser constructor
     */
    public Parser() {
        // Nothing here
    }

    /**
     * Create a new Parser object from the field data
     *
     * @param commandFile   input commandFile
     * @throws FileNotFoundException if the specified command file is not found
     */
    public Parser(String commandFile) {
        try {
            this.scanner = new Scanner(new File(commandFile));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    /**
     * Parse the command file and performance operations
     *
     * @param hashTable the hash table to perform operations based
     * @param memManager the memory manager
     * on the parsed commands
     */

    public void parse(HashTable hashTable, MemManager memManager) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().replaceAll("\\s+", " ");

            switch(line.split(" ")[0]) {
                case "insert":
                    insert(line, scanner, hashTable, memManager);
                    break;
                case "search":
                    search(line, hashTable, memManager);
                    break;
                case "delete":
                    delete(line, hashTable, memManager);
                    break;
                case "print":
                    print(line, hashTable, memManager);
                    break;
                default:
                    continue;
            }
        }
    }

    /**
     * Insert a seminar record into the hash table.
     *
     * @param line      the command line containing the insert command
     * @param s         the scanner to read the following lines
     * @param hashTable the hash table to insert the record
     * @param memManager the memory manager
     */

    public void insert(String line, Scanner s, HashTable hashTable, 
            MemManager memManager) {
        int id = Integer.parseInt(line.split(" ")[1]);
        if (hashTable.search(id) != null) {
            System.out.println("Insert FAILED - "
                    + "There is already a record with ID " + id);
            return;
        }
        String title = s.nextLine().trim();
        line = s.nextLine();
        String[] details = line.trim().replaceAll("\\s+", " ").split(" ");
        String date = details[0];
        int length = Integer.parseInt(details[1]);
        short x = Short.parseShort(details[2]);
        short y = Short.parseShort(details[3]);
        int cost = Short.parseShort(details[4]);
        String[] keywords = s.nextLine().trim().
                replaceAll("\\s+", " ").split(" ");
        String desc = s.nextLine().trim();
        Seminar seminar = new Seminar(id, title, date, 
                length, x, y, cost, keywords, desc);
        byte[] bytes = new byte[0];
        try {
            bytes = seminar.serialize();
        } 
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int bytesLength = bytes.length;
        Handle handle = memManager.insert(bytes, bytesLength);
        hashTable.insert(id, handle);
        System.out.println("Successfully inserted record with ID " + id);
        System.out.println(seminar.toString());
        System.out.println("Size: " + bytesLength);
    }

    /**
     * Search for a seminar record in the hash table
     *
     * @param line      the command line containing the search command
     * @param hashTable the hash table to search for the record
     * @param memManager the memory manager
     * @throws Exception 
     */

    public void search(String line, HashTable hashTable, 
            MemManager memManager) {
        int id = Integer.parseInt(line.split(" ")[1]);
        if (hashTable.search(id) == null) {
            System.out.println("Search FAILED -- "
                    + "There is no record with ID " + id);
            return;
        }
        Handle handle = hashTable.search(id);
        System.out.println("Found record with ID " + id + ":");
        byte[] bytes = memManager.getBytes(
                handle.getPosition(), handle.getLength());
        Seminar seminar = new Seminar();
        try {
            seminar = Seminar.deserialize(bytes);
        } 
        catch (Exception e) {
            for (int i = 0; i < bytes.length; i++) {
                System.out.print(bytes[i] + " ");
            }
            e.printStackTrace();
            return;
        }
        System.out.println(seminar.toString());
    }

    /**
     * Delete a seminar record in the hash table
     *
     * @param line      the command line containing the delete command
     * @param hashTable the hash table to delete the record
     * @param memManager the memory manager
     */

    public void delete(String line, HashTable hashTable, 
            MemManager memManager) {
        int id = Integer.parseInt(line.split(" ")[1]);
        Handle handle = hashTable.search(id);
        if (handle == null) {
            System.out.println("Delete FAILED -- "
                    + "There is no record with ID " + id);
            return;
        }
        hashTable.delete(id);
        memManager.remove(handle);
        System.out.println("Record with ID " + id + 
                " successfully deleted from the database");
    }

    /**
     * Print out either a complete listing of the contents of the hash
     * table, or list the free blocks in the memory pool
     *
     * @param line      the command line containing the print command
     * @param hashTable the hash table to be printed
     * @param memManager the memory manager
     */

    public void print(String line, HashTable hashTable, 
            MemManager memManager) {
        String value = line.split(" ")[1];
        if (value.equals("hashtable")) {
            System.out.println("Hashtable: ");
            System.out.println(hashTable.print());
        }
        else {
            System.out.println("Freeblock List:");
            memManager.print();
        }
    }
}
