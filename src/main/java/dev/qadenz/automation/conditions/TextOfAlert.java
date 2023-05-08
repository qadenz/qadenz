/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.config.WebDriverProvider;
import org.openqa.selenium.WebDriver;

/**
 * A Condition for evaluating the text shown on a JavaScript Alert.
 *
 * @author Tim Slifer
 */
public class TextOfAlert implements Condition {
    
    private Expectation<String> expectation;
    
    private String alertText;
    
    public TextOfAlert(Expectation<String> expectation) {
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Text of Alert " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebDriver webDriver = WebDriverProvider.getWebDriver();
        alertText = webDriver.switchTo().alert().getText();
        
        return expectation.matcher().matches(alertText);
    }
    
    @Override
    public String output() {
        return "Found [" + alertText + "].";
    }
}
