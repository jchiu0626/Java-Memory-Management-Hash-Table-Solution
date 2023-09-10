/**
 * MemManager class that manages the memory
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class MemManager {

    private int initialMemorySize; // Initial memory size
    private byte[] memoryPool; // Memory pool of the memory manager

    // ----------------------------------------------------------
    /**
     * Dummy MemManager constructor
     */
    public MemManager() {
        // Nothing here
    }

    /**
     * Create a new MemManager object from the field data
     *
     * @param initialMemorySize input initialMemorySize
     */
    public MemManager(int initialMemorySize) {
        this.initialMemorySize = initialMemorySize;
        this.memoryPool = new byte[initialMemorySize];
    }
    
    /**
     * Return the length of the record associated with Handle handle
     *
     * @param   handle the handle
     * @return  the length of the record associated with the handle
     */

    public int getLength(Handle handle) {
        return 0;
    }
    
    /**
     * Free a block at the position specified by handle
     * Merge adjacent free blocks
     *
     * @param   handle  the handle
     */

    public void remove(Handle handle) {
        
    }
    
    /**
     * Return the record with handle posHandle, up to size bytes, by
     * copying it into space
     *
     * @param   space   the byte array
     * @param   handle  the handle
     * @param   size    the size of the byte array
     * @return  the handle corresponding to the given key
     */

    public int get(byte[] space, Handle handle, int size) {
        return 0;
    }
}
