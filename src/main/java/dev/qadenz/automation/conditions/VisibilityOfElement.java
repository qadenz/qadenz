package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the DOM,
 * has a height and width greater than zero, and is not styled to be hidden.
 *
 * @author Tim Slifer
 */
public class VisibilityOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private Boolean match;
    private boolean visible;
    
    public VisibilityOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Visibility of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        visible = webInspector.getVisibilityOfElement(locator);
        
        match = expectation.matcher().matches(visible);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + visible + "].";
    }
}
