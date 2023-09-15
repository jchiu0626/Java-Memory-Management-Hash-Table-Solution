import java.io.FileNotFoundException;

/**
 * Handle class
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class Handle {
    private int position;
    private int length;

    // ----------------------------------------------------------
    /**
     * Dummy handle constructor
     */
    public Handle() {
        // Nothing here
    }
    
    /**
     * Create a new Handle object from the field data
     *
     * @param position  the starting position
     * @param length    the byte array length
     */
    public Handle(int position, int length) {
        this.position = position;
        this.length = length;
    }
    
    // ----------------------------------------------------------

    /**
     * Return the position of the byte array that the handle stores
     *
     * @return the position of the byte array that the handle stores
     */

    public int getPosition() {
        return this.position;
    }

    // ----------------------------------------------------------

    /**
     * Return the length of the byte array that the handle stores
     *
     * @return the length of the byte array that the handle stores
     */

    public int getLength() {
        return this.length;
    }
}
