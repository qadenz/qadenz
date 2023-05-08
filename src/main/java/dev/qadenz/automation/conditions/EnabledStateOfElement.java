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

/**
 * A Condition for evaluating element to be enabled.
 *
 * @author Tim Slifer
 */
public class EnabledStateOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private boolean enabled;
    
    public EnabledStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Enabled state of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        enabled = webInspector.getEnabledStateOfElement(locator);
        
        return expectation.matcher().matches(enabled);
    }
    
    @Override
    public String output() {
        return "Found [" + enabled + "].";
    }
}
