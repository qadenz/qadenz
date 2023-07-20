package dev.qadenz.automation.config;

import dev.qadenz.automation.reporter.TestReporter;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.time.LocalDateTime;

/**
 * The top-level class for all test classes. The AutomatedTest manages configurations that are independent of any type
 * of automated test being executed.
 *
 * @author Tim Slifer
 */
@Listeners({TestReporter.class})
public class AutomatedTest {
    
    /**
     * Captures a timestamp as the start time of the suite.
     *
     * @param testContext The injected {@link ITestContext}.
     */
    @BeforeSuite(alwaysRun = true)
    public void captureStartDateTime(ITestContext testContext) {
        WebConfig.suiteStartDate = LocalDateTime.now();
    }
    
    /**
     * Captures the date/time when the Suite is completed.
     */
    @AfterSuite(alwaysRun = true)
    public void captureEndDateTime() {
        WebConfig.suiteEndDate = LocalDateTime.now();
    }
}
