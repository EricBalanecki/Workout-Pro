package ui;


import model.Day;
import model.Exercise;
import model.Set;
import model.WorkoutPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Code inspired by TellerApp example and JsonSerializationDemo
// Workout Planner application
public class WorkoutPlannerApp {
    private static final String JSON_FILE_PATH = "./data/workroom.json";
    private WorkoutPlan workoutPlan;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private Day sunday;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs a workout plan and runs the workout planner app
    public WorkoutPlannerApp() {
        workoutPlan = new WorkoutPlan("My Workout Planner");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_FILE_PATH);
        jsonReader = new JsonReader(JSON_FILE_PATH);
        loadWorkoutOption();
    }

    // MODIFIES: this,
    // EFFECTS: displays the option to load file and processes the user input
    public void loadWorkoutOption() {
        boolean validSelection = false;
        String command = null;

        while (!validSelection) {
            System.out.println("\nDo you want to load the saved file? (yes/no): ");
            command = input.next();
            if (command.equals("yes")) {
                validSelection = true;
                loadWorkoutPlan();
                runWorkoutPlanner();
            } else if (command.equals("no")) {
                validSelection = true;
                addDays();
                runWorkoutPlanner();
            } else {
                System.out.println("Invalid selection");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user inputs
    public void runWorkoutPlanner() {
        boolean programRunning = true;
        String command = null;

        while (programRunning) {
            mainMenu();
            command = input.next();
            if (command.equals("quit")) {
                programRunning = false;
            } else if (command.equals("plan workouts")) {
                selectDay();
            } else if (command.equals("save workouts")) {
                saveWorkoutPlan();
            } else {
                System.out.println("Invalid selection");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user inputs
    public void selectDay() {
        String command = "";
        while (!command.equals("back")) {
            dayMenu();
            command = input.next();
            command = command.toLowerCase();
            processCommand(command);
        }
    }

    // MODIFIES: this
    //EFFECTS: processes the given input by the user
    public void processCommand(String command) {
        if (command.equals("monday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(0));
        } else if (command.equals("tuesday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(1));
        } else if (command.equals("wednesday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(2));
        } else if (command.equals("thursday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(3));
        } else if (command.equals("friday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(4));
        } else if (command.equals("saturday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(5));
        } else if (command.equals("sunday")) {
            displayExercises((Day) workoutPlan.getDaysOfWeek().get(6));
        } else if (!command.equals("back")) {
            System.out.println("Invalid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the days of the week
    private void addDays() {
        monday = new Day("Monday");
        tuesday = new Day("Tuesday");
        wednesday = new Day("Wednesday");
        thursday = new Day("Thursday");
        friday = new Day("Friday");
        saturday = new Day("Saturday");
        sunday = new Day("Sunday");
        workoutPlan.addDay(monday);
        workoutPlan.addDay(tuesday);
        workoutPlan.addDay(wednesday);
        workoutPlan.addDay(thursday);
        workoutPlan.addDay(friday);
        workoutPlan.addDay(saturday);
        workoutPlan.addDay(sunday);
    }

    // EFFECTS: displays the main menu options for user
    private void mainMenu() {
        System.out.println("\nSelect a option:");
        System.out.println("\tplan workouts");
        System.out.println("\tsave workouts");
        System.out.println("\tquit");
    }

    // EFFECTS: displays the days of the week for the user
    public void dayMenu() {
        System.out.println("\nSelect day of week");
        System.out.println("\tMonday");
        System.out.println("\tTuesday");
        System.out.println("\tWednesday");
        System.out.println("\tThursday");
        System.out.println("\tFriday");
        System.out.println("\tSaturday");
        System.out.println("\tSunday");
        System.out.println("\tback");
    }

    // MODIFIES: this
    // EFFECTS: Display the exercise options in the case of the exercise list being empty
    //          and process the user input for exercise options
    public void displayExercises(Day day) {
        String command = "";

        while (!command.equals("back")) {
            if (day.getExercises().size() == 0) {
                System.out.println("\nNo exercises added for " + day.getName());
                System.out.println("\nSelect an option below:");
                System.out.println("\tadd exercise");
                System.out.println("\tback");
            } else {
                displayNonEmptyExercises(day);
            }
            command = input.next();
            if (command.equals("add exercise")) {
                addExercise(day);
            } else if (command.equals("remove exercise")) {
                removeExercise(day);
            } else if (command.equals("update sets")) {
                updateSets(day);
            } else if (!command.equals("back")) {
                System.out.println("invalid selection");
            }
        }
    }

    // EFFECTS: displays the exercises and the exercise options for the case where 1 or more
    //          exercises has been added to that day
    public void displayNonEmptyExercises(Day day) {

        System.out.println("\nExercises for " + day.getName() + ":");
        int number = 0;
        for (Exercise exercise : day.getExercises()) {
            number += 1;
            System.out.println("\t" + number + ") " + exercise.getName() + " (" + exercise.getMuscleGroup() + ")");
            for (Set set : exercise.getSets()) {
                System.out.println("\t\t" + set.getReps() + " reps at " + set.getPercent1RM() + "% 1rm");
            }
        }
        System.out.println("\nSelect an option below:");
        System.out.println("\tadd exercise");
        System.out.println("\tremove exercise");
        System.out.println("\tupdate sets");
        System.out.println("\tback");
    }


    // MODIFIES: this
    // EFFECTS: prompts the user to enter an exercise name and target muscle group,
    //          and adds that exercise to the list of exercises for that day
    public void addExercise(Day day) {
        System.out.println("Enter exercise name:");
        String name = input.next();
        System.out.println("Enter target muscle group for exercise:");
        String muscle = input.next();

        Exercise exercise = new Exercise(name, muscle);
        if (day.addExercise(exercise)) {
            System.out.println("Exercise added successfully");
        } else {
            System.out.println("Exercise with that name already exists in this day");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter the exercise number, and removes that
    //          exercise from the list of exercises for the current day
    public void removeExercise(Day day) {
        System.out.println("Enter exercise number:");
        int number = Integer.valueOf(input.next()) - 1;
        day.removeExerciseInd(number);
        System.out.println("Exercise removed successfully");
    }

    // EFFECTS: Displays all the info for the imputed exercise
    public void updateSets(Day day) {
        System.out.println("Enter exercise number:");
        int number = Integer.valueOf(input.next()) - 1;
        Exercise exercise = day.getExercises().get(number);
        String command = "";

        while (!command.equals("back")) {
            System.out.println("\n" + exercise.getName() + " (" + exercise.getMuscleGroup() + ")");
            for (Set set : exercise.getSets()) {
                System.out.println("\t" + set.getReps() + " reps at " + set.getPercent1RM() + "% 1rm");
            }
            displaySetOptions();
            command = input.next();
            processSetOptions(command, exercise);
        }
    }

    // EFFECTS: Displays the options for sets
    public void displaySetOptions() {
        System.out.println("\nSelect an option below:");
        System.out.println("\tadd set");
        System.out.println("\tclear sets");
        System.out.println("\tback");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command for the set options
    public void processSetOptions(String command, Exercise e) {
        if (command.equals("add set")) {
            addSet(e);
        } else if (command.equals("clear sets")) {
            clearSets(e);
        } else if (!command.equals("back")) {
            System.out.println("invalid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter reps in set and percent 1RM and adds set
    //          to the list of sets for that exercise
    public void addSet(Exercise e) {
        System.out.println("Enter # of reps in set");
        int reps = Integer.valueOf(input.next());
        System.out.println("Enter the percent of the one rep max to work at in the set");
        int p1RM = Integer.valueOf(input.next());
        Set set = new Set(reps, p1RM);
        e.addSet(set);
    }

    // MODIFIES: this
    // EFFECTS: clears all the sets from the current exercise selected
    public void clearSets(Exercise e) {
        e.clearSets();
        System.out.println("sets successfully cleared");
    }

    // EFFECTS: saves the workout plan to file
    private void saveWorkoutPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutPlan);
            jsonWriter.close();
            System.out.println("Saved workout plan to " + JSON_FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE_PATH);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout plan from file
    private void loadWorkoutPlan() {
        try {
            workoutPlan = jsonReader.read();
            System.out.println("Loaded workout plan from " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE_PATH);
        }
    }
}
