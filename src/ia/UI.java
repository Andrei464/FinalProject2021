package ia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
//import collections.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
* UI.java - 
*
* @author Andrei S. 
* @since Mar. 8, 2021 
*/
class UI extends JFrame{

    //Constant Variables
    final private int               ZERO            = 0;
    final private int               ONE             = 1;
    final private int               TWO             = 2;
    final private int               FOUR            = 4;
    final private int               MARGIN          = 5;
    final private int               SPACER          = 10;
    final private int               B_HEIGHT        = 40;    
    final private int               B_WIDTH         = 145;
    
    final private String            DEF_FILE        = "SavingTest.txt";
    final private Color             BLACK           = Color.BLACK;
    
    //Non-Constant Variables
    private String                  activeFile      = DEF_FILE;
    
    //Objects 
    private LinkedList<Data>        linkedList      = new LinkedList<>();
    private LinkedList<Data>        searchedList    = new LinkedList<>();
    private FileHandler             filehandler     = new FileHandler();
    
    //UI Elements
    private JTextArea               textbox         = new JTextArea();
    private JLabel                  imagePanel      = new JLabel();
    private JButton                 addIndex        = new JButton();
    private JButton                 save            = new JButton();
    private JButton                 load            = new JButton();
    private JButton                 deleteIndex     = new JButton();
    private JButton                 deleteTag       = new JButton();
    private JButton                 enter           = new JButton();
    private JButton                 search          = new JButton();
    private Font                    font            = new Font("", 20, 20);
    private List                    list            = new List();
    private Icon                    icon;
    
    public UI(){
        initUIElements();
        createList();
    }

    private void createList() {
        try{
            File file = new File(activeFile);
            if(!file.exists() || file.length() == 0){
                file.createNewFile();
            }
            else{
                try{
                    linkedList = 
                    (LinkedList<Data>)filehandler.openObject(file);
                }
                catch(ClassCastException e){
                    file.delete();
                    return;
                }
                if(linkedList == null){
                    linkedList = new LinkedList<>();
                }
                updateUIList();
            }
        }
        catch(IOException e){}
    }

    private void updateUIList() {
        list.removeAll();
        for (int i = 0; i < linkedList.size(); i++) {
            Data data = linkedList.get(i);
            list.add(data.toString(), i);
        }  
        for (int i = 0; i < searchedList.size(); i++) {
            Data data = searchedList.get(i);
            list.add(data.toString(), i);
        }
    }
    
    private void initUIElements(){
        //Instantiate the JFrame
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Photo Database");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        
        //Instantiate JLabel
        imagePanel.setBounds(SPACER, SPACER, 300, 300);
        imagePanel.setOpaque(true);
        imagePanel.setBorder(
            BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        this.add(imagePanel);
        imagePanel.setVisible(true);
        icon = new ImageIcon("C:\\Users\\aasho\\Desktop\\Maid Outfit.png");
        //C:\Users\aasho\Desktop\Maid Outfit.png
        //C:\Users\A.sholokhov\Desktop\Maid Outfit.png
        imagePanel.setIcon(icon);
        resizeToContainer(imagePanel);
        
        //Instantiate the text box
        textbox.setBounds(SPACER,
            imagePanel.getY() + imagePanel.getHeight() + SPACER,
            imagePanel.getWidth(), SPACER * TWO);
        textbox.setOpaque(true);
        textbox.setCaretColor(BLACK);
        textbox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyPress(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPress(e);
            }
        });
        this.add(textbox);
        textbox.setVisible(true);
        
        //Instantiate list
        list.setBounds(imagePanel.getX() + imagePanel.getWidth() + SPACER,
            SPACER, this.getWidth() - imagePanel.getWidth() - (SPACER * FOUR)
            - MARGIN, 525);
        list.add("",ZERO);
        this.add(list);
        list.setVisible(true);
        
