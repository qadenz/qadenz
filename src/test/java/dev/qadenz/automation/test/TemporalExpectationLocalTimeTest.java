/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationLocalTimeTest extends TemporalExpectationTest {
    
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
    
    @Test
    public void testLocalTimeIsNotSameHour_ReturnsFalseWhenActualIsSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameHour_ReturnsTrueWhenActualIsNotSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameHour(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinute_ReturnsFalseWhenActualIsSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinute_ReturnsTrueWhenActualIsNotSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinute(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinute_ReturnsFalseWhenActualIsSameMinuteOfDifferentHour() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecond_ReturnsFalseWhenActualIsSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecond(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecond_ReturnsTrueWhenActualIsNotSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusSeconds(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecond(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecond_ReturnsFalseWhenActualIsSameSecondOfDifferentMinute() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecond(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsTrueWhenActualIsNotInRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(3)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsWithinRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON));
    }
    
    @Test
    public void testLocalTimeIsSameHour_ReturnsTrueWhenActualIsSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isSameHour(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameHour_ReturnsFalseWhenActualIsNotSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isSameHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameMinute_ReturnsTrueWhenActualIsSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isSameMinute(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameMinute_ReturnsFalseWhenActualIsNotSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isSameMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameMinute_ReturnsTrueWhenActualIsSameMinuteOfDifferentHour() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isSameMinute(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameSecond_ReturnsTrueWhenActualIsSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isSameSecond(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameSecond_ReturnsFalseWhenActualIsNotSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusSeconds(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isSameSecond(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsSameSecond_ReturnsTrueWhenActualIsSameSecondOfDifferentMinute() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isSameSecond(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsInRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsOuterRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsFalseWhenActualIsNotWithinRangeOfExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(3)));
    }
}
