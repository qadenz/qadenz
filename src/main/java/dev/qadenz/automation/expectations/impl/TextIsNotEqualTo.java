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
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;

/**
 * An Expectation for a String value to be not equal to the given value.
 *
 * @author Tim Slifer
 */
public class TextIsNotEqualTo implements Expectation<String> {
    
    private String expectedText;
    
    public TextIsNotEqualTo(String expectedText) {
        this.expectedText = expectedText;
    }
    
    @Override
    public Matcher<String> matcher() {
        return new IsNot<>(new IsEqual<>(expectedText));
    }
    
    @Override
    public String description() {
        return "is not equal to [" + expectedText + "]";
    }
}
