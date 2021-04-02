
/** required package class namespace */
package collections;

import java.io.Serializable;

 
/**
 * Node.java - a representation of a generic node for use with
 * a LinkedList object, and could be "visualized" as:
 * 
 * <pre>
 * 
 *                      NODE
 *                +--------------+
 * previous {---- |              |
 *                |     data     |
 *                |   (generic)  |
 *                |              |----} next
 *                +--------------+
 *
 * </pre>
 * 
 * @author Mr. Wachs 
 * @param <T> the generic data type used in the class
 * @since 6-May-2019 
 */
public class Node <T> implements Serializable
{
    
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
        this.data     = data;
        this.next     = next;
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
