package dev.qadenz.automation.expectations;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * Interface modeling an expectation used for evaluating List-based Conditions.
 *
 * @author Tim Slifer
 */
public interface ListExpectation<T> {
    
    /**
     * @return A Matcher that defines the expectation.
     */
    List<Matcher<T>> matchers();
}
