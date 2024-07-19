package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

public class TextOfListElementsInOrder implements Condition {
    
    private Locator locator;
    private ListExpectation expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public TextOfListElementsInOrder(Locator locator, ListExpectation expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        List<String> elementValues = webInspector.getTextOfElements(locator);
        
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
    
    @Override
    public String actual() {
        return "Discrepancies: \n" + failures.toString();
    }
    
    @Override
    public String toString() {
        return "Text of each instance of element [" + locator.getName() + "] " + expectation + ".";
    }
}
