/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.expectations.Expectations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextExpectationTest {
    
    private static final String FOOBAR = "FOOBAR";
    private static final String FOO = "FOO";
    private static final String BAR = "BAR";
    private static final String BAZ = "BAZ";
    private static final String OOBA = "OOBA";
    private static final String FOOBAR_LOWER_CASE = "foobar";
    private static final String FOO_LOWER_CASE = "foo";
    private static final String BAR_LOWER_CASE = "bar";
    private static final String OOBA_LOWER_CASE = "ooba";
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    
    @Test
    public void testContains_ReturnsTrueWhenActualContainsExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.contains(OOBA);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContains_ReturnsFalseWhenActualContainsExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.contains(OOBA_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContains_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.contains(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContains_ReturnsFalseWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.contains(FOOBAR_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContains_ReturnsFalseWhenActualDoesNotContainExpected() {
        Expectation<String> expectation = Expectations.contains(BAZ);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContainsIgnoreCase_ReturnsTrueWhenActualContainsExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.containsIgnoringCase(OOBA);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContainsIgnoreCase_ReturnsTrueWhenActualContainsExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.containsIgnoringCase(OOBA_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContainsIgnoreCase_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.containsIgnoringCase(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContainsIgnoreCase_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.containsIgnoringCase(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testContainsIgnoreCase_ReturnsFalseWhenActualDoesNotContainExpected() {
        Expectation<String> expectation = Expectations.containsIgnoringCase(BAZ);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotContain_ReturnsFalseWhenActualContainsExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotContain(OOBA);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotContain_ReturnsTrueWhenActualContainsExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotContain(OOBA_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotContain_ReturnsFalseWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotContain(FOOBAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotContain_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotContain(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotContain_ReturnsTrueWhenActualDoesNotContainExpected() {
        Expectation<String> expectation = Expectations.doesNotContain(BAZ);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotEndWith_ReturnsFalseWhenActualEndsWithExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotEndWith(BAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotEndWith_ReturnsTrueWhenActualEndsWithExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotEndWith(BAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotEndWith_ReturnsFalseWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotEndWith(FOOBAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotEndWith_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotEndWith(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotEndWith_ReturnsTrueWhenActualDoesNotEndWithExpected() {
        Expectation<String> expectation = Expectations.doesNotEndWith(BAZ);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotStartWith_ReturnsFalseWhenActualStartsWithExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotStartWith(FOO);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotStartWith_ReturnsTrueWhenActualStartsWithExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotStartWith(FOO_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotStartWith_ReturnsFalseWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.doesNotStartWith(FOOBAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotStartWith_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.doesNotStartWith(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testDoesNotStartWith_ReturnsTrueWhenActualDoesNotStartWithExpected() {
        Expectation<String> expectation = Expectations.doesNotStartWith(BAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEndsWith_ReturnsTrueWhenActualEndsWithExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.endsWith(BAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEndsWith_ReturnsFalseWhenActualEndsWithExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.endsWith(BAR_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEndsWith_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.endsWith(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEndsWith_ReturnsFalseWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.endsWith(FOOBAR_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEndsWith_ReturnsFalseWhenActualDoesNotEndWithExpected() {
        Expectation<String> expectation = Expectations.endsWith(BAZ);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEqualsIgnoreCase_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.equalsIgnoringCase(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEqualsIgnoreCase_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.equalsIgnoringCase(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testEqualsIgnoreCase_ReturnsFalseWhenActualIsNotSameAsExpected() {
        Expectation<String> expectation = Expectations.equalsIgnoringCase(OOBA);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsEmptyOrNull_ReturnsTrueWhenActualIsEmptyString() {
        Expectation<String> expectation = Expectations.isEmptyOrNull();
        assertTrue(expectation.matcher().matches(EMPTY_STRING));
    }
    
    @Test
    public void testIsEmptyOrNull_ReturnsTrueWhenActualIsNull() {
        Expectation<String> expectation = Expectations.isEmptyOrNull();
        assertTrue(expectation.matcher().matches(NULL_STRING));
    }
    
    @Test
    public void testIsEmptyOrNull_ReturnsFalseWhenActualIsNotEmptyOrNull() {
        Expectation<String> expectation = Expectations.isEmptyOrNull();
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.isEqualTo(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsEqualTo_ReturnsFalseWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.isEqualTo(FOOBAR_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsEqualTo_ReturnsFalseWhenActualIsNotSameAsExpected() {
        Expectation<String> expectation = Expectations.isEqualTo(BAZ);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsTrueWhenActualIsEqualToOneOfExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO, BAR, BAZ);
        assertTrue(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsFalseWhenActualIsEqualToOneOfExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO_LOWER_CASE, BAR, BAZ);
        assertFalse(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsTrueWhenActualIsEqualToMultipleExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO, BAR, BAZ, FOO);
        assertTrue(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsFalseWhenActualIsEqualToMultipleExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO_LOWER_CASE, BAR, BAZ, FOO_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsTrueWhenActualIsEqualToMultipleExpected_IsMixedCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO_LOWER_CASE, BAR, BAZ, FOO);
        assertTrue(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsTrueWhenActualIsEqualToAllExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO, FOO, FOO);
        assertTrue(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsFalseWhenActualIsEqualToAllExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO_LOWER_CASE, FOO_LOWER_CASE, FOO_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsTrueWhenActualIsEqualToAllExpected_IsMixedCase() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(FOO_LOWER_CASE, FOO, FOO_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsEqualToOneOf_ReturnsFalseWhenActualIsNotEqualToAnyExpected() {
        Expectation<String> expectation = Expectations.isEqualToOneOf(BAR, BAZ);
        assertFalse(expectation.matcher().matches(FOO));
    }
    
    @Test
    public void testIsNotEmptyOrNull_ReturnsFalseWhenActualIsEmptyString() {
        Expectation<String> expectation = Expectations.isNotEmptyOrNull();
        assertFalse(expectation.matcher().matches(EMPTY_STRING));
    }
    
    @Test
    public void testIsNotEmptyOrNull_ReturnsFalseWhenActualIsNull() {
        Expectation<String> expectation = Expectations.isNotEmptyOrNull();
        assertFalse(expectation.matcher().matches(NULL_STRING));
    }
    
    @Test
    public void testIsNotEmptyOrNull_ReturnsTrueWhenActualIsNotEmptyOrNull() {
        Expectation<String> expectation = Expectations.isNotEmptyOrNull();
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsFalseWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.isNotEqualTo(FOOBAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsTrueWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.isNotEqualTo(FOOBAR_LOWER_CASE);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testIsNotEqualTo_ReturnsTrueWhenActualIsNotSameAsExpected() {
        Expectation<String> expectation = Expectations.isNotEqualTo(BAZ);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testStartsWith_ReturnsTrueWhenActualStartsWithExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.startsWith(FOO);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testStartsWith_ReturnsFalseWhenActualStartsWithExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.startsWith(FOO_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testStartsWith_ReturnsTrueWhenActualIsEqualToExpected_IsSameCase() {
        Expectation<String> expectation = Expectations.startsWith(FOOBAR);
        assertTrue(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testStartsWith_ReturnsFalseWhenActualIsEqualToExpected_IsDifferentCase() {
        Expectation<String> expectation = Expectations.startsWith(FOOBAR_LOWER_CASE);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
    
    @Test
    public void testStartsWith_ReturnsFalseWhenActualDoesNotStartWithExpected() {
        Expectation<String> expectation = Expectations.startsWith(BAR);
        assertFalse(expectation.matcher().matches(FOOBAR));
    }
}
