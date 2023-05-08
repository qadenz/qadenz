package dev.qadenz.automation.expectations.numeric.integer;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.not;

/**
 * An Expectation for an integer value to be not equal to the given value.
 *
 * @author Tim Slifer
 */
public class IntegerIsNotEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IntegerIsNotEqualTo(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return not(Matchers.equalTo(value));
    }
    
    @Override
    public String description() {
        return "is not equal to [" + value + "]";
    }
}
