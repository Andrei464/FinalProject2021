/** required package class namespace */
package ia;

/** required imports */
import java.io.Serializable;
import collections.LinkedList;
import javax.swing.ImageIcon;

/**
* Data.java - This class holds the information associated with an image,
* it stores its address/directory, the tags the user has entered in association
* with the address, and the image saved to the address.
*
* @author Andrei S. 
* @since Mar. 8, 2021 
*/
public class Data implements Serializable {

    /** The directory or address, the image object is saved to*/
    public String address;
;    /** The tags associated with the data, and that describe the data*/
    public LinkedList<String> tags;
    /** The image associated with the data and is saved to the address*/
    public ImageIcon image;
    
    /**
     * The default constructor of the class
     */
    public Data(){
    
    }
    
    /**
     * Checks if the data class has any initialized variables
     * 
     * @return true if no variables are initialized, false if else
     */
    private boolean isEmpty(){
        if(tags.isEmpty() && (address.equals("")
            || address == null)) return true;
        else return false;
    }
    
    /**
     * Converts the object into a string variable to be displayed in the list
     * and visualized for the user
     * @return the data of the class put into a string format, first all the 
     * tags and then the directory of the image
     */
    @Override
    public String toString(){
        String data = "";
        if(!tags.isEmpty()){
            for (int i = 0; i < tags.size() - 1; i++){
                data += tags.get(i) + ", ";
            }
            if(address == null || address.equals("")) data += tags.back() ;
            else data += tags.back() + ", Directory: " + address;
        }
        else{
            if(address == null || address.equals("")) data = " ";
            else data = "Directory: " + address;
        }
        return data;
    }
}
