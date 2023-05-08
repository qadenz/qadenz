package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on the
 * DOM.
 *
 * @author Tim Slifer
 */
public class DirectTextOfElement implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private Boolean match;
    private String elementText;
    
    public DirectTextOfElement(Locator locator, Expectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Direct text of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getDirectTextOfElement(locator);
        
        match = expectation.matcher().matches(elementText);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "].";
    }
}
