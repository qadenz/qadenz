package io.slifer.automation.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Configures the Browser Options for the session.
 *
 * @author Tim Slifer
 */
public class CapabilityProvider {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    /**
     * Loads the options for the given browser, and performs default configurations based on Suite Parameters.
     *
     * @return The Browser Options.
     */
    public static MutableCapabilities getBrowserOptions() {
        Browser browser = WebConfig.browser;
        
        List<String> browserArgs = loadArgs(browser);
        
        MutableCapabilities capabilities = null;
        switch (browser) {
            case CHROME:
                capabilities = new ChromeOptions();
                LOG.info("Chrome Args: {}", browserArgs);
                ((ChromeOptions) capabilities).addArguments(browserArgs);
                break;
            case EDGE:
                capabilities = new EdgeOptions();
                break;
            case FIREFOX:
                capabilities = new FirefoxOptions();
                LOG.info("Firefox Args: {}", browserArgs);
                ((FirefoxOptions) capabilities).addArguments(browserArgs);
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
    
    private static List<String> loadArgs(Browser browser) {
        List<String> args = new ArrayList<>();
        String fileName = "config/" + browser.name().toLowerCase() + "-args.json";
        
        try {
            Path jsonFile = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            String jsonText = Files.readString(jsonFile);
            JSONArray jsonArray = new JSONObject(jsonText).getJSONArray("args");
            for (int i = 0; i < jsonArray.length(); i++) {
                args.add(jsonArray.get(i).toString());
            }
        }
        catch (Exception exception) {
            LOG.error("Error loading args for browser [{}] :: {}: {}", browser.toString(),
                    exception.getClass().getSimpleName(), exception.getMessage());
            // Log the exception and return an empty list.
        }
        
        return args;
    }
}
