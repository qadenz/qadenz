package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A second means of executing validations with Conditions, but outside the confines of the Commands class, with logging
 * at the WARN level.
 *
 * @author Tim Slifer
 */
public class Assert {
    
    private Logger LOG = LoggerFactory.getLogger(Assert.class);
    
    /**
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void that(Condition... conditions) {
        List<Throwable> exceptions = new ArrayList<>();
        boolean failed = false;
        
        for (Condition condition : conditions) {
            LOG.warn("Asserting Condition - {}", condition.description());
            try {
                boolean result = condition.result();
                org.testng.Assert.assertTrue(result);
                LOG.warn("Result - PASS");
            }
            catch (AssertionError error) {
                LOG.warn("Result - FAIL :: {}", condition.output());
                exceptions.add(error);
                failed = true;
            }
            catch (Exception exception) {
                exceptions.add(exception);
                LOG.error("Result - ERROR :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
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
}
