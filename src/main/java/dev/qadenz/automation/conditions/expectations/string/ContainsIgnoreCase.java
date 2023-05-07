package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to contain the given value, ignoring case.
 *
 * @author Tim Slifer
 */
public class ContainsIgnoreCase implements Expectation<String> {
    
    private String text;
    
    public ContainsIgnoreCase(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.containsStringIgnoringCase(text);
    }
    
    @Override
    public String description() {
        return "ignoring case, contains [" + text + "]";
    }
}
