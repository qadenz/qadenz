/*
Copyright Tim Slifer

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
    public void testLocalTimeIsAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isAfter(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsAfter_ReturnsFalseWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isAfter(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsBefore_ReturnsFalseWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isBefore(expected);
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
    public void testLocalTimeIsBefore_ReturnsFalseWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isBefore(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualTo_ReturnsFalseWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualTo(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualTo_ReturnsFalseWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualTo(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isEqualTo(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrAfter_ReturnsTrueWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualToOrAfter(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrAfter_ReturnsFalseWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualToOrAfter(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrAfter_ReturnsTrueWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isEqualToOrAfter(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrBefore_ReturnsFalseWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualToOrBefore(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrBefore_ReturnsTrueWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        Expectation<LocalTime> expectation = Expectations.isEqualToOrBefore(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsEqualToOrBefore_ReturnsTrueWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isEqualToOrBefore(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotEqualTo_ReturnsTrueWhenActualIsAfterExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        Expectation<LocalTime> expectation = Expectations.isNotEqualTo(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotEqualTo_ReturnsTrueWhenActualIsBeforeExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.minusHours(1);
        Expectation<LocalTime> expectation = Expectations.isNotEqualTo(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        Expectation<LocalTime> expectation = Expectations.isNotEqualTo(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsTrueWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.plusSeconds(3)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.plusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.plusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsFalseWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsNotWithin_ReturnsTrueWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isNotWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(3)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsFalseWhenActualIsAfterExpectedAndOutsideRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(3)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndAtRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsAfterExpectedAndWithinRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsEqualToExpected() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndWithinRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(1)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsTrueWhenActualIsBeforeExpectedAndAtRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertTrue(expectation.matcher().matches(LocalTime.NOON.minusSeconds(2)));
    }
    
    @Test
    public void testLocalTimeIsWithin_ReturnsFalseWhenActualIsBeforeExpectedAndOutsideRange() {
        TemporalExpectation<LocalTime> expectation = Expectations.isWithin(2, ChronoUnit.SECONDS, LocalTime.NOON);
        assertFalse(expectation.matcher().matches(LocalTime.NOON.minusSeconds(3)));
    }
}
