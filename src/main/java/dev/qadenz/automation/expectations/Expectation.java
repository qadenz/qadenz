/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations;

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
}
