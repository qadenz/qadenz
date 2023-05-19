package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.Expectation;
import dev.qadenz.automation.conditions.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanExpectationTest {
    
    @Test
    public void testIsFalse_ReturnsTrueWhenActualIsFalse() {
        Expectation<Boolean> expectation = Expectations.isFalse();
        assertTrue(expectation.matcher().matches(false));
    }
    
    @Test
    public void testIsFalse_ReturnsFalseWhenActualIsTrue() {
        Expectation<Boolean> expectation = Expectations.isFalse();
        assertFalse(expectation.matcher().matches(true));
    }
    
    @Test
    public void testIsTrue_ReturnsTrueWhenActualIsTrue() {
        Expectation<Boolean> expectation = Expectations.isTrue();
        assertTrue(expectation.matcher().matches(true));
    }
    
    @Test
    public void testIsTrue_ReturnsFalseWhenActualIsFalse() {
        Expectation<Boolean> expectation = Expectations.isTrue();
        assertFalse(expectation.matcher().matches(false));
    }
}
