package io.slifer.automation.reports.html;

import io.slifer.automation.commands.WebDriverCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class HtmlReporter implements IReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverCommands.class);
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
    }
}
