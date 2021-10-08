package model;

import java.util.ArrayList;

// represents a users inventory with slots in which to store different Food of varying quantities
public class Inventory {
    private ArrayList<Food> storage;        //list of distinct types of Food
    private ArrayList<Integer> quantity;    //tandem list for quantity of each Food in storage

    public static final Integer MAX_SIZE = 10;   //maximum number slots allowed in an Inventory - 1


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

    // REQUIRES: valid list index number
    // EFFECTS : reads the slot position of given index, and returns a statement the Food
    //           it contains; returns null if there is nothing in the slot.
    public Food readFood(Integer index) {
        if (storage.get(index) != null) {
            return storage.get(index);
        }
        return null;
    }

    public int readAmount(Integer index) {
        if (quantity.get(index) != null) {
            return quantity.get(index);
        }
        return 0;
    }

    // REQUIRES: amountFood > 0
    // MODIFIES: this
    // EFFECTS : returns true if inventory is not full and adds the amountFood of typeFood to
    //           preexisting quantity of the same Food, or if typeFood does not preexist in storage,
    //           sets amountFood of typeFood to the first empty spot in Inventory;
    //           returns false otherwise.
    public boolean addFood(Food typeFood, int amountFood) {
        if (storage.contains(typeFood)) {
            int i = storage.indexOf(typeFood);
            quantity.set(i, (quantity.get(i) + amountFood));
            return true;
        } else if (storage.contains(null)) {
            for (int c = 0; c < MAX_SIZE; c++) {
                if (storage.get(c) == null) {
                    storage.set(c, typeFood);
                    quantity.set(c, amountFood);
                    return true;
                }
            }
        }
        return false;
    }

    // REQUIRES: amountFood > 0
    // MODIFIES: this
    // EFFECTS : returns true if Inventory contains typeFood and then removes amountFood of
    //           typeFood from it; if amountFood > pre-existing amount of typeFood, clears typeFood
    //           from inventory; returns false otherwise.
    public boolean removeFood(Food typeFood, int amountFood) {
        if (storage.contains(typeFood)) {
            int i = storage.indexOf(typeFood);
            int count = quantity.get(i);
            if ((count - amountFood) > 0) {
                quantity.set(i, count - amountFood);
            } else {
                storage.set(i, null);
                quantity.set(i, null);
            }
            return true;
//            for (int c = amountFood; c > 0; c--) {
//                if (quantity.get(i) > 0) {
//                    quantity.set(i, quantity.get(i) - 1);
//                } else {
//                    c = 0;
//                }
//            }
        }   else {
            return false;
        }
    }






}
