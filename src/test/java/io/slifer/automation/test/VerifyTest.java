package io.slifer.automation.test;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.reporter.Screenshot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith (MockitoExtension.class)
public class VerifyTest {
    
    @Mock
    private Screenshot screenshot;
    
    @InjectMocks
    private TestCommander cmd;
    
    @Test
    public void singleCondition_ReturnsTrue_ResultIsPass() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        cmd.verify(returnsTrue);
        Mockito.verify(returnsTrue, times(1)).result();
    }
    
    @Test
    public void singleCondition_ReturnsFalse_ResultIsFail() {
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(AssertionError.class, () -> cmd.verify(returnsFalse));
        Mockito.verify(returnsFalse, times(1)).result();
    }
    
    @Test
    public void singleCondition_ThrowsError_ResultIsError() {
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.verify(throwsError));
        Mockito.verify(throwsError, times(1)).result();
    }
    
    @Test
    public void multipleConditions_AllReturnTrue_ResultIsPass_AllAreAsserted() {
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue3 = Mockito.spy(TestConditions.returnsTrue());
        cmd.verify(returnsTrue1, returnsTrue2, returnsTrue3);
        Mockito.verify(returnsTrue1, times(1)).result();
        Mockito.verify(returnsTrue2, times(1)).result();
        Mockito.verify(returnsTrue3, times(1)).result();
    }
    
    @Test
    public void multipleConditions_OneReturnsFalse_ResultIsFail_AllAreAsserted() {
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(AssertionError.class, () -> cmd.verify(returnsFalse, returnsTrue1, returnsTrue2));
        Mockito.verify(returnsFalse, times(1)).result();
        Mockito.verify(returnsTrue1, times(1)).result();
        Mockito.verify(returnsTrue2, times(1)).result();
    }
    
    @Test
    public void multipleConditions_OneThrowsError_ResultIsError_AssertionStopsAtError() {
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.verify(returnsTrue1, throwsError, returnsTrue2));
        Mockito.verify(returnsTrue1, times(1)).result();
        Mockito.verify(throwsError, times(1)).result();
        Mockito.verify(returnsTrue2, times(0)).result();
    }
    
    @Test
    public void multipleConditions_MixFalseAndError_ResultIsError_AssertionStopsAtError() {
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.verify(returnsFalse, throwsError, returnsTrue));
        Mockito.verify(returnsFalse, times(1)).result();
        Mockito.verify(throwsError, times(1)).result();
        Mockito.verify(returnsTrue, times(0)).result();
    }
    
    @Test
    public void multipleConditions_MultipleThrowError_ResultIsError_AssertionStopsAtFirstError() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        Condition throwsError1 = Mockito.spy(TestConditions.throwsError());
        Condition throwsError2 = Mockito.spy(TestConditions.throwsError());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.verify(returnsTrue, throwsError1, throwsError2));
        Mockito.verify(returnsTrue, times(1)).result();
        Mockito.verify(throwsError1, times(1)).result();
        Mockito.verify(throwsError2, times(0)).result();
    }
}
