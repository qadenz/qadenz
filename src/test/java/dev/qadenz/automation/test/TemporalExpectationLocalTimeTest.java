package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalTimeTest {
    
    @Test
    public void testLocalTimeIsAfter_ReturnsTrueWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isAfter(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsAfter_ReturnsTrueWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isAfter(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsAfter_ReturnsFalseWhenActualIsSameAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isAfter(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isBefore(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isBefore(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsBefore_ReturnsFalseWhenActualIsSameAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isBefore(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
}
