/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.OrderedListComparator;
import dev.qadenz.automation.expectations.Expectations;
import dev.qadenz.automation.expectations.ListExpectation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderedListComparatorTest {
    
    private static final String FOOBAR = "FOOBAR";
    private static final String FOO = "FOO";
    private static final String BAR = "BAR";
    private static final String BAZ = "BAZ";
    private static final String OOBA = "OOBA";
    
    @Test
    public void testListInOrder_ReturnsTrueWhenActualIsEqualToExpectedInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR, BAZ);
        assertTrue(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenActualIsEqualToExpectedNotInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(BAZ, BAR, FOO);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenActualIsNotEqualToExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO, BAR);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenNoMatchAndActualIsLongerThanExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO, BAR, BAZ);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenNoMatchAndExpectedIsLongerThanActual() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenMatchButActualIsLongerThanExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR, BAZ, FOOBAR);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenMatchButExpectedIsLongerThanActual() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR);
        assertFalse(new OrderedListComparator(expectation, actual).getResult());
    }
}
