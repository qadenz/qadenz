package io.slifer.automation.reporter;

import java.util.List;

public class JsonTest {
    
    private String className;
    private String testName;
    private String parameters;
    private Result result;
    
    private String startMillis;
    private String endMillis;
    
    private List<JsonTestLog> logs;
    
    private String screenshot;
    private String throwable;
    private String stackTrace;
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public String getParameters() {
        return parameters;
    }
    
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    
    public Result getResult() {
        return result;
    }
    
    public void setResult(Result result) {
        this.result = result;
    }
    
    public String getStartMillis() {
        return startMillis;
    }
    
    public void setStartMillis(String startMillis) {
        this.startMillis = startMillis;
    }
    
    public String getEndMillis() {
        return endMillis;
    }
    
    public void setEndMillis(String endMillis) {
        this.endMillis = endMillis;
    }
    
    public List<JsonTestLog> getLogs() {
        return logs;
    }
    
    public void setLogs(List<JsonTestLog> logs) {
        this.logs = logs;
    }
    
    public String getScreenshot() {
        return screenshot;
    }
    
    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }
    
    public String getThrowable() {
        return throwable;
    }
    
    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }
    
    public String getStackTrace() {
        return stackTrace;
    }
    
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
