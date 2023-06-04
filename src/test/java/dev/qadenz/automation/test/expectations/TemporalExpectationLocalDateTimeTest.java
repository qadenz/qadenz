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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalDateTimeTest extends TemporalExpectationTest {
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(AUG_04_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsDayOfWeek_ReturnsTrueWhenActualIsSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isDayOfWeekAsLocalDateTime(DayOfWeek.TUESDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualTo(AUG_07_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_07_2015_NOON));
    }
    
    @Test
    public void testLocalDateIsEqualTo_ReturnsFalseWhenActualIsNotEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualTo(AUG_07_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_06_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotDayOfWeek_ReturnsTrueWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isDayOfWeekAsLocalDateTime(DayOfWeek.MONDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotDayOfWeek_ReturnsFalseWhenActualIsSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isNotDayOfWeekAsLocalDateTime(DayOfWeek.MONDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsDayOfWeek_ReturnsFalseWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isNotDayOfWeekAsLocalDateTime(DayOfWeek.TUESDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsTrueWhenActualIsNotEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isNotEqualTo(AUG_07_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_06_2015_NOON));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isNotEqualTo(AUG_07_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_07_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsTrueWhenActualIsNotInRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_07_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_06_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsWithinRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_04_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsInRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_06_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsFalseWhenActualIsNotWithinRangeOfExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_07_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
}
