package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food food;

    @Test
    public void makeFoodZero() {
        food = new Food("Free Apple", -1);
        assertEquals(0, food.getPrice());
    }

    @Test
    public void testGetFood() {
        food = new Food("Apple", 5);
        assertEquals("Apple", food.getType());
    }

    @Test
    public void testGetFoodNumbers() {
        food = new Food("25 Oranges", 10);
        assertEquals("25 Oranges", food.getType());
    }

    @Test
    public void testGetPrice() {
        food = new Food("Apple", 5);
        assertEquals(5, food.getPrice());
    }

    @Test
    public void testGetPriceZero() {
        food = new Food("FreeApple", 0);
        assertEquals(0, food.getPrice());
    }

    @Test
    public void testGetPriceLot() {
        food = new Food("ExpensiveApple", 1000);
        assertEquals(1000, food.getPrice());
    }
}
