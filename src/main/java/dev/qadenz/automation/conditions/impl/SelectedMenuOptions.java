package dev.qadenz.automation.conditions.impl;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;
import dev.qadenz.automation.conditions.OrderedListComparator;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

/**
 * A Condition for evaluating each of the currently selected options of a Select menu element.
 *
 * @author Tim Slifer
 */
public class SelectedMenuOptions implements Condition {
    
    private Locator locator;
    private ListExpectation expectation;
    
    private String failures;
    
    public SelectedMenuOptions(Locator locator, ListExpectation expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        List<String> elementValues = webInspector.getSelectedMenuOptions(locator);
        
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
        return "Selected options of menu element [" + locator.getName() + "] " + expectation + ".";
    }
}
