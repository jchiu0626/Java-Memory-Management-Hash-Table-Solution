/**
 * MemManager class that manages the memory
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class MemManager {

    private int initialMemorySize; // Initial memory size

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
    }
}
