package ui;

import model.Day;
import model.Exercise;
import model.Set;

import javax.swing.*;
import java.awt.*;

// A panel that displays the info for a exercise
public class ExercisePanelGUI extends JPanel {
    private Exercise exercise;
    private JLabel exerciseLabel;
    private JButton addSet;
    private JButton delete;
    private JPanel parentPanel;
    private Day day;

    // EFFECTS: Creates a new panel setting the parent panel to panel, the exercise to exercise, and day of the week
    //          to day, also creates the buttons in panel
    public ExercisePanelGUI(JPanel panel, Exercise exercise, Day day) {
        this.exercise = exercise;
        this.addSet = new JButton("+");
        this.delete = new JButton("X");
        this.parentPanel = panel;
        this.day = day;
        createLabel();
    }

    // MODIFIES: this
    // EFFECTS: handles button inout and adds the label to the panel
    public void createLabel() {
        exerciseLabel = new JLabel(exercise.getName() + " - " + exercise.getMuscleGroup());
        exerciseLabel.setPreferredSize(Constants.EXERCISEFIELD_SIZE);
        addSet.setPreferredSize(Constants.DELETEBUTTON);
        delete.setPreferredSize(Constants.DELETEBUTTON);
        delete.addActionListener(e -> {
            parentPanel.remove(this);
            day.removeExercise(exercise);
        });
        addSet.addActionListener(e -> {
            addSet(exercise);
        });
        add(exerciseLabel);
        imageIcons();
        add(delete);
        add(addSet);
        parentPanel.add(this);
    }

    // MODIFIES: this
    // EFFECTS: if given name of exercise matches name with image, adds image to panel
    public void imageIcons() {
        JLabel image = new JLabel();
        image.setPreferredSize(new Dimension(75, 75));
        if (exercise.getName().equals("Bench Press")) {
            image.setIcon(new ImageIcon("src/main/ui/Images/Bench.jpg"));
            add(image);
        }
        if (exercise.getName().equals("Bar Squat")) {
            image.setIcon(new ImageIcon("src/main/ui/Images/Squat.png"));
            add(image);
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the frame containing the inputs for the new set
    public void addSet(Exercise exercise) {
        JTextField reps = new JTextField(20);
        JPanel panel = new JPanel();
        reps.setMaximumSize(new Dimension(165, 25));
        JLabel exerciseName = new JLabel("Exercise reps:");
        JLabel p1RM = new JLabel("Percent 1 rep max:");
        JTextField mg = new JTextField(20);
        mg.setMaximumSize(new Dimension(165, 25));
        JButton but = new JButton("add set");
        JFrame addSetFrame = new JFrame();
        addSetFrame.setSize(300, 200);
        but.addActionListener(e -> {
            exercise.addSet(new Set(Integer.valueOf(reps.getText()), Integer.valueOf(mg.getText())));
            addSetFrame.setVisible(false);
            addSetFrame.dispose();
            new SetPanelGUI(parentPanel, new Set(Integer.valueOf(reps.getText()), Integer.valueOf(mg.getText())));
        });
        panel.add(exerciseName);
        panel.add(reps);
        panel.add(p1RM);
        panel.add(mg);
        panel.add(but);
        addSetFrame.add(panel);
        addSetFrame.setVisible(true);
    }
}
