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
        return b;
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
