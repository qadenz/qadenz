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
 * A Condition for evaluating an element to be selected. This applies only elements such as checkboxes, radio options,
 * and {@code <option>} child of a {@code <select>} elements.
 *
 * @author Tim Slifer
 */
public class SelectedStateOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private boolean selected;
    
    public SelectedStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        selected = webInspector.getSelectedStateOfElement(locator);
        
        return expectation.matcher().matches(selected);
    }
    
    @Override
    public String actual() {
        return String.valueOf(selected);
    }
    
    @Override
    public String toString() {
        return "Selected state of element [" + locator.getName() + "] " + expectation + ".";
    }
}
