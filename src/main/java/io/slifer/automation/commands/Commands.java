package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.config.RunContext;
import org.slf4j.Logger;
import org.testng.Assert;

/**
 * Common commands for all test types.
 *
 * @author Tim Slifer
 */
public abstract class Commands {
    
    private static final Logger LOG = RunContext.TEST_LOG;
    // private static final Logger LOG = LoggerFactory.getLogger(Commands.class);
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        boolean hasFailures = false;
        
        for (Condition condition : conditions) {
            LOG.info("Asserting Condition :: {}", condition.description());
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.warn("Result: PASS");
            }
            catch (AssertionError error) {
                hasFailures = true;
                LOG.warn("Result: FAIL: {}", condition.output());
            }
            catch (Exception exception) {
                hasFailures = true;
                LOG.error("Result: ERROR", exception);
            }
        }
        
        if (hasFailures) {
            throw new AssertionError();
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
