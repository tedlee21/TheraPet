package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import persistence.JsonWriter;

// Represents a JPanel for which dialogue, the save button, and the coin button will be
public class DialoguePanel extends JPanel {
    private static final Integer HEIGHT = 100;      //Height of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user
    private JsonWriter jsonWriter;
    protected JTextArea textLog;


    // REQUIRES: mainJsonWriter and leftPanel have been instantiated
    // EFFECTS : jsonWriter is set to mainImport.JsonWriter, main is set to mainImport, user is set
    //           to main.user; sets up main JPanel for which dialogue, the save button, and the
    //           coin button will appear; layout is set to BorderLayout and preferred size is set
    public DialoguePanel(PetAppGUI mainImport) {
        this.jsonWriter = new JsonWriter(main.JSON_STORE);
        this.main = mainImport;
        setLayout(new BorderLayout());
        Dimension size = new Dimension();
        size.height = HEIGHT;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(new Color(143, 111, 79), 2));
        setBackground(new Color(180, 139, 98));
        setOpaque(true);

        addTextPanel();
        addSaveButton();
        addCoinButton();
    }

    // MODIFIES: this
    // EFFECTS : adds a text panel to the center of main panel
    private void addTextPanel() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,1));
        Dimension panelSize = new Dimension();
        panelSize.width = (PetAppGUI.WIDTH - BagPanel.WIDTH);
        textPanel.setPreferredSize(panelSize);
        textPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(143, 111, 79), 2),
                main.user.getPetName() + ": ",
                0, 0,
                new Font(Font.SERIF, Font.BOLD,14), Color.white));
        textPanel.setBackground(new Color(180, 139, 98));
        textPanel.setOpaque(true);
        textLog = new JTextArea();
        textLog.setEditable(false);
        textPanel.add(textLog);

        add(textPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS : adds a save button to west of main panel
    private void addSaveButton() {
        JButton saveButton = new JButton(new SaveAction());
        Dimension panelSize = new Dimension();
        panelSize.width = ShopPanel.WIDTH;
        saveButton.setPreferredSize(panelSize);
        try {
            Image img = ImageIO.read(new File("resources/button/save.png"));
            Image scaledImg = img.getScaledInstance(80,80, Image.SCALE_REPLICATE);
            saveButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(saveButton, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS : adds a coin earning button to east of main panel
    private void addCoinButton() {
        JButton coinButton = new JButton(new CoinAction());
        Dimension panelSize = new Dimension();
        panelSize.width = BagPanel.WIDTH;
        coinButton.setPreferredSize(panelSize);
        try {
            Image img = ImageIO.read(new File("resources/button/coin.png"));
            Image scaledImg = img.getScaledInstance(80,80, Image.SCALE_REPLICATE);
            coinButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(coinButton, BorderLayout.EAST);
    }

    /**
     * Represents action to be taken when user wants to
     * save the game
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {}

        // MODIFIES: this
        // EFFECTS : saves profile to JSON; updates text display so show game was saved
        @Override
        public void actionPerformed(ActionEvent evt) {
            saveProfile();
            textLog.setText("Saved the game!");
        }
    }

    /**
     * Represents action to be taken when user add a coin to
     * their balance.
     */
    private class CoinAction extends AbstractAction {

        CoinAction() {}

        // MODIFIES: this
        // EFFECTS : sets pet image to neutral state; adds 1 to user balance;
        //           updates user stat display to show new balance
        @Override
        public void actionPerformed(ActionEvent evt) {
            main.updatePetIconBase();
            main.user.addBalance(1);
            main.shopPanel.coins.setText("Hi " + main.user.getName()
                    + "\nYou have: " + main.user.getBalance() + " coins");
        }
    }

    // EFFECTS : saves the profile to file
    private void saveProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(main.user);
            jsonWriter.close();
            System.out.println("Saved " + main.user.getName() + " to " + PetAppGUI.JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PetAppGUI.JSON_STORE);
        }
    }
}
