package io.slifer.automation.commands;

import io.slifer.automation.conditions.Condition;
import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

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
     * Evaluates each of the given conditions as a group. If one or more Conditions results in a failure, execution will
     * be aborted after the final Condition is evaluated.
     *
     * @param conditions The Conditions to be evaluated.
     */
    public void verify(Condition... conditions) {
        boolean hasFailures = false;
        
        for (Condition condition : conditions) {
            LOG.info("Asserting Condition -> " + condition.description());
            try {
                boolean result = condition.result(webDriver);
                Assert.assertTrue(result);
                LOG.warn("Assertion Passed: " + condition.output());
            }
            catch (AssertionError error) {
                hasFailures = true;
                LOG.warn("Assertion Failed: " + condition.output());
            }
            catch (Exception exception) {
                hasFailures = true;
                LOG.error("Error making assertion.", exception);
            }
            
            if (hasFailures) {
                throw new AssertionError();
            }
        }
    }
}
