/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter;

import dev.qadenz.automation.config.WebConfig;
import dev.qadenz.automation.reporter.model.JsonClass;
import dev.qadenz.automation.reporter.model.JsonLogEvent;
import dev.qadenz.automation.reporter.model.JsonMethod;
import dev.qadenz.automation.reporter.model.JsonReport;
import dev.qadenz.automation.reporter.model.JsonTest;
import dev.qadenz.automation.reporter.testng.ClassResult;
import dev.qadenz.automation.reporter.testng.MethodResult;
import dev.qadenz.automation.reporter.testng.SuiteResult;
import dev.qadenz.automation.reporter.testng.TestResult;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;
import org.testng.xml.XmlSuite;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Combines logging output from Logback, with data elements from this library, along with results data extracted from
 * TestNG to build a JSON data object representing the complete result data of the Test Suite. The intent is to allow
 * flexible usage of the results data via the in-built {@link HtmlReporter}, as well as via export to external
 * presentation systems.
 *
 * @author Tim Slifer
 */
public class JsonReporter {
    
    private XmlSuite xmlSuite;
    private ISuite suite;
    private SuiteResult suiteResult;
    
    private JsonReport jsonReport;
    
    private static final Pattern UUID_PATTERN =
            Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    
    public JsonReporter(XmlSuite xmlSuite, ISuite suite) {
        this.xmlSuite = xmlSuite;
        this.suite = suite;
        this.suiteResult = new SuiteResult(suite);
        
        this.jsonReport = new JsonReport();
    }
    
    public JsonReport compileJsonReport() {
        SuiteResult suiteResult = new SuiteResult(suite);
        
        setSuiteHeaderDetails(suiteResult);
        processSuiteResults(suiteResult);
        
        return jsonReport;
    }
    
