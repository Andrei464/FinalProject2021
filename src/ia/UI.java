/** required package class namespace */
package ia;

/** required imports */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import collections.LinkedList;
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
    final private int               FONT_SIZE       = 20;
    final private int               B_HEIGHT        = 40;    
    final private int               B_WIDTH         = 145;
    final private int               I_SIZE          = 300;
    final private int               F_HEIGHT        = 585;
    final private int               F_WIDTH         = 1000;
    
    final private String            DEF_FILE        = 
        "SavingTest.txt";
    final private String            FILE_MSG        = 
        "Enter the Directory of the Database File";
    final private String            IMAGE_MSG       = 
        "Enter the Directory of the Image";
    final private String            TAG_MSG         = 
        "Enter the Tag You Want to Search by";
    final private String            VALID_FILE_MSG  = 
        "Enter a Valid Directory";
    final private String            VALID_TAG_MSG   = 
        "Enter a Valid Tag";
    final private String            DATA_SAVED      = 
        "Data Saved Successfully";
    final private String            TAG_NOT_FOUND   = 
        "Tag Not Found";
    final private String            TAG_FOUND       = 
        "Tag Found";
    final private String            NAME;
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
    private JButton                 enterTag        = new JButton();
    private JButton                 search          = new JButton();
    private JButton                 addImage        = new JButton();            
    private Font                    font            = 
        new Font("", FONT_SIZE, FONT_SIZE);
    private List                    list            = new List();
    private Icon                    icon;
    
    public UI(String name){
        this.NAME = name;
        initUIElements();
        createList();
        
        //Once all is done and set, reveal the interface to the user
        this.setVisible(true);
    }

    private void createList() {
        try{
            File file = new File(activeFile);
            if(!file.exists() || file.length() == ZERO) file.createNewFile();
            else{
                linkedList = 
                (LinkedList<Data>)filehandler.openObject(file);
                if(linkedList == null) linkedList = new LinkedList<>();
                else if(linkedList.isEmpty()) addIndex();
                updateUIList();
            }
        }
        catch(IOException e){}
    }

    private void updateUIList() {
        list.removeAll();
        for (int i = ZERO; i < linkedList.size(); i++) {
            Data data = linkedList.get(i);
            list.add(data.toString(), i);
        }  
    }
    
    private void initUIElements(){
        //Instantiate the JFrame
        this.setSize(F_WIDTH, F_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle(NAME);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        
        //Instantiate JLabel
        imagePanel.setBounds(SPACER, SPACER, I_SIZE, I_SIZE);
        imagePanel.setOpaque(true);
        imagePanel.setBorder(
            BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        this.add(imagePanel);
        imagePanel.setVisible(true);
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
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.add(textbox);
        textbox.setVisible(true);
        
        //Instantiate list
        list.setBounds(imagePanel.getX() + imagePanel.getWidth() + SPACER,
            SPACER, this.getWidth() - imagePanel.getWidth() - (SPACER * FOUR)
            - MARGIN, F_HEIGHT - B_HEIGHT - SPACER - MARGIN);
        list.add("",ZERO);
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateImage();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(list);
        list.setVisible(true);
        
        //Instantiate button
        deleteTag.setBounds(SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        deleteTag.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteTag.setText("Delete Tag");
        deleteTag.setFont(font);
        deleteTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTag();
            }
        });
        this.add(deleteTag);
        deleteTag.setVisible(true);
        
        //Instantiate button
        enterTag.setBounds(deleteTag.getX()+deleteTag.getWidth()+SPACER,
            textbox.getY()+textbox.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        enterTag.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        enterTag.setFont(font);
        enterTag.setText("Enter Tag");
        enterTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterTag();
            }
        });
        this.add(enterTag);
        enterTag.setVisible(true);
        
        //Instantiate button
        save.setBounds(SPACER,
            enterTag.getY()+enterTag.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
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
        load.setBounds(deleteTag.getX()+deleteTag.getWidth()+SPACER,
            enterTag.getY()+enterTag.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
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
        addIndex.setBounds(deleteTag.getX()+deleteTag.getWidth()+SPACER,
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
        deleteIndex.setBounds(SPACER,
            save.getY()+save.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        deleteIndex.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteIndex.setFont(font);
        deleteIndex.setText("Delete Index");
        deleteIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteIndex();
            }
        });
        this.add(deleteIndex);
        deleteIndex.setVisible(true);
        
        //Instantiate button
        search.setBounds(SPACER, deleteIndex.getY()+deleteIndex.getHeight() +
            SPACER, B_WIDTH, B_HEIGHT);
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
        
        //Instantiate button
        addImage.setBounds(search.getX()+search.getWidth()+SPACER,
            addIndex.getY()+addIndex.getHeight()+SPACER, B_WIDTH, B_HEIGHT);
        addImage.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        addImage.setFont(font);
        addImage.setText("Add Image");
        addImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImage();
            }
        });
        this.add(addImage);
        addImage.setVisible(true);
    }

    private void updateImage() {
        int index = list.getSelectedIndex();
        if(index < ZERO || linkedList.size() < index) return;
        imagePanel.setIcon(linkedList.get(list.getSelectedIndex()).image);
        resizeToContainer(imagePanel);
    }
    
    private void addImage(){
        int index = list.getSelectedIndex();
        if(index < ZERO || linkedList.get(index).adress != null) return;
        String directory = input(IMAGE_MSG, VALID_FILE_MSG);
        if(directory == null) return;
        File file = new File(directory);
        if(file.exists()){
            linkedList.get(index).adress = directory;
            ImageIcon image = new ImageIcon(directory);
            linkedList.get(index).image = image;
            imagePanel.setIcon(image);
            resizeToContainer(imagePanel);
            updateUIList();
        }
        else{
            output(VALID_FILE_MSG);
            addImage();
        }
    }
    
    private String input(String message, String error){
        String line = "";
        String title = "";
        while(line == null || line.equals("")){
            if(line == null){
                return null;
            }
            line = JOptionPane.showInputDialog
                (null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
            title = error;
        }
        return line;
    }
    
    private void search(){
        String tag = input(TAG_MSG, VALID_TAG_MSG);
        boolean exists = false;
        for (int i = ZERO; i < linkedList.size(); i++) {
            boolean isMatching = false;
            for (int j = ZERO; j < linkedList.get(i).tags.size(); j++) {
                if(tag.equals(linkedList.get(i).tags.get(j))){
                    isMatching = true;
                    exists = true;
                }
            }if(isMatching) searchedList.add(linkedList.get(i));
        }
        if(exists){
            for (int i = ZERO; i < searchedList.size(); i++) {
                linkedList.remove(searchedList.get(i));
                linkedList.addFront(searchedList.get(i));
            }
            updateUIList();
            output(TAG_FOUND);
        } 
        else output(TAG_NOT_FOUND);
    }
    
    private void output(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    private void save() {
        filehandler.saveObject(linkedList, activeFile);
        output(DATA_SAVED);
    }
    
    private void loadData(){
        String line = input(IMAGE_MSG, VALID_FILE_MSG);
        if(line == null) return;
        activeFile = line;
        createList();
    }
    
    private void deleteIndex() {
        if(list.getSelectedIndex() < ZERO) {} 
        else { 
            linkedList.remove(list.getSelectedIndex());
            imagePanel.setIcon(null);
            updateUIList();
        }
    }
    
    private void deleteTag() {
        if(list.getSelectedIndex() < ZERO) {
        } else {
            if(linkedList.get(list.getSelectedIndex()) == null) return;
            if(linkedList.get(list.getSelectedIndex()).tags.isEmpty()){
                linkedList.get(list.getSelectedIndex()).adress = "";
                linkedList.get(list.getSelectedIndex()).image = null;
                imagePanel.setIcon(null);
            }
            else{
                linkedList.get(list.getSelectedIndex()).tags.removeBack();
            }
            updateUIList();
        }
    }
    
    private void keyPress(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(textbox.getText().equals("")) {}
            else{
                enterTag();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DELETE){
            deleteIndex();
        }
        else{}
    }

    private void enterTag() {
        int index;
        if(linkedList.isEmpty()) {
            addIndex();
            index = list.getSelectedIndex() + ONE;
        }
        else {
            index = list.getSelectedIndex();
        }
        if(index < ZERO || textbox.getText().equals("")) return;
        linkedList.get(index).tags.add(textbox.getText());
        textbox.setText("");
        updateUIList();
    }

    private void addIndex() {
        Data data = new Data();
        LinkedList<String> subList = new LinkedList<>();
        data.tags = subList;
        linkedList.add(data);
        updateUIList();
    }
    
    /** 
     * Resizes the image inside the label to match the size of the label 
     * 
     * @param label the JLabel object to resize to
     */
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
