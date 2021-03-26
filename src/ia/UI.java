package ia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JFileChooser;
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
    final int                       ZERO        = 0;
    final int                       TWO         = 2;
    final int                       FOUR        = 4;
    final int                       MARGIN      = 5;
    final int                       SPACER      = 10;
    final int                       B_HEIGHT    = 40;    
    final int                       B_WIDTH     = 145;
    
    final String                    DEF_FILE    = "SavingTest.txt";
    final private Color             BLACK       = Color.BLACK;
    
    //Non-Constant Variables
    String                          activeFile  = DEF_FILE;
    
    //Objects 
    LinkedList<LinkedList<String>>  linkedList  = new LinkedList<>();
    FileHandler                     filehandler = new FileHandler();
    
    //UI Elements
    JTextArea                       textbox     = new JTextArea();
    JLabel                          imagePanel  = new JLabel();
    JButton                         addIndex    = new JButton();
    JButton                         save        = new JButton();
    JButton                         deleteIndex = new JButton();
    JButton                         deleteTag   = new JButton();
    JButton                         enter       = new JButton();
    JButton                         load        = new JButton();
    Font                            font        = new Font("Helvetica", 20, 20);
    List                            list        = new List();
    Icon                            icon;
    
    public UI(){
        initUIElements();
        createList();
    }

    private void createList() {
        try{
            File file = new File(activeFile);
            if(!file.exists() || file.length() == 0) file.createNewFile();
            else{
                linkedList = 
                (LinkedList<LinkedList<String>>)filehandler.openObject(file);
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
            LinkedList<String> subList = linkedList.get(i);
            String line = "";
            for (int j = 0; j < subList.size() - 1; j++) {
                line += subList.get(j) + ", ";
            }
            if(!subList.isEmpty()) line += subList.get(subList.size() - 1);
            list.add(line, i);
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
            BorderFactory.createMatteBorder(1,1,1,1,BLACK));
        this.add(imagePanel);
        imagePanel.setVisible(true);
        icon = new ImageIcon(
            "C:\\Users\\A.sholokhov\\Desktop\\Maid Outfit.png");
        imagePanel.setIcon(icon);
        resizeToContainer(imagePanel);
        
        //Instantiate the text box
        textbox.setBounds(SPACER,
            imagePanel.getY() + imagePanel.getHeight() + SPACER,
            imagePanel.getWidth(), SPACER * TWO);
//        textbox.setMargin(m);/////////////////////////////////////////////////
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
        deleteIndex.setBorder(BorderFactory.createMatteBorder(1,1,1,1,BLACK));
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
        enter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,BLACK));
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
        save.setBorder(BorderFactory.createMatteBorder(1,1,1,1,BLACK));
        save.setFont(font);
        save.setText("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1");
                save();
            }
        });
        this.add(save);
        save.setVisible(true);
        
        //Instantiate button
        load.setBounds(deleteIndex.getX()+deleteIndex.getWidth()+SPACER,
            enter.getY()+enter.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        load.setBorder(BorderFactory.createMatteBorder(1,1,1,1,BLACK));
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
            (BorderFactory.createMatteBorder(1,1,1,1,BLACK));
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
        deleteTag.setBorder(BorderFactory.createMatteBorder(1,1,1,1,BLACK));
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
        
        //Once all is done and set, reveal the interface to the user
        this.setVisible(true);
    }

    private void save() {
        System.out.println(activeFile);
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
            linkedList.get(list.getSelectedIndex()).removeLast();
            updateUIList();
        }
    }
    
    private void keyPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(textbox.getText().equals("")|| imagePanel.getIcon() != null) {}
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
        System.out.println(linkedList.size());
        linkedList.get(index).add(textbox.getText());
        updateUIList();
    }

    private void addIndex() {
        LinkedList<String> subList = new LinkedList<>();
        subList.add(" ");
        linkedList.add(subList);
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
    
//    public void resizeToMaxRatio(JLabel label){
//        resizeToContainer(label);
//        ImageIcon oldIcon = (ImageIcon)label.getIcon();
//        int iconWidth = oldIcon.getIconWidth();
//        int iconHeight = oldIcon.getIconHeight();
//        int gdc = gdc(iconWidth, iconHeight);
//        int heightOffset = iconHeight / gdc;
//        int widthOffset = iconWidth / gdc;
//        int newIconHeight = iconHeight;
//        int newIconWidth = iconWidth;
//        System.out.println(widthOffset + ", " + heightOffset);
//        if(iconHeight > label.getWidth() || iconWidth > label.getHeight()){
//            System.out.println(newIconWidth + ", " + newIconHeight);
//            while (newIconHeight > label.getWidth() &&
//                    newIconWidth > label.getHeight()) {
//                newIconHeight -= heightOffset;
//                newIconWidth  -= widthOffset;
//                System.out.println(newIconWidth + ", " + newIconHeight);
//            }
//            Image oldImage = oldIcon.getImage();
//            Image newImage = oldImage.getScaledInstance
//                (newIconWidth, newIconHeight, Image.SCALE_SMOOTH);
//            Icon newIcon = new ImageIcon(newImage);
//            label.setIcon(newIcon);
//        }
//        else{
//            System.out.println("2");
//        }
//    }
//    
//    private int gdc(int p, int q) {
//        if (q == ZERO) return p;
//        else return gdc(q, p % q);
//    }
}
