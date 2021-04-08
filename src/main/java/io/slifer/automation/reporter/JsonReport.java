package io.slifer.automation.reporter;

import java.time.LocalDateTime;
import java.util.List;

public class JsonReport {
    
    private String suiteName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
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
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
