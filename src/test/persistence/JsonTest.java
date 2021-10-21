package persistence;

import model.FoodType;
import model.Slot;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test class heavily based off of JsonSerializationDemo file from CPSC 210
public class JsonTest {
    protected void checkSlot(FoodType foodType, Integer foodPrice, Integer quantity, Slot slot) {
        assertEquals(quantity, slot.getQuantity());
        assertEquals(foodType, slot.getFood().getType());
        assertEquals(foodPrice, slot.getFood().getPrice());
    }
}
