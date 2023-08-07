/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter.model;

import java.util.List;

/**
 * Models a full test Suite for reporting in JSON format.
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
    
    private List<JsonTest> tests;
    
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
    
    public List<JsonTest> getTests() {
        return tests;
    }
    
    public void setTests(List<JsonTest> tests) {
        this.tests = tests;
    }
}
