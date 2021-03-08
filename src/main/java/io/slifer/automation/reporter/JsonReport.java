package io.slifer.automation.reporter;

import java.util.List;

public class JsonReport {
    
    public String suiteName;
    
    public String browser;
    public String browserVersion;
    public String platform;
    
    public String appUrl;
    
    public List<TestLog> failedTests;
    public List<TestLog> stoppedTests;
    public List<TestLog> skippedTests;
    public List<TestLog> passedTests;
    
    public String getSuiteName() {
        return suiteName;
    }
    
    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
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
    
    public List<TestLog> getFailedTests() {
        return failedTests;
    }
    
    public void setFailedTests(List<TestLog> failedTests) {
        this.failedTests = failedTests;
    }
    
    public List<TestLog> getStoppedTests() {
        return stoppedTests;
    }
    
    public void setStoppedTests(List<TestLog> stoppedTests) {
        this.stoppedTests = stoppedTests;
    }
    
    public List<TestLog> getSkippedTests() {
        return skippedTests;
    }
    
    public void setSkippedTests(List<TestLog> skippedTests) {
        this.skippedTests = skippedTests;
    }
    
    public List<TestLog> getPassedTests() {
        return passedTests;
    }
    
    public void setPassedTests(List<TestLog> passedTests) {
        this.passedTests = passedTests;
    }
}
