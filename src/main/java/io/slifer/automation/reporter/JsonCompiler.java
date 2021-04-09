package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.testng.ITestResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JsonCompiler {
    
    private static final Logger SUITE_LOG = RunContext.SUITE_LOG;
    
    private ResultsMap resultsMap;
    private Screenshots screenshots;
    private JsonReport jsonReport;
    
    public JsonCompiler(ResultsMap resultsMap, Screenshots screenshots) {
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
        SUITE_LOG.info("Writing Suite Header Info.");
        jsonReport.setSuiteName(resultsMap.getSuiteName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d hh:mm");
        String startDate = RunContext.startDate.format(formatter);
        
        jsonReport.setStartDate(startDate);
        
        Duration duration = Duration.between(RunContext.startDate, RunContext.endDate);
        String executionTime = String.format("%02d:%02d:%02d:%02d",
                duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
        
        jsonReport.setDuration(executionTime);
        jsonReport.setBrowser(RunContext.browser.name());
        jsonReport.setBrowserVersion(RunContext.browserVersion);
        // jsonReport.setPlatform(RunContext.platform.name());
        jsonReport.setAppUrl(RunContext.appUrl);
    }
    
    private void processTestLogs() {
        SUITE_LOG.info("Processing Test Logs.");
        
        List<JsonTest> jsonTests = new ArrayList<>();
        
        for (String key : resultsMap.keySet()) {
            SUITE_LOG.info("Starting Log Processing for Test [{}]", key);
            ITestResult testResult = resultsMap.get(key);
            
            JsonTest jsonTest = new JsonTest();
            jsonTest.setClassName(testResult.getTestClass().getName());
            jsonTest.setTestName(testResult.getName());
            jsonTest.setParameters(Arrays.toString(testResult.getParameters()).replace("/", "-"));
            jsonTest.setResult(computeTestResult(testResult));
            jsonTest.setStartMillis(testResult.getStartMillis());
            jsonTest.setEndMillis(testResult.getEndMillis());
            
            Throwable throwable = testResult.getThrowable();
            if (throwable != null) {
                // Commenting these items in case the need arises to restore them
                // jsonTest.setThrowable(throwable.getClass().getName());
                // jsonTest.setStackTrace(ExceptionUtils.getStackTrace(throwable));
                jsonTest.setScreenshot(screenshots.get(key));
            }
            
            String fileName = "test-logs/test-" + key + ".json";
            List<JSONObject> jsonLogs = readJsonFile(fileName);
            List<JsonTestLog> logs = new ArrayList<>();
            for (JSONObject log : jsonLogs) {
                JsonTestLog jsonTestLog = new JsonTestLog();
                jsonTestLog.setTimestamp(log.getString("timestamp"));
                jsonTestLog.setLogger(log.getString("logger"));
                jsonTestLog.setLevel(log.getString("level"));
                jsonTestLog.setMessage(log.getString("message"));
                
                logs.add(jsonTestLog);
            }
            jsonTest.setLogs(logs);
            
            jsonTests.add(jsonTest);
        }
        
        sortTestLogsByResult(jsonTests);
    }
    
    private List<JSONObject> readJsonFile(String fileName) {
        SUITE_LOG.info("Opening logs for test [{}].", fileName);
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
    
    private void sortTestLogsByResult(List<JsonTest> jsonTests) {
        List<JsonTest> passed = new ArrayList<>();
        List<JsonTest> failed = new ArrayList<>();
        List<JsonTest> stopped = new ArrayList<>();
        List<JsonTest> skipped = new ArrayList<>();
        
        for (JsonTest log : jsonTests) {
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
        
        jsonReport.setPassedTests(passed);
        jsonReport.setFailedTests(failed);
        jsonReport.setStoppedTests(stopped);
        jsonReport.setSkippedTests(skipped);
    }
}
