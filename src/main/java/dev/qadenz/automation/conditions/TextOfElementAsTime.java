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
    private Expectation<LocalTime> expectation;
    
    private LocalTime elementTime;
    
    public TextOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            Expectation<LocalTime> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Time-formatted text of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementTime = webInspector.getTextOfElementAsTime(locator, dateTimeFormatter);
        
        return expectation.matcher().matches(elementTime);
    }
    
    @Override
    public String output() {
        return "Found [" + elementTime + "].";
    }
}
