package io.slifer.automation.reporter.testng;

import org.testng.ITestResult;

import java.util.List;

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
