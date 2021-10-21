package model;

import org.json.JSONObject;
import persistence.Writable;

//represents food items user can purchase for
// a set price, store in Inventory, and feed to a Pet
public class Food implements Writable {
    public static final Food COOKIE = new Food(FoodType.COOKIE, 2);
    public static final Food ICE_CREAM = new Food(FoodType.ICE_CREAM, 5);
    public static final Food MAC_N_CHEESE = new Food(FoodType.MAC_AND_CHEESE, 10);
    private FoodType type;      //type of the Food ie, Apple
    private int price;        //price of the food for user to purchase


    /*
     * REQUIRES: typeFood has a non-zero length
     * EFFECTS : type of Food is set to typeFood;
     *           if foodPrice >= 0 then price of Food is set to
     *           foodPrice, otherwise price is zero
     */
    public Food(FoodType typeFood, int foodPrice) {
        type = typeFood;

        if (foodPrice >= 0) {
            price = foodPrice;
        } else {
            price = 0;
        }
    }

    public FoodType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("price", price);
        return json;
    }
}
