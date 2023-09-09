/**
 * Record class that combines the integer key and the Handle handle together
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class Record {

    private int key; // key in the record
    private Handle handle; // handle in the record

    // ----------------------------------------------------------
    /**
     * Dummy Record constructor
     */
    public Record() {
        // Nothing here
    }

    /**
     * Create a new Record object from the field data
     *
     * @param key    input key
     * @param handle input handle
     */
    public Record(int key, Handle handle) {
        this.key = key;
        this.handle = handle;
    }

    // ----------------------------------------------------------
    /**
     * Return the integer key of current Record
     *
     * @return the integer key
     */

    public int getKey() {
        return this.key;
    }

    /**
     * Return the Handle handle of current Record
     *
     * @return the Handle handle
     */

    public Handle getHandle() {
        return this.handle;
    }
}
