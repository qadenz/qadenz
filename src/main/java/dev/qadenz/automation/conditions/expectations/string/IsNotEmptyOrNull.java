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

import static org.hamcrest.Matchers.emptyOrNullString;

/**
 * An Expectation for a String value to be neither empty nor null.
 *
 * @author Tim Slifer
 */
public class IsNotEmptyOrNull implements Expectation<String> {
    
    @Override
    public Matcher<String> matcher() {
        return Matchers.not(emptyOrNullString());
    }
    
    @Override
    public String description() {
        return "is neither null nor empty";
    }
}