/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.LocatorGroup;
import dev.qadenz.automation.ui.WebFinder;

/**
 * A Condition for evaluating whether a group of elements is present on the DOM, regardless of if the elements are
 * visible on the page.
 *
 * @author Tim Slifer
 */
public class PresenceOfElements implements Condition {
    
    private LocatorGroup locatorGroup;
    private Expectation<Boolean> expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public PresenceOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        this.locatorGroup = locatorGroup;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebFinder webFinder = new WebFinder();
        
        for (Locator locator : locatorGroup) {
            boolean present = !webFinder.findAll(locator).isEmpty();
            boolean instanceMatch = expectation.matcher().matches(present);
            
            if (!instanceMatch) {
                failures.append("--> Element [")
                        .append(locator.getName())
                        .append("] was [")
                        .append(present)
                        .append("].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        return match;
    }
    
    @Override
    public String actual() {
        return "Discrepancies: \n" + failures.toString();
    }
    
    @Override
    public String toString() {
        return "Presence of elements [" + locatorGroup.getName() + "] " + expectation + ".";
    }
}
