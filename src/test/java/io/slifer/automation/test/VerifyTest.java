package io.slifer.automation.test;

import io.slifer.automation.reporter.Screenshots;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

public class VerifyTest {
    
    @Test
    public void verifyWillPassWithOneConditionThatReturnsTrue() {
        TestCommander cmd = new TestCommander();
        cmd.verify(TestConditions.returnsTrue());
    }
    
    @Test
    public void verifyWillFailWithOneConditionThatReturnsFalse() {
        try (MockedStatic<Screenshots> screenshots = Mockito.mockStatic(Screenshots.class)) {
            TestCommander cmd = new TestCommander();
            screenshots.when(Screenshots::captureScreen).thenAnswer((Answer<Void>) invocation -> null);
            Assertions.assertThrows(AssertionError.class, () -> {
                cmd.verify(TestConditions.returnsFalse());
            });
        }
    }
}
