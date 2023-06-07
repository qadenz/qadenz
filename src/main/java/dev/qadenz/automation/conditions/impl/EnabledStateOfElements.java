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
import dev.qadenz.automation.ui.LocatorGroup;

/**
 * A Condition for evaluating whether a group of elements is enabled.
 *
 * @author Tim Slifer
 */
public class EnabledStateOfElements implements Condition {
    
    private LocatorGroup locatorGroup;
    private Expectation<Boolean> expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public EnabledStateOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        this.locatorGroup = locatorGroup;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Enabled state of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        
        for (Locator locator : locatorGroup) {
            boolean enabled = webInspector.getEnabledStateOfElement(locator);
            boolean instanceMatch = expectation.matcher().matches(enabled);
            
            if (!instanceMatch) {
                failures.append("--> Element [" + locator.getName() + "] was [" + enabled + "].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        return match;
    }
    
    @Override
    public String output() {
        return "Discrepancies: \n" + failures.toString();
    }
}
