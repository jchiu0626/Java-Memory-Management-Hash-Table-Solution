/**
 * MemManager class that manages the memory
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */

public class MemManager {

    private int initialMemorySize; // Initial memory size
    private byte[] memoryPool; // memory pool of the memory manager
    private FreeBlockList freeBlockList; // free block list

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
     * @param resize            if this constructor is for resize
     */
    public MemManager(int initialMemorySize, boolean resize) {
        this.initialMemorySize = initialMemorySize;
        this.memoryPool = new byte[initialMemorySize];
        this.freeBlockList = new FreeBlockList(initialMemorySize, resize);
    }
    
    /**
     * Insert a record and return its position handle.
     * space contains the record to be inserted, of length size.
     *
     * @param   space   the byte array
     * @param   size    the size of the byte array
     * @return  the handle of the current byte array
     */
    public Handle insert(byte[] space, int size) {
        while (freeBlockList.maxSizeFreeBlock() < size) {
            resize(initialMemorySize * 2);
        }
        int blockSize = 2;
        int powIndex = 0;
        while (blockSize < size) {
            blockSize <<= 1;
            powIndex++;
        }
        int curIndex = powIndex;
        while (freeBlockList.getDoublyLinkedList()[curIndex].getSize() == 0) {
            curIndex++;
        }
        while (curIndex != powIndex) {
            int index1 = freeBlockList.getDoublyLinkedList()
                    [curIndex].getHeadValue();
            freeBlockList.getDoublyLinkedList()[curIndex].removeHead();
            curIndex--;
            int index2 = index1 + freeBlockList.getSizeArray()[curIndex];
            freeBlockList.getDoublyLinkedList()[curIndex].addAtTail(index1);
            freeBlockList.getDoublyLinkedList()[curIndex].addAtTail(index2);
        }
        int curNodeValue = freeBlockList.getDoublyLinkedList()
                [powIndex].getHeadValue();
        freeBlockList.getDoublyLinkedList()[powIndex].removeHead();
        for (int i = 0; i < size; i++) {
            memoryPool[curNodeValue + i] = space[i];
        }
        Handle handle = new Handle(curNodeValue, size);
        return handle;
    }
    
    /**
     * Return the length of the record associated with Handle handle
     *
     * @param   handle the handle
     * @return  the length of the record associated with the handle
     */
    public int getLength(Handle handle) {
        return handle.getLength();
    }
    
    /**
     * Free a block at the position specified by handle
     * Merge adjacent free blocks
     *
     * @param   handle  the handle
     */
    public void remove(Handle handle) {
        int position = handle.getPosition();
        int length = handle.getLength();
        int blockSize = 2;
        int powIndex = 0;
        while (blockSize < length) {
            blockSize <<= 1;
            powIndex++;
        }
        freeBlockList.getDoublyLinkedList()[powIndex].addInOrder(position);
        while (true) {
            int buddy = position ^ blockSize;
            if (freeBlockList.getDoublyLinkedList()
                    [powIndex].findValue(buddy)) {
                freeBlockList.getDoublyLinkedList()
                [powIndex].removeValue(buddy);
                freeBlockList.getDoublyLinkedList()
                [powIndex].removeValue(position);
                
                blockSize <<= 1;
                powIndex++;
                position = Math.min(position, buddy);
                freeBlockList.getDoublyLinkedList()
                [powIndex].addInOrder(position);
            }
            else {
                break;
            }
        }
    }
    
    
    /**
     * Resize the memory pool
     *
     * @param   newMemorySize  the size of the memory pool
     */
    public void resize(int newMemorySize) {
        System.out.println("Memory pool "
                + "expanded to " + newMemorySize +  " bytes");
        MemManager tmp = new MemManager(newMemorySize, true);
        for (int i = 0; i < initialMemorySize; i++) {
            tmp.memoryPool[i] = memoryPool[i];
        }
        freeBlockList.getDoublyLinkedList()[freeBlockList.getLength() - 1].
                 addAtHead(freeBlockList.getSizeArray()
                [freeBlockList.getLength() - 1]);
        for (int i = 0; i < freeBlockList.getLength(); i++) {
            tmp.freeBlockList.getDoublyLinkedList()[i] 
                    = freeBlockList.getDoublyLinkedList()[i];
        }
        this.initialMemorySize = newMemorySize;
        this.memoryPool = tmp.memoryPool;
        this.freeBlockList = tmp.freeBlockList;
    }
    
    /**
     * Print all indices of free blocks
     *
     */
    public void print() {
        int count = 0;
        for (int i = 0; i < freeBlockList.getLength(); i++) {
            if (freeBlockList.getDoublyLinkedList()[i].getSize() != 0) {
                System.out.print(freeBlockList.getSizeArray()[i] + ":");
                System.out.print(freeBlockList.getDoublyLinkedList()[i]
                        .print());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("There are no freeblocks in the memory pool");
        }
    }
    
    /**
     * Return the byte array
     *
     * @param   index   the starting index
     * @param   size    the size
     * @return the byte array
     */

    public byte[] getBytes(int index, int size) {
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = memoryPool[index + i];
        }
        return bytes;
    }
}
