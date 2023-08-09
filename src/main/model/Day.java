package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a day of the week having a name and a list of exercises for that day
public class Day implements Writable {
    private String name;
    private List<Exercise> exercises;

    // REQUIRES: day has a non-zero length
    // EFFECTS: constructs a day with the name set to the given day
    //          and an empty list of exercises
    public Day(String day) {
        this.name = day;
        this.exercises = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an exercise to the list of exercises, returns true
    //          unless an exercise exists with that name, then returns false
    public boolean addExercise(Exercise e) {
        boolean b = true;
        if (exercises.size() > 0) {
            for (Exercise exercise : exercises) {
                if (exercise.getName().equals(e.getName())) {
                    b = false;
                }
            }
        }
        if (b) {
            exercises.add(e);
        }
        EventLog.getInstance().logEvent(new Event("Exercise " + e.getName() + " added to " + name));
        return b;
    }

    // REQUIRES: index given to be within the index of list exercises
    // MODIFIES: this
    // EFFECTS removes the given exercise index from list of exercises
    public void removeExerciseInd(int ind) {
        exercises.remove(ind);
    }

    // MODIFIES: this
    // EFFECTS removes the given exercise list of exercises
    public void removeExercise(Exercise e) {
        exercises.remove(e);
        EventLog.getInstance().logEvent(new Event("Exercise " + e.getName() + " removed from " + name));
    }

    // EFFECTS: returns the name of the day
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of all the exercises
    public List<Exercise> getExercises() {
        return exercises;
    }

    // EFFECTS: returns Day as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", name);
        json.put("exercises", exercisesToJson());
        return json;
    }

    // EFFECTS: returns exercises in the day as a Json Array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Exercise t : exercises) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
