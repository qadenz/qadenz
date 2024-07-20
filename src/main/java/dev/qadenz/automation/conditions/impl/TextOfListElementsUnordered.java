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
import dev.qadenz.automation.conditions.UnorderedListComparator;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

/**
 * A Condition to evaluate the visible inner text of each instance of an element as an unordered list.
 *
 * @author Tim Slifer
 */
public class TextOfListElementsUnordered implements Condition {
    
    private Locator locator;
    private ListExpectation expectation;
    
    private String failures;
    
    public TextOfListElementsUnordered(Locator locator, ListExpectation expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        List<String> elementValues = webInspector.getTextOfElements(locator);
        
        UnorderedListComparator unorderedListComparator = new UnorderedListComparator(expectation, elementValues);
        match = unorderedListComparator.getResult();
        
        if (!match) {
            failures = unorderedListComparator.getFailures();
        }
        
        return match;
    }
    
    @Override
    public String actual() {
        return "Discrepancies: \n" + failures;
    }
    
    @Override
    public String toString() {
        return "Text of each instance of element [" + locator.getName() + "] are listed in any order:\n" + expectation;
    }
}
