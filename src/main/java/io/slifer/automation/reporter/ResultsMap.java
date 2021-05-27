package io.slifer.automation.reporter;

import org.testng.ITestResult;

import java.util.HashMap;

/**
 * Tracks and links the TestNG results for each test to the UUID generated by the MDC in the Logback logs.
 *
 * @author Tim Slifer
 */
public class ResultsMap extends HashMap<String, ITestResult> {
    
    private static ResultsMap instance;
    private String suiteName;
    
    private ResultsMap() {
        // Singleton
    }
    
    public static ResultsMap getInstance() {
        if (instance == null) {
            instance = new ResultsMap();
        }
        
        return instance;
    }
    
    public String getSuiteName() {
        return suiteName;
    }
    
    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
}
