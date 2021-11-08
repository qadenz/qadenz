/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.config;

import io.qadenz.automation.commands.Assertions;
import io.qadenz.automation.reporter.TestReporter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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
@Listeners ({TestReporter.class})
public class AutomatedWebTest {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    /**
     * Captures a timestamp as the start time of the suite.
     *
     * @param testContext The injected {@link ITestContext}.
     */
    @BeforeSuite (alwaysRun = true)
    public void captureStartDateTime(ITestContext testContext) {
        WebConfig.suiteStartDate = LocalDateTime.now();
    }
    
    /**
     * Begins the suite execution process by reading the Suite-level parameters given on the Suite XML file, validating,
     * and assigning values on the {@link WebConfig}.
     *
     * @param testContext The injected {@link ITestContext}.
     */
    @BeforeSuite (alwaysRun = true, dependsOnMethods = "captureStartDateTime")
    public void processXmlSuiteParameters(ITestContext testContext) {
        LOG.info("Reading XML Suite Parameters.");
        
        Map<String, String> xmlParameters = testContext.getCurrentXmlTest().getAllParameters();
        XmlParameterValidator xmlParameterValidator = new XmlParameterValidator(xmlParameters);
        
        WebConfig.gridHost = xmlParameterValidator.validateGridHost();
        WebConfig.applicationName = xmlParameterValidator.validateApplicationName();
    }
    
    /**
     * Prior to each {@code <test>} on the Suite XML file, reads the given Test-level parameters, validates, and assigns
     * values on the {@link WebConfig}.
     *
     * @param testContext The injected {@link ITestContext}.
     */
    @BeforeTest (alwaysRun = true)
    public void processXmlTestParameters(ITestContext testContext) {
        LOG.info("Reading XML Test Parameters.");
        
        Map<String, String> xmlParameters = testContext.getCurrentXmlTest().getAllParameters();
        XmlParameterValidator xmlParameterValidator = new XmlParameterValidator(xmlParameters);
        
        WebConfig.browser = xmlParameterValidator.validateBrowser();
        WebConfig.browserVersion = xmlParameterValidator.validateBrowserVersion();
        WebConfig.platform = xmlParameterValidator.validatePlatform();
        WebConfig.timeout = xmlParameterValidator.validateTimeout();
        WebConfig.appUrl = xmlParameterValidator.validateAppUrl();
        WebConfig.retryInterceptedClicks = xmlParameterValidator.validateRetryInterceptedClicks();
    }
    
    /**
     * Begins execution of a test by launching a {@link RemoteWebDriver} on a Selenium Grid, and opening the application
     * URL.
     *
     * @throws Exception on invalid Grid URL.
     */
    @BeforeMethod (alwaysRun = true)
    public void startWebDriver() throws Exception {
        LOG.info("Launching WebDriver.");
        Assertions.init();
        Capabilities capabilities = CapabilityProvider.getBrowserOptions();
        
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
