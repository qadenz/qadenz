package dev.qadenz.automation.conditions.expectations.bool;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a boolean outcome to be true.
 *
 * @author Tim Slifer
 */
public class IsTrue implements Expectation<Boolean> {
    
    @Override
    public Matcher<Boolean> matcher() {
        return Matchers.is(true);
    }
    
    @Override
    public String description() {
        return "is TRUE";
    }
}
