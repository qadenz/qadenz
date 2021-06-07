package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.reporter.Screenshots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        List<Throwable> exceptions = new ArrayList<>();
        boolean failed = false;
        
        for (Condition condition : conditions) {
            LOG.info("Asserting Condition - {}", condition.description());
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.info("Result - PASS");
            }
            catch (AssertionError error) {
                LOG.info("Result - FAIL :: {}", condition.output());
                exceptions.add(error);
                failed = true;
                String uuid = UUID.randomUUID().toString();
                Screenshots.getInstance().captureScreenshot(uuid);
                Reporter.log(uuid);
            }
            catch (Exception exception) {
                exceptions.add(exception);
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
                String uuid = UUID.randomUUID().toString();
                Screenshots.getInstance().captureScreenshot(uuid);
                Reporter.log(uuid);
            }
        }
        
        if (exceptions.size() > 0) {
            if (failed) {
                throw new AssertionError("One or more validations failed.");
            }
            else {
                throw new RuntimeException("One or more validations encountered an error.");
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
