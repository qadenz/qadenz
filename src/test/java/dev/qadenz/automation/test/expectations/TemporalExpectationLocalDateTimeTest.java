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
    public void testLocalDateTimeIsAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsAfter_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isAfter(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsBefore_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isBefore(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsDayOfWeek_ReturnsTrueWhenActualIsSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isDayOfWeekAsLocalDateTime(DayOfWeek.TUESDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsDayOfWeek_ReturnsFalseWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isDayOfWeekAsLocalDateTime(DayOfWeek.MONDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualTo_ReturnsFalseWhenActualIsAfterExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualTo(AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualTo_ReturnsFalseWhenActualIsBeforeExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualTo(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualTo(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrAfter_ReturnsTrueWhenActualIsAfterExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrAfter(AUG_04_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrAfter(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrAfter_ReturnsTrueWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrAfter(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrBefore_ReturnsFalseWhenActualIsAfterExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrBefore(AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrBefore(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsEqualToOrBefore_ReturnsTrueWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isEqualToOrBefore(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotDayOfWeek_ReturnsFalseWhenActualIsSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isNotDayOfWeekAsLocalDateTime(DayOfWeek.TUESDAY);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotDayOfWeek_ReturnsTrueWhenActualIsNotSameDayOfWeek() {
        Expectation<LocalDateTime> expectation = Expectations.isNotDayOfWeekAsLocalDateTime(DayOfWeek.MONDAY);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotEqualTo_ReturnsTrueWhenActualIsAfterExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isNotEqualTo(AUG_04_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotEqualTo_ReturnsTrueWhenActualIsBeforeExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isNotEqualTo(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        Expectation<LocalDateTime> expectation = Expectations.isNotEqualTo(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsTrueWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_01_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_02_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_03_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_04_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_06_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotWithin_ReturnsTrueWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotWithin(2, ChronoUnit.DAYS, AUG_07_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsFalseWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_01_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_02_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_03_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_04_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_06_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsWithin_ReturnsFalseWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isWithin(2, ChronoUnit.DAYS, AUG_07_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_04_2015_NOON));
    }
}
