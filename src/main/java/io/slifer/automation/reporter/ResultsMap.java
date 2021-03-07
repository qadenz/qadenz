package io.slifer.automation.reporter;

import org.testng.ITestResult;

import java.util.HashMap;

public class ResultsMap extends HashMap<String, ITestResult> {
    
    private String suiteName;
    
    public String getSuiteName() {
        return suiteName;
    }
    
    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
}
