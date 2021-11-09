package ui;

import model.PetType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// Represents a JPanel for which the buttons to play with user's pet will be
public class PlayPanel extends JPanel {
    private static final Integer HEIGHT = 85;       //Height of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user

    // EFFECTS : main is set to mainImport; layout is set to GridLayout
    //           and preferred size is set; Border is titled to reflect
    //           panel function; buttons are added to panel
    public PlayPanel(PetAppGUI mainImport) {
        this.main = mainImport;
        setLayout(new GridLayout(1,4));
        Dimension size = new Dimension();
        size.height = HEIGHT;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(143, 111, 79), 2),
                "Play",
                0, 0,
                new Font(Font.SERIF, Font.BOLD,14), Color.white));
        setBackground(new Color(180, 139, 98));
        setOpaque(true);
        setVisible(true);

        addButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS : helper method adds play option buttons to main panel
    private void addButtonPanel() {
        addChatButton();
        addPetButton();
        addExerciseButton();
        addBallButton();
    }

    // MODIFIES: this
    // EFFECTS : creates new JButton chatButton, and assigns Action and Icon;
    //           adds button to PlayPanel
    private void addChatButton() {
        JButton chatButton = new JButton(new TalkAction());
        try {
            Image img = ImageIO.read(new File("resources/button/chat.png"));
            Image scaledImg = img.getScaledInstance(65,65, Image.SCALE_REPLICATE);
            chatButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(chatButton);
    }

    // MODIFIES: this
    // EFFECTS : creates new JButton petButton, and assigns Action and Icon;
    //           adds button to PlayPanel
    private void addPetButton() {
        JButton petButton = new JButton(new PetAction());
        try {
            Image img = ImageIO.read(new File("resources/button/touch.png"));
            Image scaledImg = img.getScaledInstance(60,60, Image.SCALE_REPLICATE);
            petButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(petButton);
    }

    // MODIFIES: this
    // EFFECTS : creates new JButton exerciseButton, and assigns Action and Icon;
    //           adds button to PlayPanel
    private void addExerciseButton() {
        JButton exerciseButton = new JButton(new ExerciseAction());
        try {
            Image img = ImageIO.read(new File("resources/button/exercise.png"));
            Image scaledImg = img.getScaledInstance(65,65, Image.SCALE_REPLICATE);
            exerciseButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(exerciseButton);
    }

    // MODIFIES: this
    // EFFECTS : creates new JButton ballButton, and assigns Action and Icon;
    //           adds button to PlayPanel
    private void addBallButton() {
        JButton ballButton = new JButton(new BallAction());
        try {
            Image img = ImageIO.read(new File("resources/button/ball.png"));
            Image scaledImg = img.getScaledInstance(65,65, Image.SCALE_REPLICATE);
            ballButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(ballButton);
    }

    /**
     * Represents action to be taken when user wants to talk to
     * their pet.
     */
    private class TalkAction extends AbstractAction {
        private JDialog popup;

        //Constructor sets button label
        TalkAction() {
            super("Chat");
        }

        // MODIFIES: this
        // EFFECTS : displays popup dialog with buttons to select user's mood;
        @Override
        public void actionPerformed(ActionEvent evt) {
            popup = new JDialog((Dialog) null, "Talk", true);
            popup.setSize(400,400);
            popup.setLocationRelativeTo(null);
            popup.setLayout(new GridLayout(5,1));

            JLabel label = new JLabel("How are you feeling today?");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            popup.add(label);

            addButtons();

            popup.setVisible(true);
            popup.pack();
        }

        // EFFECTS : adds button options for mood
        private void addButtons() {
            popup.add(new JButton(new HappyAction()));
            popup.add(new JButton(new SadAction()));
            popup.add(new JButton(new AngryAction()));
            popup.add(new JButton(new TiredAction()));
        }

        /**
         * Represents action to be taken when user reports
         * they are happy
         */
        private class HappyAction extends AbstractAction {

            //Constructor sets button label
            HappyAction() {
                super("Happy");
            }

            // MODIFIES: this
            // EFFECTS : disposes mood select popup;
            //           calls method to update pet icon with happy file name;
            //           text log displays reaction to user mood
            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconFile("happy");
                main.textPanel.textLog.setText("YAY! I'm happy because you're happy!"
                        + " Let's continue to have a great day!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are sad
         */
        private class SadAction extends AbstractAction {

            //Constructor sets button label
            SadAction() {
                super("Sad");
            }

            // MODIFIES: this
            // EFFECTS : disposes mood select popup;
            //           calls method to update pet icon with sad file name;
            //           text log displays reaction to user mood
            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconFile("sad");
                main.textPanel.textLog.setText("Aw " + main.user.getName()
                        + ", I'm so sorry you're feeling down, try having some tea!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are angry
         */
        private class AngryAction extends AbstractAction {

            //Constructor sets button label
            AngryAction() {
                super("Angry");
            }

            // MODIFIES: this
            // EFFECTS : disposes mood select popup;
            //           calls method to update pet icon to neutral;
            //           text log displays reaction to user mood
            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconBase();
                main.textPanel.textLog.setText("Who made you upset! Whoever it is, I'll beat them up!!!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are tired
         */
        private class TiredAction extends AbstractAction {

            //Constructor sets button label
            TiredAction() {
                super("Tired");
            }

            // MODIFIES: this
            // EFFECTS : disposes mood select popup;
            //           calls method to update pet icon with sad file name;
            //           text log displays reaction to user mood
            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconFile("sad");
                main.textPanel.textLog.setText("Life can be a lot sometimes."
                        + " Better days will come, but until then, you have me!");
            }
        }

    }

    /**
     * Represents action to be taken when user wants to pet
     * their pet.
     */
    private class PetAction extends AbstractAction {

        //Constructor sets button label
        PetAction() {
            super("Pet");
        }

        // MODIFIES: this
        // EFFECTS : calls method to update pet icon with happy file name;
        //           text log displays reaction to PetAction
        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIconFile("happy");
            main.textPanel.textLog.setText("Oh yeah, that's the spot!!");
        }
    }


    /**
     * Represents action to be taken when user wants to exercise
     * with their pet.
     */
    private class ExerciseAction extends AbstractAction {

        //Constructor sets button label
        ExerciseAction() {
            super("Work Out");
        }

        // MODIFIES: this
        // EFFECTS : calls method to update pet icon with exercise file name;
        //           text log displays reaction to ExerciseAction
        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIconFile("exercise");
            main.textPanel.textLog.setText("Whew! Ha! Whew! Ha! Working up a sweat!!");
        }
    }

    /**
     * Represents action to be taken when user wants to play ball
     * with their pet.
     */
    private class BallAction extends AbstractAction {

        //Constructor sets button label
        BallAction() {
            super("Ball");
        }

        // EFFECTS : runs playBall
        @Override
        public void actionPerformed(ActionEvent evt) {
            playBall();
        }
    }

    // MODIFIES: this
    // EFFECTS : displays results of playing with a ball depending on user's pet type
    private void playBall() {
        PetType type = main.user.getPetType();
        main.updatePetIconFile("ball");

        if (type.equals(PetType.DOG)) {
            main.textPanel.textLog.setText("Ooh Ooh! Ball!! I got it!! I got it!!");
        } else if (type.equals(PetType.CAT)) {
            main.textPanel.textLog.setText("Hmmm.. a Ball? Amusing.");
        } else if (type.equals(PetType.BIRD)) {
            main.textPanel.textLog.setText("I.. I'm not sure how to play with this .. but thank you!");
        } else {
            main.textPanel.textLog.setText("This ball is as big as I am!!\n ... \n"
                    + "My new best friend! ..Other than you of course!!");
        }
    }
}
