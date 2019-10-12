package io.slifer.automation.conditions;

import org.openqa.selenium.WebDriver;

/**
 * Interface modeling a condition to be evaluated as part of a validation or synchronization.
 *
 * @author Tim Slifer
 */
public interface Condition {
    
    /**
     * Returns a statement describing the condition and criteria being evaluated.
     *
     * @return
     */
    String description();
    
    /**
     * Evaluates the condition and returns a result based on the expected and actual values.
     *
     * @param webDriver
     *
     * @return
     */
    Boolean result(WebDriver webDriver);
    
    /**
     * Returns a statement describing the actual value being evaluated by the Condition.
     *
     * @return
     */
    String output();
}
