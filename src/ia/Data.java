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

    public String adress;
    public LinkedList<String> tags;
    public ImageIcon image;
    
    public Data(String url, ImageIcon image){
        this.adress = url;
        this.image = image;
    }
    
    public Data(String url){
        this(url,null);
    }
    
    public Data(){
        this(null,null);
    }
    
    @Override
    public String toString(){
        String data = "|" + adress + "|";
        for (String tag : tags) data += tag + "|";
        return data;
    }
}
