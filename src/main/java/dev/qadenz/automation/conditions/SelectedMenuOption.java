package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition for evaluating the currently selected option of a Select menu element.
 *
 * @author Tim Slifer
 */
public class SelectedMenuOption implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private Boolean match;
    private String selectedOption;
    
    public SelectedMenuOption(Locator locator, Expectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Selected option of menu element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        selectedOption = webInspector.getSelectedMenuOption(locator);
        
        match = expectation.matcher().matches(selectedOption);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + selectedOption + "].";
    }
}
