package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.emptyOrNullString;

/**
 * An Expectation for a String value to be either empty or null.
 *
 * @author Tim Slifer
 */
public class IsEmptyOrNull implements Expectation<String> {
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.is(emptyOrNullString());
    }
    
    @Override
    public String description() {
        return "is null or empty";
    }
}
