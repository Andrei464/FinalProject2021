/** required package class namespace */
package collections;

/** required imports */
import java.io.Serializable;

 
/**
 * Node.java - The node is a piece of whatever "data" the list will be storing. 
 * It has a section of memory for that data as well as a "link" to the next 
 * node that will be in the list.
 * 
 * @author Andrei S. 
 * @param <T> the generic data type used in the class
 * @since Mar. 1, 2021 
 */
public class Node <T> implements Serializable {
    
    /** the generic data to store */
    public T data;    
    /** Self reference (link) to the next node in the "chain" */
    public Node next;
    /** Self reference (pointer) to the previous node in the "list" */
    public Node previous;
    
    /**
     * Class constructor sets class properties 
     * 
     * @param data the node data
     * @param next reference to the next Node object
     * @param previous reference to the previous Node object
     */
    public Node(T data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
       
    /**
     * Class constructor sets class properties 
     * 
     * @param data the node data
     * @param next reference to the next Node object
     */
    public Node(T data, Node next) {
        this(data,next,null);
    }
        
    /**
     * Class constructor sets class properties 
     * 
     * @param data the node data
     */
    public Node(T data) {
        this(data,null,null);
    }
    
    /** 
     * Default constructor, sets class properties
     */
    public Node() {
        this(null,null,null);
    }
    
    /**
     * Frees up all memory used by this object
     */
    @Override
    public void finalize() {
        data = null;
        next = previous = null;
        System.gc();                // runs the garbage collector in Java
    }

}
