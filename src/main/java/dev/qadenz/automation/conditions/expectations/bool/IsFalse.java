/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.bool;

import dev.qadenz.automation.expectations.Expectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

/**
 * An Expectation for a boolean outcome to be false.
 *
 * @author Tim Slifer
 */
public class IsFalse implements Expectation<Boolean> {
    
    @Override
    public Matcher<Boolean> matcher() {
        return new IsEqual<>(false);
    }
    
    @Override
    public String description() {
        return "is FALSE";
    }
}
