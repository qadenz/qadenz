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
 * A Condition for evaluating the currently selected option of a Select menu element.
 *
 * @author Tim Slifer
 */
public class SelectedMenuOption implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private String selectedOption;
    
    public SelectedMenuOption(Locator locator, Expectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        selectedOption = webInspector.getSelectedMenuOption(locator);
        
        return expectation.matcher().matches(selectedOption);
    }
    
    @Override
    public String actual() {
        return selectedOption;
    }
    
    @Override
    public String toString() {
        return "Selected option of menu element [" + locator.getName() + "] " + expectation + ".";
    }
}
