package io.slifer.automation.reporters;

import java.util.ArrayList;
import java.util.List;

public class TestRunReport {
    
    private List<TestCaseReport> passedTestCases = new ArrayList<>();
    private List<TestCaseReport> failedTestCases = new ArrayList<>();
    private List<TestCaseReport> stoppedTestCases = new ArrayList<>();
    private List<TestCaseReport> skippedTestCases = new ArrayList<>();
    
    public TestRunReport() {
        
    }
    
    public void addTestCaseReport(TestCaseReport testCaseReport) {
        // filter on itestresult and add to a list
    }
}
