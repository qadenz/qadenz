package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import io.slifer.automation.reporter.model.JsonMethod;
import io.slifer.automation.reporter.model.JsonReport;
import io.slifer.automation.reporter.model.JsonTestLog;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Combines Logback logging output with data pulled from TestNG components to generate an uber-JSON with full detail
 * results of the test run. This JSON output is fed to the HTML Reporter for visual representation, but is also written
 * to a file so it can be made available to other consuming systems.
 *
 * @author Tim Slifer
 */
public class OldJsonCompiler {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private ResultsMap resultsMap;
    private Screenshots screenshots;
    private JsonReport jsonReport;
    
    public OldJsonCompiler(ResultsMap resultsMap, Screenshots screenshots) {
        this.resultsMap = resultsMap;
        this.screenshots = screenshots;
        jsonReport = new JsonReport();
    }
    
    public JsonReport compileJsonReport() {
        setSuiteHeaderInfo();
        processTestLogs();
        
        return jsonReport;
    }
    
    private void setSuiteHeaderInfo() {
        LOG.info("Writing Suite Header Info.");
        jsonReport.setSuiteName(resultsMap.getSuiteName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDate = RunContext.suiteStartDate.format(formatter);
        
        jsonReport.setSuiteStartDate(startDate);
        
        Duration duration = Duration.between(RunContext.suiteStartDate, RunContext.suiteEndDate);
        String executionTime = String.format("%02dh %02dm %02d.%02ds",
                duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
        
        jsonReport.setSuiteExecutionTime(executionTime);
        jsonReport.setBrowser(RunContext.browser.name());
        jsonReport.setBrowserVersion(RunContext.browserVersion);
        // jsonReport.setPlatform(RunContext.platform.name());
        jsonReport.setAppUrl(RunContext.appUrl);
    }
    
    private void processTestLogs() {
        LOG.info("Processing Test Logs.");
        
        List<JsonMethod> jsonMethods = new ArrayList<>();
        
        for (String key : resultsMap.keySet()) {
            LOG.info("Processing Log for Test [{}]", key);
            ITestResult testNgResult = resultsMap.get(key);
            
            JsonMethod jsonMethod = new JsonMethod();
            jsonMethod.setClassName(testNgResult.getTestClass().getName());
            jsonMethod.setTestName(testNgResult.getName());
            jsonMethod.setParameters(Arrays.toString(testNgResult.getParameters()).replace("/", "-"));
            jsonMethod.setResult(computeTestResult(testNgResult));
            
            LocalDateTime startDateMillis =
                    Instant.ofEpochMilli(resultsMap.get(key).getStartMillis()).atZone(ZoneId.systemDefault())
                           .toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
            String testStartTime = startDateMillis.format(formatter);
            
            jsonMethod.setTestStartTime(testStartTime);
            
            LocalDateTime endDateMillis =
                    Instant.ofEpochMilli(resultsMap.get(key).getEndMillis()).atZone(ZoneId.systemDefault())
                           .toLocalDateTime();
            Duration duration = Duration.between(startDateMillis, endDateMillis);
            String testExecutionTime = String.format("%02dm %02d.%02ds",
                    duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
            
            jsonMethod.setTestExecutionTime(testExecutionTime);
            
            Throwable throwable = testNgResult.getThrowable();
            if (throwable != null) {
                // Commenting these items in case the need arises to restore them
                // jsonTest.setThrowable(throwable.getClass().getName());
                // jsonTest.setStackTrace(ExceptionUtils.getStackTrace(throwable));
                jsonMethod.setScreenshot(screenshots.get(key));
            }
            
            String fileName = RunContext.reportOutputPath + "test-logs/test-" + key + ".json";
            List<JSONObject> jsonLogFile = readJsonFile(fileName);
            List<JsonTestLog> logs = new ArrayList<>();
            for (JSONObject logEvent : jsonLogFile) {
                JsonTestLog jsonTestLog = new JsonTestLog();
                jsonTestLog.setTimestamp(logEvent.getString("timestamp"));
                jsonTestLog.setLogger(logEvent.getString("logger"));
                jsonTestLog.setLevel(logEvent.getString("level"));
                jsonTestLog.setMessage(logEvent.getString("message"));
                
                logs.add(jsonTestLog);
            }
            jsonMethod.setLogs(logs);
            
            jsonMethods.add(jsonMethod);
        }
        
        sortTestLogsByResult(jsonMethods);
    }
    
    private List<JSONObject> readJsonFile(String fileName) {
        LOG.debug("Opening logs for test [{}].", fileName);
        List<JSONObject> jsonLogs = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> jsonLogs.add(new JSONObject(s)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return jsonLogs;
    }
    
    private Result computeTestResult(ITestResult testResult) {
        int status = testResult.getStatus();
        if (status == 1) {
            return Result.PASSED;
        }
        else if (status == 2) {
            if (testResult.getThrowable() instanceof AssertionError) {
                return Result.FAILED;
            }
            else {
                return Result.STOPPED;
            }
        }
        else if (status == 3) {
            return Result.SKIPPED;
        }
        
        return null;
    }
    
    private void sortTestLogsByResult(List<JsonMethod> jsonMethods) {
        List<JsonMethod> passed = new ArrayList<>();
        List<JsonMethod> failed = new ArrayList<>();
        List<JsonMethod> stopped = new ArrayList<>();
        List<JsonMethod> skipped = new ArrayList<>();
        
        for (JsonMethod log : jsonMethods) {
            switch (log.getResult()) {
                case PASSED:
                    passed.add(log);
                    break;
                case FAILED:
                    failed.add(log);
                    break;
                case STOPPED:
                    stopped.add(log);
                    break;
                case SKIPPED:
                    skipped.add(log);
            }
        }
        
        // jsonReport.setPassedTests(passed);
        // jsonReport.setFailedTests(failed);
        // jsonReport.setStoppedTests(stopped);
        // jsonReport.setSkippedTests(skipped);
    }
}
