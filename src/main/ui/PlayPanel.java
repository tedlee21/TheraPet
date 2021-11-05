package ui;

import model.PetType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Represents a JPanel for which the buttons to play with user's pet will be
public class PlayPanel extends JPanel {
    private static final Integer HEIGHT = 60;       //Height of the main panel
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
        setVisible(true);
        setBorder(BorderFactory.createTitledBorder("Play"));

        addButtonPanel();
    }

    /**
     * Helper to add main play buttons.
     */
    private void addButtonPanel() {
        add(new JButton(new TalkAction()));
        add(new JButton(new PetAction()));
        add(new JButton(new ExerciseAction()));
        add(new JButton(new BallAction()));
    }

    /**
     * Represents action to be taken when user wants to talk to
     * their pet.
     */
    private class TalkAction extends AbstractAction {
        private JDialog popup;
        private JPanel panel;

        TalkAction() {
            super("Talk");
        }

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

            HappyAction() {
                super("Happy");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconHappy();
                main.textPanel.textLog.setText("YAY! I'm happy because you're happy!"
                        + " Let's continue to have a great day!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are sad
         */
        private class SadAction extends AbstractAction {

            SadAction() {
                super("Sad");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIcon();
                main.textPanel.textLog.setText("Aw " + main.user.getName()
                        + ", I'm so sorry you're feeling down, try having some tea!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are angry
         */
        private class AngryAction extends AbstractAction {

            AngryAction() {
                super("Angry");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIcon();
                main.textPanel.textLog.setText("Who made you upset! Whoever it is, I'll beat them up!!!");
            }
        }

        /**
         * Represents action to be taken when user reports
         * they are tired
         */
        private class TiredAction extends AbstractAction {

            TiredAction() {
                super("Tired");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                popup.dispose();
                main.updatePetIconHappy();
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

        PetAction() {
            super("Pet");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIconHappy();
            main.textPanel.textLog.setText("Oh yeah, that's the spot!!");
        }
    }


    /**
     * Represents action to be taken when user wants to exercise
     * with their pet.
     */
    private class ExerciseAction extends AbstractAction {

        ExerciseAction() {
            super("Exercise");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIcon();
            main.textPanel.textLog.setText("Whew! Ha! Whew! Ha! Working up a sweat!!");
        }
    }

    /**
     * Represents action to be taken when user wants to play ball
     * with their pet.
     */
    private class BallAction extends AbstractAction {

        BallAction() {
            super("Ball");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            playBall();

        }
    }

    // MODIFIES: this
    // EFFECTS : displays results of playing with a ball depending on user's pet type
    private void playBall() {
        PetType type = main.user.getPetType();
        main.updatePetIconHappy();

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
