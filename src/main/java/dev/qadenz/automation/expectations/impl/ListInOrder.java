package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.ListExpectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.util.ArrayList;
import java.util.List;

public class ListInOrder implements ListExpectation<String> {
    
    private List<String> expectedValues;
    
    public ListInOrder(List<String> expectedValues) {
        this.expectedValues = expectedValues;
    }
    
    @Override
    public List<Matcher<String>> matchers() {
        List<Matcher<String>> matchers = new ArrayList<>();
        for (String value : expectedValues) {
            matchers.add(new IsEqual(value));
        }
        
        return matchers;
    }
    
    @Override
    public String toString() {
        StringBuilder values = new StringBuilder();
        values.append("Listed element values in order: \n");
        for (String value : expectedValues) {
            values.append(value).append("\n");
        }
        
        return values.toString();
    }
}
