/**
 * HashTable class with Insert/Delete/Search/Resize/h1 and h2 hashing methods
 * support.
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class HashTable {

    private int size; // hash table size
    private Record[] array; // the array to store records
    private int count; // the number of records in the hash map

    // ----------------------------------------------------------
    /**
     * Dummy hash table constructor
     */
    public HashTable() {
        // Nothing here
    };

    /**
     * Create a new HashTable object from the field data
     *
     * @param length input length
     */
    public HashTable(int length) {
        size = length;
        this.array = new Record[size];
        this.count = 0;
    }

    /**
     * Insert a key and its corresponding handle into the hash table
     *
     * @param key    the seminar id
     * @param handle the handle corresponding to the current seminar id
     */
    public void insert(int key, Handle handle) {
        count++;
        if (count > size / 2) {
            resize(2 * size);
        }
        int i;
        for (i = h1(key); (array[i] != null && 
                array[i].getKey() != -1) ; i = (i + h2(key)) % size) {
        }
        array[i] = new Record(key, handle);
    }

    /**
     * Delete the record with the given key from the hash table
     *
     * @param key the seminar id
     */

    public void delete(int key) {
        if (search(key) == null) {
            return;
        }
        int i = h1(key);
        while (array[i] != null && array[i].getKey() != key) {
            i = (i + h2(key)) % size;
        }
        if (array[i] != null) {
            array[i] = new Record(-1, new Handle());
            count--;
        }
    }

    /**
     * Search in the hash table with an integer key
     *
     * @param   key the seminar id
     * @return  the handle corresponding to the given key
     */

    public Handle search(int key) {
        for (int i = h1(key); array[i] != null; i = (i + h2(key)) % size) {
            if (array[i].getKey() == key) {
                return array[i].getHandle();
            }
        }
        return null;
    }

    /**
     * Resize the hash table to the specified size
     *
     * @param newSize the new size of the hash table
     */

    public void resize(int newSize) {
        HashTable temp = new HashTable(newSize);
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].getKey() != -1) {
                temp.insert(array[i].getKey(), array[i].getHandle());
            }
        }
        this.array = temp.array;
        this.size = temp.size;
        System.out.println("Hash table expanded to " + newSize + " records");
    }
    
    /**
     * Print the index with keys in the hash table
     *
     */

    public void print() {
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                if (array[i].getKey() == -1) {
                    System.out.println(i + ": TOMBSTONE");
                }
                else {
                    System.out.println(i + ": " + array[i].getKey());
                }
            }
        }
        System.out.println("total records: " + this.count);
    }

    /**
     * Hash the integer id using the first hash function
     *
     * @param k the seminar id
     * @return the hash value of the id using the first hash function
     */

    int h1(int k) {
        try {
            return k % size;
        }
        catch (java.lang.ArithmeticException e) {
            e.printStackTrace();
            System.out.println("can't mod by " + size);
            return -1;
        }
    }

    /**
     * Hash the integer id using the second hash function to resolve collision
     *
     * @param k the seminar id
     * @return the hash value of the id using the second hash function
     */

    int h2(int k) {
        try {
            return (((k / size) % (size / 2)) * 2) + 1;
        }
        catch (java.lang.ArithmeticException e) {
            e.printStackTrace();
            System.out.println("can't mod or divide by " + size);
            return -1;
        }
    }
    
    /**
     * Return the size of the hash table
     *
     * @return the size of the hash table
     */

    public int getSize() {
        return this.size;
    }
}
