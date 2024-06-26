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
import dev.qadenz.automation.conditions.NumberFormatters;
import dev.qadenz.automation.expectations.NumericExpectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the number of times an element appears on a page.
 *
 * @author Tim Slifer
 */
public class CountOfElement implements Condition {
    
    private Locator locator;
    private NumericExpectation<Integer> expectation;
    
    private int elementCount;
    
    public CountOfElement(Locator locator, NumericExpectation<Integer> expectation) {
        this.locator = locator;
        this.expectation = expectation;
        
        this.expectation.setNumberFormat(NumberFormatters.nonGroupedNumber());
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementCount = webInspector.getCountOfElement(locator);
        
        return expectation.matcher().matches(elementCount);
    }
    
    @Override
    public String actual() {
        return String.valueOf(elementCount);
    }
    
    @Override
    public String toString() {
        return "Count of element [" + locator.getName() + "] " + expectation + ".";
    }
}
