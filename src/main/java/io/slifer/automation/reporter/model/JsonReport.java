package io.slifer.automation.reporter.model;

import java.util.List;

/**
 * This is the core data model for for reporting content that drives the HTML reports. The JSON generated from this
 * object can be shared with other systems to visualize the captured information from during the test run.
 *
 * @author Tim Slifer
 */
public class JsonReport {
    
    private String suiteName;
    private String suiteStartDate;
    private String suiteExecutionTime;
    
    private String browser;
    private String browserVersion;
    private String platform;
    
    private String appUrl;
    
    private List<JsonTest> failedTests;
    private List<JsonTest> stoppedTests;
    private List<JsonTest> skippedTests;
    private List<JsonTest> passedTests;
    
    public String getSuiteName() {
        return suiteName;
    }
    
    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
    
    public String getSuiteStartDate() {
        return suiteStartDate;
    }
    
    public void setSuiteStartDate(String suiteStartDate) {
        this.suiteStartDate = suiteStartDate;
    }
    
    public String getSuiteExecutionTime() {
        return suiteExecutionTime;
    }
    
    public void setSuiteExecutionTime(String suiteExecutionTime) {
        this.suiteExecutionTime = suiteExecutionTime;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    public String getBrowserVersion() {
        return browserVersion;
    }
    
    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public String getAppUrl() {
        return appUrl;
    }
    
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
    
    public List<JsonTest> getFailedTests() {
        return failedTests;
    }
    
    public void setFailedTests(List<JsonTest> failedTests) {
        this.failedTests = failedTests;
    }
    
    public List<JsonTest> getStoppedTests() {
        return stoppedTests;
    }
    
    public void setStoppedTests(List<JsonTest> stoppedTests) {
        this.stoppedTests = stoppedTests;
    }
    
    public List<JsonTest> getSkippedTests() {
        return skippedTests;
    }
    
    public void setSkippedTests(List<JsonTest> skippedTests) {
        this.skippedTests = skippedTests;
    }
    
    public List<JsonTest> getPassedTests() {
        return passedTests;
    }
    
    public void setPassedTests(List<JsonTest> passedTests) {
        this.passedTests = passedTests;
    }
}
