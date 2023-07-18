package model;

public class Set {
    private int reps;
    private int percent1RM;

    // REQUIRES: reps to be >= 1 and p1RM to be >= 1 and <= 100
    // EFFECTS: # of reps is set to reps and percent1RM is set to p1RM
    public Set(int reps, int p1RM) {
        this.reps = reps;
        this.percent1RM = p1RM;
    }

    // EFFECTS: returns the number of reps in the set
    public int getReps() {
        return this.reps;
    }

    // EFFECTS: returns the percent of your 1RM you should lift
    public int getPercent1RM() {
        return percent1RM;
    }
}
