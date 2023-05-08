package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Condition to evaluate the visible inner text of an element as a formatted LocalDate.
 *
 * @author Tim Slifer
 */
public class TextOfElementAsDate implements Condition {
    
    private Locator locator;
    private DateTimeFormatter dateTimeFormatter;
    private Expectation<LocalDate> expectation;
    
    private Boolean match;
    private LocalDate elementDate;
    
    public TextOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter,
            Expectation<LocalDate> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Date-formatted text of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementDate = webInspector.getTextOfElementAsDate(locator, dateTimeFormatter);
        
        match = expectation.matcher().matches(elementDate);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + elementDate + "].";
    }
}
