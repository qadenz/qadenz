package io.qadenz.automation.test;

import io.qadenz.automation.conditions.Condition;
import io.qadenz.automation.conditions.Conditions;

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
