package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a slot in a user's inventory
// Data persistence implementations based off JsonSerializationDemo file
public class Slot implements Writable {
    private Food food;           //type of food stored in the slot
    private int quantity;        //amount of stored food in the slot

    // EFFECTS : sets food as f, and quantity as n; if n is negative, quantity is set to 0;
    // a quantity of 0 is interpreted as an empty slot
    public Slot(Food foodType, int amount) {
        food = foodType;

        if (amount < 0) {
            quantity = 0;
        } else {
            quantity = amount;
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

    // REQUIRES: n >= 0
    // MODIFIES: this
    // EFFECTS : adds n to quantity
    public void addQuantity(int n) {
        quantity += n;
    }

    // REQUIRES: n >= 0 && n-quantity > 0
    // MODIFIES: this
    // EFFECTS : subtracts n from quantity
    public void subQuantity(int n) {
        quantity -= n;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food", food.toJson());
        json.put("quantity", quantity);
        return json;
    }
}
