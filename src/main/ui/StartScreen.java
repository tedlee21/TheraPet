package ui;

import model.PetType;
import model.Profile;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// Represents the startup screen allowing user to start a new game or load previous save
public class StartScreen extends JFrame {
    private static final String JSON_STORE = "./data/profile.json";
    private static final Integer INITIAL_BAL = 50;
    private static final Integer WIDTH = 500;
    private static final Integer HEIGHT = 550;

    protected Profile user;                 //main user profile
    private PetType petType;                //user's pet type
    private JsonReader jsonReader;


    // EFFECTS : layout is set to FlowLayout; preferred size is set;
    //           start buttons are added
    public StartScreen() {
        super("Digital Pet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton newGame = new JButton(new NewAction());
        JButton loadGame = new JButton(new LoadAction());
        addImage("Top");
        add(newGame);
        add(loadGame);
        addImage("Bot");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        jsonReader = new JsonReader(JSON_STORE);
    }

    /**
     * Represents action to be taken when user wants to load
     * their game
     */
    private class NewAction extends AbstractAction {
        private JDialog popup;

        //Constructor sets button label
        NewAction() {
            super("New Game");
        }


        // MODIFIES: this
        // EFFECTS : opens new popup window to bring user through setup process
        @Override
        public void actionPerformed(ActionEvent evt) {
            popup = new JDialog((Dialog) null, "Choose a Pet!", true);
            popup.setSize(400,400);
            popup.setLocationRelativeTo(null);
            popup.setLayout(new GridLayout(5,1));

            String name = JOptionPane.showInputDialog(null,
                        "What is your name?",
                        "Enter name",
                        JOptionPane.QUESTION_MESSAGE);

            JLabel label = new JLabel("What type of pet would you like?");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            popup.add(label);

            addButtons();

            popup.setVisible(true);
            popup.pack();

            String petName = JOptionPane.showInputDialog(null,
                    "What would you like to name your " + petType.toString().toLowerCase() + "?",
                    "Enter name",
                    JOptionPane.QUESTION_MESSAGE);

            user = new Profile(name, INITIAL_BAL, petName, petType);
            new PetAppGUI(user, false);
            dispose();

        }

        // EFFECTS : adds buttons for pet options
        private void addButtons() {
            popup.add(new JButton(new PetAction(PetType.DOG)));
            popup.add(new JButton(new PetAction(PetType.CAT)));
            popup.add(new JButton(new PetAction(PetType.BIRD)));
            popup.add(new JButton(new PetAction(PetType.HEDGEHOG)));
        }

        /**
         * Represents action to be taken when user selects
         * given pet
         */
        private class PetAction extends AbstractAction {
            private PetType type;

            //Constructor sets button label to given pet type
            PetAction(PetType type) {
                super(type.name().substring(0,1) + type.name().substring(1).toLowerCase());
                this.type = type;
            }

            // EFFECTS : sets user petType to type; disposes the popup
            @Override
            public void actionPerformed(ActionEvent evt) {
                petType = type;
                popup.dispose();
            }
        }
    }

    /**
     * Represents action to be taken when user wants to load
     * their game
     */
    private class LoadAction extends AbstractAction {

        //Constructor sets button label
        LoadAction() {
            super("Load Game");
        }


        // MODIFIES: this
        // EFFECTS : loads user profile from save; starts game with loaded value true;
        //           disposes main popup
        @Override
        public void actionPerformed(ActionEvent evt) {
            loadProfile();
            new PetAppGUI(user, true);
            dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS : adds image of corresponding file name to frame
    private void addImage(String filename) {
        try {
            Image img = ImageIO.read(new File("resources/titleScreen" + filename + ".png"));
            Image scaledImg;
            if (filename == "Top") {
                scaledImg = img.getScaledInstance(380,150, Image.SCALE_REPLICATE);
            } else {
                scaledImg = img.getScaledInstance(WIDTH,340, Image.SCALE_REPLICATE);
            }

            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(scaledImg));
            add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
