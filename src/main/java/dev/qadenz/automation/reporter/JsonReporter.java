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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Combines logging output from Logback, with data elements from this library, along with results data extracted from
 * TestNG to build a JSON data object representing the complete result data of the Test Suite. The intent is to allow
 * flexible usage of the results data via the in-built {@link HtmlReporter}, as well as via export to external
 * presentation systems.
 *
 * @author Tim Slifer
 */
public class JsonReporter {
    
    private ISuite suite;
    private JsonReport jsonReport;
    
    private static final Pattern UUID_PATTERN =
            Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    
    public JsonReporter(ISuite suite) {
        this.suite = suite;
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
        
        AtomicInteger totalFailedConfigurations = new AtomicInteger();
        failedConfigurations.forEach(jsonClass -> totalFailedConfigurations.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalFailedConfigurations(totalFailedConfigurations.get());
        jsonTest.setFailedConfigurations(failedConfigurations);
        
        AtomicInteger totalFailedTests = new AtomicInteger();
        failedTests.forEach(jsonClass -> totalFailedTests.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalFailedTests(totalFailedTests.get());
        jsonTest.setFailedTests(failedTests);
        
        AtomicInteger totalStoppedTests = new AtomicInteger();
        stoppedTests.forEach(jsonClass -> totalStoppedTests.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalStoppedTests(totalStoppedTests.get());
        jsonTest.setStoppedTests(stoppedTests);
        
        AtomicInteger totalSkippedConfigurations = new AtomicInteger();
        skippedConfigurations.forEach(jsonClass -> totalSkippedConfigurations.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalSkippedConfigurations(totalSkippedConfigurations.get());
        jsonTest.setSkippedConfigurations(skippedConfigurations);
        
        AtomicInteger totalSkippedTests = new AtomicInteger();
        skippedTests.forEach(jsonClass -> totalSkippedTests.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalSkippedTests(totalSkippedTests.get());
        jsonTest.setSkippedTests(skippedTests);
        
        AtomicInteger totalPassedTests = new AtomicInteger();
        passedTests.forEach(jsonClass -> totalPassedTests.addAndGet(jsonClass.getMethods().size()));
        jsonTest.setTotalPassedTests(totalPassedTests.get());
        jsonTest.setPassedTests(passedTests);
        
        return jsonTest;
    }
    
    private List<JsonClass> processClassResults(List<ClassResult> classResults) {
        List<JsonClass> jsonClasses = new ArrayList<>();
        classResults.forEach(classResult -> {
            JsonClass jsonClass = new JsonClass();
            jsonClass.setClassName(classResult.getClassName());
            jsonClass.setMethods(processMethodResults(classResult.getMethodResults()));
            
            jsonClasses.add(jsonClass);
        });
        
        return jsonClasses;
    }
    
    private List<JsonMethod> processMethodResults(List<MethodResult> methodResults) {
        List<JsonMethod> jsonMethods = new ArrayList<>();
        
        methodResults.forEach(methodResult -> {
            List<ITestResult> results = methodResult.getResults();
            results.forEach(result -> {
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
            });
        });
        
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
        return IntStream.range(0, logs.size())
                        // Filter out indices that should be skipped
                        .filter(i -> i % 2 == 0 || !checkForUuid(logs.get(i)))
                        .mapToObj(i -> {
                            String logMessage = logs.get(i);
                            String screenshot;
                            if ((i + 1) < logs.size() && checkForUuid(logs.get(i + 1))) {
                                screenshot = ScreenshotData.getInstance().get(logs.get(i + 1));
                                return new JsonLogEvent(logMessage, screenshot);
                            }
                            else {
                                return new JsonLogEvent(logMessage, null);
                            }
                        })
                        .collect(Collectors.toList());
    }
    
    private List<String> siftAndTrim(List<String> input) {
        List<String> output = new ArrayList<>();
        input.forEach(s -> output.addAll(Arrays.asList(s.split("\n"))));
        // Yes, the reporter layout could be changed to accommodate this, but the console output will not be wrapped.
        
        return output;
    }
    
    private boolean checkForUuid(String input) {
        if (input == null) {
            
            return false;
        }
        
        return UUID_PATTERN.matcher(input).matches();
    }
}
