package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// Represents a JPanel that will display user's inventory from which they may feed the pet from
public class BagPanel extends JPanel {
    protected static final Integer WIDTH = 230;     //Width of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user
    protected Profile user;                         //main user profile
    protected JButton slotButton1;
    protected JButton slotButton2;
    protected JButton slotButton3;
    protected JButton slotButton4;
    protected DialoguePanel textLog;                //DialoguePanel to access pet text field


    // EFFECTS: main is set to mainImport; user is set to main.user;
    //          layout is set to GridLayout and preferred size is set;
    //          textLog is set to main.textPanel; Border is titled to reflect
    //          panel function; buttons are added to panel
    public BagPanel(PetAppGUI mainImport) {
        this.main = mainImport;
        this.user = main.user;
        this.textLog = main.textPanel;
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
        slotButton1 = makeSlotButton(user.readSlot(0).getFood(), user.readSlot(0).getQuantity());
        slotButton2 = makeSlotButton(user.readSlot(1).getFood(), user.readSlot(1).getQuantity());
        slotButton3 = makeSlotButton(user.readSlot(2).getFood(), user.readSlot(2).getQuantity());
        slotButton4 = makeSlotButton(user.readSlot(3).getFood(), user.readSlot(3).getQuantity());
        add(slotButton1);
        add(slotButton2);
        add(slotButton3);
        add(slotButton4);
    }

    // REQUIRES: food is instantiated
    // EFFECTS : returns button for given food with food type and price
    private JButton makeSlotButton(Food food, int amount) {
        JButton slotButton = new JButton(new FeedFoodAction(food));
        slotButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        slotButton.setHorizontalTextPosition(SwingConstants.CENTER);

        if (food.getType() != FoodType.EMPTY) {
            try {
                Image img = ImageIO.read(new File("resources/" + main.foodToString(food.getType()) + ".png"));
                Image scaledImg = img.getScaledInstance(60,60, Image.SCALE_REPLICATE);
                slotButton.setIcon(new ImageIcon(scaledImg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            slotButton.setText("x " + amount);
        }

        return slotButton;
    }

    /**
     * Represents action to be taken when user wants to feed
     * selected food
     */
    private class FeedFoodAction extends AbstractAction {
        private Food food;

        FeedFoodAction(Food type) {
            food = type;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (food.getType() != FoodType.EMPTY) {
                user.removeFood(food, 1);
                main.updatePetIconFood(food);
                textLog.textLog.setText("Thanks for the "
                        + main.foodToString(food.getType())
                        + ", "
                        + user.getName() + "!! Yum, Yum!");
                if (user.findFood(food) != -1) {
                    updateSlot(food);
                } else {
                    updateEmptySlots();
                }
            }
        }
    }

    // REQUIRES: food is stored in a slot
    // MODIFIES: slotButton
    // EFFECTS : updates slotButton in BagPanel with current quantity of food in the slot
    private void updateSlot(Food food) {
        int index = user.findFood(food);
        for (int i = 0; i < Profile.MAX_SIZE; i++) {
            if (index == i) {
                getSlotButton(i).setText("x " + user.readSlot(i).getQuantity());
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS : updates each empty slot to reflect its current contents
    private void updateEmptySlots() {
        for (int i = 0; i < Profile.MAX_SIZE; i++) {
            if (user.readSlot(i).getFood().getType() == FoodType.EMPTY) {
                emptySlot(getSlotButton(i));
            }
        }
    }

    // EFFECTS : returns corresponding slotButton given index number
    private JButton getSlotButton(int buttonIndex) {
        if (buttonIndex == 0) {
            return slotButton1;
        } else if (buttonIndex == 1) {
            return slotButton2;
        } else if (buttonIndex == 2) {
            return slotButton3;
        }
        return slotButton4;
    }

    // MODIFIES: button
    // EFFECTS : removes text and Icon of button
    private void emptySlot(JButton button) {
        button.setText("");
        button.setIcon(null);
        button.setAction(null);
    }
}
