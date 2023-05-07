package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to be equal to the given value, ignoring case.
 *
 * @author Tim Slifer
 */
public class EqualsIgnoreCase implements Expectation<String> {
    
    private String text;
    
    public EqualsIgnoreCase(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.equalToIgnoringCase(text);
    }
    
    @Override
    public String description() {
        return "is, ignoring case, equal to [" + text + "]";
    }
}
