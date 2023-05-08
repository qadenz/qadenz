package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to end with the given value.
 *
 * @author Tim Slifer
 */
public class EndsWith implements Expectation<String> {
    
    private String text;
    
    public EndsWith(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.endsWith(text);
    }
    
    @Override
    public String description() {
        return "ends with [" + text + "]";
    }
}
