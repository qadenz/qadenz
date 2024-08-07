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
import dev.qadenz.automation.ui.WebFinder;

/**
 * A Condition for evaluating whether an element is present on the DOM, regardless of if the element is visible on the
 * page.
 *
 * @author Tim Slifer
 */
public class PresenceOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private boolean present;
    
    public PresenceOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        WebFinder webFinder = new WebFinder();
        present = !webFinder.findAll(locator).isEmpty();
        
        return expectation.matcher().matches(present);
    }
    
    @Override
    public String actual() {
        return String.valueOf(present);
    }
    
    @Override
    public String toString() {
        return "Presence of element [" + locator.getName() + "] " + expectation + ".";
    }
}
