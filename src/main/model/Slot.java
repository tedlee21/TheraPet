package model;

// Represents a slot in a user's inventory.
public class Slot {
    private Food food;           //type of food stored in the slot
    private int quantity;        //amount of stored food in the slot

    // EFFECTS : sets food as f, and quantity as n; if n is negative, quantity is set to 0;
    // a quantity of 0 is interpreted as an empty slot
    public Slot(Food f, int n) {
        food = f;

        if (n < 0) {
            quantity = 0;
        } else {
            quantity = n;
        }
    }

    // MODIFIES: this
    // EFFECTS : sets this slot's values to the same as given slot
    public void setSlot(Slot s) {
        food = s.getFood();
        quantity = s.getQuantity();
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int n) {
        quantity += n;
    }

    public void subQuantity(int n) {
        quantity -= n;
    }
}
