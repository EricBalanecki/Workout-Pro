package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    private Exercise testExercise;
    private Set s1;
    private Set s2;
    private Set s3;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Bench Press", "Chest");
        s1 = new Set(7, 75);
        s2 = new Set(6, 80);
        s3 = new Set(5, 80);
    }

    @Test
    void testConstructor() {
        assertEquals("Bench Press", testExercise.getName());
        assertEquals("Chest", testExercise.getMuscleGroup());
        assertTrue(testExercise.getSets().isEmpty());
    }

    @Test
    void testAddSet() {
        testExercise.addSet(s1);
        assertEquals(1, testExercise.getSets().size());
        assertEquals(s1, testExercise.getSets().get(0));
    }

    @Test
    void testAddMultipleSets() {
        testExercise.addSet(s1);
        testExercise.addSet(s3);
        testExercise.addSet(s2);
        assertEquals(3, testExercise.getSets().size());
        assertEquals(s1, testExercise.getSets().get(0));
        assertEquals(s3, testExercise.getSets().get(1));
        assertEquals(s2, testExercise.getSets().get(2));
    }

    @Test
    void testClearSets() {
        testExercise.addSet(s1);
        testExercise.addSet(s3);
        assertEquals(2, testExercise.getSets().size());

        testExercise.clearSets();
        assertEquals(0, testExercise.getSets().size());
    }
}
