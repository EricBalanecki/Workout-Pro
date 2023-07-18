package ui;


import model.Day;

// Workout Planner application
public class WorkoutPlannerApp {
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private Day sunday;

    // runs the workout planner app
    public WorkoutPlannerApp() {
        runWorkoutPlanner();
    }

    // Modifies: this
    // Effects: processes the user inputs
    public void runWorkoutPlanner() {
        days();

    }

    private void days() {
        monday = new Day("Monday");
        tuesday = new Day("Tuesday");
        wednesday = new Day("Wednesday");
        thursday = new Day("Thursday");
        friday = new Day("Friday");
        saturday = new Day("Saturday");
        sunday = new Day("Sunday");
    }
}
