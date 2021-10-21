package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {
    private Slot slot;
    private Slot slot1;

    @BeforeEach
    public void setUpSlot() {
        slot = new Slot(Food.COOKIE, 1);
    }

    @Test
    public void testInvalidQuantity() {
        slot1 = new Slot(Food.ICE_CREAM, -1);
        assertEquals(0, slot1.getQuantity());
    }

    @Test
    public void testReplaceSlot() {
        slot1 = new Slot(Food.ICE_CREAM, 2);
        assertEquals(Food.COOKIE, slot.getFood());
        assertEquals(1, slot.getQuantity());

        slot.setSlot(slot1);
        assertEquals(Food.ICE_CREAM, slot.getFood());
        assertEquals(2, slot.getQuantity());
    }

    @Test
    public void testAddQuantity() {
        assertEquals(1, slot.getQuantity());
        slot.addQuantity(1);
        assertEquals(2, slot.getQuantity());
        slot.addQuantity(40);
        assertEquals(42, slot.getQuantity());
    }

    @Test
    public void testSubQuantity() {
        slot.addQuantity(10);
        assertEquals(11, slot.getQuantity());
        slot.subQuantity(5);
        assertEquals(6, slot.getQuantity());
    }
}
