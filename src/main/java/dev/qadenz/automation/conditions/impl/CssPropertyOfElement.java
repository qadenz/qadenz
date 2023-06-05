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
import dev.qadenz.automation.conditions.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the value of a CSS property on an element.
 *
 * @author Tim Slifer
 */
public class CssPropertyOfElement implements Condition {
    
    private Locator locator;
    private String cssPropertyName;
    private Expectation<String> expectation;
    
    private String cssPropertyValue;
    
    public CssPropertyOfElement(Locator locator, String cssPropertyName, Expectation<String> expectation) {
        this.locator = locator;
        this.cssPropertyName = cssPropertyName;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "CSS Property [" + cssPropertyName + "] of element [" + locator.getName() + "] " +
                expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        cssPropertyValue = webInspector.getCssPropertyOfElement(locator, cssPropertyName);
        
        return expectation.matcher().matches(cssPropertyName);
    }
    
    @Override
    public String output() {
        return "Found [" + cssPropertyValue + "].";
    }
}
