package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Exercise having a name, targeted muscle group, and a list of sets
public class Exercise implements Writable {
    private String name;
    private String muscleGroup;
    private List<Set> sets;

    // REQUIRES: nm and muscle have a non zero-length
    // EFFECTS: constructs an exercise with the name set to nm,
    //          muscleGroup set to muscle, and an empty list of sets
    public Exercise(String nm, String muscle) {
        this.name = nm;
        this.muscleGroup = muscle;
        this.sets = new ArrayList<>();
    }

    // MODIFIES this,
    // EFFECTS: adds the given set to the sets list
    public void addSet(Set set) {
        sets.add(set);
    }

    // MODIFIES: this
    // EFFECTS: sets the sets list to empty
    public void clearSets() {
        this.sets = new ArrayList<>();
    }

    // EFFECTS: returns the name of the exercise
    public String getName() {
        return name;
    }

    // EFFECTS: returns the muscle group targeted by the exercise
    public String getMuscleGroup() {
        return muscleGroup;
    }

    // EFFECTS: returns a list of all the sets
    public List<Set> getSets() {
        return sets;
    }

    // EFFECTS: returns exercise as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("muscleGroup", muscleGroup);
        json.put("sets", setToJson());
        return json;
    }

    // EFFECTS: returns the sets in an exercise as a JSON array
    private JSONArray setToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Set s : sets) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
