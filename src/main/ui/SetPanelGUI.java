package ui;

import model.Exercise;
import model.Set;

import javax.swing.*;

// panel for a set
public class SetPanelGUI extends JPanel {
    private Set set;
    private JLabel setLabel;
    private JPanel parentPanel;

    // EFFECTS: creates a new set panel with the given set and parent panel panel
    public SetPanelGUI(JPanel panel, Set set) {
        this.set = set;
        this.parentPanel = panel;
        createLabel();
    }

    // MODIFIES: this, WorkoutPlannerGUI
    // EFFECTS: creates a new label containing set info and adds it to this then adds this to parent panel
    public void createLabel() {
        setLabel = new JLabel(" " + set.getReps() + " reps at " + set.getPercent1RM() + "% 1 rep max");
        setLabel.setPreferredSize(Constants.EXERCISEFIELD_SIZE);
        add(setLabel);
        parentPanel.add(this);
    }
}
