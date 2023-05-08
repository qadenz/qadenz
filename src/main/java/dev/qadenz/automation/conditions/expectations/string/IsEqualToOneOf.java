package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An Expectation for a String value to be equal to one of several possible options.
 *
 * @author Tim Slifer
 */
public class IsEqualToOneOf implements Expectation<String> {
    
    private String[] options;
    
    public IsEqualToOneOf(String... options) {
        this.options = options;
    }
    
    @Override
    public Matcher<String> matcher() {
        List<Matcher<String>> matchers = new ArrayList<>();
        for (String option : options) {
            matchers.add(Matchers.is(option));
        }
        
        return Matchers.anyOf(matchers.toArray(new Matcher[matchers.size()]));
    }
    
    @Override
    public String description() {
        return "is equal to one of [" + Arrays.toString(options) + "]";
    }
}
