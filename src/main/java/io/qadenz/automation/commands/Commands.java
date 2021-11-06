/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.commands;

import io.qadenz.automation.conditions.Condition;
import io.qadenz.automation.reporter.Screenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Common commands for all test types.
 *
 * @author Tim Slifer
 */
public abstract class Commands {
    
    private Logger LOG;
    
    private Screenshot screenshot = new Screenshot();
    
    public Commands() {
        LOG = LoggerFactory.getLogger(Commands.class);
    }
    
    public Commands(Class<?> proxyLogger) {
        LOG = LoggerFactory.getLogger(proxyLogger);
    }
    
    /**
     * Evaluates each of the given {@link Condition}s as a group. Execution will be allowed to continue after the final
     * Condition is evaluated, even if one or more Conditions results in a failure. A call to {@link Assertions} will
     * flush the failures and abort the test at a user designated point in the test. If a call to {@code verify()}
     * exists after a call to Check and before a call to {@code flush()}, the test will still be aborted if {@code
     * verify()} produces a failure. Execution will be stopped immediately if an error is encountered during the {@code
     * check()}. A screenshot will be captured for each failed Condition.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void check(Condition... conditions) {
        check(true, conditions);
    }
    
    /**
     * An overloaded implementation of {@code check()} that allows the user to disable the screenshot for failures
     * encountered by this validation.
     *
     * @param captureScreen False to disable screenshots.
     * @param conditions The Conditions to be evaluated.
     */
    public void check(boolean captureScreen, Condition... conditions) {
        for (Condition condition : conditions) {
            LOG.info("Checking Condition - {}", condition.description());
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.info("Result - PASS");
            }
            catch (AssertionError error) {
                LOG.info("Result - FAIL :: {}", condition.output());
                Assertions.setFailures(true);
                if (captureScreen) {
                    screenshot.capture();
                }
            }
            catch (Exception exception) {
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
                if (captureScreen) {
                    screenshot.capture();
                }
                
                throw new RuntimeException("Error while verifying condition.");
            }
        }
    }
    
    /**
     * Evaluates each of the given {@link Condition}s as a group. If one or more Conditions results in a failure,
     * execution will be aborted after the final Condition is evaluated. Execution will be stopped immediately if an
     * error is encountered.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        verify(true, conditions);
    }
    
    /**
     * An overloaded implementation of {@code verify()} that allows the user to disable the screenshot for failures
     * encountered by this validation.
     *
     * @param captureScreen False to disable screenshots.
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(boolean captureScreen, Condition... conditions) {
        boolean failed = false;
        
        for (Condition condition : conditions) {
            LOG.info("Verifying Condition - {}", condition.description());
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.info("Result - PASS");
            }
            catch (AssertionError error) {
                LOG.info("Result - FAIL :: {}", condition.output());
                failed = true;
                if (captureScreen) {
                    screenshot.capture();
                }
            }
            catch (Exception exception) {
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
                if (captureScreen) {
                    screenshot.capture();
                }
                
                throw new RuntimeException("Error while verifying condition.");
            }
        }
        
        if (failed) {
            throw new AssertionError("One or more validations failed.");
        }
    }
    
    /**
     * Pauses execution for the given amount of time, expressed in seconds.
     *
     * @param seconds The amount of time to wait.
     */
    public void pause(int seconds) {
        try {
            LOG.info("Pausing for [{}] seconds.", seconds);
            Thread.sleep(seconds * 1000L);
        }
        catch (Exception e) {
            LOG.error("Error during pause.");
            
            throw new RuntimeException("Thread.sleep failed.");
            // Not ideal, but prevents us from having to add a throws declaration to our upstream methods.
        }
    }
    
    /**
     * Creates a log event at the WARN level, intended to provide a means to add high level notes to the logging/report
     * output, such as Labeling procedures, noting sections of tests, or adding information useful to interpreting
     * results.
     *
     * @param message The information to be added to the logs and report.
     */
    public void annotate(String message) {
        LOG.warn(message);
    }
    
    /**
     * Creates a log event at the INFO level, intended to supplement the in-built logging of individual commands or
     * inspections. This is useful for capturing system output on the logging output for further analysis or to aid the
     * investigation of test failures.
     *
     * @param message The information to be added to the logs and report.
     */
    public void log(String message) {
        LOG.info(message);
    }
}
