package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a workout plan containing a name and a list of days
public class WorkoutPlan implements Writable {
    private List<Day> daysOfWeek;
    private String name;

    // EFFECTS: Constructs a workout plan with an empty list of days and name set to name
    public WorkoutPlan(String name) {
        this.name = name;
        this.daysOfWeek = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds day unless day with that name already exists
    public void addDay(Day day) {
        List names = new ArrayList<Day>();
        for (Day d: daysOfWeek) {
            names.add(d.getName());
        }
        if (!names.contains(day.getName())) {
            daysOfWeek.add(day);
        }
    }

    // EFFECTS: returns name of workout planner
    public String getName() {
        return name;
    }

    // EFFECTS: returns daysOfWeek
    public List<Day> getDaysOfWeek() {
        return daysOfWeek;
    }

    // EFFECTS: returns Workout Plan as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("days", daysToJson());
        return json;
    }

    // EFFECTS: returns exercises in the day as a Json Array
    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Day d : daysOfWeek) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }
}
