package ui;

import model.PetType;
import model.Profile;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class StartScreen extends JFrame {
    private static final String JSON_STORE = "./data/profile.json";
    private static final Integer INITIAL_BAL = 50;

    protected Profile user;
    private PetType petType;
    private JsonReader jsonReader;

    public StartScreen() {
        super("Digital Pet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton newGame = new JButton(new NewAction());
        JButton loadGame = new JButton(new LoadAction());
        add(newGame);
        add(loadGame);
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
        private JPanel panel;

        NewAction() {
            super("New Game");
        }

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

            PetAction(PetType type) {
                super(type.name().substring(0,1) + type.name().substring(1).toLowerCase());
                this.type = type;
            }

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

        LoadAction() {
            super("Load Game");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadProfile();
            new PetAppGUI(user, true);
            dispose();
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
