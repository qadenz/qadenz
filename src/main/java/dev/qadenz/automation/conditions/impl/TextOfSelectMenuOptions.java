package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.conditions.OrderedListComparator;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

/**
 * A Condition to evaluate the values of each {@code <option>} child of a {@code <select>} element.
 *
 * @author Tim Slifer
 */
public class TextOfSelectMenuOptions implements Condition {
    
    private Locator locator;
    private ListExpectation expectation;
    
    private String failures;
    
    public TextOfSelectMenuOptions(Locator locator, ListExpectation expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        List<String> elementValues = webInspector.getTextOfOptions(locator);
        
        OrderedListComparator orderedListComparator = new OrderedListComparator(expectation, elementValues);
        match = orderedListComparator.getResult();
        
        if (!match) {
            failures = orderedListComparator.getFailures();
        }
        
        return match;
    }
    
    @Override
    public String actual() {
        return "Discrepancies: \n" + failures;
    }
    
    @Override
    public String toString() {
        return "Text of options on Select element [" + locator.getName() + "] " + expectation + ".";
    }
}
