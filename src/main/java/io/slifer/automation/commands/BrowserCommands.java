package io.slifer.automation.commands;

import org.openqa.selenium.WebDriver;

/**
 * Commands used to interact with a web browser.
 *
 * @author Tim Slifer
 */
public class BrowserCommands extends WebDriverCommands {
    
    public BrowserCommands(WebDriver webDriver) {
        super(webDriver);
    }
    
    /**
     * Accepts a JavaScript Alert.
     */
    public void acceptAlert() {
        webDriver.switchTo().alert().accept();
    }
    
    /**
     * Dismisses a JavaScript Alert.
     */
    public void dismissAlert() {
        webDriver.switchTo().alert().dismiss();
    }
    
    /**
     * Closes the current browser window.
     */
    public void closeBrowser() {
        webDriver.close();
    }
    
    /**
     * Navigates back to the previous page.
     */
    public void goBack() {
        webDriver.navigate().back();
    }
    
    /**
     * Navigates forward to the next page, if one exists.
     */
    public void goForward() {
        webDriver.navigate().forward();
    }
    
    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        webDriver.navigate().refresh();
    }
    
    /**
     * Deletes all current cookies.
     */
    public void deleteCookies() {
        webDriver.manage().deleteAllCookies();
    }
    
    /**
     * Navigates to the given URL.
     *
     * @param url The URL to be opened.
     */
    public void openUrl(String url) {
        webDriver.get(url);
    }
}
