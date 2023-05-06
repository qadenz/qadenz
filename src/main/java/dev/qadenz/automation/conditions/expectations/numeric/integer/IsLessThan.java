package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be less than the given value.
 *
 * @author Tim Slifer
 */
public class IsLessThan implements Expectation<Integer> {
    
    private int value;
    
    public IsLessThan(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.lessThan(value);
    }
    
    @Override
    public String description() {
        return "is less than [" + value + "]";
    }
}
