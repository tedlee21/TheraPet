package persistence;

import org.json.JSONObject;

// taken from JsonSerializationDemo file from CPSC 210
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
