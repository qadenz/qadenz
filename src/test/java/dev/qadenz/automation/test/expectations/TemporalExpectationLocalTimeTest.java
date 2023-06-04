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
    public void testLocalDateIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isEqualTo(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateIsEqualTo_ReturnsFalseWhenActualIsNotEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualTo(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsTrueWhenActualIsNotEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isNotEqualTo(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isNotEqualTo(expected);
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
