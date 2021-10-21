package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//represents user's profile with name and balance
//Data persistence implementations based off JsonSerializationDemo file
public class Profile implements Writable {
    private String name;     //users name
    private int balance;     //current balance of user


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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("balance", balance);
        return json;
    }






}
