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
        int minSize = Math.min(expectation.matchers().size(), elementValues.size());
        List<String> unmatchedValues = new ArrayList<>(elementValues);
        for (int i = 0; i < minSize; i++) {
            String instanceValue = expectation.getExpectedValues().get(i);
            Matcher<String> matcher = expectation.matchers().get(i);
            boolean instanceMatch = elementValues.stream().anyMatch(matcher::matches);
            
            if (instanceMatch) {
                unmatchedValues.remove(instanceValue);
            }
            else {
                failures.append("--> expected [" + instanceValue + "].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        if (expectation.getExpectedValues().size() > elementValues.size()) {
            failures.append("--- Expected more list values than were found.\n");
            for (int i = elementValues.size(); i < expectation.matchers().size(); i++) {
                failures.append(
                        "--> expected [" + expectation.getExpectedValues().get(i) + "].\n");
            }
            match = false;
        }
        else if (elementValues.size() > expectation.getExpectedValues().size()) {
            failures.append("--- Found more list values than were expected.\n");
            for (String value : unmatchedValues) {
                failures.append("--> found [" + value + "].\n");
            }
            match = false;
        }
        
        return match;
    }
    
    public String getFailures() {
        return failures.toString();
    }
}
