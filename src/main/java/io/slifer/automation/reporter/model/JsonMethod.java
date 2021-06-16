package io.slifer.automation.reporter.model;

import java.util.List;

/**
 * This is the fourth level data model for individual test case results and logging events.
 *
 * @author Tim Slifer
 */
public class JsonMethod {
    
    private String methodName;
    private String parameters;
    
    private String testStartTime;
    private String testExecutionTime;
    
    private List<JsonLogEvent> logEvents;
    
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
    
    public List<JsonLogEvent> getLogEvents() {
        return logEvents;
    }
    
    public void setLogEvents(List<JsonLogEvent> logEvents) {
        this.logEvents = logEvents;
    }
    
    public String getStackTrace() {
        return stackTrace;
    }
    
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
