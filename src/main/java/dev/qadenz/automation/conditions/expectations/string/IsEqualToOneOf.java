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
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An Expectation for a String value to be equal to one of several possible options.
 *
 * @author Tim Slifer
 */
public class IsEqualToOneOf implements Expectation<String> {
    
    private String[] options;
    
    public IsEqualToOneOf(String... options) {
        this.options = options;
    }
    
    @Override
    public Matcher<String> matcher() {
        List<Matcher<String>> matchers = new ArrayList<>();
        for (String option : options) {
            matchers.add(new IsEqual<>(option));
        }
        
        return new AnyOf<>(matchers.toArray(new Matcher[matchers.size()]));
    }
    
    @Override
    public String description() {
        return "is equal to one of [" + Arrays.toString(options) + "]";
    }
}
