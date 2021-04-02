/** required package class namespace */
package collections;

/** required imports */
import java.io.Serializable;


/**
 * @author Andrei S. 
 * @param <T> the generic data type used in the class
 * @since Mar. 1, 2021 
 */
public class LinkedList <T> implements Serializable
{
    
    /** Flag to indicate a search was not found */
    public final int NOT_FOUND = -1;
    
    /** Reference (link) to the first (front) node in the list (entry point) */
    private Node head;
    
    /** Reference (link) to the last (back) node in the list (entry point) */
    private Node tail;
    
    /** The number of nodes in the list, immutable property */
    private int length;
    
    /** the longest "word" size of the largest node data */
    public int longestWord;
    
    /**
     * Default constructor, set class properties
     */
    public LinkedList() {
        finalize();
    }
        
    /**
     * Determines if the list is empty (no content)
     * 
     * @return is empty (true) or not empty (false)
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * Accessor method of the immutable property
     * 
     * @return the number of nodes in the list
     */
    public int size() {
        return length;
    }
        
    /**
     * Inserts data into the front (head) of the list
     * 
     * @param data the data type to add
     * @return the operation was successful (true) or not (false)
     */
    public boolean addFront(T data) {
        if (data == null) return false;     // null data cannot be added        
        Node<T> node = new Node<>(data);    // new node memory created 
        checkForLongest(node);
        // Scenarios to consider:    
        // 1) empty list
        // 2) list of 1 or more nodes
        if (isEmpty()) {                    // adding first node
            head = tail = node;             // set references
        }
        else {                              // subsequent nodes added
            node.next = head;               // link node to rest of list
            head.previous = node;           // connect rest of list to node
            head = node;                    // reassign head reference
        }
        length++;                           // increase length environmental
        return true;                        // operation successful
    }
    
    /**
     * Inserts data into the back (tail) of the list
     * 
     * @param data the data type to add
     * @return the operation was successful (true) or not (false)
     */
    public boolean addBack(T data) {
        if (data == null) return false;     // null data cannot be added            
        Node<T> node = new Node<>(data);    // new node memory created    
        checkForLongest(node);
        if (isEmpty()) {                    // adding first node
            head = tail = node;             // set references
        }
        else {                              // subsequent nodes added
            node.previous = tail;           // link node to rest of list
            tail.next = node;               // connect rest of list to node
            tail = node;                    // reassign tail reference
        }
        length++;                           // increase length environmental
        return true;                        // operation successful
    } 
    
    /**
     * Accessor for the data at the specified index
     * 
     * @param index the index location to access
     * @return the data (or null) at the index
     */
    public T get(int index) {        
        if (!inRange(index)) return null;   // invalid index, return flag        
        return (T)getNode(index).data;      // get reference and retrieve data  
    }
    
    /**
     * Accesses the last, tail, back data in the list
     * 
     * @return the tail data
     */
    public T back() {
        return get(length-1);                       // last node
    }
    
    /**
     * Removes (deletes) the first (head) node of the list
     * 
     * @return the data in the first node (or null)
     */
    public T removeFront() {
        if (isEmpty()) return null;             // no front to remove
        T data = (T)head.data;                  // store head data
        if (length == 1) finalize();            // 1 node list, wipe list
        else {                
            checkIfLongest(head);
            head = head.next;                   // advanced head reference
            head.previous.next = null;          // cut old head reference
            head.previous = null;               // cut reference to old head
            length--;                           // reduce list length
            System.gc();                        // call system garbage collector
        }
        return data;                            // return stored data
    }
    
    /**
     * Removes (deletes) the last (tail) node of the list
     * 
     * @return the data in the last node (or null)
     */
    public T removeBack() {
        if (isEmpty()) return null;             // no back to remove
        T data = (T)tail.data;                  // store tail data
        if (length == 1) finalize();            // 1 node list, wipe list
        else {   
            checkIfLongest(tail);
            tail = tail.previous;               // advanced tail reference
            tail.next.previous = null;          // cut old tail reference
            tail.next = null;                   // cut reference to old tail
            length--;                           // reduce list length
            System.gc();                        // call system garbage collector
        }
        return data;                            // return stored data
    }
    
    /**
     * Adds the data to the back of the list (wrapper method)
     * 
     * @param data the data to add
     * @return the operation was successful (true) or not (false)
     */
    public boolean add(T data) {
        return addBack(data);                           // wrapper method call
    }
    
