/** required package class namespace */
package ia;

/**
* Database.java - This class initializes the UI object to being the program.
*
* @author Andrei S. 
* @since Mar. 1, 2021 
*/
public class Database {
    
    /** The name of the program*/
    static private final String NAME = "Photo Database";
    
    /**
     * only used to create an instance of the UI object
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UI ui = new UI(NAME);
    }
}
