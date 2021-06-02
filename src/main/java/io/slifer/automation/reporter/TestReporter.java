package io.slifer.automation.reporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class TestReporter implements IReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        LOG.info("Starting JSON Compilation.");
        
        // System.out.println("**********");
        // System.out.println("*");
        // System.out.println("* REPORTER");
        // System.out.println("*");
        // System.out.println("* Results Map Size: " + ResultsMap.getInstance().size());
        // System.out.println("* Screenshots Size: " + Screenshots.getInstance().size());
        // System.out.println("*");
        // System.out.println("*");
        // System.out.println("* XML Suites Size: " + xmlSuites.size());
        // System.out.println("* ISuites size: " + xmlSuites.size());
        // System.out.println("*");
        // System.out.println("**********");
    }
}