    /**
     * Deletes the node at the specified index and mutates the list
     * 
     * @param index the index location to remove
     * @return the data at the specified index (or null)
     */
    public T remove(int index) {
        if (!inRange(index))   return null;             // not in range
        if (index == 0)        return removeFront();    // remove first
        if (index == length-1) return removeBack();     // remove last
        Node current = getNode(index);                  // get to index
        checkIfLongest(current);
        current.next.previous = current.previous;       // change references
        current.previous.next = current.next;
        current.next = current.previous = null;        
        length--;                                       // reduce list length
        return (T)current.data;                         // return index data
    }
    
    /**
     * Finds the node matching the data at the first occurrence in the list
     * and returns it's index or -1 (NOT_FOUND) if not in the list
     * 
     * @param data the node data to search for
     * @return index of first occurrence or -1 (NOT_FOUND)
     */
    public int firstIndexOf(T data) {
        if (data == null) return NOT_FOUND;     // null data rejected
        Node current = head;                    // start at head
        int index = 0;                          // start count at 0
        while (current != null) {               // traverse list
            if (current.data.equals(data)) {    // found first occurrence
                return index;                   // return location
            }
            current = current.next;             // advance to next node
            index++;                            // advance count
        }
        return NOT_FOUND;                       // data not found
    }
    
    /**
     * Deletes the first occurrence of the data in the list
     * 
     * @param data the node data to remove
     * @return the operation was successful (true) or not (false) 
     */
    public boolean remove(T data) {
        if (data == null) return false;         // nothing to remove
        int index = firstIndexOf(data);         // get first location
        if (index == NOT_FOUND) return false;   // not in list
        remove(index);                          // remove
        return true;                            // operation successful
    }

    /**
     * Frees up all memory used by this object
     */
    @Override
    public void finalize() {
        length = 0;                 // length set to zero
        head = tail = null;         // references set to nulls
        System.gc();                // runs the garbage collector in Java
    }
//        
    /**
     * Accessor method to the encapsulated (private) property of the first
     * (head) node of the list
     * 
     * @return reference to the first node
     */
    protected Node getFirstNode() {
        return head;
    }
    
    /**
     * Accessor method to the encapsulated (private) property of the last
     * (tail) node of the list
     * 
     * @return reference to the last node
     */
    protected Node getLastNode() {
        return tail;
    }
    
    /**
     * Accesses the node reference for this index location
     * 
     * @param index the index location
     * @return a reference to the node at this index or null
     */
    protected Node getNode(int index) {
        if (!inRange(index))   return null;             // not valid index
        if (index == 0)        return getFirstNode();   // first node returned
        if (index == length-1) return getLastNode();    // last node returned
        Node current = head;                            // start at first node
        for (int i = 0; i < index; i++) {               // move to index
            current = current.next;                     // advance reference
        }
        return current;                                 // return reference
    }
//        
    /**
     * Checks to see if the index is in range of the list
     * 
     * @param index the location to check
     * @return it is in range (true) or not (false)
     */        
    private boolean inRange(int index) {
        if (isEmpty())       return false;  // empty list no valid index
        if (index < 0)       return false;  // index before first valid number
        if (index >= length) return false;  // index after last valid number
        return true;                        // index is valid
    }

    /**
     * Checks as new data is added if it has the longest "word" length, and 
     * if it does, it stores that length
     * 
     * @param node the Node to check the "word" length for
     */
    private void checkForLongest(Node<T> node) {
        int wordLength = node.toString().length();
        if (longestWord == 0 || wordLength > longestWord) 
            longestWord = wordLength;
    }
//    
    /**
     * Checks to see if when removing a node if it was the longest "word" 
     * length node, if it was it finds the new longest "word" length node data
     * 
     * @param node the Node to check the "word" length for
     */
    private void checkIfLongest(Node<T> node) {
        int wordLength = node.toString().length();
        if (wordLength == longestWord) {
            Node current = head;
            int newLongestWord = 0;
            while (current != null) {
                int currentLength = current.toString().length();
                if (currentLength != longestWord && 
                    currentLength > newLongestWord) {
                    newLongestWord = currentLength;
                }
                current = current.next;                
            }
            longestWord = newLongestWord;
        }
    }
        
}
