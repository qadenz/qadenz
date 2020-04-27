package io.slifer.automation.conditions;

import org.hamcrest.Matcher;

/**
 * Interface modeling an expectation used for evaluating Conditions.
 *
 * @author Tim Slifer
 */
public interface Expectation<T> {
    
    /**
     * @return A Matcher that defines the expectation.
     */
    Matcher<T> matcher();
    
    /**
     * @return A statement describing the expectation.
     */
    String description();
}
