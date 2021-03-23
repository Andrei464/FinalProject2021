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
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    
    //Objects 
    LinkedList<LinkedList<String>>  linkedlist  = new LinkedList<>();
    FileHandler                     filehandler = new FileHandler();
    
    //UI Elements
    JTextArea                       textbox     = new JTextArea();
    JLabel                          imagePanel  = new JLabel();
    JButton                         removeWord  = new JButton();
    JButton                         addWord     = new JButton();
    JButton                         save        = new JButton();
    JButton                         delete      = new JButton();
    JButton                         enter       = new JButton();
    JButton                         search      = new JButton();
    Font                            font        = new Font("Helvetica", 20, 20);
    List                            list        = new List();
    Icon                            icon;
    
    public UI(){
        initUIElements();
        LinkedList<LinkedList<String>> awd = new LinkedList<>();
        for (int i = ZERO; i < 10; i++) {
            LinkedList<String> list = new LinkedList<>();
            awd.add(list);
            for (int j = ZERO; j < 10; j++) {
                list.add(j * i + "");
            }
        }
        File file = new File("SavingTest.txt");
        
        try{
            if(!file.exists()) file.createNewFile();
        }
        catch(IOException e){}
        
        //filehandler.saveObject(awd, file);
        LinkedList<String> newList = 
            (LinkedList<String>)filehandler.openObject(file);
        System.out.println(newList.toString());
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
            BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));
        this.add(imagePanel);
        imagePanel.setVisible(true);
        icon = new ImageIcon(
            "C:\\Users\\A.sholokhov\\Desktop\\Maid Outfit.png");
        imagePanel.setIcon(icon);
        resizeToContainer(imagePanel);
        
        //Instantiate the text box
        textbox.setBounds(SPACER,
            imagePanel.getY() + imagePanel.getHeight() + SPACER,
            imagePanel.getWidth(), SPACER * FOUR);
//        textbox.setMargin(m);/////////////////////////////////////////////////
        textbox.setOpaque(true);
        textbox.setCaretColor(Color.BLACK);
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
        delete.setBounds(SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        delete.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        delete.setText("Delete");
        delete.setFont(font);
        this.add(delete);
        delete.setVisible(true);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedIndex() < ZERO){}
                else{
                    deleteIndex();
                }
            }
        });
                
        
        //Instantiate button
        enter.setBounds(delete.getX()+delete.getWidth()+SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        enter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        enter.setFont(font);
        this.add(enter);
        enter.setText("Enter");
        enter.setVisible(true);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedIndex() < ZERO){}
                else{
                    deleteIndex();
                }
            }
        });
        
        //Once all is done and set, reveal the interface to the user
        this.setVisible(true);
    }

    private void deleteIndex() {
        list.remove(list.getSelectedIndex());
    }
    
    private void keyPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(textbox.getText().equals("")|| imagePanel.getIcon() != null){}
            else{
                enter();
            }
        }
        else{
            System.out.println(textbox.getText());
        }
    }

    private void enter() {
        String line = list.getItem(ZERO);
        list.add(textbox.getText() + ", " + line);
        textbox.setText("");
    }
    
    private void resizeToContainer(JLabel label) {
        int       width         = label.getWidth();     // get label width
        int       height        = label.getHeight();    // get label height
        ImageIcon originalIcon  = (ImageIcon)label.getIcon();   // get icon
        if (originalIcon == null) return;               // error trap
        Image     originalImage = originalIcon.getImage();      // get image
        Image     newImage      = originalImage.getScaledInstance(
            width,height,Image.SCALE_SMOOTH);
        Icon icon               = new ImageIcon(newImage);  // set new image
        label.setIcon(icon);                            // set icon to label
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
//            while (newIconHeight > label.getWidth() && newIconWidth > label.getHeight()) {
//                newIconHeight -= heightOffset;
//                newIconWidth  -= widthOffset;
//                System.out.println(newIconWidth + ", " + newIconHeight);
//            }
//            Image oldImage = oldIcon.getImage();
//            Image newImage = oldImage.getScaledInstance(newIconWidth, newIconHeight, Image.SCALE_SMOOTH);
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
