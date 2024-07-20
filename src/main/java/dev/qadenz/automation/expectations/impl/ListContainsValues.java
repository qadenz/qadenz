/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.ListExpectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.util.ArrayList;
import java.util.List;

/**
 * An Expectation for a List of String 'actual' values to contain the given 'expected' values.
 *
 * @author Tim Slifer
 */
public class ListContainsValues implements ListExpectation {
    
    private List<String> expectedValues;
    
    public ListContainsValues(List<String> expectedValues) {
        this.expectedValues = expectedValues;
    }
    
    @Override
    public List<Matcher<String>> matchers() {
        List<Matcher<String>> expectations = new ArrayList<>();
        for (String value : expectedValues) {
            expectations.add(new IsEqual<>(value));
        }
        
        return expectations;
    }
    
    @Override
    public String toString() {
        StringBuilder values = new StringBuilder();
        for (String value : expectedValues) {
            values.append(value).append("\n");
        }
        
        return values.toString();
    }
    
    @Override
    public List<String> getExpectedValues() {
        return expectedValues;
    }
}
