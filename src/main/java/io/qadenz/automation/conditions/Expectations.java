/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.conditions;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class Expectations {
    
    /**
     * An Expectation for a boolean outcome to be true.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isTrue() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Boolean> matcher() {
                return Matchers.is(true);
            }
            
            @Override
            public String description() {
                return "is TRUE";
            }
        };
    }
    
    /**
     * An Expectation for a boolean outcome to be false.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isFalse() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Boolean> matcher() {
                return Matchers.is(false);
            }
            
            @Override
            public String description() {
                return "is FALSE";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThan(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.greaterThan(value);
            }
            
            @Override
            public String description() {
                return "is greater than [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than or equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThanOrEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.greaterThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is greater than or equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.equalTo(value);
            }
            
            @Override
            public String description() {
                return "is equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be not equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isNotEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return not(Matchers.equalTo(value));
            }
            
            @Override
            public String description() {
                return "is not equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than or equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThanOrEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.lessThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is less than or equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThan(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.lessThan(value);
            }
            
            @Override
            public String description() {
                return "is less than [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualTo(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.is(text);
            }
            
            @Override
            public String description() {
                return "is equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the given value, ignoring case.
     *
     * @return The Expectation.
     */
    public static Expectation<String> equalsIgnoreCase(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.equalToIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "is, ignoring case, equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be not equal to the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEqualTo(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.not(text);
            }
            
            @Override
            public String description() {
                return "is not equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> contains(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.containsString(text);
            }
            
            @Override
            public String description() {
                return "contains [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the given value, ignoring case.
     *
     * @return The Expectation.
     */
    public static Expectation<String> containsIgnoreCase(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.containsStringIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "ignoring case, contains [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not contain the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotContain(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.containsString(text));
            }
            
            @Override
            public String description() {
                return "does not contain [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to start with the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> startsWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.startsWith(text);
            }
            
            @Override
            public String description() {
                return "starts with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not start with the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotStartWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.startsWith(text));
            }
            
            @Override
            public String description() {
                return "does not start with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to end with the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> endsWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.endsWith(text);
            }
            
            @Override
            public String description() {
                return "ends with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not end with the given value.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotEndWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.endsWith(text));
            }
            
            @Override
            public String description() {
                return "does not end with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to one of several possible options.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualToOneOf(final String... options) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                List<Matcher<String>> matchers = new ArrayList<>();
                for (String option : options) {
                    matchers.add(Matchers.is(option));
                }
                
                return Matchers.anyOf(matchers.toArray(new Matcher[matchers.size()]));
            }
            
            @Override
            public String description() {
                return "is equal to one of [" + Arrays.toString(options) + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to either empty or null.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEmptyOrNull() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.is(emptyOrNullString());
            }
            
            @Override
            public String description() {
                return "is null or empty";
            }
        };
    }
}
