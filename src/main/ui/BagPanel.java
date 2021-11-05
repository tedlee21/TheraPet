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
    protected static final Integer WIDTH = 250;     //Width of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user
    protected Profile user;                         //main user profile
    protected JButton slotButton1;
    protected JButton slotButton2;
    protected JButton slotButton3;
    protected JButton slotButton4;
    protected JButton slotButton5;
    protected JButton slotButton6;
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
        slotButton5 = makeSlotButton(user.readSlot(4).getFood(), user.readSlot(4).getQuantity());
        slotButton6 = makeSlotButton(user.readSlot(5).getFood(), user.readSlot(5).getQuantity());
        add(slotButton1);
        add(slotButton2);
        add(slotButton3);
        add(slotButton4);
        add(slotButton5);
        add(slotButton6);
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
//            } else {
//                JOptionPane.showMessageDialog(null,
//                        "should not happen",
//                        "Insufficient Balance",
//                        JOptionPane.ERROR_MESSAGE);
//            }
        }
    }

    // REQUIRES: food is stored in a slot
    // MODIFIES: slotButton
    // EFFECTS : updates slotButton in BagPanel with current quantity of food in the slot
    private void updateSlot(Food food) {
        int index = user.findFood(food);
        if (index == 0) {
            slotButton1.setText("x " + user.readSlot(0).getQuantity());
        } else if (index == 1) {
            slotButton2.setText("x " + user.readSlot(1).getQuantity());
        } else if (index == 2) {
            slotButton3.setText("x " + user.readSlot(2).getQuantity());
        } else if (index == 3) {
            slotButton4.setText("x " + user.readSlot(3).getQuantity());
        } else if (index == 4) {
            slotButton5.setText("x " + user.readSlot(4).getQuantity());
        } else {
            slotButton6.setText("x " + user.readSlot(5).getQuantity());
        }
    }

    // MODIFIES: this
    // EFFECTS : updates each empty slot to reflect its current contents
    private void updateEmptySlots() {
        if (user.readSlot(0).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton1);
        }
        if (user.readSlot(1).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton2);
        }
        if (user.readSlot(2).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton3);
        }
        if (user.readSlot(3).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton4);
        }
        if (user.readSlot(4).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton5);
        }
        if (user.readSlot(5).getFood().getType() == FoodType.EMPTY) {
            emptySlot(slotButton6);
        }
    }

    // MODIFIES: button
    // EFFECTS : removes text and Icon of button
    private void emptySlot(JButton button) {
        button.setText("");
        button.setIcon(null);
        button.setAction(null);
    }
}
