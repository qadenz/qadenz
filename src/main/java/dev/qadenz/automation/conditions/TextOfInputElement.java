package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the text shown inside an {@code <input>} element.
 *
 * @author Tim Slifer
 */
public class TextOfInputElement implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private String elementText;
    
    public TextOfInputElement(Locator locator, Expectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Text of input element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementText = webInspector.getAttributeOfElement(locator, "value");
        
        return expectation.matcher().matches(elementText);
    }
    
    @Override
    public String output() {
        return "Found [" + elementText + "].";
    }
}
