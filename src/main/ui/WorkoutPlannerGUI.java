package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// Workout Planner application GUI
public class WorkoutPlannerGUI extends JFrame implements WindowListener {
    private WorkoutPlan workoutPlan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_FILE_PATH = "./data/workroom.json";
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private Day sunday;

    //EFFECTS: constructs a workout plan and runs the workout planner GUI app
    public WorkoutPlannerGUI() {
        super("Workout Planner App");
        workoutPlan = new WorkoutPlan("My Workout Planner");
        jsonWriter = new JsonWriter(JSON_FILE_PATH);
        jsonReader = new JsonReader(JSON_FILE_PATH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 400));
        setResizable(false);
        addWindowListener(this);
        buildJavaSwing();
    }

    // MODIFIES: this
    // EFFECTS: displays the options to load save file and processes button actions
    public void buildJavaSwing() {
        JLabel label = new JLabel("Do you want to load the saved file?");
        JButton yes = new JButton("yes");
        JPanel panel = new JPanel();
        label.setBounds(10, 5, 400, 25);
        yes.setBounds(20, 50, 80, 25);
        yes.addActionListener(e -> {
            loadWorkoutPlan();
            remove(panel);
            runWorkoutPlanner();
        });
        JButton no = new JButton("no");
        no.setBounds(20, 80, 80, 25);
        no.addActionListener(e -> {
            remove(panel);
            addDays();
            runWorkoutPlanner();
        });
        panel.add(label);
        panel.add(yes);
        panel.add(no);
        panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        createBasicPanel(panel);
    }

    // MODIFIES: this
    // EFFECTS: processes the user actions
    public void runWorkoutPlanner() {
        setVisible(false);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Choose an option below:");
        JButton planButton = new JButton("Plan Workouts");
        JButton saveButton = new JButton("Save Workouts");
        JButton quit = new JButton("quit");
        mainMenu(planButton, saveButton, quit, panel);
        panel.add(label);
        panel.add(planButton);
        panel.add(saveButton);
        panel.add(quit);
        createBasicPanel(panel);
    }

    // MODIFIES: this
    // EFFECTS: Processes user actions
    public void mainMenu(JButton plan, JButton save, JButton quit, JPanel panel) {
        plan.addActionListener(e -> {
            setVisible(false);
            remove(panel);
            loadDays();
        });
        save.addActionListener(e -> {
            saveWorkoutPlan();
        });
        quit.addActionListener(e -> {
            dispose();
        });
    }

    // MODIFIES: this
    // EFFECTS: shows the options for the days and processes user actions
    public void loadDays() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JLabel label = new JLabel("Select a day");
        JComboBox comboBox = new JComboBox<>(days);
        JButton selectButton = new JButton("select");
        JButton backButton = new JButton("back");
        JPanel panel = new JPanel();
        selectButton.addActionListener(e -> {
            setVisible(false);
            remove(panel);
            selectDay(comboBox.getSelectedItem());
        });
        backButton.addActionListener(e -> {
            setVisible(false);
            remove(panel);
            runWorkoutPlanner();
        });
        panel.add(label);
        panel.add(comboBox);
        panel.add(selectButton);
        panel.add(backButton);
        createBasicPanel(panel);
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

    // MODIFIES: this
    //EFFECTS: processes the given input by the user
    public void selectDay(Object day) {
        if (day.equals("Monday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(0), this);
        } else if (day.equals("Tuesday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(1), this);
        } else if (day.equals("Wednesday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(2), this);
        } else if (day.equals("Thursday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(3), this);
        } else if (day.equals("Friday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(4), this);
        } else if (day.equals("Saturday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(5), this);
        } else if (day.equals("Sunday")) {
            new ExercisesFrameGUI(workoutPlan.getDaysOfWeek().get(6), this);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the info to add a workout and processes user input
    public void addWorkout(Day day) {
        JTextField name = new JTextField(20);
        JPanel panel = new JPanel();
        name.setMaximumSize(new Dimension(165, 25));
        JLabel exerciseName = new JLabel("Exercise name:");
        JLabel mgtext = new JLabel("Muscle Group:");
        JTextField mg = new JTextField(20);
        mg.setMaximumSize(new Dimension(165, 25));
        JButton but = new JButton("add exercise");
        JFrame addWorkoutFrame = new JFrame();
        addWorkoutFrame.setSize(300, 200);
        but.addActionListener(e -> {
            day.addExercise(new Exercise(name.getText(), mg.getText()));
            addWorkoutFrame.setVisible(false);
            addWorkoutFrame.dispose();
            new ExercisesFrameGUI(day, this);
        });
        panel.add(exerciseName);
        panel.add(name);
        panel.add(mgtext);
        panel.add(mg);
        panel.add(but);
        addWorkoutFrame.add(panel);
        addWorkoutFrame.setVisible(true);
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

    // MODIFIES: this
    // EFFECTS: creates a basic panel and adds it to the frame
    private void createBasicPanel(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
//        panel.setBackground(Color.cyan);
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

