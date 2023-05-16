/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.util;

import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.conditions.Conditions;

/**
 * A collection of generic {@link Condition}s used specifically for testing that provide controlled return values
 * without mocking, and avoid the need to randomly call {@link Conditions} methods.
 *
 * @author Tim Slifer
 */
public class TestConditions {
    
    public static Condition returnsTrue() {
        return new Condition() {
            @Override
            public String description() {
                return "Always returns true.";
            }
            
            @Override
            public Boolean result() {
                return true;
            }
            
            @Override
            public String output() {
                return "Returned true.";
            }
        };
    }
    
    public static Condition returnsFalse() {
        return new Condition() {
            @Override
            public String description() {
                return "Always returns false.";
            }
            
            @Override
            public Boolean result() {
                return false;
            }
            
            @Override
            public String output() {
                return "Returned false.";
            }
        };
    }
    
    public static Condition throwsError() {
        return new Condition() {
            @Override
            public String description() {
                return "Always throws an error.";
            }
            
            @Override
            public Boolean result() {
                throw new IllegalStateException("Testing a validation that produces an error.");
            }
            
            @Override
            public String output() {
                return "Threw an error.";
            }
        };
    }
}
