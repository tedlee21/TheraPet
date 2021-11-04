package ui;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel that will display user's inventory from which they may feed the pet from
public class BagPanel extends JPanel {
    protected static final Integer WIDTH = 250;     //Width of the main panel

    // EFFECTS: layout is set to GridLayout and preferred size is set; Border is titled to reflect
    //          panel function; buttons are added to panel
    public BagPanel() {
        setLayout(new GridLayout(2, 3));
        Dimension size = new Dimension();
        size.width = WIDTH;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Bag"));
        setVisible(true);

        addPanelButtons();
    }

    /**
     * Helper to add buttons for storage slots.
     */
    private void addPanelButtons() {
        add(new JButton());
        add(new JButton());
        add(new JButton());
        add(new JButton());
        add(new JButton());
        add(new JButton());
    }
}
