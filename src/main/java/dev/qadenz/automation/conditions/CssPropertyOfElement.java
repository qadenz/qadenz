package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the value of a CSS property on an element.
 *
 * @author Tim Slifer
 */
public class CssPropertyOfElement implements Condition {
    
    private Locator locator;
    private String cssPropertyName;
    private Expectation<String> expectation;
    
    private String cssPropertyValue;
    
    public CssPropertyOfElement(Locator locator, String cssPropertyName, Expectation<String> expectation) {
        this.locator = locator;
        this.cssPropertyName = cssPropertyName;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "CSS Property [" + cssPropertyName + "] of element [" + locator.getName() + "] " +
                expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        cssPropertyValue = webInspector.getCssPropertyOfElement(locator, cssPropertyName);
        
        return expectation.matcher().matches(cssPropertyName);
    }
    
    @Override
    public String output() {
        return "Found [" + cssPropertyValue + "].";
    }
}
