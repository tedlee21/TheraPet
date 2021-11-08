package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import model.Profile;
import persistence.JsonWriter;

// Represents a JPanel for which dialogue, the save button, and the coin button will be
public class DialoguePanel extends JPanel {
    private static final Integer HEIGHT = 120;      //Height of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user
    private Profile user;                           //main user profile
    private JsonWriter jsonWriter;
    protected JTextArea textLog;


    // REQUIRES: mainJsonWriter and leftPanel have been instantiated
    // EFFECTS : jsonWriter is set to mainImport.JsonWriter, main is set to mainImport, user is set
    //           to main.user; sets up main JPanel for which dialogue, the save button, and the
    //           coin button will appear; layout is set to BorderLayout and preferred size is set
    public DialoguePanel(PetAppGUI mainImport) {
        this.jsonWriter = mainImport.jsonWriter;
        this.main = mainImport;
        this.user = main.user;
        setLayout(new BorderLayout());
        Dimension size = new Dimension();
        size.height = HEIGHT;
        setPreferredSize(size);

        setBorder(BorderFactory.createLineBorder(Color.blue));

        addTextPanel();
        addSaveButton();
        addCoinButton();
    }

    // EFFECTS : adds a text panel to the left of main panel
    private void addTextPanel() {
        JPanel textPanel = new JPanel();
        Dimension panelSize = new Dimension();
        panelSize.width = (PetAppGUI.WIDTH - BagPanel.WIDTH);
        textPanel.setPreferredSize(panelSize);
        textPanel.setBorder(BorderFactory.createTitledBorder(user.getPetName() + ": "));
        textLog = new JTextArea();
        textPanel.add(textLog);

        add(textPanel, BorderLayout.WEST);
    }

    // EFFECTS : adds a save button to center of main panel
    private void addSaveButton() {
        JButton saveButton = new JButton(new SaveAction());
        add(saveButton, BorderLayout.CENTER);
    }

    // EFFECTS : adds a coin earning button to right of main panel
    private void addCoinButton() {
        JButton coinButton = new JButton(new CoinAction());
        add(coinButton, BorderLayout.EAST);
    }

    /**
     * Represents action to be taken when user wants to
     * save the game
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save Game");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            saveProfile();
        }
    }

    /**
     * Represents action to be taken when user add a coin to
     * their balance.
     */
    private class CoinAction extends AbstractAction {

        CoinAction() {
            super("Earn Coins");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIconBase();
            user.addBalance(1);
            main.leftPanel.coins.setText("Hi " + user.getName() + "\nYou have: " + user.getBalance() + " coins");
        }
    }

    // EFFECTS : saves the profile to file
    private void saveProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved " + user.getName() + " to " + PetAppGUI.JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PetAppGUI.JSON_STORE);
        }
    }
}
