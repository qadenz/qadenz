package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

public class TextOfElementsOnList implements Condition {
    
    private Locator locator;
    private ListExpectation<String> expectation;
    
    private List<String> elementValues;
    private StringBuilder failures = new StringBuilder();
    
    public TextOfElementsOnList(Locator locator, ListExpectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementValues = webInspector.getTextOfElements(locator);
        
        for (int i = 0; i < elementValues.size(); i++) {
            String instanceValue = elementValues.get(i);
            boolean instanceMatch = expectation.matchers().get(i).matches(instanceValue);
            
            if (!instanceMatch) {
                failures.append("--> at index [" + i + "], found [" + instanceValue + "].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
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
        return "Text of of each instance of element [" + locator.getName() + "] " + expectation + ".";
    }
}
