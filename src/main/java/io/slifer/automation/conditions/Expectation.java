package io.slifer.automation.conditions;

import org.hamcrest.Matcher;

/**
 * Interface modeling an expectation used for evaluating Conditions.
 *
 * @author Tim Slifer
 */
public interface Expectation<T> {
    
    /**
     * Returns a Matcher that defines the expectation.
     *
     * @return
     */
    Matcher<T> matcher();
    
    /**
     * Returns a statement describing the expectation.
     *
     * @return
     */
    
    String description();
}
