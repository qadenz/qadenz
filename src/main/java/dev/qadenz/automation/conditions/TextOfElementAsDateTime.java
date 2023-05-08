package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Condition to evaluate the visible inner text of an element as a formatted LocalDateTime.
 *
 * @author Tim Slifer
 */
public class TextOfElementAsDateTime implements Condition {
    
    private Locator locator;
    private DateTimeFormatter dateTimeFormatter;
    private Expectation<LocalDateTime> expectation;
    
    private LocalDateTime elementDateTime;
    
    public TextOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            Expectation<LocalDateTime> expectation) {
        this.locator = locator;
        this.dateTimeFormatter = dateTimeFormatter;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "DateTime-formatted text of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementDateTime = webInspector.getTextOfElementAsDateTime(locator, dateTimeFormatter);
        
        return expectation.matcher().matches(elementDateTime);
    }
    
    @Override
    public String output() {
        return "Found [" + elementDateTime + "].";
    }
}
