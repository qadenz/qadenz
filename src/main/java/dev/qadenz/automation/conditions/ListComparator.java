package dev.qadenz.automation.conditions;

import dev.qadenz.automation.expectations.ListExpectation;

import java.util.List;

public class ListComparator {
    
    private ListExpectation expectation;
    private List<String> elementValues;
    private StringBuilder failures;
    
    public ListComparator(ListExpectation expectation, List<String> elementValues) {
        this.expectation = expectation;
        this.elementValues = elementValues;
        this.failures = new StringBuilder();
    }
    
    public Boolean getResult() {
        
        Boolean match = null;
        for (int i = 0; i < expectation.matchers().size(); i++) {
            String instanceValue = elementValues.get(i);
            boolean instanceMatch = expectation.matchers().get(i).matches(instanceValue);
            
            if (!instanceMatch) {
                failures.append("--> at index [" + i + "], found [" + instanceValue + "].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        if (expectation.getExpectedValues().size() > elementValues.size()) {
            failures.append("--- Expected more list values than were found.\n");
            for (int i = elementValues.size(); i < expectation.matchers().size(); i++) {
                failures.append(
                        "--> at index [" + i + "], expected [" + expectation.getExpectedValues().get(i) + "].\n");
            }
        }
        else if (elementValues.size() > expectation.getExpectedValues().size()) {
            failures.append("--- Found more list values than were expected.\n");
            for (int i = expectation.getExpectedValues().size(); i < elementValues.size(); i++) {
                failures.append("--> at index [" + i + "], found [" + elementValues.get(i) + "].\n");
            }
        }
        
        return match;
    }
    
    public String getFailures() {
        return failures.toString();
    }
}
