package io.slifer.automation.test;

import io.slifer.automation.reporter.Screenshot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith (MockitoExtension.class)
public class VerifyTest {
    
    @Mock
    private Screenshot screenshot;
    
    @InjectMocks
    private TestCommander cmd;
    
    @Test
    public void singleCondition_ReturnsTrue_ResultIsPass() {
        cmd.verify(TestConditions.returnsTrue());
    }
    
    @Test
    public void singleCondition_ReturnsFalse_ResultIsFail() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(AssertionError.class, () -> cmd.verify(TestConditions.returnsFalse()));
    }
    
    @Test
    public void singleCondition_ThrowsError_ResultIsError() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.verify(TestConditions.throwsError()));
    }
    
    @Test
    public void multipleConditions_AllReturnTrue_ResultIsPass_AllAreAsserted() {
        cmd.verify(TestConditions.returnsTrue(), TestConditions.returnsTrue(), TestConditions.returnsTrue());
    }
    
    @Test
    public void multipleConditions_OneReturnsFalse_ResultIsFail_AllAreAsserted() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(AssertionError.class, () ->
                cmd.verify(TestConditions.returnsFalse(), TestConditions.returnsTrue(), TestConditions.returnsTrue()));
    }
    
    @Test
    public void multipleConditions_OneThrowsError_ResultIsError_AssertionStopsAtError() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () ->
                cmd.verify(TestConditions.returnsTrue(), TestConditions.throwsError(), TestConditions.returnsTrue()));
    }
    
    @Test
    public void multipleConditions_MixFalseAndError_ResultIsError_AssertionStopsAtError() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () ->
                cmd.verify(TestConditions.returnsFalse(), TestConditions.throwsError(), TestConditions.returnsTrue()));
    }
    
    @Test
    public void multipleConditions_MultipleThrowError_ResultIsError_AssertionStopsAtFirstError() {
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () ->
                cmd.verify(TestConditions.returnsTrue(), TestConditions.throwsError(), TestConditions.throwsError()));
    }
}
