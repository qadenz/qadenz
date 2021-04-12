package io.slifer.automation.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class XmlParameterValidator {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private Map<String, String> xmlParameters;
    
    public XmlParameterValidator(Map<String, String> xmlParameters) {
        this.xmlParameters = xmlParameters;
    }
    
    /**
     * Ensures the `grid-host` parameter has been provided.
     *
     * @return The validated Grid Host.
     */
    public String validateGridHost() {
        if (xmlParameters.containsKey("grid-host")) {
            String gridHost = xmlParameters.get("grid-host");
            LOG.info("Using Selenium Grid at Host [{}].", gridHost);
            
            return gridHost;
        }
        else {
            String message = "Grid Host parameter is required.";
            LOG.error(message);
            
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Ensures the 'browser' parameter has been provided and is a valid option.
     *
     * @return The enumerated Browser name.
     */
    public Browser validateBrowser() {
        if (xmlParameters.containsKey("browser")) {
            String xmlBrowser = xmlParameters.get("browser");
            try {
                Browser browser = Browser.fromString(xmlBrowser);
                LOG.info("Using Browser [{}].", browser.toString());
                
                return browser;
            }
            catch (WebDriverException e) {
                String message = "Unrecognized Browser [" + xmlBrowser + "].";
                LOG.error(message);
                
                throw new IllegalArgumentException(message);
            }
        }
        else {
            String message = "Browser parameter is required.";
            LOG.error(message);
            
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Reads and returns the value of the 'browser-version' parameter, if one is provided.
     *
     * @return The Browser Version value.
     */
    public String validateBrowserVersion() {
        if (xmlParameters.containsKey("browser-version")) {
            String xmlBrowserVersion = xmlParameters.get("browser-version");
            LOG.info("Using Browser Version [{}].", xmlBrowserVersion);
            
            return xmlBrowserVersion;
        }
        else {
            LOG.info("No Browser Version given, using any available.");
            
            return null;
        }
    }
    
    /**
     * Reads and returns the value of the 'platform' parameter, if one is provided and is a valid Platform option.
     *
     * @return The enumerated Platform value.
     */
    public Platform validatePlatform() {
        if (xmlParameters.containsKey("platform")) {
            String xmlPlatform = xmlParameters.get("platform");
            try {
                Platform platform = Platform.fromString(xmlPlatform);
                LOG.info("Using Platform [" + platform.toString() + "].", platform.toString());
                
                return platform;
            }
            catch (WebDriverException e) {
                String message = "Unrecognized Platform [" + xmlPlatform + "].";
                LOG.error(message);
                
                throw new IllegalArgumentException(message);
            }
        }
        else {
            LOG.info("No Platform given, using any available.");
            
            return null;
        }
    }
    
    /**
     * Reads and returns the value of the 'application-name' parameter, if one is provided.
     *
     * @return The Application Name value.
     */
    public String validateApplicationName() {
        if (xmlParameters.containsKey("applicationName")) {
            String xmlApplicationName = xmlParameters.get("applicationName");
            LOG.info("Using Application Name [{}].", xmlApplicationName);
            
            return xmlApplicationName;
        }
        else {
            LOG.info("No Application Name given.");
            
            return null;
        }
    }
    
    /**
     * Reads and returns the Timeout limit, if one is provided, otherwise a default value is set.
     *
     * @return The Timeout limit.
     */
    public int validateTimeout() {
        if (xmlParameters.containsKey("timeout")) {
            int timeout = Integer.parseInt(xmlParameters.get("timeout"));
            LOG.info("Timeout limit declared [{}] seconds.", timeout);
            
            return timeout;
        }
        else {
            LOG.info("No timeout limit declared, using [{}] seconds.", 30);
            
            return 30;
        }
    }
    
    /**
     * Ensures the `app-url` parameter has been provided.
     *
     * @return The application URL.
     */
    public String validateAppUrl() {
        if (xmlParameters.containsKey("app-url")) {
            String url = xmlParameters.get("app-url");
            LOG.info("Using Application URL [" + url + "].");
            
            return url;
        }
        else {
            String message = "Application URL parameter is required.";
            LOG.error(message);
            
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Reads and returns the value of the 'retryInterceptedClicks' parameter, if one is provided, otherwise a default
     * value is set.
     *
     * @return The retryInterceptedClicks value.
     */
    public boolean validateRetryInterceptedClicks() {
        boolean xmlRetryInterceptedClicks = true;
        if (xmlParameters.containsKey("retryInterceptedClicks")) {
            xmlRetryInterceptedClicks = Boolean.parseBoolean(xmlParameters.get("retryInterceptedClicks"));
        }
        LOG.info("Retrying Intercepted Clicks [{}].", xmlRetryInterceptedClicks);
        
        return xmlRetryInterceptedClicks;
    }
}
