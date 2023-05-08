package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to start with the given value.
 *
 * @author Tim Slifer
 */
public class StartsWith implements Expectation<String> {
    
    private String text;
    
    public StartsWith(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.startsWith(text);
    }
    
    @Override
    public String description() {
        return "starts with [" + text + "]";
    }
}
