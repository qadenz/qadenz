/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.expectations.Expectations;
import dev.qadenz.automation.conditions.NumberFormatters;
import dev.qadenz.automation.expectations.NumericExpectation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericExpectationDoubleTest {
    
    @Test
    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsEqualTo_ReturnsFalseWhenActualIsNotEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsTrueWhenActualIsNotEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isNotEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isNotEqualTo(10.00);
        expectation.setNumberFormat(NumberFormatters.nonGroupedDecimal("0.00"));
        assertFalse(expectation.matcher().matches(10.00));
    }
}
