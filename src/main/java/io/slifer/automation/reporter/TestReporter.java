package io.slifer.automation.reporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.slifer.automation.config.RunContext;
import io.slifer.automation.reporter.model.JsonReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.List;

public class TestReporter implements IReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        LOG.info("Starting JSON Compilation.");
        JsonReporter jsonReporter = new JsonReporter(xmlSuites.get(0), suites.get(0));
        JsonReport jsonReport = jsonReporter.compileJsonReport();
        
        try {
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            objectWriter.writeValue(new File(RunContext.reportOutputPath + "results.json"), jsonReport);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        HtmlReporter htmlReporter = new HtmlReporter(jsonReport);
        htmlReporter.generateReport(RunContext.reportOutputPath);
    }
}
