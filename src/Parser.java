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
     * on the parsed commands
     */

    public void parse(HashTable hashTable) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().replaceAll("\\s+", " ");

            switch(line.split(" ")[0]) {
                case "insert":
                    insert(line, scanner, hashTable);
                    break;
                case "search":
                    search(line, hashTable);
                    break;
                case "delete":
                    delete(line, hashTable);
                    break;
                case "print":
                    print(line, hashTable);
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
     * @param hashtable the hash table to insert the record
     */

    public void insert(String line, Scanner s, HashTable hashtable) {
        int id = Integer.parseInt(line.split(" ")[1]);
        if (hashtable.search(id) != null) {
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
        try {
            byte[] bytes = seminar.serialize();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        // TODO insert the byte array into the memory manager and get the handle
        // TODO insert the key and handle into the hash table
        System.out.println("Successfully inserted record with ID " + id);
        System.out.println("ID: " + id + ", Title: " + title);
        System.out.println("Date: " + date + ", Length: " + length + 
                ", X: " + x + ", Y: " + y + ", Cost: " + cost);
        System.out.println("Description: " + desc);
        System.out.print("Keywords: ");
        for (int i = 0; i < keywords.length; i++) {
            System.out.print(keywords[i]);
            if (i != (keywords.length - 1)) {
                System.out.print(", ");
            }
            else {
                System.out.println();
            }
        }
        // TODO print the size
        System.out.println("Size: ");
        Handle handle = new Handle();
        hashtable.insert(id, handle);
    }

    /**
     * Search for a seminar record in the hash table
     *
     * @param line      the command line containing the search command
     * @param hashtable the hash table to search for the record
     */

    public void search(String line, HashTable hashtable) {
        int id = Integer.parseInt(line.split(" ")[1]);
        if (hashtable.search(id) == null) {
            System.out.println("Search FAILED -- "
                    + "There is no record with ID " + id);
            return;
        }
        Handle handle = hashtable.search(id);
        System.out.println("Found record with ID " + id);
        // TODO get the record and print the seminar data
    }

    /**
     * Delete a seminar record in the hash table
     *
     * @param line      the command line containing the delete command
     * @param hashtable the hash table to delete the record
     */

    public void delete(String line, HashTable hashtable) {
        int id = Integer.parseInt(line.split(" ")[1]);
        if (hashtable.search(id) == null) {
            System.out.println("Delete FAILED -- "
                    + "There is no record with ID " + id);
            return;
        }
        hashtable.delete(id);
        System.out.println("Record with ID " + id + 
                " successfully deleted from the database");
    }

    /**
     * Print out either a complete listing of the contents of the hash
     * table, or list the free blocks in the memory pool
     *
     * @param line      the command line containing the print command
     * @param hashTable the hash table to be printed
     */

    public void print(String line, HashTable hashTable) {
        String value = line.split(" ")[1];
        if (value.equals("hashtable")) {
            System.out.println("Hashtable: ");
            hashTable.print();
        }
        else {
            System.out.println("Freeblock List:");
            
        }
        // TODO Not implemented the free blocks yet.
    }
}
