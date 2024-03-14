import java.io.*;

/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

// On my honor:
// - I have not used source code obtained from another current or
//   former student, or any other unauthorized source, either
//   modified or unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
//   anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assignedz
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.


public class SemManager {
    /**
     * @param args
     *     Command line parameters
     * @throws FileNotFoundException 
     */
    // public HashTable hashtable = new HashTable();

    public static void main(String[] args) throws FileNotFoundException {
        // This is the main file for the program.
        if (args == null || args.length < 3) {
            return;
        }
        String initialMemorySize = args[0]; // Initial memory size
        String initialHashSize = args[1]; // Initial hash size
        String commandFile = args[2]; // Input file name

        /**
         * Create new Seminar object, new HashTable object, 
         * new　MemManager object
         */
        Seminar dum = new Seminar();
        int hashSize = 0;
        int memorySize = 0;
        try {
            hashSize = Integer.parseInt(initialHashSize);
        }
        catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Can't convert "
                    + "string to integer: " + initialHashSize);
            return;
        }
        try {
            memorySize = Integer.parseInt(initialMemorySize);
        }
        catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Can't convert string "
                    + "to integer: " + initialMemorySize);
            return;
        }
        HashTable hashTable = new HashTable(hashSize);
        MemManager memManager = new MemManager(memorySize, false);
        

        /**
         * Read file
         */
        try {
            FileReader fr = new FileReader(commandFile);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can't find the file: " + commandFile);
            return;
        }

        /**
         * Create new Parser object and parse the content in the file
         */
        Parser parser = new Parser(commandFile);
        parser.parse(hashTable, memManager);
    }
}

