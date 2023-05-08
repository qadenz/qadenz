package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.ui.Locator;

/**
 * A Condition for evaluating an element to be selected. This applies only elements such as checkboxes, radio options,
 * and {@code <option>} child of a {@code <select>} elements.
 *
 * @author Tim Slifer
 */
public class SelectedStateOfElement implements Condition {
    
    private Locator locator;
    private Expectation<Boolean> expectation;
    
    private boolean selected;
    
    public SelectedStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        this.locator = locator;
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Selected state of element [" + locator.getName() + "] " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebInspector webInspector = new WebInspector(Conditions.class);
        selected = webInspector.getSelectedStateOfElement(locator);
        
        return expectation.matcher().matches(selected);
    }
    
    @Override
    public String output() {
        return "Found [" + selected + "].";
    }
}
