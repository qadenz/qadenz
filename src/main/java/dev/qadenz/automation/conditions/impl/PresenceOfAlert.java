/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.expectations.Expectation;
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
    
    @Override
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
    
    @Override
    public String actual() {
        return String.valueOf(present);
    }
    
    @Override
    public String toString() {
        return "Presence of Alert " + expectation + ".";
    }
}
