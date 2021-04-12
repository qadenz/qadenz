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
    
    private Logger LOG;
    
    private ElementFinder elementFinder = new ElementFinder();
    private ElementInspector elementInspector = new ElementInspector();
    
    public WebDriverCommands() {
        super();
        LOG = LoggerFactory.getLogger(WebDriverCommands.class);
    }
    
    public WebDriverCommands(Class<?> proxyLogger) {
        super(proxyLogger);
        LOG = LoggerFactory.getLogger(proxyLogger);
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
        catch (Exception exception) {
            LOG.error("Error clearing contents :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
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
            webElement = elementFinder.findWhenClickable(locator);
            webElement.click();
        }
        catch (ElementClickInterceptedException exception) {
            if (RunContext.retryInterceptedClicks) {
                LOG.debug("Click intercepted, trying with Actions.");
                webElement = elementFinder.findWhenClickable(locator);
                
                Actions actions = new Actions(RunContext.getWebDriver());
                actions.moveToElement(webElement).click().perform();
            }
            else {
                LOG.error("Error clicking with Actions :: {}: {}", exception.getClass().getSimpleName(),
                        exception.getMessage());
                
                throw exception;
            }
        }
        catch (Exception exception) {
            LOG.error("Error clicking element :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
        catch (Exception exception) {
            LOG.error("Error clicking element :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
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
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.keyDown(Keys.CONTROL);
            for (Locator locator : locators) {
                WebElement element = elementFinder.findWhenClickable(locator);
                actions.click(element);
            }
            actions.keyUp(Keys.CONTROL);
            actions.perform();
        }
        catch (Exception exception) {
            LOG.error("Error clicking elements :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.deselectByVisibleText(option);
        }
        catch (Exception exception) {
            LOG.error("Error deselecting option :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
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
            WebElement webElement = elementFinder.findWhenClickable(locator);
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.doubleClick(webElement).perform();
        }
        catch (Exception exception) {
            LOG.error("Error double-clicking element :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
            throw exception;
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
        catch (Exception exception) {
            LOG.error("Error entering text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Actions actions = new Actions(RunContext.getWebDriver());
            actions.moveToElement(webElement).perform();
        }
        catch (Exception exception) {
            LOG.error("Error hovering on element :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
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
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.selectByVisibleText(option);
        }
        catch (Exception exception) {
            LOG.error("Error selecting option :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
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
            RemoteWebDriver remoteWebDriver = (RemoteWebDriver) RunContext.getWebDriver();
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            
            URL url = WebDriverCommands.class.getClassLoader().getResource(fileName);
            File file = Paths.get(url.toURI()).toFile();
            String filePath = file.getAbsolutePath();
            
            WebElement webElement = elementFinder.findWhenPresent(fileInput);
            webElement.sendKeys(filePath);
        }
        catch (Exception exception) {
            LOG.error("Error uploading file :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
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
            RunContext.getWebDriver().switchTo().defaultContent();
        }
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
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
            RunContext.getWebDriver().switchTo().defaultContent();
            WebElement webElement = elementFinder.findWhenVisible(locator);
            RunContext.getWebDriver().switchTo().frame(webElement);
        }
        catch (Exception exception) {
            LOG.error("Error switching focus :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
    }
    
    /**
     * Pauses execution until the given condition is met, or a timeout occurs.
     *
     * @param condition The Condition to be satisfied during the wait.
     */
    public void pause(Condition condition) {
        LOG.info("Waiting for condition :: {}", condition.description());
        WebDriverWait webDriverWait = new WebDriverWait(RunContext.getWebDriver(), RunContext.timeout);
        
        try {
            webDriverWait.until((ExpectedCondition<Boolean>) webDriver -> condition.result());
        }
        catch (Exception exception) {
            LOG.error("Error while waiting :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
            return elementInspector.getTextOfElement(locator);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
            return elementInspector.getTextOfElements(locator);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
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
        
        List<String> elementValues;
        try {
            elementValues = elementInspector.getTextOfElements(locator);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            
            throw exception;
        }
        
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
        LOG.info("Finding instance of element [{}] with attribute [{}] containing value [{}].",
                locator.getName(), attribute, expectedValue);
        
        List<String> attributeValues;
        try {
            attributeValues = elementInspector.getAttributeOfElements(locator, attribute);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving attributes :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            
            throw exception;
        }
        
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
