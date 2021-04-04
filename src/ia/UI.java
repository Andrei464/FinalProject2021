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
class UI extends JFrame {

    //Constant Variables
    final private int               ZERO                = 0;
    final private int               ONE                 = 1;
    final private int               TWO                 = 2;
    final private int               FOUR                = 4;
    final private int               MARGIN              = 5;
    final private int               SPACER              = 10;
    final private int               FONT_SIZE           = 20;
    final private int               BUTTON_HEIGHT       = 40;    
    final private int               BUTTON_WIDTH        = 145;
    final private int               IMAGE_SIZE          = 300;
    final private int               FRAME_HEIGHT        = 585;
    final private int               FRAME_WIDTH         = 1000;
    
    final private String            DEFAULT_FILE        = 
        "Database.txt";
    final private String            ACTIVE_DATA_FILE    = 
        "Previus_Database.txt";
    final private String            FILE_MSG            = 
        "Enter the Directory of the Database File";
    final private String            IMAGE_MSG           = 
        "Enter the Directory of the Image";
    final private String            TAG_MSG             = 
        "Enter the Tag You Want to Search by";
    final private String            VALID_FILE_MSG      = 
        "Enter a Valid Directory";
    final private String            VALID_TAG_MSG       = 
        "Enter a Valid Tag";
    final private String            DATA_SAVED          = 
        "Data Saved Successfully";
    final private String            TAG_NOT_FOUND       = 
        "Tag Not Found";
    final private String            TAG_FOUND           = 
        "Tag Found";
    final private String            NAME;
    final private Color             BLACK               = Color.BLACK;
    
    //Non-Constant Variables
    private String                  activeFile;
    
    
    //Objects 
    private LinkedList<Data>        linkedList          = new LinkedList<>();
    private LinkedList<Data>        searchedList        = new LinkedList<>();
    final private FileHandler       filehandler         = new FileHandler();
    
    //UI Elements
    final private JTextArea         textbox             = new JTextArea();
    final private JLabel            imagePanel          = new JLabel();
    final private JButton           addIndex            = new JButton();
    final private JButton           save                = new JButton();
    final private JButton           load                = new JButton();
    final private JButton           deleteIndex         = new JButton();
    final private JButton           deleteTag           = new JButton();
    final private JButton           enterTag            = new JButton();
    final private JButton           search              = new JButton();
    final private JButton           addImage            = new JButton();            
    final private Font              defaultFont         = new Font
        ("", FONT_SIZE, FONT_SIZE);
    final private List                    list                = new List();
    
    public UI(String name){
        NAME = name;
        initUIElements();
        createList();
        //Once all is ready and set, reveal the interface to the user
        this.setVisible(true);
    }

    private void createList() {
        try{
            String newFile;
            File file;
            if(activeFile == null) {
                newFile = filehandler.open(ACTIVE_DATA_FILE);
                if(newFile == null) file = new File(DEFAULT_FILE);
                else file = new File(newFile);
            }
            else file = new File(activeFile);
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
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle(NAME);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        
        //Instantiate JLabel
        imagePanel.setBounds(SPACER, SPACER, IMAGE_SIZE, IMAGE_SIZE);
        imagePanel.setOpaque(true);
        imagePanel.setBorder(
            BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        this.add(imagePanel);
        imagePanel.setVisible(true);
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
//        list.setBounds(imagePanel.getX() + imagePanel.getWidth() + SPACER,
//            SPACER, this.getWidth() - imagePanel.getWidth() - (SPACER * FOUR)
//            - MARGIN, FRAME_HEIGHT - BUTTON_HEIGHT - SPACER - MARGIN);
        list.setBounds(imagePanel.getX() + imagePanel.getWidth() + SPACER,
            SPACER, 200, 100);
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
            textbox.getY()+textbox.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        deleteTag.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteTag.setText("Delete Tag");
        deleteTag.setFont(defaultFont);
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
            textbox.getY()+textbox.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        enterTag.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        enterTag.setFont(defaultFont);
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
            enterTag.getY()+enterTag.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        save.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        save.setFont(defaultFont);
        save.setText("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        this.add(save);
        save.setVisible(true);
        
        //Instantiate button
        load.setBounds(deleteTag.getX()+deleteTag.getWidth()+SPACER,
            enterTag.getY()+enterTag.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        load.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        load.setFont(defaultFont);
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
            load.getY()+load.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        addIndex.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        addIndex.setFont(defaultFont);
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
            save.getY()+save.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        deleteIndex.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        deleteIndex.setFont(defaultFont);
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
            SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        search.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        search.setFont(defaultFont);
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
            addIndex.getY()+addIndex.getHeight()+SPACER, BUTTON_WIDTH, BUTTON_HEIGHT);
        addImage.setBorder
            (BorderFactory.createMatteBorder(ONE,ONE,ONE,ONE,BLACK));
        addImage.setFont(defaultFont);
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
        if(linkedList.get(list.getSelectedIndex()) == null) return;
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
        do{
            line = JOptionPane.showInputDialog
                (null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
            if(line == null) return null;
            title = error;
        }while(line.equals(""));
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
        JOptionPane.showMessageDialog
        (null, message,NAME,JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveData() {
        String directory = input(FILE_MSG, VALID_FILE_MSG);
        if(directory == null) return;
        File file = new File(directory);
        if(file.exists()){
            filehandler.saveObject(linkedList, file);
            saveActiveDatabaseFile();
        }
        else{
            try{
                file.createNewFile();
                filehandler.saveObject(linkedList, file);
                saveActiveDatabaseFile();
            }
            catch(IOException e){
                output(VALID_FILE_MSG);
                return;
            }
        }
        output(DATA_SAVED);
    }

    private void saveActiveDatabaseFile() {
        filehandler.save(activeFile, ACTIVE_DATA_FILE);
    }
    
    private void loadData(){
        String line;
        String message = FILE_MSG;
        do{
            line = input(message, VALID_FILE_MSG);
            if(line == null) return;
            message = VALID_FILE_MSG;
        }while(!legitimatePath(line));
        activeFile = line;
        saveActiveDatabaseFile();
        createList();
    }
    
    private boolean legitimatePath(String line){
        File file = new File(line);
        return file.exists();
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
    }

    private void enterTag() {
        int index;
        if(linkedList.isEmpty()) {
            addIndex();
            index = list.getSelectedIndex() + ONE;
        }
        else index = list.getSelectedIndex();
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
        int width = label.getWidth();                       // get label width
        int height = label.getHeight();                     // get label height
        ImageIcon originalIcon = (ImageIcon)label.getIcon();// get icon
        if (originalIcon == null) return;                   // error trap
        Image originalImage = originalIcon.getImage();      // get image
        Image newImage = originalImage.getScaledInstance(
            width,height,Image.SCALE_SMOOTH);
        Icon newIcon = new ImageIcon(newImage);             // set new image
        label.setIcon(newIcon);                             // set icon to label
    }
}
