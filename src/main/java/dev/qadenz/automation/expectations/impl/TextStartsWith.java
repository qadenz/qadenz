/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.StringStartsWith;

/**
 * An Expectation for a String 'actual' value to start with the given 'expected' value.
 *
 * @author Tim Slifer
 */
public class TextStartsWith implements Expectation<String> {
    
    private String expectedText;
    
    public TextStartsWith(String expectedText) {
        this.expectedText = expectedText;
    }
    
    @Override
    public Matcher<String> matcher() {
        return new StringStartsWith(false, expectedText);
    }
    
    @Override
    public String toString() {
        return "starts with [" + expectedText + "]";
    }
}
