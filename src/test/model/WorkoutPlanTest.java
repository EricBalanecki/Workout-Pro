package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutPlanTest {
    private WorkoutPlan testWorkoutPlan;
    private Day monday;
    private Day d2;
    private Day d3;

    @BeforeEach
    void runBefore() {
        testWorkoutPlan = new WorkoutPlan("Workout Plan");
        monday = new Day("Monday");
        d2 = new Day("Friday");
        d3 = new Day("Friday");
    }

    @Test
    public void testConstructor() {
        assertEquals("Workout Plan", testWorkoutPlan.getName());
        assertEquals(0, testWorkoutPlan.getDaysOfWeek().size());
    }

    @Test
    public void testAddDay() {
        testWorkoutPlan.addDay(monday);
        assertEquals(1, testWorkoutPlan.getDaysOfWeek().size());

        testWorkoutPlan.addDay(d2);
        assertEquals(2, testWorkoutPlan.getDaysOfWeek().size());
        testWorkoutPlan.addDay(d3);
        assertEquals(2, testWorkoutPlan.getDaysOfWeek().size());
    }
}

