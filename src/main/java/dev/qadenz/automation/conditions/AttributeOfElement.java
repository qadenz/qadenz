package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition to evaluate the value of an attribute on an element.
 *
 * @author Tim Slifer
 */
public class AttributeOfElement implements Condition {
    
    private Locator locator;
    private String attributeName;
    private Expectation<String> expectation;
    
    private String attributeValue;
    
    public AttributeOfElement(Locator locator, String attributeName, Expectation<String> expectation) {
        this.locator = locator;
        this.attributeName = attributeName;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Attribute [" + attributeName + "] of element [" + locator.getName() + "] " +
                expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        attributeValue = webInspector.getAttributeOfElement(locator, attributeName);
        
        return expectation.matcher().matches(attributeValue);
    }
    
    @Override
    public String output() {
        return "Found [" + attributeValue + "].";
    }
}
