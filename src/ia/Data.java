package ia;

import java.io.Serializable;
import collections.LinkedList;
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
    
    public boolean isEmpty(){
        if(tags.isEmpty() && (adress.equals("") || adress == null)) return true;
        else return false;
    }
    
    @Override
    public String toString(){
        String data = "";
        if(!tags.isEmpty()){
            for (int i = 0; i < tags.size() - 1; i++) {
                data += tags.get(i) + ", ";
            }
            if(adress == null || adress.equals("")){
                data += tags.back() ;
            }
            else{
                data += tags.back() + ", Directory: " + adress;
            }
        }
        else{
            if(adress == null) data = " ";
            else data = "Directory: " + adress;
        }
        return data;
    }
}
