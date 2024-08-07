/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.expectations.ListExpectation;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Compares two lists of strings to determine if both lists match, regardless of item order. Differences in item values
 * between the base and comparison lists are captured. Lists of different sizes will return a false result regardless of
 * any matches.
 *
 * @author Tim Slifer
 */
public class UnorderedListComparator {
    
    private ListExpectation expectation;
    private List<String> elementValues;
    private StringBuilder failures;
    
    public UnorderedListComparator(ListExpectation expectation, List<String> elementValues) {
        this.expectation = expectation;
        this.elementValues = elementValues;
        this.failures = new StringBuilder();
    }
    
    public Boolean getResult() {
        Boolean match = null;
        List<String> unmatchedValues = new ArrayList<>(elementValues);
        for (int i = 0; i < expectation.matchers().size(); i++) {
            String instanceValue = expectation.getExpectedValues().get(i);
            Matcher<String> matcher = expectation.matchers().get(i);
            boolean instanceMatch = elementValues.stream().anyMatch(matcher::matches);
            
            if (instanceMatch) {
                unmatchedValues.remove(instanceValue);
            }
            else {
                failures.append("--> expected [")
                        .append(instanceValue)
                        .append("].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        if (!unmatchedValues.isEmpty()) {
            failures.append("--- Some element values were not matched.\n");
            for (String unmatchedValue : unmatchedValues) {
                failures.append("--> found [")
                        .append(unmatchedValue)
                        .append("].\n");
            }
        }
        
        int expectedSize = expectation.getExpectedValues().size();
        int actualSize = elementValues.size();
        if (expectedSize != actualSize) {
            failures.append("--- Number of expected list values [")
                    .append(expectedSize)
                    .append("] ")
                    .append("differs from those found [")
                    .append(actualSize)
                    .append("].");
            match = false;
        }
        
        return match;
    }
    
    public String getFailures() {
        return failures.toString();
    }
}
