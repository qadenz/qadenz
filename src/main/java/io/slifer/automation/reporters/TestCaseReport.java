package io.slifer.automation.reporters;

import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class TestCaseReport {
    
    private List<TestStepEntry> testStepEntries = new ArrayList<>();
    private ITestResult testResult;
    
    public TestCaseReport() {
        
    }
    
    public List<TestStepEntry> getTestStepEntries() {
        return this.testStepEntries;
    }
    
    public void addTestStepEntry(TestStepEntry testStepEntry) {
        this.testStepEntries.add(testStepEntry);
    }
    
    public ITestResult getTestResult() {
        return testResult;
    }
    
    public void setTestResult(ITestResult testResult) {
        this.testResult = testResult;
    }
}
