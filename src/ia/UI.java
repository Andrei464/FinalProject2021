package ia;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
* UI.java - 
*
* @author Andrei S. 
* @since Mar. 8, 2021 
*/
class UI extends JFrame{

    final int TWO    = 2;
    final int FOUR   = 4;
    final int MARGIN = 5;
    final int SPACER = 10;
    
    final int B_HEIGHT = SPACER * 3;    
    final int B_WIDTH = SPACER * SPACER;
    
    JTextField textbox = new JTextField();
    JLabel imagePanel = new JLabel();
    Icon icon;
    JButton button = new JButton();
    List list = new List();
    
    public UI(){
        instantiateUIElements();
    }
    
    private void instantiateUIElements(){
        //Instantiate the JFrame
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Photo Database");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        
        //Instantiate JLabel
//        imagePanel.setBounds(textbox.getX() - MARGIN,textbox.getY()-MARGIN,
//            textbox.getWidth()  + MARGIN * TWO,
//            textbox.getHeight() + MARGIN * TWO);
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
            imagePanel.getWidth(), SPACER + SPACER);
        textbox.setOpaque(true);
        textbox.setCaretColor(Color.BLACK);
        textbox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
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
        //instatiate list
        list.setBounds(imagePanel.getX() + imagePanel.getWidth() + SPACER,
            SPACER, this.getWidth() - imagePanel.getWidth() - (SPACER * FOUR)
            - MARGIN, 525);
        this.add(list);
        list.setVisible(true);
        
        //Instantiate button
        button.setBounds(20, 500, 60, 20);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        this.add(button);
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(0);
            }
        });
                
        this.setVisible(true);
    }
    
    private void keyPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(textbox.getText().equals("")&& imagePanel.getIcon() != null){}
            else{
                String test = list.toString();
                System.out.println(test + "awd");
                list.add("",0);
                String line = list.getItem(0);
                list.remove(0);
                list.add(textbox.getText() + ", " + line , 0);
                textbox.setText("");
            }
        }
        else{
            System.out.println(textbox.getText());
        }
    }
    
    public void resizeToContainer(JLabel label) {
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
//        if (q == 0) return p;
//        else return gdc(q, p % q);
//    }
}
