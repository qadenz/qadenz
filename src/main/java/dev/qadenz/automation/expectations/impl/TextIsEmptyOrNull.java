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
import org.hamcrest.text.IsEmptyString;

/**
 * An Expectation for a String value to be either empty or null.
 *
 * @author Tim Slifer
 */
public class TextIsEmptyOrNull implements Expectation<String> {
    
    @Override
    public Matcher<String> matcher() {
        return IsEmptyString.emptyOrNullString();
    }
    
    @Override
    public String toString() {
        return "is null or empty";
    }
}
