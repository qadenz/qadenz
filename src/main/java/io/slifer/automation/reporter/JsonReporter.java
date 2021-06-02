package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import io.slifer.automation.reporter.model.JsonReport;
import io.slifer.automation.reporter.testng.SuiteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class JsonReporter {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private XmlSuite xmlSuite;
    private ISuite suite;
    private SuiteResult suiteResult;
    
    private JsonReport jsonReport;
    
    public JsonReporter(XmlSuite xmlSuite, ISuite suite) {
        this.xmlSuite = xmlSuite;
        this.suite = suite;
        this.suiteResult = new SuiteResult(suite);
        
        this.jsonReport = new JsonReport();
    }
    
    public void compileJsonReport() {
        
    }
    
    private void setSuiteHeaderDetails() {
        jsonReport.setSuiteName(suite.getName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDate = RunContext.suiteStartDate.format(formatter);
        
        jsonReport.setSuiteStartDate(startDate);
        
        Duration duration = Duration.between(RunContext.suiteStartDate, RunContext.suiteEndDate);
        String executionTime = String.format("%02dh %02dm %02d.%02ds",
                duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
        
        jsonReport.setSuiteExecutionTime(executionTime);
        
        // TODO uncomment these when report shows suite config params
        // jsonReport.setBrowser(RunContext.browser.name());
        // jsonReport.setBrowserVersion(RunContext.browserVersion);
        // jsonReport.setPlatform(RunContext.platform.name()); 
        // jsonReport.setAppUrl(RunContext.appUrl);
    }
}
