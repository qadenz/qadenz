package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.reporter.Screenshots;
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
    
    public Commands() {
        LOG = LoggerFactory.getLogger(Commands.class);
    }
    
    public Commands(Class<?> proxyLogger) {
        LOG = LoggerFactory.getLogger(proxyLogger);
    }
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * not be aborted after the final Condition is evaluated. A call to {@link Assertions} will flush the failures and
     * abort the test at a user designated point in the test. If a call to Verify exists after a call to Check and
     * before a flush, the test will still be aborted if the verify produces a failure. Execution will be stopped
     * immediately if an error is encountered.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void check(Condition... conditions) {
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
                Screenshots.captureScreen();
            }
            catch (Exception exception) {
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
                Screenshots.captureScreen();
                
                throw new RuntimeException("Error while verifying condition.");
            }
        }
    }
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated. Execution will be stopped immediately if an error is
     * encountered.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
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
                Screenshots.captureScreen();
            }
            catch (Exception exception) {
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
                Screenshots.captureScreen();
                
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
            Thread.sleep(seconds * 1000);
        }
        catch (Exception e) {
            LOG.error("Error during pause.");
            
            throw new RuntimeException("Thread.sleep failed.");
            // Not ideal, but prevents us from having to add a throws declaration to our upstream methods.
        }
    }
}
