package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day testDay;
    private Exercise e1;
    private Exercise e2;

    @BeforeEach
    void runBefore() {
        testDay = new Day("Monday");
        e1 = new Exercise("Bench Press", "Chest");
        e2 = new Exercise("Bar Squat", "Legs");
    }

    @Test
    void testConstructor() {
        assertEquals("Monday", testDay.getName());
        assertTrue(testDay.getExercises().isEmpty());
    }

    @Test
    void addExercise() {
        testDay.addExercise(e1);
        assertEquals(1, testDay.getExercises().size());
        assertEquals(e1, testDay.getExercises().get(0));
    }

    @Test
    void addMultipleExercises() {
        testDay.addExercise(e1);
        testDay.addExercise(e2);
        assertEquals(2, testDay.getExercises().size());
        assertEquals(e1, testDay.getExercises().get(0));
        assertEquals(e2, testDay.getExercises().get(1));
    }
}
