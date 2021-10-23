/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.reporter.model;

import java.util.List;

/**
 * Model that holds results for each {@code <test>} on the TestNG Suite XML file.
 *
 * @author Tim Slifer
 */
public class JsonTest {
    
    private String testName;
    
    private int totalFailedConfigurations;
    private List<JsonClass> failedConfigurations;
    
    private int totalSkippedConfigurations;
    private List<JsonClass> skippedConfigurations;
    
    private int totalFailedTests;
    private List<JsonClass> failedTests;
    
    private int totalStoppedTests;
    private List<JsonClass> stoppedTests;
    
    private int totalSkippedTests;
    private List<JsonClass> skippedTests;
    
    private int totalPassedTests;
    private List<JsonClass> passedTests;
    
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public int getTotalFailedConfigurations() {
        return totalFailedConfigurations;
    }
    
    public void setTotalFailedConfigurations(int totalFailedConfigurations) {
        this.totalFailedConfigurations = totalFailedConfigurations;
    }
    
    public List<JsonClass> getFailedConfigurations() {
        return failedConfigurations;
    }
    
    public void setFailedConfigurations(List<JsonClass> failedConfigurations) {
        this.failedConfigurations = failedConfigurations;
    }
    
    public int getTotalSkippedConfigurations() {
        return totalSkippedConfigurations;
    }
    
    public void setTotalSkippedConfigurations(int totalSkippedConfigurations) {
        this.totalSkippedConfigurations = totalSkippedConfigurations;
    }
    
    public List<JsonClass> getSkippedConfigurations() {
        return skippedConfigurations;
    }
    
    public void setSkippedConfigurations(List<JsonClass> skippedConfigurations) {
        this.skippedConfigurations = skippedConfigurations;
    }
    
    public int getTotalFailedTests() {
        return totalFailedTests;
    }
    
    public void setTotalFailedTests(int totalFailedTests) {
        this.totalFailedTests = totalFailedTests;
    }
    
    public List<JsonClass> getFailedTests() {
        return failedTests;
    }
    
    public void setFailedTests(List<JsonClass> failedTests) {
        this.failedTests = failedTests;
    }
    
    public int getTotalStoppedTests() {
        return totalStoppedTests;
    }
    
    public void setTotalStoppedTests(int totalStoppedTests) {
        this.totalStoppedTests = totalStoppedTests;
    }
    
    public List<JsonClass> getStoppedTests() {
        return stoppedTests;
    }
    
    public void setStoppedTests(List<JsonClass> stoppedTests) {
        this.stoppedTests = stoppedTests;
    }
    
    public int getTotalSkippedTests() {
        return totalSkippedTests;
    }
    
    public void setTotalSkippedTests(int totalSkippedTests) {
        this.totalSkippedTests = totalSkippedTests;
    }
    
    public List<JsonClass> getSkippedTests() {
        return skippedTests;
    }
    
    public void setSkippedTests(List<JsonClass> skippedTests) {
        this.skippedTests = skippedTests;
    }
    
    public int getTotalPassedTests() {
        return totalPassedTests;
    }
    
    public void setTotalPassedTests(int totalPassedTests) {
        this.totalPassedTests = totalPassedTests;
    }
    
    public List<JsonClass> getPassedTests() {
        return passedTests;
    }
    
    public void setPassedTests(List<JsonClass> passedTests) {
        this.passedTests = passedTests;
    }
}
