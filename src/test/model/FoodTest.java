package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food food;

    @Test
    public void makeFoodZero() {
        food = new Food(FoodType.COOKIE, -1);
        assertEquals(0, food.getPrice());
    }

    @Test
    public void testGetFood() {
        food = new Food(FoodType.COOKIE, 5);
        assertEquals(FoodType.COOKIE, food.getType());
    }

    @Test
    public void testGetPrice() {
        food = new Food(FoodType.COOKIE, 5);
        assertEquals(5, food.getPrice());
    }

    @Test
    public void testGetPriceZero() {
        food = new Food(FoodType.COOKIE, 0);
        assertEquals(0, food.getPrice());
    }

    @Test
    public void testGetPriceLot() {
        food = new Food(FoodType.MAC_AND_CHEESE, 1000);
        assertEquals(1000, food.getPrice());
    }
}
