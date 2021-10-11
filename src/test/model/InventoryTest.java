package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {
    private Inventory bag;
    private Food food;
    private Food food1;

    @BeforeEach
    public void runBefore() {
        bag = new Inventory();
        food = new Food("Apple", 5);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bag.isEmpty());
    }

    @Test
    public void testReadSlot() {
        bag.addFood(food, 1);
        assertEquals(food, bag.readFood(0));
        assertEquals(1, bag.readAmount(0));

        assertEquals(null, bag.readFood(1));
        assertEquals(0, bag.readAmount(1));
    }

    @Test
    public void testReadSlotMultiple() {
        food1 = new Food("Orange", 2);
        bag.addFood(food, 2);
        bag.addFood(food1, 1);

        assertEquals(food, bag.readFood(0));
        assertEquals(2, bag.readAmount(0));
        assertEquals(food1, bag.readFood(1));
        assertEquals(1, bag.readAmount(1));
    }

    @Test
    public void testAddEmpty() {
        assertTrue(bag.isEmpty());

        boolean bool = bag.addFood(food, 1);
        assertTrue(bool);
        assertEquals(food, bag.readFood(0));
        assertEquals(1, bag.readAmount(0));
        assertFalse(bag.isEmpty());
    }

    @Test
    public void testAddNotFull() {
        food1 = new Food("Orange", 1);
        bag.addFood(food, 1);
        assertFalse(bag.isEmpty());

        boolean bool1 = bag.addFood(food1, 1);
        assertTrue(bool1);
        assertEquals(food1, bag.readFood(1));
        assertEquals(1, bag.readAmount(1));
    }

    @Test
    public void testAddFull() {
        for (int i = 0; i < Inventory.MAX_SIZE; i++) {
            bag.addFood(new Food(("Apple " + i), 1), 1);
        }

        food1 = new Food("Orange", 1);
        boolean bool1 = bag.addFood(food1, 1);
        assertFalse(bool1);
    }

    @Test
    public void testAddSame() {
        for (int i = 0; i < Inventory.MAX_SIZE; i++) {
            bag.addFood(food, 1);
        }

        food1 = new Food("Orange", 1);
        boolean bool1 = bag.addFood(food1, 1);
        assertTrue(bool1);
    }

    @Test
    public void testRemoveEmpty() {
        assertTrue(bag.isEmpty());
        assertFalse(bag.removeFood(food, 1));
    }

    @Test
    public void testRemoveClearSlot() {
        bag.addFood(food, 1);
        assertFalse(bag.isEmpty());

        assertTrue(bag.removeFood(food, 1));
        assertTrue(bag.isEmpty());
    }

    @Test
    public void testRemoveSubAmount() {
        bag.addFood(food, 2);
        assertFalse(bag.isEmpty());

        assertTrue(bag.removeFood(food, 1));
        assertFalse(bag.isEmpty());
        assertTrue(bag.removeFood(food, 1));
        assertTrue(bag.isEmpty());
    }

}