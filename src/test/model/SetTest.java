package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    private Set testSet;
    private Set testSet2;

    @BeforeEach
    void runBefore() {
        testSet = new Set(12, 70);
        testSet2 = new Set(10, 80);
    }

    @Test
    void testConstructor() {
        assertEquals(12, testSet.getReps());
        assertEquals(70, testSet.getPercent1RM());

        assertEquals(10, testSet2.getReps());
        assertEquals(80, testSet2.getPercent1RM());
    }
}
