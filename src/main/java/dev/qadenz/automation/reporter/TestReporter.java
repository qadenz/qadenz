/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.qadenz.automation.logs.Loggers;
import dev.qadenz.automation.reporter.model.JsonReport;
import org.slf4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.List;

/**
 * Compiles Suite results using a JSON model to store data, and a custom stand-alone HTML report that presents the
 * data.
 *
 * @author Tim Slifer
 */
public class TestReporter implements IReporter {
    
    private static final Logger LOG = Loggers.getReporterLogger();
    private static final String FILE_NAME = "suite-results";
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        LOG.info("Starting JSON Compilation.");
        JsonReporter jsonReporter = new JsonReporter(suites.get(0));
        JsonReport jsonReport = jsonReporter.compileJsonReport();
        
        try {
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            objectWriter.writeValue(new File(outputDirectory, FILE_NAME + ".json"), jsonReport);
        }
        catch (Exception exception) {
            LOG.error("Error writing JSON file :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
        }
        
        HtmlReporter htmlReporter = new HtmlReporter(jsonReport);
        htmlReporter.generateReport(outputDirectory, FILE_NAME);
    }
}
