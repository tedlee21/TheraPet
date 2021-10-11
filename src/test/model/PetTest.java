package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    private Pet myPet;

    @BeforeEach
    public void runBefore() {
        myPet = new Pet("Rufus", "Dog");
    }

    @Test
    public void testGetName() {
        assertEquals("Rufus", myPet.getPetName());
    }

    @Test
    public void testGetNameNumbers() {
        myPet = new Pet("50 Big Mac", "Cat");
        assertEquals("50 Big Mac", myPet.getPetName());
    }

    @Test
    public void testGetType() {
        assertEquals("Dog", myPet.getPetType());
    }

    @Test
    public void testGetTypeCat() {
        myPet = new Pet("50 Big Mac", "Cat");
        assertEquals("Cat", myPet.getPetType());
    }
}