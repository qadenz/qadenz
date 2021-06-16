package io.slifer.automation.reporter.testng;

import org.testng.ITestResult;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2} due to being protected and static within the TestNG
 * project.
 *
 * @author TestNG
 */
public class MethodResult {
    
    private final List<ITestResult> results;
    
    /**
     * @param results the non-null, non-empty result list
     */
    public MethodResult(List<ITestResult> results) {
        this.results = results;
    }
    
    /**
     * @return the non-null, non-empty result list
     */
    public List<ITestResult> getResults() {
        return results;
    }
}
