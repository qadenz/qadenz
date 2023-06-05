/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Expectation;
import dev.qadenz.automation.config.WebDriverProvider;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * A Condition for evaluating whether a JavaScript Alert is present.
 *
 * @author Tim Slifer
 */
public class PresenceOfAlert implements Condition {
    
    private Expectation<Boolean> expectation;
    
    private boolean present;
    
    public PresenceOfAlert(Expectation<Boolean> expectation) {
        this.expectation = expectation;
    }
    
    public String description() {
        return "Presence of Alert " + expectation.description() + ".";
    }
    
    public Boolean result() {
        try {
            WebDriver webDriver = WebDriverProvider.getWebDriver();
            webDriver.switchTo().alert();
            present = true;
        }
        catch (NoAlertPresentException exception) {
            present = false;
        }
        
        return expectation.matcher().matches(present);
    }
    
    public String output() {
        return "Found [" + present + "].";
    }
}
