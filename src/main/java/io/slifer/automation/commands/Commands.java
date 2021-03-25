package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Common commands for all test types.
 *
 * @author Tim Slifer
 */
public abstract class Commands {
    
    // private static final Logger LOG = RunContext.TEST_LOG;
    private Logger LOG;
    
    public Commands() {
        LOG = LoggerFactory.getLogger(BrowserCommands.class);
    }
    
    public Commands(Class<?> proxyLogger) {
        LOG = LoggerFactory.getLogger(proxyLogger);
    }
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        List<Throwable> exceptions = new ArrayList<>();
        boolean failed = false;
        
        for (Condition condition : conditions) {
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.info("Asserting Condition - {} :: Result - PASS", condition.description());
            }
            catch (AssertionError error) {
                exceptions.add(error);
                LOG.info("Asserting Condition - {} :: Result - FAIL :: Found [{}]",
                        condition.description(), condition.output());
                failed = true;
            }
            catch (Exception exception) {
                exceptions.add(exception);
                LOG.info("Asserting Condition - {} :: Result - ERROR",
                        condition.description(), exception);
            }
        }
        
        if (exceptions.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Throwable throwable : exceptions) {
                stringBuilder.append(throwable.getMessage() + "\n");
            }
            
            String exceptionMessages = stringBuilder.toString();
            
            if (failed) {
                throw new AssertionError(exceptionMessages);
            }
            else {
                throw new RuntimeException(exceptionMessages);
            }
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
