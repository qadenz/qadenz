/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.commands;

import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.reporter.Screenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Commands used to interact with a web browser.
 *
 * @author Tim Slifer
 */
public class Browser {
    
    private static final Logger LOG = LoggerFactory.getLogger(Browser.class);
    
    private static Screenshot screenshot = new Screenshot();
    
    /**
     * Accepts a JavaScript Alert.
     */
    public static void acceptAlert() {
        LOG.info("Accepting alert.");
        try {
            WebDriverProvider.getWebDriver().switchTo().alert().accept();
        }
        catch (Exception exception) {
            LOG.error("Error accepting alert :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Dismisses a JavaScript Alert.
     */
    public static void dismissAlert() {
        LOG.info("Dismissing alert.");
        try {
            WebDriverProvider.getWebDriver().switchTo().alert().dismiss();
        }
        catch (Exception exception) {
            LOG.error("Error dismissing alert :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Enters text into a field on a JavaScript Alert.
     *
     * @param input The text input.
     */
    public static void enterTextOnAlert(String input) {
        LOG.info("Entering text [{}] into Alert.", input);
        try {
            WebDriverProvider.getWebDriver().switchTo().alert().sendKeys(input);
        }
        catch (Exception exception) {
            LOG.error("Error entering text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Closes the current browser window.
     */
    public static void closeBrowser() {
        LOG.info("Closing browser.");
        try {
            WebDriverProvider.getWebDriver().close();
        }
        catch (Exception exception) {
            LOG.error("Error closing browser :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Navigates back to the previous page.
     */
    public static void goBack() {
        LOG.info("Navigating back.");
        try {
            WebDriverProvider.getWebDriver().navigate().back();
        }
        catch (Exception exception) {
            LOG.error("Error navigating back. :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Navigates forward to the next page, if one exists.
     */
    public static void goForward() {
        LOG.info("Navigating forward.");
        try {
            WebDriverProvider.getWebDriver().navigate().forward();
        }
        catch (Exception exception) {
            LOG.error("Error navigating forward :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Refreshes the current page.
     */
    public static void refreshPage() {
        LOG.info("Refreshing page.");
        try {
            WebDriverProvider.getWebDriver().navigate().refresh();
        }
        catch (Exception exception) {
            LOG.error("Error refreshing page :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Deletes all current cookies.
     */
    public static void deleteCookies() {
        LOG.info("Deleting all cookies.");
        try {
            WebDriverProvider.getWebDriver().manage().deleteAllCookies();
        }
        catch (Exception exception) {
            LOG.error("Error deleting cookies :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Navigates to the given URL.
     *
     * @param url The URL to be opened.
     */
    public static void openUrl(String url) {
        LOG.info("Opening URL [{}].", url);
        try {
            WebDriverProvider.getWebDriver().get(url);
        }
        catch (Exception exception) {
            LOG.error("Error opening URL :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Moves focus to the new browser window, relative to the current window.
     */
    public static void focusOnNextWindow() {
        LOG.info("Switch focus to next window.");
        try {
            switchWindowFocus(Window.NEXT);
        }
        catch (Exception exception) {
            LOG.error("Error switching to next window :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Moves focus to the previous browser window, relative to the current window.
     */
    public static void focusOnPreviousWindow() {
        LOG.info("Switch focus to previous window.");
        try {
            switchWindowFocus(Window.PREVIOUS);
        }
        catch (Exception exception) {
            LOG.error("Error switching previous window :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    private static void switchWindowFocus(Window window) {
        int currentWindowIndex;
        List<String> windowHandles = new ArrayList<>(WebDriverProvider.getWebDriver().getWindowHandles());
        try {
            currentWindowIndex = windowHandles.indexOf(WebDriverProvider.getWebDriver().getWindowHandle());
        }
        catch (Exception exception) {
            currentWindowIndex = windowHandles.size();
        }
        
        if (window == Window.NEXT &&
                WebDriverProvider.getWebDriver().getWindowHandles().size() - 1 > currentWindowIndex) {
            WebDriverProvider.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex + 1));
        }
        else if (window == Window.PREVIOUS && !WebDriverProvider.getWebDriver().getWindowHandles().isEmpty()) {
            WebDriverProvider.getWebDriver().switchTo().window(windowHandles.get(currentWindowIndex - 1));
        }
    }
    
    private enum Window {
        NEXT,
        PREVIOUS
    }
}
