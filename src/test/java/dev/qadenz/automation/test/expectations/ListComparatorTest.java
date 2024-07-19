package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.ListComparator;
import dev.qadenz.automation.expectations.Expectations;
import dev.qadenz.automation.expectations.ListExpectation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListComparatorTest {
    
    private static final String FOOBAR = "FOOBAR";
    private static final String FOO = "FOO";
    private static final String BAR = "BAR";
    private static final String BAZ = "BAZ";
    private static final String OOBA = "OOBA";
    
    @Test
    public void testListInOrder_ReturnsTrueWhenActualIsEqualToExpectedInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR, BAZ);
        assertTrue(new ListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenActualIsEqualToExpectedNotInOrder() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(BAZ, BAR, FOO));
        List<String> actual = List.of(FOO, BAR, BAZ);
        assertFalse(new ListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenActualIsNotEqualToExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOOBAR, FOO, BAR);
        assertFalse(new ListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenActualIsLongerThanExpected() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR);
        assertFalse(new ListComparator(expectation, actual).getResult());
    }
    
    @Test
    public void testListInOrder_ReturnsFalseWhenExpectedIsLongerThanActual() {
        ListExpectation expectation = Expectations.listContainsValues(List.of(FOO, BAR, BAZ));
        List<String> actual = List.of(FOO, BAR);
        assertFalse(new ListComparator(expectation, actual).getResult());
    }
}
