package persistence;

import model.Day;
import model.Exercise;
import model.Set;

import static org.junit.jupiter.api.Assertions.*;

// Code inspired by JsonSerializationDemo
public class JsonTest {
    protected void checkDay(String name, int exercises, Day day) {
        assertEquals(name, day.getName());
        assertEquals(exercises, day.getExercises().size());
    }

    protected void checkExercise(String name, String mg, int sets, Exercise e) {
        assertEquals(name, e.getName());
        assertEquals(mg, e.getMuscleGroup());
        assertEquals(sets, e.getSets().size());
    }

    protected void checkSet(int reps, int p1RM, Set set) {
        assertEquals(reps, set.getReps());
        assertEquals(p1RM, set.getPercent1RM());
    }
}
