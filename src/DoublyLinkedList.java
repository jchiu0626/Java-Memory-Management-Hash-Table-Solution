/**
 * The class of doubly linked list
 *
 * @author Yung-Lin Chiu
 * @version September 2023
 */
public class DoublyLinkedList {
    /**
     * dummy.next is the head
     * dummy.prev is the tail
     */
    private Node dummy; // the dummy node
    private int size; // the size of the doubly linked list

    // Node class
    private class Node {
        private int value;
        private Node next;
        private Node prev;

        Node(int value) {
            this.value = value;
        }
        
        /**
         * Return the value of the node
         *
         * @return the value of the node
         */
        public int getValue() {
            return value;
        }
    }

    // ----------------------------------------------------------
    /**
     * Dummy DoublyLinkedList constructor
     */
    public DoublyLinkedList() {
        dummy = new Node(-1);
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    /**
     * Add a node at head with value
     *
     * @param   value   the value of the new node
     */
    public void addAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = dummy.next;
        newNode.prev = dummy;
        dummy.next.prev = newNode;
        dummy.next = newNode;
        size++;
    }

    /**
     * Add a node at tail with value
     *
     * @param   value   the value of the new node
     */
    public void addAtTail(int value) {
        Node newNode = new Node(value);
        newNode.prev = dummy.prev;
        newNode.next = dummy;
        dummy.prev.next = newNode;
        dummy.prev = newNode;
        size++;
    }

    /**
     * Delete the node at head
     *
     */
    public void removeHead() {
        if (size == 0) return;
        dummy.next = dummy.next.next;
        dummy.next.prev = dummy;
        size--;
    }

    /**
     * Delete the node at tail
     *
     */
    public void removeTail() {
        if (size == 0) return;
        dummy.prev = dummy.prev.prev;
        dummy.prev.next = dummy;
        size--;
    }

    /**
     * Add a node at a specific position with value
     * so that the doubly linked list is in ascending order 
     *
     * @param   value   the value of the new node
     */
    public void addInOrder(int value) {
        Node newNode = new Node(value);
        Node cur = dummy.next;
        while (cur != dummy && cur.value < value) {
            cur = cur.next;
        }
        newNode.next = cur;
        newNode.prev = cur.prev;
        cur.prev.next = newNode;
        cur.prev = newNode;
        size++;
    }
    
    /**
     * Remove a node with a certain value
     *
     * @param   value   the value of the removed node
     */
    public void removeValue(int value) {
        Node cur = dummy.next;
        while (cur != dummy) {
            if (cur.value == value) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                size--;
                return;
            }
            cur = cur.next;
        }
    }
    
    /**
     * Check if value in the doubly linked list or not
     *
     * @param   value   the value to be found
     * @return  return true if the value exists in the list
     */
    public boolean findValue(int value) {
        Node cur = dummy.next;
        while (cur != dummy) {
            if (cur.value == value) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * Return the size of the doubly linked list
     *
     * @return the size of the doubly linked list
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Return the value of the head of the doubly linked list
     *
     * @return the value of the head of the doubly linked list
     */
    public int getHeadValue() {
        return dummy.next.value;
    }
    
    /**
     * rint out all values in the current doubly linked list
     *
     * @return  the output including the content of the current
     * doubly linked list
     */

    public String print() {
        String output = "";
        Node cur = dummy.next;
        while (cur != dummy) {
            output += " " + cur.value;
            cur = cur.next;
        }
        output += "\n";
        return output;
    }
}
