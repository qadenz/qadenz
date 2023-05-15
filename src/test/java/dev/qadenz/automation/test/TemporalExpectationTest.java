package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static dev.qadenz.automation.util.Dates.JUN_14_2012;
import static dev.qadenz.automation.util.Dates.JUN_14_2012_NOON;
import static dev.qadenz.automation.util.Dates.JUN_15_2012;
import static dev.qadenz.automation.util.Dates.JUN_15_2012_NOON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationTest {
    
    @Test
    public void testLocalDateIsAfter_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(JUN_14_2012);
        assertTrue(expectation.matcher().matches(JUN_15_2012));
    }
    
    @Test
    public void testLocalDateIsAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(JUN_15_2012);
        assertFalse(expectation.matcher().matches(JUN_14_2012));
    }
    
    @Test
    public void testLocalDateIsAfter_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(JUN_15_2012);
        assertFalse(expectation.matcher().matches(JUN_15_2012));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(JUN_14_2012_NOON);
        assertTrue(expectation.matcher().matches(JUN_15_2012_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(JUN_15_2012_NOON);
        assertFalse(expectation.matcher().matches(JUN_14_2012_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(JUN_15_2012_NOON);
        assertFalse(expectation.matcher().matches(JUN_15_2012_NOON));
    }
    
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
    public void testLocalDateIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(JUN_15_2012);
        assertTrue(expectation.matcher().matches(JUN_14_2012));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(JUN_14_2012);
        assertFalse(expectation.matcher().matches(JUN_15_2012));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(JUN_15_2012);
        assertFalse(expectation.matcher().matches(JUN_15_2012));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(JUN_15_2012_NOON);
        assertTrue(expectation.matcher().matches(JUN_14_2012_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(JUN_14_2012_NOON);
        assertFalse(expectation.matcher().matches(JUN_15_2012_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(JUN_15_2012_NOON);
        assertFalse(expectation.matcher().matches(JUN_15_2012_NOON));
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
