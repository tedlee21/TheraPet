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
    private static final Integer INITIAL_BAL = 50;
    private Profile user;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private PlayPanel playPanel;
    private BagPanel bagPanel;
    private DialoguePanel textPanel;
    private ShopPanel leftPanel;

    // EFFECTS : initializes the digital pet application GUI
    public PetAppGUI(boolean load) {
        initSystems(load);
        runPetGUI();
    }

    // EFFECTS : sets up play panel, shop panel, dialogue panel, and user's bag to be
    //           displayed on window
    private void runPetGUI() {
        setLayout(new BorderLayout());
        addMouseListener(new DesktopFocusAction());
        playPanel = new PlayPanel();
        bagPanel = new BagPanel();
        leftPanel = new ShopPanel(user);
        textPanel = new DialoguePanel(jsonWriter, leftPanel);
        add(playPanel, BorderLayout.NORTH);
        add(bagPanel, BorderLayout.EAST);
        add(textPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        setTitle("Digital Pet");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS : initializes systems and loads profile if required
    private void initSystems(boolean load) {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        if (load) {
            loadProfile();
        }
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

    // MODIFIES: this
    // EFFECTS : loads user profile from file
    private void loadProfile() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
