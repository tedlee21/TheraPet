package ui;

import model.EventLog;
import model.Event;
import model.Food;
import model.FoodType;
import model.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Digital Pet Application        //Graphical interface code in UI package based off AlarmSystem, TrafficLight,
                                 //and LabelChanger files from CPSC 210 Class;
                                 //Data persistence implementations based off JsonSerializationDemo file
public class PetAppGUI extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 500;
    protected static final String JSON_STORE = "./data/profile.json";
    private Scanner input;

    protected Profile user;                     //main user profile

    private PlayPanel playPanel;                //Panel on window for play options
    protected PetPanel petPanel;                //Panel displaying pet
    protected BagPanel bagPanel;                //Panel displaying user storage
    protected DialoguePanel textPanel;          //Panel displaying pet dialogue
    protected ShopPanel shopPanel;              //Panel displaying shop and user balance

    // EFFECTS : initializes the digital pet application GUI
    public PetAppGUI(Profile user, boolean loaded) {
        this.user = user;
        initSystems();
        runPetGUI(loaded);
    }

    // EFFECTS : sets up pet panel, play panel, shop panel, dialogue panel,
    //           and user's bag to be displayed on window
    private void runPetGUI(boolean load) {
        setLayout(new BorderLayout());
        addMouseListener(new DesktopFocusAction());
        petPanel = new PetPanel(this);
        textPanel = new DialoguePanel(this);
        playPanel = new PlayPanel(this);
        bagPanel = new BagPanel(this);
        shopPanel = new ShopPanel(this);
        add(playPanel, BorderLayout.NORTH);
        add(bagPanel, BorderLayout.EAST);
        add(textPanel, BorderLayout.SOUTH);
        add(shopPanel, BorderLayout.WEST);
        add(petPanel, BorderLayout.CENTER);

        setTitle("Digital Pet");
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
        if (load) {
            textPanel.textLog.setText("OMG hi " + user.getName() + " I missed you! Welcome back!");
        } else {
            textPanel.textLog.setText("Hello " + user.getName() + ".. Nice to meet you!");
        }
    }

    // MODIFIES: this
    // EFFECTS : initializes systems
    private void initSystems() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n");
                }
            }
        });
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            PetAppGUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS : centers main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS : updates PetPanel's image to reflect pet in neutral state
    protected void updatePetIconBase() {
        try {
            Image img = ImageIO.read(new File("resources/"
                    + user.getPetType().toString().toLowerCase()
                    + "/pet.png"));
            Image scaledImg = img.getScaledInstance(250,250, Image.SCALE_REPLICATE);
            petPanel.picture.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS : updates PetPanel's image to reflect pet with given file name
    protected void updatePetIconFile(String file) {
        try {
            Image img = ImageIO.read(new File("resources/"
                    + user.getPetType().toString().toLowerCase()
                    + "/"
                    + file
                    + ".png"));
            Image scaledImg = img.getScaledInstance(250,250, Image.SCALE_REPLICATE);
            petPanel.picture.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS : updates PetPanel's image to reflect pet in neutral state
    protected void updatePetIconFood(Food food) {
        String foodString = foodToString(food.getType());
        try {
            Image img = ImageIO.read(new File("resources/"
                    + user.getPetType().toString().toLowerCase()
                    + "/"
                    + foodString
                    + " feed.png"));
            Image scaledImg = img.getScaledInstance(250,250, Image.SCALE_REPLICATE);
            petPanel.picture.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS : returns a given string made all lowercase, and removes underscores with spaces
    protected String foodToString(FoodType f) {
        return f.toString().toLowerCase().replaceAll("_", " ");
    }

}
