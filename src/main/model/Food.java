package model;

//represents food items user can purchase for
// a set price, store in Inventory, and feed to a Pet
public class Food {
    private String type; //type of the Food ie, Apple
    private int price;   //price of the food for user to purchase

    /*
     * REQUIRES: typeFood has a non-zero length
     * EFFECTS : type of Food is set to typeFood;
     *           if foodPrice >= 0 then price of Food is set to
     *           foodPrice, otherwise price is zero
     */
    public Food(String typeFood, int foodPrice) {
        type = typeFood;

        if (foodPrice >= 0) {
            price = foodPrice;
        } else {
            price = 0;
        }
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }


}
