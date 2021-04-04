/** required package class namespace */
package ia;

/** required imports */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
* Data.java - 
*
* @author Andrei S. 
* @since Mar. 8, 2021 
*/
public class FileHandler <T> {
    
    /**
     * The default constructor of the class
     */
    public FileHandler(){
    
    }
    
    /**
     * Opens the passed filename and reads the generic object from it
     * 
     * @param filename the filename to open
     * @return the generic data type in the file
     */
    public T openObject(String filename) {
        try {                                               // Start try block
            FileInputStream   stream = new FileInputStream(filename);
            ObjectInputStream input  = new ObjectInputStream(stream);
            T object = (T)input.readObject();       // Read object and cast
            input.close();                          // Close file connection
            return object;                          // Return object read
        }
        catch (ClassCastException error) {        // Casting class error caught
            return null;                                // Return unsuccessful
        }
        catch (ClassNotFoundException error) {    // No class type error caught
            return null;                                // Return unsuccessful
        }
        catch(NullPointerException error) {             // Null error caught 
            return null;                                // Return unsuccessful
        }
        catch (IOException error) {                 // Input/output error caught
            return null;                                // Return unsuccessful
        }
    }
    
    /**
     * Saves the generic object to the passed file
     * 
     * @param data the generic object to save
     * @param file the file object to save to
     * @return the operation was successful (true) or not (false)
     */
    public boolean saveObject(T data, File file) {
        try {                                               // Start try block
            return saveObject(data, file.getAbsolutePath());  // Call with path
        }
        catch(NullPointerException error) {                 // Null error caught
            return false;                                   // Return no success
        }
    }
    
    /**
     * Opens the passed file object and reads the generic object from it
     * 
     * @param file the file object to open
     * @return the generic data type in the file
     */
    public T openObject(File file) {
        try {                                               // Start try block
            return openObject(file.getAbsolutePath());      // Call with path
        }
        catch(NullPointerException error) {                 // Null error caught
            return null;                                    // Return no success
        }
    }    
    
    /**
     * Saves the generic object to the passed filename
     * 
     * @param data the generic object to save
     * @param filename the filename to save it to
     * @return the operation was successful (true) or not (false)
     */
    public boolean saveObject(T data, String filename) {
        try {                                               // Start try block
            FileOutputStream   stream = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(stream);
            output.writeObject(data);                   // Write object to file
            output.close();                             // Close file connection
            return true;                                // Operation successful
        }
        catch(NullPointerException error) {             // Null error caught
            return false;                               // Return unsuccessful
        }
        catch (IOException error) {                 // Input/output error caught
            return false;                               // Return unsuccessful
        }
    }
    
    /**
     * Saves the passed text to the passed file name
     * 
     * @param text the text to save
     * @param fileName the name of the file to save to
     * @return the operation was successful (true) or not (false)
     */
    public boolean save(String text, String fileName) {
        try {                                               // Start try block
            FileWriter stream = new FileWriter(fileName);   // Connect to name
            PrintWriter file  = new PrintWriter(stream);    // Connect to writer
            file.print(text);                               // Write text 
            file.close();                                   // Close connection
            return true;                                    // Return successful
        }
        catch (NullPointerException error) {                // Null error caught
            return false;                                   // Return no success
        }
        catch (IOException error) {                 // Input/output error caught
            return false;                                   // Return no success
        }
    }
    
    /**
     * Opens the passed file name and returns the contents as a single string
     * 
     * @param fileName the name of the file to open
     * @return a string of the contents of the file (or null)
     */
    public String open(String fileName) {
        try {                                               // Start try block            
            FileReader stream   = new FileReader(fileName);   // Connect to name
            BufferedReader file = new BufferedReader(stream); // Connect reader
            String line = file.readLine();                  // Read in line
            file.close();                                   // Close connection
            return line;                                    // Return line
        }
        catch (NullPointerException error) {                // Null error caught
            return null;                                    // Return no text
        }
        catch (IOException error) {                 // Input/output error caught
            return null;                                    // Return no text
        }
    }
}
