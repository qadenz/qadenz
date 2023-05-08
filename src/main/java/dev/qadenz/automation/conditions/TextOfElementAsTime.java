package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
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
    
    private Boolean match;
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
        
        match = expectation.matcher().matches(elementTime);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + elementTime + "].";
    }
}
