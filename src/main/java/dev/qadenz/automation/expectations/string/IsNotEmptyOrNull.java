package dev.qadenz.automation.expectations.string;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.emptyOrNullString;

/**
 * An Expectation for a String value to be neither empty nor null.
 *
 * @author Tim Slifer
 */
public class IsNotEmptyOrNull implements Expectation<String> {
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.not(emptyOrNullString());
    }
    
    @Override
    public String description() {
        return "is neither null nor empty";
    }
}
