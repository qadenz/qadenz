package io.slifer.automation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.slifer.automation.reporter.HtmlReporter;
import io.slifer.automation.reporter.JsonCompiler;
import io.slifer.automation.reporter.ResultsMap;
import io.slifer.automation.reporter.Screenshots;
import io.slifer.automation.reporter.model.JsonReport;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

/**
 * The top-level class for all test classes. The AutomatedTest manages configuration and launch of the WebDriver and
 * other major Suite components.
 *
 * @author Tim Slifer
 */
public class AutomatedTest {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private static ResultsMap resultsMap = ResultsMap.getInstance();
    private static Screenshots screenshots = new Screenshots();
    
    @BeforeSuite (alwaysRun = true)
    public void configureReportOutputPath(ITestContext testContext) {
        RunContext.suiteStartDate = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();
        builder.append("./test-results");
        builder.append(File.separator);
        builder.append(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(RunContext.suiteStartDate));
        builder.append(File.separator);
        builder.append(DateTimeFormatter.ofPattern("HHmm").format(RunContext.suiteStartDate));
        builder.append("-");
        builder.append((testContext.getSuite().getName() != null) ? testContext.getSuite().getName() : "Suite");
        builder.append(File.separator);
        
        String path = builder.toString();
        System.setProperty("path.ReportOutput", path);
        MDC.put("suiteId", UUID.randomUUID().toString());
        LOG.info("Report Output Path is [{}]", path);
        new File(path).mkdirs();
        RunContext.reportOutputPath = path;
    }
    
    /**
     * Begins the suite execution process by reading the parameters given on the Suite XML file, validating, and
     * assigning values on the RunContext.
     *
     * @param testContext The injected ITestContext.
     */
    @BeforeSuite (alwaysRun = true, dependsOnMethods = "configureReportOutputPath")
    public void processXmlParameters(ITestContext testContext) {
        
        LOG.info("Reading XML Parameters.");
        Map<String, String> xmlParameters = testContext.getCurrentXmlTest().getAllParameters();
        XmlParameterValidator parameterValidator = new XmlParameterValidator(xmlParameters);
        
        RunContext.gridHost = parameterValidator.validateGridHost();
        RunContext.browser = parameterValidator.validateBrowser();
        RunContext.browserVersion = parameterValidator.validateBrowserVersion();
        RunContext.platform = parameterValidator.validatePlatform();
        RunContext.applicationName = parameterValidator.validateApplicationName();
        RunContext.timeout = parameterValidator.validateTimeout();
        RunContext.appUrl = parameterValidator.validateAppUrl();
        RunContext.retryInterceptedClicks = parameterValidator.validateRetryInterceptedClicks();
    }
    
    /**
     * Assigns a UUID in preparation for logging and reporting activities.
     *
     * @param testContext The injected ITestContext.
     */
    @BeforeMethod (alwaysRun = true)
    public void prepareTestInfo(ITestContext testContext) {
        RunContext.setTestId(UUID.randomUUID().toString());
        MDC.put("testId", RunContext.getTestId());
        
        resultsMap.setSuiteName(testContext.getSuite().getName());
    }
    
    /**
     * Begins execution of a test by launching a RemoteWebDriver on a Selenium Grid, and opening the application URL.
     *
     * @throws Exception on invalid Grid URL.
     */
    @BeforeMethod (dependsOnMethods = {"prepareTestInfo"}, alwaysRun = true)
    public void startWebDriver() throws Exception {
        LOG.info("Launching RemoteWebDriver for test [{}].", RunContext.getTestId());
        MutableCapabilities capabilities = CapabilityProvider.getBrowserOptions();
        
        try {
            RunContext.setWebDriver(
                    new RemoteWebDriver(new URL("http://" + RunContext.gridHost + ":4444/wd/hub"), capabilities));
        }
        catch (MalformedURLException exception) {
            LOG.error("Grid URL is invalid.");
            throw exception;
        }
        
        RunContext.getWebDriver().manage().window().maximize();
        RunContext.getWebDriver().get(RunContext.appUrl);
    }
    
    /**
     * Captures a screenshot for any failed test.
     *
     * @param testResult The Injected ITestResult.
     */
    @AfterMethod (alwaysRun = true)
    public void saveScreenshot(ITestResult testResult) {
        if (testResult.getStatus() == 2) {
            screenshots.captureScreenshot();
        }
    }
    
    /**
     * Concludes the test by stopping the WebDriver instance.
     */
    @AfterMethod (alwaysRun = true, dependsOnMethods = "saveScreenshot")
    public void stopWebDriver() {
        LOG.info("Stopping the WebDriver.");
        RunContext.getWebDriver().quit();
    }
    
    /**
     * Captures the ITestResult instance for the test and links to the generated Test ID for processing by the HTML
     * Reporter.
     *
     * @param testResult The injected ITestResult.
     */
    @AfterMethod (alwaysRun = true, dependsOnMethods = "stopWebDriver")
    public void saveTestNgResult(ITestResult testResult) {
        LOG.info("Capturing Test Results.");
        resultsMap.put(RunContext.getTestId(), testResult);
    }
    
    /**
     * Triggers the generation of the HTML Report output and supporting JSON files.
     *
     * @param testContext The injected ITestContext.
     */
    @AfterSuite (alwaysRun = true)
    public void generateReports(ITestContext testContext) throws Exception {
        RunContext.suiteEndDate = LocalDateTime.now();
        
        JsonReport jsonReport = new JsonCompiler(resultsMap, screenshots).compileJsonReport();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(new File(RunContext.reportOutputPath + "results.json"), jsonReport);
        
        HtmlReporter htmlReporter = new HtmlReporter(jsonReport);
        htmlReporter.generateReport(RunContext.reportOutputPath);
    }
}
