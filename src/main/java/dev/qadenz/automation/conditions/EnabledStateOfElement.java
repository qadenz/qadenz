package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition for evaluating element to be enabled.
 *
 * @author Tim Slifer
 */
public class EnabledStateOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private Boolean match;
    private boolean enabled;
    
    public EnabledStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Enabled state of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        enabled = webInspector.getEnabledStateOfElement(locator);
        
        match = expectation.matcher().matches(enabled);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + enabled + "].";
    }
}
