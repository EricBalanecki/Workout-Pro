package model;

import java.util.ArrayList;
import java.util.List;

public class Day {
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
    // EFFECTS: adds an exercise to the list of exercises
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    // REQUIRES: index given to be within the index of list exercises
    // MODIFIES: this
    // EFFECTS removes the given exercise index from list of exercises
    public void removeExercise(int ind) {
        exercises.remove(ind);
    }

    // EFFECTS: returns the name of the day
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of all the exercises
    public List<Exercise> getExercises() {
        return exercises;
    }
}
