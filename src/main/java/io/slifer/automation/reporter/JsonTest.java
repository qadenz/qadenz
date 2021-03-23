package io.slifer.automation.reporter;

import java.util.List;

public class JsonTest {
    
    public String className;
    public String testName;
    public String parameters;
    public Result result;
    
    public long startMillis;
    public long endMillis;
    
    public List<JsonTestLog> jsonTestLogs;
    
    public String screenshot;
    public String throwable;
    public String stackTrace;
    
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
    
    public long getStartMillis() {
        return startMillis;
    }
    
    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }
    
    public long getEndMillis() {
        return endMillis;
    }
    
    public void setEndMillis(long endMillis) {
        this.endMillis = endMillis;
    }
    
    public List<JsonTestLog> getStepLogs() {
        return jsonTestLogs;
    }
    
    public void setStepLogs(List<JsonTestLog> jsonTestLogs) {
        this.jsonTestLogs = jsonTestLogs;
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
