package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IsEqualTo(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.equalTo(value);
    }
    
    @Override
    public String description() {
        return "is equal to [" + value + "]";
    }
}
