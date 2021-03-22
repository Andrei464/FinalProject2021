package ia;

import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
* Data.java - 
*
* @author Andrei S. 
* @since Mar. 8, 2021 
*/
public class Data implements Serializable{

    String name;
    String url;
    LinkedList<String> tags;
    ImageIcon image;
    
    public Data(String name, String url, ImageIcon image){
        this.name = name;
        this.url = url;
        this.image = image;
    }
    
    public Data(String name, String url){
        this(name,url,null);
    }
    
    public Data(String name){
        this(name,null,null);
    }
    
    public String toString(){
        String data = "|" + name + "|" + url + "|";
        for (String tag : tags) {
            data += tag + "|";
        }
        return data;
    }
}
