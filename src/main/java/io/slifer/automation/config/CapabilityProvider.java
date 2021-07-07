package io.slifer.automation.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * Configures the Browser Options for the session.
 *
 * @author Tim Slifer
 */
public class CapabilityProvider {
    
    /**
     * Loads the options for the given browser, and performs default configurations based on Suite Parameters.
     *
     * @return The Browser Options.
     */
    public static MutableCapabilities getBrowserOptions() {
        Browser browser = WebConfig.browser;
        
        MutableCapabilities capabilities = null;
        switch (browser) {
            case CHROME:
                capabilities = new ChromeOptions();
                break;
            case EDGE:
                capabilities = new EdgeOptions();
                break;
            case FIREFOX:
                capabilities = new FirefoxOptions();
                break;
            case INTERNET_EXPLORER:
                capabilities = new InternetExplorerOptions();
                break;
        }
        
        if (WebConfig.browserVersion != null) {
            capabilities.setCapability("version", WebConfig.browserVersion);
        }
        
        if (WebConfig.platform != null) {
            capabilities.setCapability("platform", WebConfig.platform);
        }
        
        if (WebConfig.applicationName != null) {
            capabilities.setCapability("applicationName", WebConfig.applicationName);
        }
        
        return capabilities;
    }
}
