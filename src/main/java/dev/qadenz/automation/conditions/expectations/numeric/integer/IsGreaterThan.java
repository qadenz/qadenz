package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be greater than the given value.
 *
 * @author Tim Slifer
 */
public class IsGreaterThan implements Expectation<Integer> {
    
    private int value;
    
    public IsGreaterThan(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.greaterThan(value);
    }
    
    @Override
    public String description() {
        return "is greater than [" + value + "]";
    }
}
