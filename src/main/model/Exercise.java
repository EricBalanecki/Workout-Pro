package model;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
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
}
