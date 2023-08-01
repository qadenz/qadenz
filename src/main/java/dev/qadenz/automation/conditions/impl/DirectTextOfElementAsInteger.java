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
import dev.qadenz.automation.expectations.NumericExpectation;
import dev.qadenz.automation.ui.Locator;

import java.text.NumberFormat;

/**
 * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on the
 * DOM, as a formatted Integer.
 *
 * @author Tim Slifer
 */
public class DirectTextOfElementAsInteger implements Condition {
    
    private Locator locator;
    private NumberFormat numberFormat;
    private NumericExpectation<Integer> expectation;
    
    private String elementText;
    private Integer elementValue;
    
    public DirectTextOfElementAsInteger(Locator locator, NumberFormat numberFormat,
            NumericExpectation<Integer> expectation) {
        this.locator = locator;
        this.numberFormat = numberFormat;
        this.expectation = expectation;
        
        this.expectation.setNumberFormat(numberFormat);
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getDirectTextOfElement(locator);
        elementValue = webInspector.getDirectTextOfElementAsInteger(locator, numberFormat);
        
        return expectation.matcher().matches(elementValue);
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "] formatted as [" + numberFormat.format(elementValue) + "].";
    }
    
    @Override
    public String toString() {
        return "Text of element [" + locator.getName() + "] as Integer " + expectation.description() + ".";
    }
}
