package io.slifer.automation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;

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
    public void onFinish(ITestContext context) {
        LOG.info("Finished");
    }
}