    private void setSuiteHeaderDetails(SuiteResult suiteResult) {
        jsonReport.setSuiteName(suiteResult.getSuiteName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDate = WebConfig.suiteStartDate.format(formatter);
        
        jsonReport.setSuiteStartDate(startDate);
        
        Duration duration = Duration.between(WebConfig.suiteStartDate, WebConfig.suiteEndDate);
        String executionTime = String.format("%02dh %02dm %02d.%02ds",
                duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
        
        jsonReport.setSuiteExecutionTime(executionTime);
        
        // TODO uncomment these when report shows suite config params
        // jsonReport.setBrowser(RunContext.browser.name());
        // jsonReport.setBrowserVersion(RunContext.browserVersion);
        // jsonReport.setPlatform(RunContext.platform.name());
        // jsonReport.setAppUrl(RunContext.appUrl);
    }
    
    private void processSuiteResults(SuiteResult suiteResult) {
        List<JsonTest> jsonTests = new ArrayList<>();
        for (TestResult testResult : suiteResult.getTestResults()) {
            jsonTests.add(processTestResults(testResult));
        }
        
        jsonReport.setTests(jsonTests);
    }
    
    private JsonTest processTestResults(TestResult testResult) {
        JsonTest jsonTest = new JsonTest();
        jsonTest.setTestName(testResult.getTestName());
        
        List<JsonClass> failedConfigurations = processClassResults(testResult.getFailedConfigurationResults());
        List<JsonClass> failedTests = processClassResults(testResult.getFailedTestResults());
        List<JsonClass> stoppedTests = processClassResults(testResult.getStoppedTestResults());
        List<JsonClass> skippedConfigurations = processClassResults(testResult.getSkippedConfigurationResults());
        List<JsonClass> skippedTests = processClassResults(testResult.getSkippedTestResults());
        List<JsonClass> passedTests = processClassResults(testResult.getPassedTestResults());
        
        int totalFailedConfigurations = 0;
        for (JsonClass jsonClass : failedConfigurations) {
            totalFailedConfigurations += jsonClass.getMethods().size();
        }
        jsonTest.setTotalFailedConfigurations(totalFailedConfigurations);
        jsonTest.setFailedConfigurations(failedConfigurations);
        
        int totalFailedTests = 0;
        for (JsonClass jsonClass : failedTests) {
            totalFailedTests += jsonClass.getMethods().size();
        }
        jsonTest.setTotalFailedTests(totalFailedTests);
        jsonTest.setFailedTests(failedTests);
        
        int totalStoppedTests = 0;
        for (JsonClass jsonClass : stoppedTests) {
            totalStoppedTests += jsonClass.getMethods().size();
        }
        jsonTest.setTotalStoppedTests(totalStoppedTests);
        jsonTest.setStoppedTests(stoppedTests);
        
        int totalSkippedConfigurations = 0;
        for (JsonClass jsonClass : skippedConfigurations) {
            totalSkippedConfigurations += jsonClass.getMethods().size();
        }
        jsonTest.setTotalSkippedConfigurations(totalSkippedConfigurations);
        jsonTest.setSkippedConfigurations(skippedConfigurations);
        
        int totalSkippedTests = 0;
        for (JsonClass jsonClass : skippedTests) {
            totalSkippedTests += jsonClass.getMethods().size();
        }
        jsonTest.setTotalSkippedTests(totalSkippedTests);
        jsonTest.setSkippedTests(skippedTests);
        
        int totalPassedTests = 0;
        for (JsonClass jsonClass : passedTests) {
            totalPassedTests += jsonClass.getMethods().size();
        }
        jsonTest.setTotalPassedTests(totalPassedTests);
        jsonTest.setPassedTests(passedTests);
        
        return jsonTest;
    }
    
    private List<JsonClass> processClassResults(List<ClassResult> classResults) {
        List<JsonClass> jsonClasses = new ArrayList<>();
        for (ClassResult classResult : classResults) {
            JsonClass jsonClass = new JsonClass();
            jsonClass.setClassName(classResult.getClassName());
            jsonClass.setMethods(processMethodResults(classResult.getMethodResults()));
            
            jsonClasses.add(jsonClass);
        }
        
        return jsonClasses;
    }
    
    private List<JsonMethod> processMethodResults(List<MethodResult> methodResults) {
        List<JsonMethod> jsonMethods = new ArrayList<>();
        for (MethodResult methodResult : methodResults) {
            
            List<ITestResult> results = methodResult.getResults();
            for (ITestResult result : results) {
                JsonMethod jsonMethod = new JsonMethod();
                jsonMethod.setMethodName(result.getName());
                jsonMethod.setParameters(processParameters(result));
                
                LocalDateTime startDateMillis =
                        Instant.ofEpochMilli(result.getStartMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
                String testStartTime = startDateMillis.format(formatter);
                jsonMethod.setTestStartTime(testStartTime);
                
                LocalDateTime endDateMillis =
                        Instant.ofEpochMilli(result.getEndMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                Duration duration = Duration.between(startDateMillis, endDateMillis);
                String testExecutionTime = String.format("%02dm %02d.%02ds",
                        duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
                jsonMethod.setTestExecutionTime(testExecutionTime);
                
                List<JsonLogEvent> logEvents = processLogOutput(Reporter.getOutput(result));
                jsonMethod.setLogEvents(logEvents);
                
                if (result.getThrowable() != null && !(result.getThrowable() instanceof AssertionError)) {
                    jsonMethod.setStackTrace(Utils.shortStackTrace(result.getThrowable(), false));
                }
                
                jsonMethods.add(jsonMethod);
            }
        }
        
        return jsonMethods;
    }
    
    private String processParameters(ITestResult testResult) {
        if (testResult.getParameters().length > 0) {
            
            return Arrays.toString(testResult.getParameters());
        }
        else if (testResult.getFactoryParameters().length > 0) {
            
            return Arrays.toString(testResult.getFactoryParameters());
        }
        else {
            
            return null;
        }
    }
    
    private List<JsonLogEvent> processLogOutput(List<String> logOutput) {
        List<String> logs = siftAndTrim(logOutput);
        List<JsonLogEvent> logEvents = new ArrayList<>();
        
        for (int i = 0; i < logs.size(); i++) {
            String logMessage = logs.get(i);
            String screenshot = null;
            
            if ((i + 1) < logs.size()) {
                if (checkForUuid(logs.get(i + 1))) {
                    screenshot = ScreenshotData.getInstance().get(logs.get(i + 1));
                    i++;
                }
            }
            
            logEvents.add(new JsonLogEvent(logMessage, screenshot));
        }
        
        return logEvents;
    }
    
    private List<String> siftAndTrim(List<String> input) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (!input.get(i).equals("")) {
                // Yes, the reporter layout could be changed to accommodate this,
                // but the console output will not be wrapped.
                output.add(input.get(i).trim());
            }
        }
        
        return output;
    }
    
    private boolean checkForUuid(String input) {
        if (input == null) {
            
            return false;
        }
        
        return UUID_PATTERN.matcher(input).matches();
    }
}
