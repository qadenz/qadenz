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
 * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the DOM,
 * has a height and width greater than zero, and is not styled to be hidden.
 *
 * @author Tim Slifer
 */
public class VisibilityOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private boolean visible;
    
    public VisibilityOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        visible = webInspector.getVisibilityOfElement(locator);
        
        return expectation.matcher().matches(visible);
    }
    
    @Override
    public String actual() {
        return String.valueOf(visible);
    }
    
    @Override
    public String toString() {
        return "Visibility of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
}
