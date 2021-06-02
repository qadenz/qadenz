package io.slifer.automation.reporter.model;

import io.slifer.automation.reporter.Result;

import java.util.List;

/**
 * This is the JSON model for individual test case results and logging events.
 *
 * @author Tim Slifer
 */
public class JsonMethod {
    
    private String methodName;
    private String parameters;
    private Result result;
    
    private String testStartTime;
    private String testExecutionTime;
    
    private List<JsonMethodLogEvent> logs;
    
    private String screenshot;
    private String throwable;
    private String stackTrace;
    
    public String getMethodName() {
        return methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
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
    
    public String getTestStartTime() {
        return testStartTime;
    }
    
    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }
    
    public String getTestExecutionTime() {
        return testExecutionTime;
    }
    
    public void setTestExecutionTime(String testExecutionTime) {
        this.testExecutionTime = testExecutionTime;
    }
    
    public List<JsonMethodLogEvent> getLogs() {
        return logs;
    }
    
    public void setLogs(List<JsonMethodLogEvent> logs) {
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
