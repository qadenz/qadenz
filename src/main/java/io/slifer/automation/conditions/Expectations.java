package io.slifer.automation.conditions;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.emptyOrNullString;

public class Expectations {
    
    /**
     * An Expectation for a boolean outcome to be true.
     *
     * @return The Expectation
     */
    public static Expectation isTrue() {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.is(true);
            }
            
            @Override
            public String description() {
                return "is TRUE.";
            }
        };
    }
    
    /**
     * An Expectation for a boolean outcome to be false.
     *
     * @return The Expectation
     */
    public static Expectation isFalse() {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.is(false);
            }
            
            @Override
            public String description() {
                return "is FALSE.";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isGreaterThan(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.greaterThan(value);
            }
            
            @Override
            public String description() {
                return "is greater than [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than or equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isGreaterThanOrEqualTo(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.greaterThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is greater than or equal to [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isEqualTo(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.equalTo(value);
            }
            
            @Override
            public String description() {
                return "is equal to [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be not equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isNotEqualTo(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return not(Matchers.equalTo(value));
            }
            
            @Override
            public String description() {
                return "is not equal to [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than or equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isLessThanOrEqualTo(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.lessThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is less than or equal to [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isLessThan(final int value) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.lessThan(value);
            }
            
            @Override
            public String description() {
                return "is less than [" + value + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isEqualTo(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.is(text);
            }
            
            @Override
            public String description() {
                return "is equal to [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the expected value, ignoring case.
     *
     * @return The Expectation
     */
    public static Expectation equalsIgnoreCase(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.equalToIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "is, ignoring case, equal to [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be not equal to the expected value.
     *
     * @return The Expectation
     */
    public static Expectation isNotEqualTo(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.not(text);
            }
            
            @Override
            public String description() {
                return "is not equal to [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the expected value.
     *
     * @return The Expectation
     */
    public static Expectation contains(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.containsString(text);
            }
            
            @Override
            public String description() {
                return "contains [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the expected value, ignoring case.
     *
     * @return The Expectation
     */
    public static Expectation containsIgnoreCase(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.containsStringIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "ignoring case, contains [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not contain the expected value.
     *
     * @return The Expectation
     */
    public static Expectation doesNotContain(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return not(Matchers.containsString(text));
            }
            
            @Override
            public String description() {
                return "does not contain [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to start with the expected value.
     *
     * @return The Expectation
     */
    public static Expectation startsWith(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.startsWith(text);
            }
            
            @Override
            public String description() {
                return "starts with [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not start with the expected value.
     *
     * @return The Expectation
     */
    public static Expectation doesNotStartWith(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return not(Matchers.startsWith(text));
            }
            
            @Override
            public String description() {
                return "does not start with [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to end with the expected value.
     *
     * @return The Expectation
     */
    public static Expectation endsWith(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.endsWith(text);
            }
            
            @Override
            public String description() {
                return "ends with [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not end with the expected value.
     *
     * @return The Expectation
     */
    public static Expectation doesNotEndWith(final String text) {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return not(Matchers.endsWith(text));
            }
            
            @Override
            public String description() {
                return "does not end with [" + text + "].";
            }
        };
    }
    
    /**
     * An Expectation for a String value to either empty or null.
     *
     * @return The Expectation
     */
    public static Expectation isEmptyOrNull() {
        
        return new Expectation() {
            
            @Override
            public Matcher matcher() {
                return Matchers.is(emptyOrNullString());
            }
            
            @Override
            public String description() {
                return "is null or empty.";
            }
        };
    }
    
    private static Matcher not(Matcher matcher) {
        return Matchers.not(matcher);
    }
}
