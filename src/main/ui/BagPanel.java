package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// Represents a JPanel that will display user's inventory from which they may feed the pet
public class BagPanel extends JPanel {
    protected static final Integer WIDTH = 230;     //Width of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels
    protected JButton slotButton1;
    protected JButton slotButton2;
    protected JButton slotButton3;
    protected JButton slotButton4;
    protected DialoguePanel textLog;                //DialoguePanel to access pet text field


    // EFFECTS: main is set to mainImport; user is set to main.user; textLog is set to main.textPanel;
    //          layout is set to GridLayout and preferred size is set;
    //          textLog is set to main.textPanel; Border is titled to reflect
    //          panel function; buttons are added to panel
    public BagPanel(PetAppGUI mainImport) {
        this.main = mainImport;
        this.textLog = main.textPanel;
        setLayout(new GridLayout(2, 3));
        Dimension size = new Dimension();
        size.width = WIDTH;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(143, 111, 79), 2),
                "Shop",
                0, 0,
                new Font(Font.SERIF, Font.BOLD,14), Color.white));
        setBackground(new Color(180, 139, 98));
        setOpaque(true);
        setVisible(true);

        addPanelButtons();
    }

    // MODIFIES: this
    // EFFECTS : makes and adds buttons representing each slot of user's storage
    private void addPanelButtons() {
        slotButton1 = makeSlotButton(main.user.readSlot(0).getFood(), main.user.readSlot(0).getQuantity());
        slotButton2 = makeSlotButton(main.user.readSlot(1).getFood(), main.user.readSlot(1).getQuantity());
        slotButton3 = makeSlotButton(main.user.readSlot(2).getFood(), main.user.readSlot(2).getQuantity());
        slotButton4 = makeSlotButton(main.user.readSlot(3).getFood(), main.user.readSlot(3).getQuantity());
        add(slotButton1);
        add(slotButton2);
        add(slotButton3);
        add(slotButton4);
    }

    // REQUIRES: food is instantiated
    // EFFECTS : returns new button for given food with food type and price
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

        // EFFECTS : Constructor sets food to type
        FeedFoodAction(Food type) {
            food = type;
        }

        // MODIFIES: this
        // EFFECTS : if slot button contains food to feed, feeds 1 of the food
        //           in slot to pet, and updates user storage, slot button, and
        //           displays feedback on text log;
        //           else does nothing
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (food.getType() != FoodType.EMPTY) {
                main.user.removeFood(food, 1);
                main.updatePetIconFood(food);
                textLog.textLog.setText("Thanks for the "
                        + main.foodToString(food.getType())
                        + ", "
                        + main.user.getName() + "!! Yum, Yum!");
                if (main.user.findFood(food) != -1) {
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
        int index = main.user.findFood(food);
        for (int i = 0; i < Profile.MAX_SIZE; i++) {
            if (index == i) {
                getSlotButton(i).setText("x " + main.user.readSlot(i).getQuantity());
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS : updates each empty slot to reflect its current contents
    public void updateEmptySlots() {
        for (int i = 0; i < Profile.MAX_SIZE; i++) {
            if (main.user.readSlot(i).getFood().getType() == FoodType.EMPTY) {
                emptySlot(getSlotButton(i));
            }
        }
    }

    // EFFECTS : returns corresponding slotButton given index number
    public JButton getSlotButton(int buttonIndex) {
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
    // EFFECTS : removes text, Icon and Action of button
    private void emptySlot(JButton button) {
        button.setText("");
        button.setIcon(null);
        button.setAction(null);
    }
}
