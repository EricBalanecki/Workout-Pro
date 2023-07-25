package persistence;

import model.Day;
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
            Day day = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
        try {
            Day day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(0, day.getExercises().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderDay() {
        JsonReader reader = new JsonReader("./data/testWriterDay.json");
        try {
            Day day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(2, day.getExercises().size());
            checkExercise("Bench Press", "chest", 0, day.getExercises().get(0));
            checkExercise("Split Squats", "legs", 0, day.getExercises().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderExerciseSets() {
        JsonReader reader = new JsonReader("./data/testWriterExerciseSets.json");
        try {
            Day day = reader.read();
            assertEquals("Sunday", day.getName());
            assertEquals(2, day.getExercises().size());
            assertEquals(2, day.getExercises().get(0).getSets().size());
            assertEquals(1, day.getExercises().get(1).getSets().size());
            checkSet(10, 50, day.getExercises().get(0).getSets().get(0));
            checkSet(8, 70, day.getExercises().get(0).getSets().get(1));
            checkSet(10, 80, day.getExercises().get(1).getSets().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}