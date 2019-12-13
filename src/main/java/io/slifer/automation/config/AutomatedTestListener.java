package io.slifer.automation.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * A non-intrusive way of bootstrapping some tests that all follow the Slifer way of using selenium.
 * <p>
 * This follows the TestNG Listeners (https://testng.org/doc/documentation-main.html#testng-listeners) paradigm using
 * the ServiceLoader method instead of forcing all tests to extend a specific class. The change is mostly cosmetic from
 * the testers point of view and saves this team the trouble of not being able to refactor the 'AutomatedTest classes
 * name.
 *
 * @author Tim Slifer
 * @author Matt Maurer
 */
public class AutomatedTestListener implements ITestListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(AutomatedTestListener.class);
    
    @Override
    public void onStart(ITestContext context) {
        LOG.info("Reading XML Parameters.");
        Map<String, String> xmlParameters = context.getCurrentXmlTest().getAllParameters();
        XmlParameterValidator parameterValidator = new XmlParameterValidator(xmlParameters);
        
        RunContext.gridHost = parameterValidator.validateGridHost();
        RunContext.browser = parameterValidator.validateBrowser();
        RunContext.browserVersion = parameterValidator.validateBrowserVersion();
        RunContext.platform = parameterValidator.validatePlatform();
        RunContext.timeout = parameterValidator.validateTimeout();
        RunContext.appUrl = parameterValidator.validateAppUrl();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        RunContext.setTestCaseName(result.getName());
        LOG.info("Launching RemoteWebDriver for test [{}].", RunContext.getTestCaseName());
        MutableCapabilities capabilities = CapabilityProvider.getBrowserOptions();
        
        try {
            WebDriverHolder.setWebDriver(
                    new RemoteWebDriver(new URL("http://" + RunContext.gridHost + ":4444/wd/hub"), capabilities));
        }
        catch (MalformedURLException exception) {
            LOG.error("Grid URL is invalid.");
            throw new RuntimeException(exception);
        }
        
        WebDriverHolder.getWebDriver().manage().window().maximize();
        WebDriverHolder.getWebDriver().get(RunContext.appUrl);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        shutdownWebDriver();
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        shutdownWebDriver();
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        shutdownWebDriver();
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        shutdownWebDriver();
    }
    
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        shutdownWebDriver();
    }
    
    private void shutdownWebDriver() {
        LOG.info("Stopping the WebDriver.");
        WebDriverHolder.getWebDriver().quit();
    }
    
    @Override
    public void onFinish(ITestContext context) {
        LOG.info("Finished");
    }
}
