package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day testDay;
    private Exercise e1;
    private Exercise e2;
    private Exercise e3;
    private Exercise e4;

    @BeforeEach
    void runBefore() {
        testDay = new Day("Monday");
        e1 = new Exercise("Bench Press", "Chest");
        e2 = new Exercise("Bar Squat", "Legs");
        e3 = new Exercise("Pullups", "Lats");
        e4 = new Exercise("Bench Press", "Shoulders");
    }

    @Test
    void testConstructor() {
        assertEquals("Monday", testDay.getName());
        assertTrue(testDay.getExercises().isEmpty());
    }

    @Test
    void testAddExercise() {
        assertTrue(testDay.addExercise(e1));
        assertEquals(1, testDay.getExercises().size());
        assertEquals(e1, testDay.getExercises().get(0));
    }

    @Test
    void testAddMultipleExercises() {
        assertTrue(testDay.addExercise(e1));
        assertTrue(testDay.addExercise(e2));
        assertFalse(testDay.addExercise(e2));
        assertFalse(testDay.addExercise(e4));
        assertEquals(2, testDay.getExercises().size());
        assertEquals(e1, testDay.getExercises().get(0));
        assertEquals(e2, testDay.getExercises().get(1));
    }

    @Test
    void testRemoveExercise() {
        testDay.addExercise(e1);
        testDay.addExercise(e2);
        testDay.addExercise(e3);
        assertEquals(3, testDay.getExercises().size());
        testDay.removeExerciseInd(1);
        assertEquals(2, testDay.getExercises().size());
        assertEquals(e1, testDay.getExercises().get(0));
        assertEquals(e3, testDay.getExercises().get(1));
    }
}
