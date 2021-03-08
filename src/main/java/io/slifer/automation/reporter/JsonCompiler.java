package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
}
