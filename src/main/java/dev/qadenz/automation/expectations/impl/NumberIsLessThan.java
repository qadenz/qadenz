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
 * An Expectation for a numeric 'actual' value to be less than the given 'expected' value.
 *
 * @author Tim Slifer
 */
public class NumberIsLessThan<T extends Comparable<T>> implements NumericExpectation<T> {
    
    private T expectedValue;
    
    private NumberFormat numberFormat;
    
    public NumberIsLessThan(T expectedValue) {
        this.expectedValue = expectedValue;
    }
    
    @Override
    public Matcher<T> matcher() {
        return OrderingComparison.lessThan(expectedValue);
    }
    
    @Override
    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }
    
    @Override
    public String toString() {
        return "is less than [" + numberFormat.format(expectedValue) + "]";
    }
}
