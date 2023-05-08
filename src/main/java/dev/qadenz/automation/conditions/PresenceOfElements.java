package dev.qadenz.automation.conditions;

import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.LocatorGroup;
import dev.qadenz.automation.ui.WebFinder;

/**
 * A Condition for evaluating whether a group of elements is present on the DOM, regardless of if the elements are
 * visible on the page.
 *
 * @author Tim Slifer
 */
public class PresenceOfElements implements Condition {
    
    private LocatorGroup locatorGroup;
    private Expectation<Boolean> expectation;
    
    private StringBuilder failures = new StringBuilder();
    
    public PresenceOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        this.locatorGroup = locatorGroup;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Presence of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        Boolean match = null;
        WebFinder webFinder = new WebFinder();
        
        for (Locator locator : locatorGroup) {
            boolean present = webFinder.findAll(locator).size() > 0;
            boolean instanceMatch = expectation.matcher().matches(present);
            
            if (!instanceMatch) {
                failures.append("--> Element [" + locator.getName() + "] was [" + present + "].\n");
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
