/** required package class namespace */
package collections;

/** required imports */
import java.io.Serializable;

 
/**
 * @author Andrei S. 
 * @param <T> the generic data type used in the class
 * @since Mar. 1, 2021 
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
     * Default constructor, sets class properties
     */
    public Node(T data) {
        
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
