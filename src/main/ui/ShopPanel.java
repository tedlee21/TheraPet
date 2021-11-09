package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import model.*;

// Represents a JPanel displaying a user's name and balance, along with the shop to buy food from
public class ShopPanel extends JPanel {
    protected static final Integer WIDTH = 160;       //Width of the main panel
    private PetAppGUI main;                         //main GUI for access to other panels and user
    protected Profile user;                         //main user profile
    protected JTextArea coins;                      //JTextArea displaying user's name and balance
    protected BagPanel slots;                       //JPanel to access bag slot buttons

    // REQUIRES: mainUser must be instantiated
    // EFFECTS : user is set to bagPanel.user; layout is set to GridLayout and preferred size is set;
    //           Border is titled to reflect panel function; Stats panel is added, and Shop items
    //           are added
    public ShopPanel(PetAppGUI mainImport) {
        this.main = mainImport;
        this.user = main.user;
        this.slots = main.bagPanel;
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
        addFoodButton(Food.COOKIE);
        addFoodButton(Food.ICE_CREAM);
        addFoodButton(Food.PIZZA);
    }

    // REQUIRES: food is instantiated
    // EFFECTS : adds button for given food with food type and price
    private void addFoodButton(Food food) {
        JButton foodButton = new JButton(new BuyFoodAction(food));
        foodButton.setLayout(new FlowLayout(FlowLayout.TRAILING));

        try {
            Image img = ImageIO.read(new File("resources/" + main.foodToString(food.getType()) + ".png"));
            Image scaledImg = img.getScaledInstance(70,70, Image.SCALE_REPLICATE);
            foodButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        foodButton.setText("   " + food.getPrice() + " coins");
        foodButton.setHorizontalAlignment(SwingConstants.LEFT);

        add(foodButton);
    }

    /**
     * Represents action to be taken when user wants to buy
     * given food
     */
    private class BuyFoodAction extends AbstractAction {
        private Food food;

        BuyFoodAction(Food type) {
            food = type;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (food.getPrice() <= user.getBalance()) {
                user.subBalance(food.getPrice());
                user.addFood(food, 1);
                coins.setText("Hi " + user.getName() + "\nYou have: " + user.getBalance() + " coins");
                updateSlot(food);
            } else {
                JOptionPane.showMessageDialog(null,
                        "You do not have enough coins!",
                        "Insufficient Balance",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: slotButton
    // EFFECTS : updates slotButton in BagPanel with current quantity of food in the slot
    private void updateSlot(Food food) {
        int index = user.findFood(food);
        for (int i = 0; i < Profile.MAX_SIZE; i++) {
            if (index == i) {
                updateMapping(getSlotButton(i), food);
                getSlotButton(i).setText("x " + user.readSlot(i).getQuantity());
                break;
            }
        }
    }

    // MODIFIES: button
    // EFFECTS : updates the mapping of Action and Icon of button
    private void updateMapping(JButton button, Food food) {
        button.setAction(new FeedFoodAction(food));
        try {
            Image img = ImageIO.read(new File("resources/" + main.foodToString(food.getType()) + ".png"));
            Image scaledImg = img.getScaledInstance(60,60, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**                                                             //following classes and methods
     * Represents action to be taken when user wants to feed        //duplicated from BagPanel
     * selected food                                                //to retain desired behavior
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
                slots.textLog.textLog.setText("Thanks for the "
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
            return slots.slotButton1;
        } else if (buttonIndex == 1) {
            return slots.slotButton2;
        } else if (buttonIndex == 2) {
            return slots.slotButton3;
        }
        return slots.slotButton4;
    }

    // MODIFIES: button
    // EFFECTS : removes text and Icon of button
    private void emptySlot(JButton button) {
        button.setText("");
        button.setIcon(null);
        button.setAction(null);
    }
}
