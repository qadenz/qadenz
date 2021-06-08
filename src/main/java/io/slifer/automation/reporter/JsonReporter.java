package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import io.slifer.automation.reporter.model.JsonClass;
import io.slifer.automation.reporter.model.JsonLogEvent;
import io.slifer.automation.reporter.model.JsonMethod;
import io.slifer.automation.reporter.model.JsonReport;
import io.slifer.automation.reporter.model.JsonTest;
import io.slifer.automation.reporter.testng.ClassResult;
import io.slifer.automation.reporter.testng.MethodResult;
import io.slifer.automation.reporter.testng.SuiteResult;
import io.slifer.automation.reporter.testng.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class JsonReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
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
        LOG.info("Writing Header Details.");
        jsonReport.setSuiteName(suite.getName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDate = RunContext.suiteStartDate.format(formatter);
        
        jsonReport.setSuiteStartDate(startDate);
        
        Duration duration = Duration.between(RunContext.suiteStartDate, RunContext.suiteEndDate);
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
            LOG.info("Processing Test [{}].", suiteResult.getTestResults().indexOf(testResult));
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
        
        jsonTest.setFailedConfigurations(failedConfigurations);
        jsonTest.setFailedTests(failedTests);
        jsonTest.setStoppedTests(stoppedTests);
        jsonTest.setSkippedConfigurations(skippedConfigurations);
        jsonTest.setSkippedTests(skippedTests);
        jsonTest.setPassedTests(passedTests);
        
        return jsonTest;
    }
    
    private List<JsonClass> processClassResults(List<ClassResult> classResults) {
        LOG.info("Processing Class Results.");
        List<JsonClass> jsonClasses = new ArrayList<>();
        for (ClassResult classResult : classResults) {
            LOG.info("Processing Class [{}].", classResults.indexOf(classResult));
            JsonClass jsonClass = new JsonClass();
            jsonClass.setClassName(classResult.getClassName());
            jsonClass.setMethods(processMethodResults(classResult.getMethodResults()));
            
            jsonClasses.add(jsonClass);
        }
        
        return jsonClasses;
    }
    
    private List<JsonMethod> processMethodResults(List<MethodResult> methodResults) {
        LOG.info("Processing Methods.");
        List<JsonMethod> jsonMethods = new ArrayList<>();
        for (MethodResult methodResult : methodResults) {
            LOG.info("Processing Method [{}].", methodResults.indexOf(methodResult));
            
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
                
                LOG.info("Processing Logs.");
                List<JsonLogEvent> logEvents = processLogOutput(Reporter.getOutput());
                jsonMethod.setLogEvents(logEvents);
                
                if (result.getThrowable() != null) {
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
            LOG.info("Processing Log [{}]", i);
            String logMessage = logs.get(i);
            String screenshot = null;
            if (checkForUuid(logs.get(i + 1))) {
                LOG.info("Found a screenshot.");
                screenshot = Screenshots.getInstance().get(logs.get(i + 1));
                i++;
            }
            
            LOG.info("Adding Log Event.");
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
        LOG.info("Checking for UUID.");
        if (input == null) {
            
            return false;
        }
        
        return UUID_PATTERN.matcher(input).matches();
    }
}
