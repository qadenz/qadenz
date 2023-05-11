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

import static org.hamcrest.Matchers.not;

/**
 * An Expectation for a String value to be not equal to the given value.
 *
 * @author Tim Slifer
 */
public class IsNotEqualTo implements Expectation<String> {
    
    private String text;
    
    public IsNotEqualTo(String text) {
        this.text = text;
    }
    
    @Override
    public Matcher<String> matcher() {
        return not(text);
    }
    
    @Override
    public String description() {
        return "is not equal to [" + text + "]";
    }
}