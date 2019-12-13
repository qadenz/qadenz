package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.config.RunContext;
import io.slifer.automation.config.WebDriverHolder;
import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            Actions actions = new Actions(WebDriverHolder.getWebDriver());
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
            Actions actions = new Actions(WebDriverHolder.getWebDriver());
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
            WebDriverHolder.getWebDriver().switchTo().defaultContent();
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
            WebDriverHolder.getWebDriver().switchTo().defaultContent();
            WebElement webElement = elementFinder.findWhenVisible(locator);
            WebDriverHolder.getWebDriver().switchTo().frame(webElement);
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
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverHolder.getWebDriver(), RunContext.timeout);
        
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
        LOG.info("Retrieving text of element [{}]", locator.getName());
        try {
            return new ElementInspector().getTextOfElement(locator);
        }
        catch (Exception e) {
            LOG.error("Error retrieving text.", e);
            
            throw e;
        }
    }
}
