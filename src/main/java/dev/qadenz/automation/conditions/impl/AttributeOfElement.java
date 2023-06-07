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

/**
 * A Condition to evaluate the value of an attribute on an element.
 *
 * @author Tim Slifer
 */
public class AttributeOfElement implements Condition {
    
    private Locator locator;
    private String attributeName;
    private Expectation<String> expectation;
    
    private String attributeValue;
    
    public AttributeOfElement(Locator locator, String attributeName, Expectation<String> expectation) {
        this.locator = locator;
        this.attributeName = attributeName;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Attribute [" + attributeName + "] of element [" + locator.getName() + "] " +
                expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        attributeValue = webInspector.getAttributeOfElement(locator, attributeName);
        
        return expectation.matcher().matches(attributeValue);
    }
    
    @Override
    public String output() {
        return "Found [" + attributeValue + "].";
    }
}
