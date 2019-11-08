package io.slifer.automation.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * The top-level class for all test classes. The AutomatedTest manages configuration and launch of the WebDriver and
 * other major Suite components.
 *
 * @author Tim Slifer
 */
public class AutomatedTest {
    
    private static final Logger log = LoggerFactory.getLogger(AutomatedTest.class);
    
    @BeforeSuite
    public void processXmlParameters(ITestContext testContext) {
        log.info("Reading XML Parameters.");
        Map<String, String> xmlParameters = testContext.getCurrentXmlTest().getAllParameters();
        XmlParameterValidator parameterValidator = new XmlParameterValidator(xmlParameters);
        
        RunContext.gridHost = parameterValidator.validateGridHost();
        RunContext.browser = parameterValidator.validateBrowser();
        RunContext.browserVersion = parameterValidator.validateBrowserVersion();
        RunContext.platform = parameterValidator.validatePlatform();
        RunContext.appUrl = parameterValidator.validateAppUrl();
    }
    
    @BeforeMethod
    public void readTestName(Method method) {
        RunContext.setTestCaseName(method.getName());
    }
    
    @BeforeMethod
    public void startWebDriver() throws Exception {
        log.info("Launching RemoteWebDriver.");
        MutableCapabilities capabilities = CapabilityProvider.getBrowserOptions();
        
        try {
            RunContext.setWebDriver(
                    new RemoteWebDriver(new URL("http://" + RunContext.gridHost + ":4444/wd/hub"), capabilities));
        }
        catch (MalformedURLException exception) {
            log.error("Grid URL is invalid.");
            throw exception;
        }
        
        RunContext.getWebDriver().manage().window().maximize();
        RunContext.getWebDriver().get(RunContext.appUrl);
    }
    
    @AfterMethod
    public void stopWebDriver() {
        log.info("Stopping the WebDriver.");
        RunContext.getWebDriver().quit();
    }
}
