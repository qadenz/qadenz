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

import java.text.DecimalFormat;

/**
 * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on the
 * DOM, as a formatted Double.
 *
 * @author Tim Slifer
 */
public class DirectTextOfElementAsDouble implements Condition {
    
    private Locator locator;
    private DecimalFormat decimalFormat;
    private NumericExpectation<Double> expectation;
    
    private String elementText;
    private Double elementValue;
    
    public DirectTextOfElementAsDouble(Locator locator, DecimalFormat decimalFormat,
            NumericExpectation<Double> expectation) {
        this.locator = locator;
        this.decimalFormat = decimalFormat;
        this.expectation = expectation;
        
        this.expectation.setNumberFormat(decimalFormat);
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getDirectTextOfElement(locator);
        elementValue = webInspector.getDirectTextOfElementAsDouble(locator, decimalFormat);
        
        return expectation.matcher().matches(elementValue);
    }
    
    @Override
    public String actual() {
        return "[" + elementText + "] formatted as [" + decimalFormat.format(elementValue) + "]";
    }
    
    @Override
    public String toString() {
        return "Text of element [" + locator.getName() + "] as Double " + expectation.description() + ".";
    }
}
