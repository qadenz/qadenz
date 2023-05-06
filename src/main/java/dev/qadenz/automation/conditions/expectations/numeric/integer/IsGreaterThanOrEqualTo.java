package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be greater than or equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsGreaterThanOrEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IsGreaterThanOrEqualTo(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.greaterThanOrEqualTo(value);
    }
    
    @Override
    public String description() {
        return "is greater than or equal to [" + value + "]";
    }
}
