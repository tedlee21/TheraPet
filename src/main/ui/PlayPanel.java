package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a JPanel for which the buttons to play with user's pet will be
public class PlayPanel extends JPanel {
    private static final Integer HEIGHT = 60;       //Height of the main panel

    // EFFECTS : layout is set to GridLayout and preferred size is set; Border is titled to reflect
    //           panel function; buttons are added to panel
    public PlayPanel() {
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

        TalkAction() {
            super("Talk");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

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
            String sensorLoc = JOptionPane.showInputDialog(null,
                    "Sensor location?",
                    "Enter sensor location",
                    JOptionPane.QUESTION_MESSAGE);
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

        }
    }
}
