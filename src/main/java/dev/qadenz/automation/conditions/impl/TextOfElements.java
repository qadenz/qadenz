/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

/**
 * A Condition to evaluate the visible inner text of each instance of an element.
 *
 * @author Tim Slifer
 */
public class TextOfElements implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private List<String> elementValues;
    private StringBuilder failures = new StringBuilder();
    
    public TextOfElements(Locator locator, Expectation<String> expectation) {
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
            boolean instanceMatch = expectation.matcher().matches(instanceValue);
            
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
        return "Text of each instance of element [" + locator.getName() + "] " + expectation + ".";
    }
}
