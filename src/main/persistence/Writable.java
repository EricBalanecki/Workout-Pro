package persistence;

import org.json.JSONObject;

// Code inspired by JsonSerializationDemo
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
