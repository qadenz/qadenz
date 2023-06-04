/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
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
    private DecimalFormat numberFormat;
    private NumericExpectation<Double> expectation;
    
    private String elementText;
    private Double elementValue;
    
    public DirectTextOfElementAsDouble(Locator locator, DecimalFormat numberFormat,
            NumericExpectation<Double> expectation) {
        this.locator = locator;
        this.numberFormat = numberFormat;
        this.expectation = expectation;
        
        this.expectation.setNumberFormat(numberFormat);
    }
    
    @Override
    public String description() {
        return "Text of element [" + locator.getName() + "] as Double " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getDirectTextOfElement(locator);
        elementValue = webInspector.getDirectTextOfElementAsDouble(locator, numberFormat);
        
        return expectation.matcher().matches(elementValue);
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "] formatted as [" + numberFormat.format(elementValue) + "].";
    }
}