        //Instantiate button
        deleteIndex.setBounds(SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        deleteIndex.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteIndex.setText("Delete");
        deleteIndex.setFont(font);
        deleteIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteIndex();
            }
        });
        this.add(deleteIndex);
        deleteIndex.setVisible(true);
        
        //Instantiate button
        enter.setBounds(deleteIndex.getX()+deleteIndex.getWidth()+SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        enter.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        enter.setFont(font);
        enter.setText("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
        });
        this.add(enter);
        enter.setVisible(true);
        
        //Instantiate button
        save.setBounds(SPACER,
            enter.getY()+enter.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        save.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        save.setFont(font);
        save.setText("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.add(save);
        save.setVisible(true);
        
        //Instantiate button
        load.setBounds(deleteIndex.getX()+deleteIndex.getWidth()+SPACER,
            enter.getY()+enter.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        load.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        load.setFont(font);
        load.setText("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        this.add(load);
        load.setVisible(true);
        
        //Instantiate button
        addIndex.setBounds(deleteIndex.getX()+deleteIndex.getWidth()+SPACER,
            load.getY()+load.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        addIndex.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        addIndex.setFont(font);
        addIndex.setText("Add Index");
        addIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIndex();
            }
        });
        this.add(addIndex);
        addIndex.setVisible(true);
        
        //Instantiate button
        deleteTag.setBounds(SPACER,
            save.getY()+save.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        deleteTag.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteTag.setFont(font);
        deleteTag.setText("Delete Tag");
        deleteTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTag();
            }
        });
        this.add(deleteTag);
        deleteTag.setVisible(true);
        
        //Instantiate button
        search.setBounds(SPACER,
            deleteTag.getY()+deleteTag.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        search.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        search.setFont(font);
        search.setText("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        this.add(search);
        search.setVisible(true);
        
        //Once all is done and set, reveal the interface to the user
        this.setVisible(true);
    }
    
    private void search(){
        if(searchedList.isEmpty()){}
        else{
            for (int i = 0; i < searchedList.size(); i++) {
                linkedList.addFirst(searchedList.get(i));
            }
            searchedList.remove();
        }
        String line = "";
        String title = "";
        while(line == null || line.equals("")){
            if(line == null) return;
            line = JOptionPane.showInputDialog
                (null,
                "Enter the Tag You Want to Search by",
                title,
                JOptionPane.INFORMATION_MESSAGE);
            title = "Enter a Valid Tag";
        }
        for (int i = 0; i < linkedList.size(); i++) {
            boolean isMatching = false;
            for (int j = 0; j < linkedList.get(i).tags.size(); j++) {
                if(line.equals(linkedList.get(i).tags.get(j))){
                    isMatching = true;
                }
            }
            if(isMatching) {
                searchedList.add(linkedList.get(i));
            }
            
        }
        for (int i = 0; i < searchedList.size(); i++) {
            linkedList.remove(searchedList.get(i));
        }
        updateUIList();
    }

    private void save() {
        filehandler.saveObject(linkedList, activeFile);
        JOptionPane.showMessageDialog(null, "Data Saved Successfully");
    }
    
    private void loadData(){
        String line = "";
        String title = "";
        while(line == null || line.equals("")){
            if(line == null){
                return;
            }
            line = JOptionPane.showInputDialog
                (null,
                "Enter the URL or the Directory of the Image You Want to Save",
                title,
                JOptionPane.INFORMATION_MESSAGE);
            title = "Enter a Valid URL or Directory";
        }
        activeFile = line;
        createList();
    }
    
    private void deleteIndex() {
        if(list.getSelectedIndex() < 0) {} 
        else { 
            linkedList.remove(list.getSelectedIndex());
            updateUIList();
        }
    }
    
    private void deleteTag() {
        if(list.getSelectedIndex() < 0) {
        } else {
            linkedList.get(list.getSelectedIndex()).tags.removeLast();
            updateUIList();
        }
    }
    
    private void keyPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(textbox.getText().equals("")) {}
            else{
                enter();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DELETE){
            deleteIndex();
        }
        else{
//            System.out.println(textbox.getText());
        }
    }

    private void enter() {
        if(linkedList.isEmpty()) addIndex();
        int index = list.getSelectedIndex();
        if(index < 0 || textbox.getText().equals("")) return;
        if(linkedList.isEmpty()) addIndex();
        linkedList.get(index).tags.add(textbox.getText());
        updateUIList();
    }

    private void addIndex() {
        Data data = new Data();
        LinkedList<String> subList = new LinkedList<>();
        data.tags = subList;
        linkedList.add(data);
        updateUIList();
    }
    
    private void resizeToContainer(JLabel label) {
        int       width         = label.getWidth();     // get label width
        int       height        = label.getHeight();    // get label height
        ImageIcon originalIcon  = (ImageIcon)label.getIcon();   // get icon
        if (originalIcon == null) return;               // error trap
        Image     originalImage = originalIcon.getImage();      // get image
        Image     newImage      = originalImage.getScaledInstance(
            width,height,Image.SCALE_SMOOTH);
        Icon newIcon               = new ImageIcon(newImage);  // set new image
        label.setIcon(newIcon);                            // set icon to label
    }
}
