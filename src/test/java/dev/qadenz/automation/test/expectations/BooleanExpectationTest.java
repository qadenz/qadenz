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
