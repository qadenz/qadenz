package dev.qadenz.automation.expectations.string;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.not;

/**
 * An Expectation for a String value to be not equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsNotEqualTo implements Expectation<String> {
    
    private String text;
    
    public IsNotEqualTo(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return not(text);
    }
    
    @Override
    public String description() {
        return "is not equal to [" + text + "]";
    }
}
