package io.slifer.automation.test;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.conditions.Conditions;

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
                return "";
            }
            
            @Override
            public Boolean result() {
                return true;
            }
            
            @Override
            public String output() {
                return "";
            }
        };
    }
    
    public static Condition returnsFalse() {
        return new Condition() {
            @Override
            public String description() {
                return "";
            }
            
            @Override
            public Boolean result() {
                return false;
            }
            
            @Override
            public String output() {
                return "";
            }
        };
    }
    
    public static Condition throwsError() {
        return new Condition() {
            @Override
            public String description() {
                return "";
            }
            
            @Override
            public Boolean result() {
                throw new IllegalStateException("Testing a validation that produces an error.");
            }
            
            @Override
            public String output() {
                return "";
            }
        };
    }
}
