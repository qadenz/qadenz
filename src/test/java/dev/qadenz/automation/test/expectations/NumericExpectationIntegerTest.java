/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.NumberFormatters;
import dev.qadenz.automation.conditions.NumericExpectation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericExpectationIntegerTest {
    
    @Test
    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1000));
    }
    
    @Test
    public void testIsEqualTo_ReturnsFalseWhenActualIsNotEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1000));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(900));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1000));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(900));
    }
    
    @Test
    public void testIsLessThan_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(900));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1000));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThan(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(900));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1000));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Integer> expectation = Expectations.isLessThanOrEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsTrueWhenActualIsNotEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isNotEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertTrue(expectation.matcher().matches(1100));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isNotEqualTo(1000);
        expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
        assertFalse(expectation.matcher().matches(1000));
    }
}
