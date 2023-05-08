package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.LocatorGroup;

/**
 * A Condition for evaluating whether a group of elements is enabled.
 *
 * @author Tim Slifer
 */
public class EnabledStateOfElements implements Condition {
    
    private LocatorGroup locatorGroup;
    private Expectation<Boolean> expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public EnabledStateOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        this.locatorGroup = locatorGroup;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Enabled state of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebInspector webInspector = new WebInspector(Conditions.class);
        
        for (Locator locator : locatorGroup) {
            boolean enabled = webInspector.getEnabledStateOfElement(locator);
            boolean instanceMatch = expectation.matcher().matches(enabled);
            
            if (!instanceMatch) {
                failures.append("--> Element [" + locator.getName() + "] was [" + enabled + "].\n");
            }
            
            if (match == null || match) {
                match = instanceMatch;
            }
        }
        
        return match;
    }
    
    @Override
    public String output() {
        return "Discrepancies: \n" + failures.toString();
    }
}
