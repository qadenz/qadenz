/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.UnorderedListComparator;
import dev.qadenz.automation.expectations.Expectations;
import dev.qadenz.automation.expectations.ListExpectation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnorderedListComparatorTest {
    
    private static final String FOOBAR = "FOOBAR";
    private static final String FOO = "FOO";
    private static final String BAR = "BAR";
    private static final String BAZ = "BAZ";
    private static final String OOBA = "OOBA";
    
    @Test
    public void testListUnordered_ReturnsTrueWhenActualIsEqualToExpectedInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR, BAZ);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertTrue(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsTrueWhenActualIsEqualToExpectedNotInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(BAZ, BAR, FOO);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertTrue(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsFalseWhenActualIsNotEqualToExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO, BAR);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertFalse(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsFalseWhenNoMatchAndActualIsLongerThanExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO, BAR, BAZ);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertFalse(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsFalseWhenNoMatchAndExpectedIsLongerThanActual() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertFalse(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsFalseWhenMatchButActualIsLongerThanExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR, BAZ, FOOBAR);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertFalse(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
    
    @Test
    public void testListUnordered_ReturnsFalseWhenMatchButExpectedIsLongerThanActual() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR);
        UnorderedListComparator comparator = new UnorderedListComparator(expectation, actual);
        assertFalse(comparator.getResult());
        System.out.println(comparator.getFailures());
    }
}
