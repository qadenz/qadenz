/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.numeric.integer;

import dev.qadenz.automation.conditions.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * An Expectation for an integer value to be equal to the given value.
 *
 * @author Tim Slifer
 */
public class IntegerIsEqualTo implements Expectation<Integer> {
    
    private int value;
    
    public IntegerIsEqualTo(int value) {
        this.value = value;
    }
    
    @Override
    public Matcher<Integer> matcher() {
        return Matchers.equalTo(value);
    }
    
    @Override
    public String description() {
        return "is equal to [" + value + "]";
    }
}
