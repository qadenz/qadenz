package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.NumberFormatters;
import dev.qadenz.automation.conditions.NumericExpectation;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericExpectationDoubleTest {
    
    @Test
    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsEqualTo_ReturnsFalseWhenActualIsNotEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsGreaterThan_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsGreaterThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsGreaterThanOrEqualTo_ReturnsFalseWhenActualIsLessThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isGreaterThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsLessThan_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThan(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsLessThenExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(9.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(10.00));
    }
    
    @Test
    public void testIsLessThanOrEqualTo_ReturnsFalseWhenActualIsGreaterThanExpected() {
        NumericExpectation<Double> expectation = Expectations.isLessThanOrEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsTrueWhenActualIsNotEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isNotEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertTrue(expectation.matcher().matches(11.00));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected() {
        NumericExpectation<Double> expectation = Expectations.isNotEqualTo(10.00);
        DecimalFormat decimalFormat = NumberFormatters.nonGroupedDecimal("0.00");
        expectation.setNumberFormat(decimalFormat);
        assertFalse(expectation.matcher().matches(10.00));
    }
}
