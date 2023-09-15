/**
 * The class of the free block list
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class FreeBlockList {

    private int memorySize; // Memory pool size
    private int[] sizes; // Size array
    private int sizesLength; // Length of size array
    private DoublyLinkedList[] doublyLinkedLists; // Doubly linked 
    // lists for free block

    // ----------------------------------------------------------
    /**
     * Dummy FreeBlockList constructor
     */
    public FreeBlockList() {
        // Nothing here
    }
    
    // ----------------------------------------------------------
    /**
     * Dummy FreeBlockList constructor
     * 
     * @param memoryPoolSize    the memory pool size
     * @param resize            if this constructor is for resize
     */
    public FreeBlockList(int memoryPoolSize, boolean resize) {
        sizesLength = (int) (Math.log(memoryPoolSize) / Math.log(2));
        sizes = new int[sizesLength];
        for (int i = 0; i < sizesLength; i++) {
            sizes[i] = (int) Math.pow(2, i + 1); 
        }
        doublyLinkedLists = new DoublyLinkedList[sizesLength];
        for (int i = 0; i < sizesLength; i++) {
            doublyLinkedLists[i] = new DoublyLinkedList();
        }
        if (!resize) {
            doublyLinkedLists[sizesLength - 1].addAtHead(0);
        }
    }
    
    /**
     * Return the size array of the hash table
     *
     * @return the size array of the hash table
     */
    public int[] getSizeArray() {
        return this.sizes;
    }
    
    /**
     * Return the free block list
     *
     * @return the free block list
     */
    public DoublyLinkedList[] getDoublyLinkedList() {
        return this.doublyLinkedLists;
    }
    
    /**
     * Return the maximum size of blocks
     *
     * @return the maximum size of blocks
     */
    public int maxSizeFreeBlock() {
        int i = sizes.length - 1;
        for (; i >= 0; i--) {
            if (doublyLinkedLists[i].getSize() != 0) {
                return sizes[i];
            }
        }
        return -1;
    }
    
    /**
     * Return the length of the size array and
     * doubly linked lists
     *
     * @return the length of the size array
     */
    public int getLength() {
        return sizesLength;
    }
}
