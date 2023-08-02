package persistence;

import model.Day;
import model.Exercise;
import model.Set;
import model.WorkoutPlan;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code inspired by JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlan() {
        try {
            WorkoutPlan wp = new WorkoutPlan("My Workout Plan");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(wp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            wp = reader.read();
            assertEquals("My Workout Plan", wp.getName());
            assertEquals(0, wp.getDaysOfWeek().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterDay() {
        try {
            WorkoutPlan wp = new WorkoutPlan("My Workout Plan");
            Day day = new Day("Sunday");
            wp.addDay(day);
            day.addExercise(new Exercise("Bench Press", "chest"));
            day.addExercise(new Exercise("Split Squats", "legs"));
            JsonWriter writer = new JsonWriter("./data/testWriterDay.json");
            writer.open();
            writer.write(wp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDay.json");
            wp = reader.read();
            assertEquals("Sunday", wp.getDaysOfWeek().get(0).getName());
            assertEquals(2, wp.getDaysOfWeek().get(0).getExercises().size());
            checkExercise("Bench Press", "chest", 0, wp.getDaysOfWeek().get(0).getExercises().get(0));
            checkExercise("Split Squats", "legs", 0, wp.getDaysOfWeek().get(0).getExercises().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterExerciseSets() {
        try {
            WorkoutPlan wp = new WorkoutPlan("My Workout Plan");
            Day day = new Day("Sunday");
            wp.addDay(day);
            Exercise e1 = new Exercise("Bench Press", "chest");
            Exercise e2 = new Exercise("Split Squats", "legs");
            e1.addSet(new Set(10, 50));
            e1.addSet(new Set(8, 70));
            e2.addSet(new Set(10, 80));
            day.addExercise(e1);
            day.addExercise(e2);
            JsonWriter writer = new JsonWriter("./data/testWriterExerciseSets.json");
            writer.open();
            writer.write(wp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterExerciseSets.json");
            wp = reader.read();
            Day sunday = wp.getDaysOfWeek().get(0);
            assertEquals("Sunday", sunday.getName());
            assertEquals(2, sunday.getExercises().size());
            assertEquals(2, sunday.getExercises().get(0).getSets().size());
            assertEquals(1, sunday.getExercises().get(1).getSets().size());
            checkSet(10, 50, sunday.getExercises().get(0).getSets().get(0));
            checkSet(8, 70, sunday.getExercises().get(0).getSets().get(1));
            checkSet(10, 80, sunday.getExercises().get(1).getSets().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}