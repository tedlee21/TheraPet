package ui;

import javax.swing.*;
import java.awt.*;
import model.*;

// Represents a JPanel displaying a user's name and balance, along with the shop to buy food from
public class ShopPanel extends JPanel {
    private static final Integer WIDTH = 170;       //Width of the main panel
    protected Profile user;                         //main user profile
    protected JTextArea coins;                      //JTextArea displaying user's name and balance

    // REQUIRES: mainUser must be instantiated
    // EFFECTS : user is set to mainUser; layout is set to GridLayout and preferred size is set;
    //           Border is titled to reflect panel function; Stats panel is added, and Shop items
    //           are added
    public ShopPanel(Profile mainUser) {
        this.user = mainUser;
        setLayout(new GridLayout(4, 1));
        Dimension size = new Dimension();
        size.width = WIDTH;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Shop"));
        setVisible(true);

        initializeStats();
        addItems();
    }

    // MODIFIES: this
    // EFFECTS : initializes JPanel that displays user's name and current balance
    private void initializeStats() {
        JPanel stats = new JPanel();
        coins = new JTextArea("Hi " + user.getName() + "\nYou have: " + user.getBalance() + " coins");
        stats.add(coins);
        add(stats);
    }

    /**
     * Helper to add buttons for buying Food.
     */
    private void addItems() {

    }
}
