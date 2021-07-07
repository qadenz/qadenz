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
    
    /**
     * Captures a timestamp as the start time of the suite, and sets the report output path from TestNG as a system
     * property in order to facilitate writing the Suite Log json file.
     *
     * @param testContext The injected ITestContext.
     */
    @BeforeSuite (alwaysRun = true)
    public void captureStartDateTime(ITestContext testContext) {
        WebConfig.suiteStartDate = LocalDateTime.now();
        String outputPath = testContext.getOutputDirectory();
        outputPath = outputPath.substring(0, outputPath.lastIndexOf("/")) + "/";
        System.setProperty("path.ReportOutput", outputPath);
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
        
        WebConfig.gridHost = parameterValidator.validateGridHost();
        WebConfig.browser = parameterValidator.validateBrowser();
        WebConfig.browserVersion = parameterValidator.validateBrowserVersion();
        WebConfig.platform = parameterValidator.validatePlatform();
        WebConfig.applicationName = parameterValidator.validateApplicationName();
        WebConfig.timeout = parameterValidator.validateTimeout();
        WebConfig.appUrl = parameterValidator.validateAppUrl();
        WebConfig.retryInterceptedClicks = parameterValidator.validateRetryInterceptedClicks();
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
            WebDriverProvider.setWebDriver(
                    new RemoteWebDriver(new URL("http://" + WebConfig.gridHost + ":4444/wd/hub"), capabilities));
        }
        catch (MalformedURLException exception) {
            LOG.error("Grid URL is invalid.");
            throw exception;
        }
        
        WebDriverProvider.getWebDriver().manage().window().maximize();
        WebDriverProvider.getWebDriver().get(WebConfig.appUrl);
    }
    
    /**
     * Concludes the test by stopping the WebDriver instance.
     */
    @AfterMethod (alwaysRun = true)
    public void stopWebDriver() {
        LOG.info("Stopping the WebDriver.");
        WebDriverProvider.getWebDriver().quit();
    }
    
    /**
     * Captures the date/time when the Suite is completed.
     */
    @AfterSuite (alwaysRun = true)
    public void captureEndDateTime() {
        WebConfig.suiteEndDate = LocalDateTime.now();
    }
}
