package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Common commands to all test types, agnostic of application under test.
 *
 * @author Tim Slifer
 */
public abstract class Commands {
    
    private static final Logger LOG = LoggerFactory.getLogger(Commands.class);
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        boolean hasFailures = false;
        
        for (Condition condition : conditions) {
            LOG.info("Asserting Condition -> " + condition.description());
            try {
                boolean result = condition.result();
                Assert.assertTrue(result);
                LOG.warn("Assertion Passed: " + condition.output());
            }
            catch (AssertionError error) {
                hasFailures = true;
                LOG.warn("Assertion Failed: " + condition.output());
            }
            catch (Exception exception) {
                hasFailures = true;
                LOG.error("Error making assertion.", exception);
            }
        }
        
        if (hasFailures) {
            throw new AssertionError();
        }
    }
}
