package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableContainingInOrder;

import java.util.List;

public class ListInOrder implements Expectation<Iterable<?>> {
    
    private List<String> expectedValues;
    
    public ListInOrder(List<String> expectedValues) {
        this.expectedValues = expectedValues;
    }
    
    @Override
    public Matcher<Iterable<?>> matcher() {
        return IsIterableContainingInOrder.contains(expectedValues.toArray());
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
