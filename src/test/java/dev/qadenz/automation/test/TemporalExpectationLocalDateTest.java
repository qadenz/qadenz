package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalDateTest extends TemporalExpectationTest {
    
    @Test
    public void testLocalDateIsAfter_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(AUG_04_2015);
        assertTrue(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsAfter_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(AUG_04_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsNotSameAs_ReturnsTrueWhenActualIsNotSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotSameAs(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotSameAs_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotSameAs(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsNotWithin_ReturnsTrueWhenActualIsNotInRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_07_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_06_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsWithinRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_04_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
}
