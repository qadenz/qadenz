package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be less than or equal to the given value.
 *
 * @author Tim Slifer
 */
public class IntegerIsLessThanOrEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IntegerIsLessThanOrEqualTo(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.lessThanOrEqualTo(value);
    }
    
    @Override
    public String description() {
        return "is less than or equal to [" + value + "]";
    }
}
