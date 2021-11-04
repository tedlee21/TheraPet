package ui;

import model.Profile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Digital Pet Application        //Graphical interface code based off AlarmSystem, TrafficLight, and LabelChanger
                                 //files from CPSC 210 Class
                                 //Data persistence implementations based off JsonSerializationDemo file
public class PetAppGUI extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 500;
    protected static final String JSON_STORE = "./data/profile.json";
    protected Profile user;
    private Scanner input;
    protected JsonWriter jsonWriter;

    protected PetPanel petPanel;
    private PlayPanel playPanel;
    private BagPanel bagPanel;
    protected DialoguePanel textPanel;
    protected ShopPanel leftPanel;

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
        petPanel = new PetPanel(user);
        textPanel = new DialoguePanel(this);
        playPanel = new PlayPanel(this);
        bagPanel = new BagPanel(user, textPanel);
        leftPanel = new ShopPanel(bagPanel);
        add(playPanel, BorderLayout.NORTH);
        add(bagPanel, BorderLayout.EAST);
        add(textPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
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
        jsonWriter = new JsonWriter(JSON_STORE);
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

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }
}
