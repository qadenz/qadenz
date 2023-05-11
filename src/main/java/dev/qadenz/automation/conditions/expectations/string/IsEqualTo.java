/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.string;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for a String value to be equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsEqualTo implements Expectation<String> {
    
    private String text;
    
    public IsEqualTo(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.is(text);
    }
    
    @Override
    public String description() {
        return "is equal to [" + text + "]";
    }
}