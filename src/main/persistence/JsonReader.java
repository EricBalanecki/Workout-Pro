package persistence;

import model.Day;
import model.Exercise;
import model.Set;
import model.WorkoutPlan;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code inspired by JsonSerializationDemo
// Represents a reader that reads Days from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads day from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public WorkoutPlan read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutPlan(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses WorkoutPlan from JSON object and returns it
    private WorkoutPlan parseWorkoutPlan(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutPlan wp = new WorkoutPlan(name);
        addDays(wp, jsonObject);
        return wp;
    }

    private void addDays(WorkoutPlan wp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json: jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(wp, nextDay);
        }
    }

    // EFFECTS: parses day from JSON object and returns it
    private void addDay(WorkoutPlan wp, JSONObject jsonObject) {
        String name = jsonObject.getString("day");
        Day day = new Day(name);
        addExercises(day, jsonObject);
        wp.addDay(day);
    }

    // MODIFIES: day
    // EFFECTS: parses exercises from JSON object and adds them to the day
    private void addExercises(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(day, nextExercise);
        }
    }

    // MODIFIES: day
    // EFFECTS: parses exercise from JSON object and adds it to the day
    private void addExercise(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String muscleGroup = jsonObject.getString("muscleGroup");
        Exercise exercise = new Exercise(name, muscleGroup);
        addSets(exercise, jsonObject);
        day.addExercise(exercise);
    }

    // MODIFIES: Exercise
    // EFFECTS: parses sets from JSON object and adds them to the exercise
    private void addSets(Exercise exercise, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sets");
        for (Object json : jsonArray) {
            JSONObject nextSet = (JSONObject) json;
            addSet(exercise, nextSet);
        }
    }

    // MODIFIES: Exercise
    // EFFECTS: parses set from JSON object and adds it to the exercise
    private void addSet(Exercise exercise, JSONObject jsonObject) {
        int reps = jsonObject.getInt("reps");
        int percent1RM = jsonObject.getInt("percent1RM");
        Set set = new Set(reps, percent1RM);
        exercise.addSet(set);
    }
}
