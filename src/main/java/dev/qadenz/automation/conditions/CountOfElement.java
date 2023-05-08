package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the number of times an element appears on a page.
 *
 * @author Tim Slifer
 */
public class CountOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Integer> expectation;
    
    private Boolean match;
    private int elementCount;
    
    public CountOfElement(Locator locator, Expectation<Integer> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Count of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementCount = webInspector.getCountOfElement(locator);
        
        match = expectation.matcher().matches(elementCount);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + elementCount + "].";
    }
}
