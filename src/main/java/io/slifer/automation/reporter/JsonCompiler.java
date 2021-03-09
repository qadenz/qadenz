package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        jsonReport.setPlatform(RunContext.platform.name());
        jsonReport.setAppUrl(RunContext.appUrl);
    }
    
    private void processTestLogs() {
        SUITE_LOG.info("Processing Test Logs.");
        
        for (String key : resultsMap.keySet()) {
            ITestResult testResult = resultsMap.get(key);
            
            TestLog testLog = new TestLog();
            testLog.setClassName(testResult.getTestClass().getName());
            testLog.setTestName(testResult.getName());
            testLog.setParameters(Arrays.toString(testResult.getParameters()).replace("/", "-"));
            testLog.setResult(computeTestResult(testResult));
            testLog.setStartMillis(testResult.getStartMillis());
            testLog.setEndMillis(testResult.getEndMillis());
            
            String fileName = "test-" + key + ".json";
            List<JSONObject> jsonStepLogs = readJsonFile(fileName);
            List<StepLog> stepLogs = new ArrayList<>();
            for (JSONObject log : jsonStepLogs) {
                StepLog stepLog = new StepLog();
                stepLog.setTimestamp(log.getString("timestamp"));
                stepLog.setLevel(log.getString("level"));
                stepLog.setMessage(log.getString("message"));
                
                stepLogs.add(stepLog);
            }
            testLog.setStepLogs(stepLogs);
        }
    }
    
    private List<JSONObject> readJsonFile(String fileName) {
        List<JSONObject> jsonLogs = new ArrayList<>();
        InputStream inputStream = JsonCompiler.class.getClassLoader().getResourceAsStream(fileName);
        String[] jsonEntries = new String[0];
        try {
            String rawJson = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            jsonEntries = rawJson.split("\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String jsonEntry : jsonEntries) {
            jsonLogs.add(new JSONObject(jsonEntry));
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
}
