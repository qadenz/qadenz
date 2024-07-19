package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.ListExpectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.util.ArrayList;
import java.util.List;

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
        values.append("are listed in order: \n");
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
