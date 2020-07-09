package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.config.RunContext;
import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Common commands related to the WebDriver layer.
 *
 * @author Tim Slifer
 */
public abstract class WebDriverCommands extends Commands {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverCommands.class);
    
    private ElementFinder elementFinder;
    
    public WebDriverCommands() {
        this.elementFinder = new ElementFinder();
    }
    
    /**
     * Removes the text from an input field.
     *
     * @param locator The mapped UI element.
     */
    public void clear(Locator locator) {
        LOG.info("Clearing the contents of element [{}].", locator.getName());
        try {
            WebElement webElement = elementFinder.findWhenVisible(locator);
            webElement.clear();
        }
        catch (Exception e) {
            LOG.error("Error clearing contents.", e);
            
            throw e;
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
            webElement = elementFinder.findWhenClickable(locator);
            webElement.click();
        }
        catch (ElementClickInterceptedException e) {
            if (RunContext.retryInterceptedClicks) {
                LOG.debug("Click intercepted, trying with Actions.");
                webElement = elementFinder.findWhenClickable(locator);
                
                Actions actions = new Actions(RunContext.getWebDriver());
                actions.moveToElement(webElement).click().perform();
            }
            else {
                LOG.error("Click intercepted.", e);
                
                throw e;
            }
        }
        catch (Exception e) {
            LOG.error("Error clicking element.", e);
            
            throw e;
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
            WebElement webElement = elementFinder.findWhenClickable(locator);
            
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.moveToElement(webElement, xOffset, yOffset).click().perform();
        }
        catch (Exception e) {
            LOG.error("Error clicking element.", e);
            
            throw e;
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
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.keyDown(Keys.CONTROL);
            for (Locator locator : locators) {
                WebElement element = elementFinder.findWhenClickable(locator);
                actions.click(element);
            }
            actions.keyUp(Keys.CONTROL);
        }
        catch (Exception e) {
            LOG.error("Error clicking elements.", e);
            
            throw e;
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.deselectByVisibleText(option);
        }
        catch (Exception e) {
            LOG.error("Error deselecting option.", e);
            
            throw e;
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
            WebElement webElement = elementFinder.findWhenClickable(locator);
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.doubleClick(webElement).perform();
        }
        catch (Exception e) {
            LOG.error("Error double-clicking element.", e);
            
            throw e;
        }
    }
    
    /**
     * Enters text into an input field.
     *
     * @param locator The mapped UI element.
     * @param input The text input.
     */
    public void enterText(Locator locator, CharSequence... input) {
        LOG.info("Entering text [{}] into element [{}].", input, locator.getName());
        try {
            WebElement webElement = elementFinder.findWhenVisible(locator);
            webElement.sendKeys(input);
        }
        catch (Exception e) {
            LOG.error("Error entering text.", e);
            
            throw e;
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.moveToElement(webElement);
        }
        catch (Exception e) {
            LOG.error("Error hovering on element.", e);
            
            throw e;
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.selectByVisibleText(option);
        }
        catch (Exception e) {
            LOG.error("Error selecting option.", e);
            
            throw e;
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
            RemoteWebDriver remoteWebDriver = (RemoteWebDriver) RunContext.getWebDriver();
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            
            URL url = WebDriverCommands.class.getClassLoader().getResource(fileName);
            File file = Paths.get(url.toURI()).toFile();
            String filePath = file.getAbsolutePath();
            
            WebElement webElement = elementFinder.findWhenPresent(fileInput);
            webElement.sendKeys(filePath);
        }
        catch (Exception e) {
            LOG.error("Error uploading file.", e);
            
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
        LOG.info("Switch focus to default frame.");
        try {
            RunContext.getWebDriver().switchTo().defaultContent();
        }
        catch (Exception e) {
            LOG.error("Error switching focus.", e);
            
            throw e;
        }
    }
    
    /**
     * Moves focus to a frame.
     *
     * @param locator The UI mapping of the frame.
     */
    public void focusOnFrame(Locator locator) {
        LOG.info("Switch focus to frame [{}]", locator.getName());
        try {
            RunContext.getWebDriver().switchTo().defaultContent();
            WebElement webElement = elementFinder.findWhenVisible(locator);
            RunContext.getWebDriver().switchTo().frame(webElement);
        }
        catch (Exception e) {
            LOG.error("Error switching focus.", e);
            
            throw e;
        }
    }
    
    /**
     * Pauses execution until the given condition is met, or a timeout occurs.
     *
     * @param condition The Condition to be satisfied during the wait.
     */
    public void pause(Condition condition) {
        LOG.info("Wait for condition :: {}", condition.description());
        WebDriverWait webDriverWait = new WebDriverWait(RunContext.getWebDriver(), RunContext.timeout);
        
        try {
            webDriverWait.until((ExpectedCondition<Boolean>) webDriver -> condition.result());
        }
        catch (Exception e) {
            LOG.error("Error while waiting.", e);
            
            throw e;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The text value.
     */
    public String getTextOfElement(Locator locator) {
        LOG.info("Retrieving text of element [{}].", locator.getName());
        try {
            return new ElementInspector().getTextOfElement(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving text.", e);
            
            throw e;
        }
    }
    
    /**
     * Retrieves the visible inner text of each instance of an element, and any descendants, on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The text value.
     */
    public List<String> getTextOfElements(Locator locator) {
        LOG.info("Retrieving text of each instance of element [{}].", locator.getName());
        try {
            return new ElementInspector().getTextOfElements(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving text.", e);
            
            throw e;
        }
    }
    
    /**
     * Retrieves the instance of an element that contains the expected value.
     *
     * @param locator The mapped UI element.
     * @param expectedText The value to be identified.
     *
     * @return The element instance.
     */
    public int getInstanceOfElementText(Locator locator, String expectedText) {
        LOG.info("Finding instance of element [{}] with value [{}].", locator.getName(), expectedText);
        
        List<String> elementValues = new ElementInspector().getTextOfElements(locator);
        for (int i = 0; i < elementValues.size(); i++) {
            if (elementValues.get(i).equals(expectedText)) {
                LOG.debug("Found value at index [{}].", i);
                
                return i;
            }
        }
        
        LOG.error("Could not find instance with expected value.");
        
        throw new IllegalArgumentException("Value [" + expectedText + "] was not found.");
    }
    
    /**
     * Retrieves the instance of an element with an attribute that contains the expected value.
     *
     * @param locator The mapped UI element.
     * @param attribute The attribute to be examined.
     * @param expectedValue The value to be identified.
     *
     * @return The element instance.
     */
    public int getInstanceOfElementAttribute(Locator locator, String attribute, String expectedValue) {
        LOG.info("Finding instance of element [{}] with attribute [{}] containing value [{}].", locator.getName(),
                attribute, expectedValue);
        
        List<String> attributeValues = new ElementInspector().getAttributeOfElements(locator, attribute);
        for (int i = 0; i < attributeValues.size(); i++) {
            if (attributeValues.get(i).equals(expectedValue)) {
                LOG.debug("Found value at index [{}].", i);
                
                return i;
            }
        }
        
        LOG.error("Could not find instance with expected value.");
        
        throw new IllegalArgumentException("Attribute value [" + expectedValue + "] was not found.");
    }
}
