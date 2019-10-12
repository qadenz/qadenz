package io.slifer.automation.commands;

import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Common commands related to the WebDriver layer, agnostic of the implementation.
 *
 * @author Tim Slifer
 */
public abstract class WebDriverCommands extends Commands {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverCommands.class);
    
    protected WebDriver webDriver;
    private ElementFinder elementFinder;
    private ElementInspector elementInspector;
    
    public WebDriverCommands(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.elementFinder = new ElementFinder(webDriver);
        this.elementInspector = new ElementInspector(webDriver);
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
        try {
            WebElement webElement = elementFinder.findWhenClickable(locator);
            webElement.click();
        }
        catch (Exception e) {
            LOG.error("Error clicking element.", e);
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
            Actions actions = new Actions(webDriver);
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
    public void enterText(Locator locator, String input) {
        LOG.info("Entering text into element [{}].", locator.getName());
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
            Actions actions = new Actions(webDriver);
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
        LOG.info("Selecting option from element [{}].", locator.getName());
        try {
            WebElement webElement = elementFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            select.deselectByVisibleText(option);
        }
        catch (Exception e) {
            LOG.error("Error selecting option.", e);
            throw e;
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
     * Retrieves the total number of instance of an element on the page.
     *
     * @param locator The mapped UI element.
     *
     * @return The instance count.
     */
    public int getCountOfElement(Locator locator) {
        LOG.info("Retrieving count of element [{}].", locator.getName());
        try {
            return elementInspector.getCountOfElement(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving count.", e);
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
            return elementInspector.getTextOfElement(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving text.", e);
            throw e;
        }
    }
    
    /**
     * Retrieves the visible inner text from each instance of an element (any any descendants) on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The list of values.
     */
    public List<String> getTextOfElements(Locator locator) {
        LOG.info("Retrieving text of elements [{}].", locator.getName());
        try {
            return elementInspector.getTextOfElements(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving text.", e);
            throw e;
        }
    }
    
    /**
     * Retrieves the values of each {@code <option>} child of a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     *
     * @return The list of values.
     */
    public List<String> getTextOfOptions(Locator locator) {
        LOG.info("Retrieving text of options from [{}].", locator.getName());
        try {
            return elementInspector.getTextOfOptions(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving options.", e);
            throw e;
        }
    }
}
