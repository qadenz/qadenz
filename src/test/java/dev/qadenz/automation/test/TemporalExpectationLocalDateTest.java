package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static dev.qadenz.automation.util.Dates.JUN_14_2012;
import static dev.qadenz.automation.util.Dates.JUN_15_2012;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalDateTest {
    
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
}
