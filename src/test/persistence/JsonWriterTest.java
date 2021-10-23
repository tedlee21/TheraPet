package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class heavily based off of JsonSerializationDemo file from CPSC 210
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Profile pf = new Profile("User", 0, "test", PetType.DOG);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Profile pf = new Profile("User", 0, "Dog", PetType.DOG);
            pf.setDebt(0);
            JsonWriter writer = new JsonWriter("./data/testWriterBrokeProfile.json");
            writer.open();
            writer.write(pf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBrokeProfile.json");
            pf = reader.read();
            assertEquals("User", pf.getName());
            assertEquals(0, pf.getBalance());
            assertEquals(0, pf.getDebt());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Profile pf = new Profile("User", 20, "Dog", PetType.DOG);
            pf.addFood(Food.COOKIE, 5);
            pf.addFood(Food.ICE_CREAM, 2);
            pf.addFood(Food.MAC_N_CHEESE, 1);
            pf.setDebt(20);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralProfile.json");
            writer.open();
            writer.write(pf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralProfile.json");
            pf = reader.read();
            assertEquals("User", pf.getName());
            assertEquals(20, pf.getBalance());
            assertEquals(PetType.DOG, pf.getPetType());
            assertEquals("Dog", pf.getPetName());
            assertEquals(20, pf.getDebt());
            List<Slot> storage = pf.getSlots();
            assertEquals(5, storage.size());
            checkSlot(FoodType.COOKIE, 2, 5, storage.get(0));
            checkSlot(FoodType.ICE_CREAM, 5, 2, storage.get(1));
            checkSlot(FoodType.MAC_AND_CHEESE, 10, 1, storage.get(2));
            checkSlot(FoodType.EMPTY, 0, 0, storage.get(3));
            checkSlot(FoodType.EMPTY, 0, 0, storage.get(4));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
