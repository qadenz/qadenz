/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.commands;

import dev.qadenz.automation.conditions.Condition;
import dev.qadenz.automation.config.WebConfig;
import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.reporter.Screenshot;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.WebFinder;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Common commands related to the WebDriver layer.
 *
 * @author Tim Slifer
 */
public class WebCommander extends Commands {
    
    private Logger LOG;
    
    protected WebFinder webFinder = new WebFinder();
    protected Screenshot screenshot = new Screenshot();
    
    public WebCommander() {
        super();
        LOG = LoggerFactory.getLogger(WebCommander.class);
    }
    
    public WebCommander(Class<?> logger) {
        super(logger);
        LOG = LoggerFactory.getLogger(logger);
    }
    
    public WebCommander(String logger) {
        super(logger);
        LOG = LoggerFactory.getLogger(logger);
    }
    
    /**
     * Removes the text from an input field.
     *
     * @param locator The mapped UI element.
     */
    public void clear(Locator locator) {
        LOG.info("Clearing the contents of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            webElement.clear();
        }
        catch (Exception exception) {
            LOG.error("Error clearing contents :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Clicks on the given element.
     *
     * @param locator The mapped UI element.
     */
    public void click(Locator locator) {
        LOG.info("Clicking element [{}].", locator.getName());
        WebElement webElement;
        try {
            webElement = webFinder.findWhenClickable(locator);
            webElement.click();
        }
        catch (Exception exception) {
            if (WebConfig.retryInterceptedClicks && exception instanceof ElementClickInterceptedException) {
                try {
                    LOG.debug("Click intercepted, trying with Actions.");
                    webElement = webFinder.findWhenClickable(locator);
                    
                    Actions actions = new Actions(WebDriverProvider.getWebDriver());
                    actions.click(webElement).perform();
                }
                catch (Exception exception1) {
                    LOG.error("Error clicking with Actions :: {}: {}", exception.getClass().getSimpleName(),
                            exception.getMessage());
                    screenshot.capture();
                    
                    throw exception1;
                }
            }
            else {
                LOG.error("Error clicking element :: {}: {}", exception.getClass().getSimpleName(),
                        exception.getMessage());
                screenshot.capture();
                
                throw exception;
            }
        }
    }
    
    /**
     * Clicks on an element at a specific point.
     *
     * @param locator The mapped UI element.
     * @param xOffset Horizontal offset from top-left corner. A negative value means coordinates left from the element.
     * @param yOffset Vertical offset from top-left corner. A negative value means coordinates above the element.
     */
    public void click(Locator locator, int xOffset, int yOffset) {
        LOG.info("Clicking element [{}] at point [{}, {}].", locator.getName(), xOffset, yOffset);
        try {
            WebElement webElement = webFinder.findWhenClickable(locator);
            
            Actions actions = new Actions(WebDriverProvider.getWebDriver());
            actions.moveToElement(webElement, xOffset, yOffset).click().perform();
        }
        catch (Exception exception) {
            LOG.error("Error clicking element :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Clicks each of the given elements while holding the CTRL key.
     *
     * @param locators The mapped UI elements.
     */
    public void controlClick(Locator... locators) {
        List<String> names = new ArrayList<>();
        Arrays.stream(locators).forEachOrdered(locator -> names.add(locator.getName()));
        LOG.info("Control-Clicking elements [{}]", names);
        
        try {
            Actions actions = new Actions(WebDriverProvider.getWebDriver());
            actions.keyDown(Keys.CONTROL);
            Arrays.stream(locators).forEach(locator -> actions.click(webFinder.findWhenClickable(locator)));
            actions.keyUp(Keys.CONTROL);
            actions.perform();
        }
        catch (Exception exception) {
            LOG.error("Error clicking elements :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Deselects an option from a dropdown menu implemented as a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param option The option to be deselected.
     */
    public void deselect(Locator locator, String option) {
        LOG.info("Deselecting option [{}] from element [{}].", option, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.deselectByVisibleText(option);
        }
        catch (Exception exception) {
            LOG.error("Error deselecting option :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Deselects multiple options from a dropdown menu implemented as a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param options The options to be deselected.
     */
    public void deselect(Locator locator, String... options) {
        LOG.info("Deselecting options [{}] from element [{}].", options, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            Arrays.stream(options).forEach(select::deselectByVisibleText);
        }
        catch (Exception exception) {
            LOG.error("Error deselecting options :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Double-clicks on the given element.
     *
     * @param locator The mapped UI element.
     */
    public void doubleClick(Locator locator) {
        LOG.info("Double-clicking element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenClickable(locator);
            Actions actions = new Actions(WebDriverProvider.getWebDriver());
            actions.doubleClick(webElement).perform();
        }
        catch (Exception exception) {
            LOG.error("Error double-clicking element :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Sends text and/or other keystrokes into an element.
     *
     * @param locator The mapped UI element.
     * @param input The text/keystrokes to send.
     */
    public void enterText(Locator locator, CharSequence... input) {
        LOG.info("Entering text [{}] into element [{}].", stringify(input), locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            webElement.sendKeys(input);
        }
        catch (Exception exception) {
            LOG.error("Error entering text :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Hovers the mouse over an element.
     *
     * @param locator The mapped UI element.
     */
    public void hover(Locator locator) {
        LOG.info("Hovering on element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Actions actions = new Actions(WebDriverProvider.getWebDriver());
            actions.moveToElement(webElement).perform();
        }
        catch (Exception exception) {
            LOG.error("Error hovering on element :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Selects an option from a dropdown menu implemented as a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param option The option to be selected.
     */
    public void select(Locator locator, String option) {
        LOG.info("Selecting option [{}] from element [{}].", option, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.selectByVisibleText(option);
        }
        catch (Exception exception) {
            LOG.error("Error selecting option :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Selects multiple options from a dropdown menu implemented as a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param options The options to be selected.
     */
    public void select(Locator locator, String... options) {
        LOG.info("Selecting options [{}] from element [{}].", options, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            Arrays.stream(options).forEach(select::selectByVisibleText);
        }
        catch (Exception exception) {
            LOG.error("Error selecting options :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Uploads a file.
     *
     * @param fileInput The mapped DOM element upload target.
     * @param fileName The path to and name of the file to be uploaded.
     */
    public void uploadFile(Locator fileInput, String fileName) {
        LOG.info("Uploading file [{}].", fileName);
        try {
            RemoteWebDriver remoteWebDriver = (RemoteWebDriver) WebDriverProvider.getWebDriver();
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            
            URL url = WebCommander.class.getClassLoader().getResource(fileName);
            File file = Paths.get(url.toURI()).toFile();
            String filePath = file.getAbsolutePath();
            
            WebElement webElement = webFinder.findWhenPresent(fileInput);
            webElement.sendKeys(filePath);
        }
        catch (Exception exception) {
            LOG.error("Error uploading file :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw new RuntimeException("File could not be uploaded.");
        }
    }
    
    /**
     * Clears the contents of an input field and enters the given text.
     *
     * @param locator The mapped UI element.
     * @param input The text input.
     */
    public void clearAndEnterText(Locator locator, String input) {
        clear(locator);
        enterText(locator, input);
    }
    
    /**
     * Moves focus to the default or main frame.
     */
    public void focusOnDefaultContent() {
        LOG.info("Switching focus to default frame.");
        try {
            WebDriverProvider.getWebDriver().switchTo().defaultContent();
        }
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Moves focus to a frame.
     *
     * @param locator The UI mapping of the frame.
     */
    public void focusOnFrame(Locator locator) {
        LOG.info("Switching focus to frame [{}]", locator.getName());
        try {
            WebDriverProvider.getWebDriver().switchTo().defaultContent();
            WebElement webElement = webFinder.findWhenVisible(locator);
            WebDriverProvider.getWebDriver().switchTo().frame(webElement);
        }
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Pauses execution until the given condition is met, or a timeout occurs.
     *
     * @param condition The Condition to be satisfied during the wait.
     */
    public void pause(Condition condition) {
        LOG.info("Waiting for condition :: {}", condition);
        WebDriverWait webDriverWait =
                new WebDriverWait(WebDriverProvider.getWebDriver(), Duration.ofSeconds(WebConfig.timeout));
        
        try {
            webDriverWait.until((ExpectedCondition<Boolean>) webDriver -> condition.result());
        }
        catch (Exception exception) {
            LOG.error("Error while waiting :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Captures a screenshot of the current browser.
     */
    public void captureScreenshot() {
        screenshot.capture();
    }
    
    private String stringify(CharSequence... input) {
        StringJoiner joiner = new StringJoiner(", ");
        Arrays.stream(input).forEach(charSequence -> {
            if (charSequence instanceof String) {
                joiner.add(charSequence);
            }
            else if (charSequence instanceof Keys) {
                joiner.add(((Keys) charSequence).name() + "-key");
            }
        });
        
        return joiner.toString();
    }
}
