package dev.qadenz.automation.expectations.string;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.not;

/**
 * An Expectation for a String value to not end with the given value.
 *
 * @author Tim Slifer
 */
public class DoesNotEndWith implements Expectation<String> {
    
    private String text;
    
    public DoesNotEndWith(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return not(Matchers.endsWith(text));
    }
    
    @Override
    public String description() {
        return "does not end with [" + text + "]";
    }
}
