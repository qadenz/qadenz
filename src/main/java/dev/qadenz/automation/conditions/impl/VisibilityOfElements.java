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
import dev.qadenz.automation.ui.LocatorGroup;

/**
 * A Condition to evaluate the visibility of a group of elements. An element determined to be visible is present on the
 * DOM, has a height and width greater than zero, and is not styled to be hidden.
 *
 * @author Tim Slifer
 */
public class VisibilityOfElements implements Condition {
    
    private LocatorGroup locatorGroup;
    private Expectation<Boolean> expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public VisibilityOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        this.locatorGroup = locatorGroup;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Visibility of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        
        for (Locator locator : locatorGroup) {
            boolean visible = webInspector.getVisibilityOfElement(locator);
            boolean instanceMatch = expectation.matcher().matches(visible);
            
            if (!instanceMatch) {
                failures.append("--> Element [" + locator.getName() + "] was [" + visible + "].\n");
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
