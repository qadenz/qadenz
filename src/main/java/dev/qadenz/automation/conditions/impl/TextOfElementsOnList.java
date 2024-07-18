package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

public class TextOfElementsOnList implements Condition {
    
    private Locator locator;
    private Expectation<List<String>> expectation;
    
    private List<String> elementValues;
    private StringBuilder failures = new StringBuilder();
    
    public TextOfElementsOnList(Locator locator, Expectation<List<String>> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementValues = webInspector.getTextOfElements(locator);
        Boolean match = expectation.matcher().matches(elementValues);
        
        //        if (!match) {
        //            failures.append("Expected: \n");
        //            for (String value : expectation.matcher().toString()) {
        //                failures.append(value).append("\n");
        //            }
        //
        //            failures.append("Actual: \n");
        //            for (String value : elementValues) {
        //                failures.append(value).append("\n");
        //            }
        //        }
        
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
