package dev.qadenz.automation.expectations.numeric.integer;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be greater than the given value.
 *
 * @author Tim Slifer
 */
public class IntegerIsGreaterThan implements Expectation<Integer> {
    
    private int value;
    
    public IntegerIsGreaterThan(int value) {
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
