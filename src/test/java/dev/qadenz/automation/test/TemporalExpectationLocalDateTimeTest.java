package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

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
    public void testLocalDateTimeIsNotSameAs_ReturnsTrueWhenActualIsNotSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameAs(AUG_05_2015_NOON);
        assertTrue(expectation.matcher().matches(AUG_04_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameAs_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameAs(AUG_05_2015_NOON);
        assertFalse(expectation.matcher().matches(AUG_05_2015_NOON));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameHourOfDay_ReturnsFalseWhenActualIsSameHourAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected;
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameHourOfDay(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameHourOfDay_ReturnsTrueWhenActualIsNotSameHourAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusHours(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameHourOfDay(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameHourOfDay_ReturnsFalseWhenActualIsSameHourOfDifferentDay() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusDays(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameHourOfDay(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameMinuteOfHour_ReturnsFalseWhenActualIsSameMinuteAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected;
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameMinuteOfHour_ReturnsTrueWhenActualIsNotSameMinuteAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameMinuteOfHour_ReturnsFalseWhenActualIsSameMinuteOfDifferentHour() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusHours(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameMinuteOfHour(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameSecondOfMinute_ReturnsFalseWhenActualIsSameSecondAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected;
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameSecondOfMinute_ReturnsTrueWhenActualIsNotSameSecondAsExpected() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusSeconds(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
        assertTrue(expectation.matcher().matches(actual));
    }
    
    @Test
    public void testLocalDateTimeIsNotSameSecondOfMinute_ReturnsFalseWhenActualIsSameSecondOfDifferentMinute() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = expected.plusMinutes(1);
        TemporalExpectation<LocalDateTime> expectation = Expectations.isNotSameSecondOfMinute(expected);
        assertFalse(expectation.matcher().matches(actual));
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
}
