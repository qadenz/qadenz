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
    public void testLocalTimeIsNotSameHourOfDay_ReturnsFalseWhenActualIsSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameHourOfDay(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameHourOfDay_ReturnsTrueWhenActualIsNotSameHourAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameHourOfDay(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinuteOfHour_ReturnsFalseWhenActualIsSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinuteOfHour_ReturnsTrueWhenActualIsNotSameMinuteAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameMinuteOfHour_ReturnsFalseWhenActualIsSameMinuteOfDifferentHour() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusHours(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecondOfMinute_ReturnsFalseWhenActualIsSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected;
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecondOfMinute_ReturnsTrueWhenActualIsNotSameSecondAsExpected() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusSeconds(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalTimeIsNotSameSecondOfMinute_ReturnsFalseWhenActualIsSameSecondOfDifferentMinute() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
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
}
