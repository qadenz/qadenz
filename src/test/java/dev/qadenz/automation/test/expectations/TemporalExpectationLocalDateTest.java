/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.expectations.Expectations;
import dev.qadenz.automation.expectations.TemporalExpectation;
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
    public void testLocalDateIsAfter_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(AUG_04_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isBefore(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsBefore_ReturnsFalseWhenActualIsEqualToExpected() {
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
    public void testLocalDateIsEqualTo_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isEqualTo(AUG_04_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsEqualTo_ReturnsFalseWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isEqualTo(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isEqualTo(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsNotDayOfWeek_ReturnsFalseWhenActualIsSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isDayOfWeekAsLocalDate(DayOfWeek.TUESDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotDayOfWeek_ReturnsTrueWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDate> expectation = Expectations.isDayOfWeekAsLocalDate(DayOfWeek.MONDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotEqualTo(AUG_04_2015);
        assertTrue(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotEqualTo(AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotEqualTo(AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_05_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsTrueWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_01_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_02_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_03_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_04_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_05_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_06_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsNotWithin_ReturnsTrueWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_07_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsFalseWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_01_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_02_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_03_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsTrueWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_04_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_05_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_06_2015);
        assertTrue(expectation.matcher().matches(AUG_04_2015));
    }
    
    @Test
    private void testLocalDateIsWithin_ReturnsFalseWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalDate> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_07_2015);
        assertFalse(expectation.matcher().matches(AUG_04_2015));
    }
}
