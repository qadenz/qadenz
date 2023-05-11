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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on the
 * DOM, as a formatted LocalDate.
 *
 * @author Tim Slifer
 */
public class DirectTextOfElementAsDate implements Condition {
    
    private Locator locator;
    private DateTimeFormatter dateTimeFormatter;
    private TemporalExpectation<LocalDate> expectation;
    
    private LocalDate elementDate;
    
    public DirectTextOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDate> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
        
        this.expectation.setDateTimeFormatter(dateTimeFormatter);
    }
    
    @Override
    public String description() {
        return "Direct text of element [" + locator.getName() + "] as LocalDate " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementDate = webInspector.getDirectTextOfElementAsDate(locator, dateTimeFormatter);
        
        return expectation.matcher().matches(elementDate);
    }
    
    @Override
    public String output() {
        return "Found [" + elementDate + "].";
    }
}