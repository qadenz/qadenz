/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.Expectation;
import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
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
    public void testLocalDateIsDayOfWeek_ReturnsTrueWhenActualIsSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isDayOfWeekAsLocalDate(DayOfWeek.TUESDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsDayOfWeek_ReturnsFalseWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isDayOfWeekAsLocalDate(DayOfWeek.MONDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotDayOfWeek_ReturnsTrueWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isNotDayOfWeekAsLocalDate(DayOfWeek.MONDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotDayOfWeek_ReturnsFalseWhenActualIsSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isNotDayOfWeekAsLocalDate(DayOfWeek.TUESDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotSameDay_ReturnsTrueWhenActualIsNotSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotSameDay(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotSameDay_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotSameDay(AUG_05_2015);
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
    
    @Test
    public void testLocalDateIsSameDay_ReturnsTrueWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isSameDay(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsSameDay_ReturnsFalseWhenActualIsNotSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isSameDay(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsWithin_ReturnsTrueWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_04_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsWithin_ReturnsTrueWhenActualIsInRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsWithin_ReturnsTrueWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_06_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsWithin_ReturnsFalseWhenActualIsNotWithinRangeOfExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_07_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
}
