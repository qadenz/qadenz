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
import dev.qadenz.automation.expectations.TemporalExpectation;
import dev.qadenz.automation.ui.Locator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Condition to evaluate the visible inner text of an element as a formatted LocalTime.
 *
 * @author Tim Slifer
 */
public class TextOfElementAsTime implements Condition {
    
    private Locator locator;
    private DateTimeFormatter dateTimeFormatter;
    private TemporalExpectation<LocalTime> expectation;
    
    private String elementText;
    private LocalTime elementTime;
    
    public TextOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalTime> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
        
        this.expectation.setDateTimeFormatter(dateTimeFormatter);
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getTextOfElement(locator);
        elementTime = webInspector.getTextOfElementAsTime(locator, dateTimeFormatter);
        
        return expectation.matcher().matches(elementTime);
    }
    
    @Override
    public String actual() {
        return "[" + elementText + "] formatted as [" + dateTimeFormatter.format(elementTime) + "]";
    }
    
    @Override
    public String toString() {
        return "Text of element [" + locator.getName() + "] as LocalTime " + expectation + ".";
    }
}
