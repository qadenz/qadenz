/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.text.IsEqualIgnoringCase;

/**
 * An Expectation for a String value to be equal to the given value, ignoring case.
 *
 * @author Tim Slifer
 */
public class TextEqualsIgnoreCase implements Expectation<String> {
    
    private String expectedText;
    
    public TextEqualsIgnoreCase(String expectedText) {
        this.expectedText = expectedText;
    }
    
    @Override
    public Matcher<String> matcher() {
        return new IsEqualIgnoringCase(expectedText);
    }
    
    @Override
    public String description() {
        return "is, ignoring case, equal to [" + expectedText + "]";
    }
}
