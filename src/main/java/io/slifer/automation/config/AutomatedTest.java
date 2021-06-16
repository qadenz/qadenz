package io.slifer.automation.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * The top-level class for all test classes. The AutomatedTest manages configuration and launch of the WebDriver and
 * other major Suite components.
 *
 * @author Tim Slifer
 */
@Listeners ({io.slifer.automation.reporter.TestReporter.class})
public class AutomatedTest {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    @BeforeSuite (alwaysRun = true)
    public void captureStartDateTime(ITestContext testContext) {
        RunContext.suiteStartDate = LocalDateTime.now();
        System.setProperty("path.ReportOutput", testContext.getOutputDirectory());
    }
    
    /**
     * Begins the suite execution process by reading the parameters given on the Suite XML file, validating, and
     * assigning values on the RunContext.
     *
     * @param testContext The injected ITestContext.
     */
    @BeforeSuite (alwaysRun = true, dependsOnMethods = "captureStartDateTime")
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
     * Begins execution of a test by launching a RemoteWebDriver on a Selenium Grid, and opening the application URL.
     *
     * @throws Exception on invalid Grid URL.
     */
    @BeforeMethod (alwaysRun = true)
    public void startWebDriver() throws Exception {
        LOG.info("Launching WebDriver.");
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
     * Concludes the test by stopping the WebDriver instance.
     */
    @AfterMethod (alwaysRun = true)
    public void stopWebDriver() {
        LOG.info("Stopping the WebDriver.");
        RunContext.getWebDriver().quit();
    }
    
    /**
     * Captures the date/time when the Suite is completed.
     */
    @AfterSuite (alwaysRun = true)
    public void captureEndDateTime() {
        RunContext.suiteEndDate = LocalDateTime.now();
    }
}
