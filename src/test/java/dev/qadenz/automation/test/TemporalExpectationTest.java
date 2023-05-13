package dev.qadenz.automation.test;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.TemporalExpectation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalExpectationTest {
    
    @Test
    public void localDateIsAfter_WithLocalDate_ReturnsTrueWhenActualIsAfterExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(LocalDate.now().minus(1, ChronoUnit.DAYS));
        assertTrue(expectation.matcher().matches(LocalDate.now()));
    }
    
    @Test
    public void localDateIsAfter_WithLocalDate_ReturnsFalseWhenActualIsBeforeExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(LocalDate.now());
        assertFalse(expectation.matcher().matches(LocalDate.now().minus(1, ChronoUnit.DAYS)));
    }
    
    @Test
    public void localDateIsAfter_WithLocalDate_ReturnsFalseWhenActualIsSameAsExpected() {
        TemporalExpectation<LocalDate> expectation = Expectations.isAfter(LocalDate.now());
        assertFalse(expectation.matcher().matches(LocalDate.now()));
    }
}
