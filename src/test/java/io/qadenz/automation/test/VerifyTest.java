package io.qadenz.automation.test;

import io.qadenz.automation.conditions.Condition;
import io.qadenz.automation.reporter.Screenshot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith (MockitoExtension.class)
public class VerifyTest {
    
    @Mock
    private Screenshot screenshot;
    
    @InjectMocks
    private TestCommander commander;
    
    @Test
    public void singleCondition_ReturnsTrue_ResultIsPass() {
        Condition returnsTrue = spy(TestConditions.returnsTrue());
        commander.verify(returnsTrue);
        verify(returnsTrue, times(1)).result();
    }
    
    @Test
    public void singleCondition_ReturnsFalse_ResultIsFail() {
        Condition returnsFalse = spy(TestConditions.returnsFalse());
        doNothing().when(screenshot).capture();
        assertThrows(AssertionError.class, () -> commander.verify(returnsFalse));
        verify(returnsFalse, times(1)).result();
    }
    
    @Test
    public void singleCondition_ThrowsError_ResultIsError() {
        Condition throwsError = spy(TestConditions.throwsError());
        doNothing().when(screenshot).capture();
        assertThrows(RuntimeException.class, () -> commander.verify(throwsError));
        verify(throwsError, times(1)).result();
    }
    
    @Test
    public void multipleConditions_AllReturnTrue_ResultIsPass_AllAreAsserted() {
        Condition returnsTrue1 = spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = spy(TestConditions.returnsTrue());
        Condition returnsTrue3 = spy(TestConditions.returnsTrue());
        commander.verify(returnsTrue1, returnsTrue2, returnsTrue3);
        verify(returnsTrue1, times(1)).result();
        verify(returnsTrue2, times(1)).result();
        verify(returnsTrue3, times(1)).result();
    }
    
    @Test
    public void multipleConditions_OneReturnsFalse_ResultIsFail_AllAreAsserted() {
        Condition returnsFalse = spy(TestConditions.returnsFalse());
        Condition returnsTrue1 = spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = spy(TestConditions.returnsTrue());
        doNothing().when(screenshot).capture();
        assertThrows(AssertionError.class, () -> commander.verify(returnsFalse, returnsTrue1, returnsTrue2));
        verify(returnsFalse, times(1)).result();
        verify(returnsTrue1, times(1)).result();
        verify(returnsTrue2, times(1)).result();
    }
    
    @Test
    public void multipleConditions_OneThrowsError_ResultIsError_AssertionStopsAtError() {
        Condition returnsTrue1 = spy(TestConditions.returnsTrue());
        Condition throwsError = spy(TestConditions.throwsError());
        Condition returnsTrue2 = spy(TestConditions.returnsTrue());
        doNothing().when(screenshot).capture();
        assertThrows(RuntimeException.class, () -> commander.verify(returnsTrue1, throwsError, returnsTrue2));
        verify(returnsTrue1, times(1)).result();
        verify(throwsError, times(1)).result();
        verify(returnsTrue2, times(0)).result();
    }
    
    @Test
    public void multipleConditions_MixFalseAndError_ResultIsError_AssertionStopsAtError() {
        Condition returnsFalse = spy(TestConditions.returnsFalse());
        Condition throwsError = spy(TestConditions.throwsError());
        Condition returnsTrue = spy(TestConditions.returnsTrue());
        doNothing().when(screenshot).capture();
        assertThrows(RuntimeException.class, () -> commander.verify(returnsFalse, throwsError, returnsTrue));
        verify(returnsFalse, times(1)).result();
        verify(throwsError, times(1)).result();
        verify(returnsTrue, times(0)).result();
    }
    
    @Test
    public void multipleConditions_MultipleThrowError_ResultIsError_AssertionStopsAtFirstError() {
        Condition returnsTrue = spy(TestConditions.returnsTrue());
        Condition throwsError1 = spy(TestConditions.throwsError());
        Condition throwsError2 = spy(TestConditions.throwsError());
        doNothing().when(screenshot).capture();
        assertThrows(RuntimeException.class, () -> commander.verify(returnsTrue, throwsError1, throwsError2));
        verify(returnsTrue, times(1)).result();
        verify(throwsError1, times(1)).result();
        verify(throwsError2, times(0)).result();
    }
}
