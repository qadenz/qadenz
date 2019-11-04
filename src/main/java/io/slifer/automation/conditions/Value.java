package io.slifer.automation.conditions;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.emptyOrNullString;

/**
 * A collection of wrapped Hamcrest Matchers for use with Conditions.
 *
 * @author Tim Slifer
 */
public class Value {
    
    public static Matcher<Boolean> isTrue() {
        return Matchers.is(true);
    }
    
    public static Matcher<Boolean> isFalse() {
        return Matchers.is(false);
    }
    
    public static Matcher<Integer> isGreaterThan(int value) {
        return Matchers.greaterThan(value);
    }
    
    public static Matcher<Integer> isGreaterThanOrEqualTo(int value) {
        return Matchers.greaterThanOrEqualTo(value);
    }
    
    public static Matcher<Integer> isEqualTo(int value) {
        return Matchers.equalTo(value);
    }
    
    public static Matcher<Integer> isNotEqualTo(int value) {
        return Matchers.not(isEqualTo(value));
    }
    
    public static Matcher<Integer> isLessThanOrEqualTo(int value) {
        return Matchers.lessThanOrEqualTo(value);
    }
    
    public static Matcher<Integer> isLessThan(int value) {
        return Matchers.lessThan(value);
    }
    
    public static Matcher<String> isEqualTo(String text) {
        return Matchers.is(text);
    }
    
    public static Matcher<String> equalsIgnoreCase(String text) {
        return Matchers.equalToIgnoringCase(text);
    }
    
    public static Matcher<String> isNotEqualTo(String text) {
        return Matchers.not(text);
    }
    
    public static Matcher<String> contains(String text) {
        return Matchers.containsString(text);
    }
    
    public static Matcher<String> doesNotContain(String text) {
        return Matchers.not(contains(text));
    }
    
    public static Matcher<String> startsWith(String text) {
        return Matchers.startsWith(text);
    }
    
    public static Matcher<String> doesNotStartWith(String text) {
        return Matchers.not(startsWith(text));
    }
    
    public static Matcher<String> endsWith(String text) {
        return Matchers.endsWith(text);
    }
    
    public static Matcher<String> doesNotEndWith(String text) {
        return Matchers.not(endsWith(text));
    }
    
    public static Matcher<String> isEmptyOrNull() {
        return Matchers.is(emptyOrNullString());
    }
}
