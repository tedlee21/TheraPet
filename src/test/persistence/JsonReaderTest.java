package persistence;

import model.FoodType;
import model.PetType;
import model.Profile;
import model.Slot;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class heavily based off of JsonSerializationDemo file from CPSC 210
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Profile pf = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderBrokeProfile.json");
        try {
            Profile pf = reader.read();
            assertEquals("User", pf.getName());
            assertEquals(0, pf.getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProfile.json");
        try {
            Profile pf = reader.read();
            assertEquals("User", pf.getName());
            assertEquals(20, pf.getBalance());
            assertEquals(PetType.DOG, pf.getPetType());
            assertEquals("Dog", pf.getPetName());
            List<Slot> storage = pf.getSlots();
            assertEquals(5, storage.size());
            checkSlot(FoodType.COOKIE, 2, 5, storage.get(0));
            checkSlot(FoodType.ICE_CREAM, 5, 2, storage.get(1));
            checkSlot(FoodType.MAC_AND_CHEESE, 10, 1, storage.get(2));
            checkSlot(FoodType.EMPTY, 0, 0, storage.get(3));
            checkSlot(FoodType.EMPTY, 0, 0, storage.get(4));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
