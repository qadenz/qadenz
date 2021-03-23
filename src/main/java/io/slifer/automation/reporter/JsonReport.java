package io.slifer.automation.reporter;

import java.util.List;

public class JsonReport {
    
    public String suiteName;
    
    public String browser;
    public String browserVersion;
    public String platform;
    
    public String appUrl;
    
    public List<JsonTest> failedTests;
    public List<JsonTest> stoppedTests;
    public List<JsonTest> skippedTests;
    public List<JsonTest> passedTests;
    
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
