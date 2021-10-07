package model;

import java.util.ArrayList;

// represents a users inventory in which to store different Foods of varying quantities
public class Inventory {
    private ArrayList<Food> storage;        //list of distinct types of Food
    private ArrayList<Integer> quantity;    //tandem list for quantity of each Food in storage
    private static Integer MAX_SIZE = 19;   //maximum number of Food items allowed in an Inventory - 1


     /* EFFECTS : creates new user Inventory with 20 spaces
     *            to store Food
     */
    public Inventory() {
        storage = new ArrayList<>();
        quantity = new ArrayList<>();

        for (int i = 0; i < MAX_SIZE; i++) {
            storage.add(null);
            quantity.add(null);
        }
    }

    // REQUIRES: amountFood > 0
    // MODIFIES: this
    // EFFECTS : returns true if inventory is not full and adds the amountFood of typeFood to
    //           preexisting quantity of the same Food, or if typeFood does not preexist in storage,
    //           sets amountFood of typeFood to the first empty spot in Inventory.
    //           returns false otherwise.
    public boolean addFood(Food typeFood, int amountFood) {
        if (storage.contains(typeFood)) {
            int i = storage.indexOf(typeFood);
            quantity.set(i, (quantity.get(i) + amountFood));
            return true;
        } else if (storage.get(MAX_SIZE) != null) {
            for (int c = 0; c < MAX_SIZE; c++) {
                if (storage.get(c) == null) {
                    storage.set(c, typeFood);
                    return true;
                }
            }
        }
        return false;
    }

    public void removeFood(Food typeFood, int amountFood) {

    }






}
