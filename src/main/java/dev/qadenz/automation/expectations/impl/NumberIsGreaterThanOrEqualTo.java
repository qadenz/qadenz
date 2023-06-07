/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.NumericExpectation;
import org.hamcrest.Matcher;
import org.hamcrest.number.OrderingComparison;

import java.text.NumberFormat;

/**
 * An Expectation for a numeric 'actual' value to be greater than or equal to the given 'expected' value.
 *
 * @author Tim Slifer
 */
public class NumberIsGreaterThanOrEqualTo<T extends Comparable<T>> implements NumericExpectation<T> {
    
    private T expectedValue;
    
    private NumberFormat numberFormat;
    
    public NumberIsGreaterThanOrEqualTo(T expectedValue) {
        this.expectedValue = expectedValue;
    }
    
    @Override
    public Matcher<T> matcher() {
        return OrderingComparison.greaterThanOrEqualTo(expectedValue);
    }
    
    @Override
    public String description() {
        return "is greater than or equal to [" + numberFormat.format(expectedValue) + "]";
    }
    
    @Override
    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }
}
