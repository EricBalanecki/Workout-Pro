package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    private Set testSet;

    @BeforeEach
    void runBefore() {
        testSet = new Set(12, 70);
    }

    @Test
    void testConstructor() {
        assertEquals(12, testSet.getReps());
        assertEquals(70, testSet.getPercent1RM());
    }
}
