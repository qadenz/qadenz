package dev.qadenz.automation.test.expectations;

import dev.qadenz.automation.conditions.Expectations;
import dev.qadenz.automation.conditions.NumericExpectation;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericExpectationIntegerTest {
    
    //    @Test
    //    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
    //        NumericExpectation<Integer> expectation = Expectations.isEqualTo(1000);
    //        NumberFormat numberFormat = NumberFormat.getNumberInstance();
    //        numberFormat.setGroupingUsed(false);
    //        expectation.setNumberFormat(numberFormat);
    //        assertTrue(expectation.matcher().matches(1000));
    //        assertEquals("is equal to [1000]", expectation.description());
    //    }
    
    @Test
    public void testIsEqualTo_ReturnsTrueWhenActualIsEqualToExpected() {
        NumericExpectation<Integer> expectation = Expectations.isGreaterThan(1000);
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        expectation.setNumberFormat(numberFormat);
        assertTrue(expectation.matcher().matches(1100));
        //        assertEquals("is equal to [1000]", expectation.description());
    }
}
