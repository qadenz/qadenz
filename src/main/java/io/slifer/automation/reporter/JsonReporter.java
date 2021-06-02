package io.slifer.automation.reporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class JsonReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private XmlSuite xmlSuite;
    private ISuite suite;
    
    public JsonReporter(XmlSuite xmlSuite, ISuite suite) {
        this.xmlSuite = xmlSuite;
        this.suite = suite;
    }
}
