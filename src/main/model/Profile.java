package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//represents user's profile with name and balance
//Data persistence implementations based off JsonSerializationDemo file
public class Profile implements Writable {
    private String name;     //users name
    private int balance;     //current balance of user
    private ArrayList<Slot> storage;            //list of distinct types of Food

    public static final Integer MAX_SIZE = 5;
    public static final Slot EMPTY_SLOT = new Slot(new Food("empty", 0), 0);

    /*
     * REQUIRES: username has a non-zero length
     * EFFECTS : name on profile is set to username;
     *           if initialBalance >= 0 then balance on account is set to    //this part of requires clause sourced
     *           initialBalance, otherwise balance is zero                   //from TellerApp
     */
    public Profile(String username, int initialBalance) {
        name = username;

        if (initialBalance >= 0) {          //if and else statement taken from TellerApp file
            balance = initialBalance;       //given in class
        } else {
            balance = 0;
        }

        storage = new ArrayList<>();

        for (int i = 0; i < MAX_SIZE; i++) {
            storage.add(EMPTY_SLOT);
        }
    }

    public String getName() {
        return name;
    }


    public int getBalance() {
        return balance;
    }

    // REQUIRES: amount >=0
    // MODIFIES: this
    // EFFECTS : adds amount to balance
    public void addBalance(int amount) {
        balance += amount;
    }

    // REQUIRES: amount >=0
    // MODIFIES: this
    // EFFECTS : subtracts amount from balance
    public void subBalance(int amount) {
        balance -= amount;
    }

    // REQUIRES: valid list index number
    // EFFECTS : reads the slot position of given index, and returns the Slot
    //           contained; returns EMPTY_SLOT if there is no food in the slot
    public Slot readSlot(Integer index) {
        if (storage.get(index).getQuantity() != 0) {
            return storage.get(index);
        }
        return EMPTY_SLOT;
    }

    // REQUIRES: amountFood > 0
    // MODIFIES: this
    // EFFECTS : returns true if storage is not full and adds the amountFood of typeFood to
    //           preexisting quantity of the same Food, or if typeFood does not preexist in storage,
    //           sets amountFood of typeFood to the first empty slot in storage;
    //           returns false otherwise
    public boolean addFood(Food typeFood, int amountFood) {
        for (Slot s : storage) {
            if (s.getFood() == typeFood) {
                s.addQuantity(amountFood);
                return true;
            }
        }
        for (int c = 0; c < MAX_SIZE; c++) {
            if (storage.get(c).getQuantity() == 0) {
                storage.set(c, new Slot(typeFood, amountFood));
                return true;
            }
        }
        return false;
    }

    // REQUIRES: amountFood > 0
    // MODIFIES: this
    // EFFECTS : returns true if storage contains typeFood and then removes amountFood of
    //           typeFood from it; if amountFood > pre-existing amount of typeFood, clears typeFood
    //           from storage; returns false otherwise
    public boolean removeFood(Food typeFood, int amountFood) {
        for (Slot s : storage) {
            if (s.getFood() == typeFood) {
                int count = s.getQuantity();
                if ((count - amountFood) > 0) {
                    s.subQuantity(amountFood);
                } else {
                    s.setSlot(EMPTY_SLOT);
                }
                return true;
            }
        }
        return false;
    }

    // EFFECTS : checks storage slots for Food, and returns false
    //           if a slot containing food is found; returns true if empty
    public boolean isEmpty() {
        for (Slot s : storage) {
            if (s.getQuantity() != 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("balance", balance);
        return json;
    }






}
