package io.qadenz.automation.conditions;

/**
 * Interface modeling a condition to be evaluated as part of a validation or synchronization.
 *
 * @author Tim Slifer
 */
public interface Condition {
    
    /**
     * @return A statement describing the condition and criteria being evaluated.
     */
    String description();
    
    /**
     * @return A result based on the expected and actual values of the Condition.
     */
    Boolean result();
    
    /**
     * @return A statement describing the actual value being evaluated by the Condition.
     */
    String output();
}
