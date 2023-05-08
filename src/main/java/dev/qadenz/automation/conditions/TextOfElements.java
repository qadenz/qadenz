package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

import java.util.List;

/**
 * A Condition to evaluate the visible inner text of each instance of an element.
 *
 * @author Tim Slifer
 */
public class TextOfElements implements Condition {
    
    private Locator locator;
    private Expectation<String> expectation;
    
    private Boolean match;
    private List<String> elementValues;
    private StringBuilder failures = new StringBuilder();
    
    public TextOfElements(Locator locator, Expectation<String> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Text of each instance of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        elementValues = webInspector.getTextOfElements(locator);
        
        for (int i = 0; i < elementValues.size(); i++) {
            String instanceValue = elementValues.get(i);
            Boolean instanceMatch = expectation.matcher().matches(instanceValue);
            
            if (!instanceMatch) {
                failures.append("--> at index [" + i + "], found [" + instanceValue + "].\n");
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
