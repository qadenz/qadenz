package io.slifer.automation.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class XmlParameterValidator {
    
    private static final Logger LOG = LoggerFactory.getLogger(XmlParameterValidator.class);
    
    private Map<String, String> xmlParameters;
    
    public XmlParameterValidator(Map<String, String> xmlParameters) {
        this.xmlParameters = xmlParameters;
    }
    
    public String validateGridHost() {
        if (xmlParameters.containsKey("Grid Host")) {
            String gridHost = xmlParameters.get("Grid Host");
            LOG.info("Using Selenium Grid at Host [" + gridHost + "].");
            
            return gridHost;
        }
        else {
            String message = "Grid Host parameter is required.";
            LOG.error(message);
            
            throw new IllegalArgumentException(message);
        }
    }
    
    public Browser validateBrowser() {
        if (xmlParameters.containsKey("Browser")) {
            String xmlBrowser = xmlParameters.get("Browser");
            try {
                Browser browser = Browser.fromString(xmlBrowser);
                LOG.info("Using Browser [" + browser.toString() + "].");
                
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
    
    public String validateBrowserVersion() {
        if (xmlParameters.containsKey("Browser Version")) {
            String xmlBrowserVersion = xmlParameters.get("Browser Version");
            LOG.info("Using Browser Version [" + xmlBrowserVersion + "].");
            
            return xmlBrowserVersion;
        }
        else {
            LOG.info("No Browser Version given, using any available.");
            
            return null;
        }
    }
    
    public Platform validatePlatform() {
        if (xmlParameters.containsKey("Platform")) {
            String xmlPlatform = xmlParameters.get("Platform");
            try {
                Platform platform = Platform.fromString(xmlPlatform);
                LOG.info("Using Platform [" + platform.toString() + "].");
                
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
    
    public String validateAppUrl() {
        if (xmlParameters.containsKey("App URL")) {
            String url = xmlParameters.get("App URL");
            LOG.info("Using Application URL [" + url + "].");
            
            return url;
        }
        else {
            String message = "Application URL parameter is required.";
            LOG.error(message);
            
            throw new IllegalArgumentException(message);
        }
    }
}
