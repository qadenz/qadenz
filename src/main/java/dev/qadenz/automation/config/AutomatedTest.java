/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.config;

import dev.qadenz.automation.reporter.TestReporter;
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
     */
    @BeforeSuite(alwaysRun = true)
    public void captureStartDateTime() {
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
