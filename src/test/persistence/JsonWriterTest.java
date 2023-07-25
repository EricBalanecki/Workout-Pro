package persistence;

import model.Day;
import model.Exercise;
import model.Set;
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
    void testWriterEmptyDay() {
        try {
            Day day = new Day("Sunday");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(0, day.getExercises().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterDay() {
        try {
            Day day = new Day("Sunday");
            day.addExercise(new Exercise("Bench Press", "chest"));
            day.addExercise(new Exercise("Split Squats", "legs"));
            JsonWriter writer = new JsonWriter("./data/testWriterDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDay.json");
            day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(2, day.getExercises().size());
            checkExercise("Bench Press", "chest", 0, day.getExercises().get(0));
            checkExercise("Split Squats", "legs", 0, day.getExercises().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterExerciseSets() {
        try {
            Day day = new Day("Sunday");
            Exercise e1 = new Exercise("Bench Press", "chest");
            Exercise e2 = new Exercise("Split Squats", "legs");
            e1.addSet(new Set(10, 50));
            e1.addSet(new Set(8, 70));
            e2.addSet(new Set(10, 80));
            day.addExercise(e1);
            day.addExercise(e2);
            JsonWriter writer = new JsonWriter("./data/testWriterExerciseSets.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterExerciseSets.json");
            day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(2, day.getExercises().size());
            assertEquals(2, day.getExercises().get(0).getSets().size());
            assertEquals(1, day.getExercises().get(1).getSets().size());
            checkSet(10, 50, day.getExercises().get(0).getSets().get(0));
            checkSet(8, 70, day.getExercises().get(0).getSets().get(1));
            checkSet(10, 80, day.getExercises().get(1).getSets().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}