package io.slifer.automation.reporter.model;

import java.util.List;

public class JsonTest {
    
    private String testName;
    
    private List<JsonClass> failedConfigurations;
    private List<JsonClass> failedTests;
    private List<JsonClass> skippedConfigurations;
    private List<JsonClass> skippedTests;
    private List<JsonClass> stoppedTests;
    private List<JsonClass> passedTests;
    
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public List<JsonClass> getFailedConfigurations() {
        return failedConfigurations;
    }
    
    public void setFailedConfigurations(List<JsonClass> failedConfigurations) {
        this.failedConfigurations = failedConfigurations;
    }
    
    public List<JsonClass> getFailedTests() {
        return failedTests;
    }
    
    public void setFailedTests(List<JsonClass> failedTests) {
        this.failedTests = failedTests;
    }
    
    public List<JsonClass> getSkippedConfigurations() {
        return skippedConfigurations;
    }
    
    public void setSkippedConfigurations(List<JsonClass> skippedConfigurations) {
        this.skippedConfigurations = skippedConfigurations;
    }
    
    public List<JsonClass> getSkippedTests() {
        return skippedTests;
    }
    
    public void setSkippedTests(List<JsonClass> skippedTests) {
        this.skippedTests = skippedTests;
    }
    
    public List<JsonClass> getStoppedTests() {
        return stoppedTests;
    }
    
    public void setStoppedTests(List<JsonClass> stoppedTests) {
        this.stoppedTests = stoppedTests;
    }
    
    public List<JsonClass> getPassedTests() {
        return passedTests;
    }
    
    public void setPassedTests(List<JsonClass> passedTests) {
        this.passedTests = passedTests;
    }
}
