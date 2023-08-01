/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

/**
 * Interface modeling a condition to be evaluated as part of a validation or synchronization.
 *
 * @author Tim Slifer
 */
public interface Condition {
    
    /**
     * @return A result based on the expected and actual values of the Condition.
     */
    Boolean result();
    
    /**
     * @return A statement describing the actual value being evaluated by the Condition.
     */
    String actual();
}
