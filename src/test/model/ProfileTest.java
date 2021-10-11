package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile user;

    @Test
    public void makeUserZero() {
        user = new Profile("Joe", -1);
        assertEquals(0, user.getBalance());
    }

    @Test
    public void testGetName() {
        user = new Profile("Bob", 5);
        assertEquals("Bob", user.getName());
    }

    @Test
    public void testGetNameNumbers() {
        user = new Profile("25th Bob", 5);
        assertEquals("25th Bob", user.getName());
    }

    @Test
    public void testGetBalance() {
        user = new Profile("Joe", 5);
        assertEquals(5, user.getBalance());
    }

    @Test
    public void testGetBalanceZero() {
        user = new Profile("Broke Joe", 0);
        assertEquals(0, user.getBalance());
    }

    @Test
    public void testGetBalanceLot() {
        user = new Profile("Stacked Joe", 10000);
        assertEquals(10000, user.getBalance());
    }

    @Test
    public void testAddBal() {
        user = new Profile("Joe", 5);
        user.addBalance(10);
        assertEquals(15, user.getBalance());
    }

    @Test
    public void testAddBalNone() {
        user = new Profile("Joe", 5);
        user.addBalance(0);
        assertEquals(5, user.getBalance());
    }

    @Test
    public void testSubBal() {
        user = new Profile("Joe", 10);
        user.subBalance(5);
        assertEquals(5, user.getBalance());
    }

    @Test
    public void testSubBalNone() {
        user = new Profile("Joe", 10);
        user.subBalance(0);
        assertEquals(10, user.getBalance());
    }

}
