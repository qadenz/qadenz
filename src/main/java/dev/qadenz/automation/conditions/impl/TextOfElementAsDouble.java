/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.conditions.NumericExpectation;
import dev.qadenz.automation.ui.Locator;

import java.text.DecimalFormat;

/**
 * A Condition to evaluate the visible inner text of an element as a formatted Double.
 *
 * @author Tim Slifer
 */
public class TextOfElementAsDouble implements Condition {
    
    private Locator locator;
    private DecimalFormat decimalFormat;
    private NumericExpectation<Double> expectation;
    
    private String elementText;
    private Double elementValue;
    
    public TextOfElementAsDouble(Locator locator, DecimalFormat decimalFormat,
            NumericExpectation<Double> expectation) {
        this.locator = locator;
        this.decimalFormat = decimalFormat;
        this.expectation = expectation;
        
        this.expectation.setNumberFormat(decimalFormat);
    }
    
    @Override
    public String description() {
        return "Text of element [" + locator.getName() + "] as Double " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getTextOfElement(locator);
        elementValue = webInspector.getTextOfElementAsDouble(locator, decimalFormat);
        
        return expectation.matcher().matches(elementValue);
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "] formatted as [" + decimalFormat.format(elementValue) + "].";
    }
}
