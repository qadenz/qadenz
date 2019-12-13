package io.slifer.automation.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;
import java.net.URL;

public class AutomatedTestInvokedMethodListener implements IInvokedMethodListener {
    private static final Logger LOG = LoggerFactory.getLogger(AutomatedTestInvokedMethodListener.class);
    
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        RunContext.setTestCaseName(testResult.getName());
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
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        LOG.info("Stopping the WebDriver.");
        WebDriverHolder.getWebDriver().quit();
    }
}
