package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads profile from JSON data stored in file
// Class heavily based off JsonSerializationDemo file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Profile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses profile from JSON object and returns it
    private Profile parseProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String petName = jsonObject.getString("petName");
        PetType petType = PetType.valueOf(jsonObject.getString("petType"));
        Profile pf = new Profile(name, jsonObject.getInt("balance"), petName, petType);
        addStorage(pf, jsonObject);
        return pf;
    }

    // MODIFIES: pf
    // EFFECTS: parses storage from JSON object and adds them to profile
    private void addStorage(Profile pf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("storage");
        int count = 0;
        for (Object json : jsonArray) {
            JSONObject nextSlot = (JSONObject) json;
            addSlot(pf, nextSlot, count);
            count++;
        }
    }

    // MODIFIES: pf
    // EFFECTS: parses slot from JSON object and adds it to profile
    private void addSlot(Profile pf, JSONObject jsonObject, int count) {
        JSONObject f = jsonObject.getJSONObject("food");
        Integer amount = jsonObject.getInt("quantity");
        addFood(pf, f, amount, count);
    }

    // MODIFIES: pf
    // EFFECTS : parses food from JSON object and adds it to profile
    private void addFood(Profile pf, JSONObject jsonObject, int amount, int count) {
        FoodType type = FoodType.valueOf(jsonObject.getString("type"));
        Integer price = jsonObject.getInt("price");

        Food food = new Food(type, price);

        if (type.equals(FoodType.COOKIE)) {                 //this section of code sets pre-existing food type in
            food = Food.COOKIE;                             //JSON save as already defined instances of the same type;
        } else if (type.equals(FoodType.ICE_CREAM)) {       //this prevents same foods treated as separate objects
            food = Food.ICE_CREAM;
        } else if (type.equals(FoodType.MAC_AND_CHEESE)) {
            food = Food.MAC_N_CHEESE;
        }
        Slot slot = new Slot(food, amount);
        pf.setSlot(slot, count);
    }
}

