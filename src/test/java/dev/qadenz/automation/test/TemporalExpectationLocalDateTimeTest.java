package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static dev.qadenz.automation.util.Dates.JUN_14_2012_NOON;
import static dev.qadenz.automation.util.Dates.JUN_15_2012_NOON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalDateTimeTest {
    
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
}
