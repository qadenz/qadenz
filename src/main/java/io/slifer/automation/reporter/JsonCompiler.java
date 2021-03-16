package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.testng.ITestResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JsonCompiler {
    
    private static final Logger SUITE_LOG = RunContext.SUITE_LOG;
    
    private ResultsMap resultsMap;
    private JsonReport jsonReport;
    
    public JsonCompiler(ResultsMap resultsMap) {
        this.resultsMap = resultsMap;
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
        jsonReport.setBrowser(RunContext.browser.name());
        jsonReport.setBrowserVersion(RunContext.browserVersion);
        // jsonReport.setPlatform(RunContext.platform.name());
        jsonReport.setAppUrl(RunContext.appUrl);
    }
    
    private void processTestLogs() {
        SUITE_LOG.info("Processing Test Logs.");
        
        List<TestLog> testLogs = new ArrayList<>();
        
        for (String key : resultsMap.keySet()) {
            SUITE_LOG.info("Starting Log Processing for Test [{}]", key);
            ITestResult testResult = resultsMap.get(key);
            
            TestLog testLog = new TestLog();
            testLog.setClassName(testResult.getTestClass().getName());
            testLog.setTestName(testResult.getName());
            testLog.setParameters(Arrays.toString(testResult.getParameters()).replace("/", "-"));
            testLog.setResult(computeTestResult(testResult));
            testLog.setStartMillis(testResult.getStartMillis());
            testLog.setEndMillis(testResult.getEndMillis());
            
            String fileName = "test-logs/test-" + key + ".json";
            List<JSONObject> jsonStepLogs = readJsonFile(fileName);
            List<StepLog> stepLogs = new ArrayList<>();
            for (JSONObject log : jsonStepLogs) {
                StepLog stepLog = new StepLog();
                stepLog.setTimestamp(log.getString("timestamp"));
                stepLog.setLogger(log.getString("logger"));
                stepLog.setLevel(log.getString("level"));
                stepLog.setMessage(log.getString("message"));
                
                stepLogs.add(stepLog);
            }
            testLog.setStepLogs(stepLogs);
            
            testLogs.add(testLog);
        }
        
        sortTestLogsByResult(testLogs);
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
    
    private void sortTestLogsByResult(List<TestLog> testLogs) {
        List<TestLog> passed = new ArrayList<>();
        List<TestLog> failed = new ArrayList<>();
        List<TestLog> stopped = new ArrayList<>();
        List<TestLog> skipped = new ArrayList<>();
        
        for (TestLog log : testLogs) {
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
