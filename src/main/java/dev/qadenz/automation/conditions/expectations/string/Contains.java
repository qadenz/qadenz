package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to contain the given value.
 *
 * @author Tim Slifer
 */
public class Contains implements Expectation<String> {
    
    private String text;
    
    public Contains(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.containsString(text);
    }
    
    @Override
    public String description() {
        return "contains [" + text + "]";
    }
}
