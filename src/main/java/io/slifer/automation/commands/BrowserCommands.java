package io.slifer.automation.commands;

import io.slifer.automation.config.RunContext;
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
    
    private Logger LOG;
    
    public BrowserCommands() {
        super();
        LOG = LoggerFactory.getLogger(BrowserCommands.class);
    }
    
    public BrowserCommands(Class<?> proxyLogger) {
        super(proxyLogger);
        LOG = LoggerFactory.getLogger(proxyLogger);
    }
    
    /**
     * Accepts a JavaScript Alert.
     */
    public void acceptAlert() {
        LOG.info("Accepting alert.");
        try {
            RunContext.getWebDriver().switchTo().alert().accept();
        }
        catch (Exception exception) {
            LOG.error("Error accepting alert :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Dismisses a JavaScript Alert.
     */
    public void dismissAlert() {
        LOG.info("Dismissing alert.");
        try {
            RunContext.getWebDriver().switchTo().alert().dismiss();
        }
        catch (Exception exception) {
            LOG.error("Error dismissing alert :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
            RunContext.getWebDriver().switchTo().alert().sendKeys(input);
        }
        catch (Exception exception) {
            LOG.error("Error entering text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Closes the current browser window.
     */
    public void closeBrowser() {
        LOG.info("Closing browser.");
        try {
            RunContext.getWebDriver().close();
        }
        catch (Exception exception) {
            LOG.error("Error closing browser :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Navigates back to the previous page.
     */
    public void goBack() {
        LOG.info("Navigating back.");
        try {
            RunContext.getWebDriver().navigate().back();
        }
        catch (Exception exception) {
            LOG.error("Error navigating back. :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Navigates forward to the next page, if one exists.
     */
    public void goForward() {
        LOG.info("Navigating forward.");
        try {
            RunContext.getWebDriver().navigate().forward();
        }
        catch (Exception exception) {
            LOG.error("Error navigating back :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        LOG.info("Refreshing page.");
        try {
            RunContext.getWebDriver().navigate().refresh();
        }
        catch (Exception exception) {
            LOG.error("Error refreshing page :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Deletes all current cookies.
     */
    public void deleteCookies() {
        LOG.info("Deleting all cookies.");
        try {
            RunContext.getWebDriver().manage().deleteAllCookies();
        }
        catch (Exception exception) {
            LOG.error("Error deleting cookies :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
            RunContext.getWebDriver().get(url);
        }
        catch (Exception exception) {
            LOG.error("Error opening URL :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    private void switchWindowFocus(Window window) {
        int currentWindowIndex;
        List<String> windowHandles = new ArrayList<>(RunContext.getWebDriver().getWindowHandles());
        try {
            currentWindowIndex = windowHandles.indexOf(RunContext.getWebDriver().getWindowHandle());
        }
        catch (Exception exception) {
            currentWindowIndex = windowHandles.size();
        }
        
        if (window == Window.NEXT && RunContext.getWebDriver().getWindowHandles().size() - 1 > currentWindowIndex) {
            RunContext.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex + 1));
        }
        else if (window == Window.PREVIOUS && RunContext.getWebDriver().getWindowHandles().size() >= 1) {
            RunContext.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex - 1));
        }
    }
    
    private enum Window {
        NEXT,
        PREVIOUS;
    }
}
