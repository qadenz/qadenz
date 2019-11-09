package io.slifer.automation.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Commands used to interact with a web browser.
 *
 * @author Tim Slifer
 */
public class BrowserCommands extends WebDriverCommands {
    
    private static final Logger LOG = LoggerFactory.getLogger(BrowserCommands.class);
    
    public BrowserCommands() {
        super();
    }
    
    /**
     * Accepts a JavaScript Alert.
     */
    public void acceptAlert() {
        LOG.info("Accepting alert.");
        try {
            webDriver.switchTo().alert().accept();
        }
        catch (Exception e) {
            LOG.error("Error accepting alert.", e);
            throw e;
        }
    }
    
    /**
     * Dismisses a JavaScript Alert.
     */
    public void dismissAlert() {
        LOG.info("Dismissing alert.");
        try {
            webDriver.switchTo().alert().dismiss();
        }
        catch (Exception e) {
            LOG.error("Error dismissing alert.", e);
            throw e;
        }
    }
    
    /**
     * Closes the current browser window.
     */
    public void closeBrowser() {
        LOG.info("Closing browser.");
        try {
            webDriver.close();
        }
        catch (Exception e) {
            LOG.error("Error closing browser.", e);
            throw e;
        }
    }
    
    /**
     * Navigates back to the previous page.
     */
    public void goBack() {
        LOG.info("Navigating back.");
        try {
            webDriver.navigate().back();
        }
        catch (Exception e) {
            LOG.error("Error navigating back.", e);
            throw e;
        }
    }
    
    /**
     * Navigates forward to the next page, if one exists.
     */
    public void goForward() {
        LOG.info("Navigating forward.");
        try {
            webDriver.navigate().forward();
        }
        catch (Exception e) {
            LOG.error("Error navigating back.", e);
            throw e;
        }
    }
    
    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        LOG.info("Refreshing page.");
        try {
            webDriver.navigate().refresh();
        }
        catch (Exception e) {
            LOG.error("Error refreshing page.", e);
            throw e;
        }
    }
    
    /**
     * Deletes all current cookies.
     */
    public void deleteCookies() {
        LOG.info("Deleting all cookies.");
        try {
            webDriver.manage().deleteAllCookies();
        }
        catch (Exception e) {
            LOG.error("Error deleting cookies.", e);
            throw e;
        }
    }
    
    /**
     * Navigates to the given URL.
     *
     * @param url The URL to be opened.
     */
    public void openUrl(String url) {
        LOG.info("Opening URL [{}].", url);
        try {
            webDriver.get(url);
        }
        catch (Exception e) {
            LOG.error("Error opening URL.", e);
            throw e;
        }
    }
}
