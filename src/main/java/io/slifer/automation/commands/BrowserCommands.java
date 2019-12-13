package io.slifer.automation.commands;

import io.slifer.automation.config.WebDriverHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
            WebDriverHolder.getWebDriver().switchTo().alert().accept();
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
            WebDriverHolder.getWebDriver().switchTo().alert().dismiss();
        }
        catch (Exception e) {
            LOG.error("Error dismissing alert.", e);
            throw e;
        }
    }
    
    /**
     * Enters text into a field on a JavaScript Alert.
     *
     * @param input The text input.
     */
    public void enterTextOnAlert(String input) {
        LOG.info("Entering text [{}] into Alert.", input);
        try {
            WebDriverHolder.getWebDriver().switchTo().alert().sendKeys(input);
        }
        catch (Exception e) {
            LOG.error("Error entering text.", e);
            throw e;
        }
    }
    
    /**
     * Closes the current browser window.
     */
    public void closeBrowser() {
        LOG.info("Closing browser.");
        try {
            WebDriverHolder.getWebDriver().close();
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
            WebDriverHolder.getWebDriver().navigate().back();
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
            WebDriverHolder.getWebDriver().navigate().forward();
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
            WebDriverHolder.getWebDriver().navigate().refresh();
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
            WebDriverHolder.getWebDriver().manage().deleteAllCookies();
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
            WebDriverHolder.getWebDriver().get(url);
        }
        catch (Exception e) {
            LOG.error("Error opening URL.", e);
            throw e;
        }
    }
    
    /**
     * Moves focus to the new browser window, relative to the current window.
     */
    public void focusOnNextWindow() {
        LOG.info("Switch focus to next window.");
        try {
            switchWindowFocus(Window.NEXT);
        }
        catch (Exception e) {
            LOG.error("Error switching focus.", e);
            throw e;
        }
    }
    
    /**
     * Moves focus to the previous browser window, relative to the current window.
     */
    public void focusOnPreviousWindow() {
        LOG.info("Switch focus to previous window.");
        try {
            switchWindowFocus(Window.PREVIOUS);
        }
        catch (Exception e) {
            LOG.error("Error switching focus.");
            throw e;
        }
    }
    
    private void switchWindowFocus(Window window) {
        int currentWindowIndex;
        List<String> windowHandles = new ArrayList<>(WebDriverHolder.getWebDriver().getWindowHandles());
        try {
            currentWindowIndex = windowHandles.indexOf(WebDriverHolder.getWebDriver().getWindowHandle());
        }
        catch (Exception exception) {
            currentWindowIndex = windowHandles.size();
        }
        
        if (window == Window.NEXT &&
                WebDriverHolder.getWebDriver().getWindowHandles().size() - 1 > currentWindowIndex) {
            WebDriverHolder.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex + 1));
        }
        else if (window == Window.PREVIOUS && WebDriverHolder.getWebDriver().getWindowHandles().size() >= 1) {
            WebDriverHolder.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex - 1));
        }
    }
    
    private enum Window {
        NEXT,
        PREVIOUS;
    }
}
