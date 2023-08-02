package persistence;

import model.Day;
import model.WorkoutPlan;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code inspired by JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutPlan wp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
        try {
            WorkoutPlan wp = reader.read();
            assertEquals("My Workout Plan", wp.getName());
            assertEquals(0, wp.getDaysOfWeek().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderDay() {
        JsonReader reader = new JsonReader("./data/testWriterDay.json");
        try {
            WorkoutPlan wp = reader.read();
            assertEquals("My Workout Plan", wp.getName());
            assertEquals(1, wp.getDaysOfWeek().size());
            assertEquals("Sunday", wp.getDaysOfWeek().get(0).getName());
            assertEquals(2, wp.getDaysOfWeek().get(0).getExercises().size());
            checkExercise("Bench Press", "chest", 0, wp.getDaysOfWeek().get(0).getExercises().get(0));
            checkExercise("Split Squats", "legs", 0, wp.getDaysOfWeek().get(0).getExercises().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderExerciseSets() {
        JsonReader reader = new JsonReader("./data/testWriterExerciseSets.json");
        try {
            WorkoutPlan wp = reader.read();
            Day sunday = wp.getDaysOfWeek().get(0);
            assertEquals("Sunday", sunday.getName());
            assertEquals(2, sunday.getExercises().size());
            assertEquals(2, sunday.getExercises().get(0).getSets().size());
            assertEquals(1, sunday.getExercises().get(1).getSets().size());
            checkSet(10, 50, sunday.getExercises().get(0).getSets().get(0));
            checkSet(8, 70, sunday.getExercises().get(0).getSets().get(1));
            checkSet(10, 80, sunday.getExercises().get(1).getSets().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}