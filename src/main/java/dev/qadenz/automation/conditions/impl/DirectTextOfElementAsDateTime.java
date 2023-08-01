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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on the
 * DOM, as a formatted LocalDateTime.
 *
 * @author Tim Slifer
 */
public class DirectTextOfElementAsDateTime implements Condition {
    
    private Locator locator;
    private DateTimeFormatter dateTimeFormatter;
    private TemporalExpectation<LocalDateTime> expectation;
    
    private String elementText;
    private LocalDateTime elementDateTime;
    
    public DirectTextOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDateTime> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
        
        this.expectation.setDateTimeFormatter(dateTimeFormatter);
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getDirectTextOfElement(locator);
        elementDateTime = webInspector.getDirectTextOfElementAsDateTime(locator, dateTimeFormatter);
        
        return expectation.matcher().matches(elementDateTime);
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "] formatted as [" + dateTimeFormatter.format(elementDateTime) + "].";
    }
    
    @Override
    public String toString() {
        return "Direct text of element [" + locator.getName() + "] as LocalDateTime" + expectation.description() + ".";
    }
}
