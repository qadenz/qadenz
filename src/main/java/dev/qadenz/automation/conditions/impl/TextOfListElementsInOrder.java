package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.conditions.ListComparator;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

public class TextOfListElementsInOrder implements Condition {
    
    private Locator locator;
    private ListExpectation expectation;
    
    private String failures;
    
    public TextOfListElementsInOrder(Locator locator, ListExpectation expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        List<String> elementValues = webInspector.getTextOfElements(locator);
        
        ListComparator listComparator = new ListComparator(expectation, elementValues);
        match = listComparator.getResult();
        
        if (!match) {
            failures = listComparator.getFailures();
        }
        
        return match;
    }
    
    @Override
    public String actual() {
        return "Discrepancies: \n" + failures;
    }
    
    @Override
    public String toString() {
        return "Text of each instance of element [" + locator.getName() + "] " + expectation + ".";
    }
}
