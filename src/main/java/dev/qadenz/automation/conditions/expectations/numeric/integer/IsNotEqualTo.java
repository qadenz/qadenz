package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.not;

/**
 * An Expectation for an integer value to be not equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsNotEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IsNotEqualTo(int value) {
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
