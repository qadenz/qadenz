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
public class CheckTest {
    
    @Mock
    private Screenshot screenshot;
    
    @InjectMocks
    private TestCommander cmd;
    
    @Test
    public void singleCondition_ReturnsTrue_ResultIsPass() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        cmd.check(returnsTrue);
        Mockito.verify(returnsTrue, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
    }
    
    @Test
    public void singleCondition_ReturnsFalse_ResultIsFail() {
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        Mockito.doNothing().when(screenshot).capture();
        cmd.check(returnsFalse);
        Mockito.verify(returnsFalse, times(1)).result();
        Assertions.assertThrows(AssertionError.class, io.slifer.automation.commands.Assertions::flush);
    }
    
    @Test
    public void singleCondition_ThrowsError_ResultIsError() {
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.check(throwsError));
        Mockito.verify(throwsError, times(1)).result();
    }
    
    @Test
    public void multipleConditions_AllReturnTrue_ResultIsPass_AllAreAsserted() {
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue3 = Mockito.spy(TestConditions.returnsTrue());
        cmd.check(returnsTrue1, returnsTrue2, returnsTrue3);
        Mockito.verify(returnsTrue1, times(1)).result();
        Mockito.verify(returnsTrue2, times(1)).result();
        Mockito.verify(returnsTrue3, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
    }
    
    @Test
    public void multipleConditions_OneReturnsFalse_ResultIsFail_AllAreAsserted() {
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Mockito.doNothing().when(screenshot).capture();
        cmd.check(returnsFalse, returnsTrue1, returnsTrue2);
        Mockito.verify(returnsFalse, times(1)).result();
        Mockito.verify(returnsTrue1, times(1)).result();
        Mockito.verify(returnsTrue2, times(1)).result();
        Assertions.assertThrows(AssertionError.class, io.slifer.automation.commands.Assertions::flush);
    }
    
    @Test
    public void multipleConditions_OneThrowsError_ResultIsError_AssertionStopsAtError() {
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        Condition returnsTrue2 = Mockito.spy(TestConditions.returnsTrue());
        Mockito.doNothing().when(screenshot).capture();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.check(returnsTrue1, throwsError, returnsTrue2));
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
        Assertions.assertThrows(RuntimeException.class, () -> cmd.check(returnsFalse, throwsError, returnsTrue));
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
        Assertions.assertThrows(RuntimeException.class, () -> cmd.check(returnsTrue, throwsError1, throwsError2));
        Mockito.verify(returnsTrue, times(1)).result();
        Mockito.verify(throwsError1, times(1)).result();
        Mockito.verify(throwsError2, times(0)).result();
    }
    
    @Test
    public void multipleFlush_ReturnsTrue_ResultIsPass() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsTrue1 = Mockito.spy(TestConditions.returnsTrue());
        cmd.check(returnsTrue);
        Mockito.verify(returnsTrue, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
        cmd.check(returnsTrue1);
        Mockito.verify(returnsTrue1, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
    }
    
    @Test
    public void multipleFlush_ReturnsFalse_ResultIsFail() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        Condition returnsFalse = Mockito.spy(TestConditions.returnsFalse());
        cmd.check(returnsTrue);
        Mockito.verify(returnsTrue, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
        cmd.check(returnsFalse);
        Mockito.verify(returnsFalse, times(1)).result();
        Assertions.assertThrows(AssertionError.class, io.slifer.automation.commands.Assertions::flush);
    }
    
    @Test
    public void multipleFlush_ThrowsError_ResultIsError() {
        Condition returnsTrue = Mockito.spy(TestConditions.returnsTrue());
        Condition throwsError = Mockito.spy(TestConditions.throwsError());
        cmd.check(returnsTrue);
        Mockito.verify(returnsTrue, times(1)).result();
        io.slifer.automation.commands.Assertions.flush();
        Assertions.assertThrows(RuntimeException.class, () -> cmd.check(throwsError));
        Mockito.verify(throwsError, times(1)).result();
    }
}
