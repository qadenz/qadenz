package io.slifer.automation.commands;

import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Common commands related to the WebDriver layer, agnostic of the implementation.
 *
 * @author Tim Slifer
 */
public abstract class WebDriverCommands extends Commands {
    
    protected WebDriver webDriver;
    private ElementFinder elementFinder;
    
    public WebDriverCommands(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.elementFinder = new ElementFinder(webDriver);
    }
    
    /**
     * Removes the text from an input field.
     *
     * @param locator The mapped UI element.
     */
    public void clear(Locator locator) {
        WebElement webElement = elementFinder.findWhenVisible(locator);
        webElement.clear();
    }
    
    /**
     * Clicks on the given element.
     *
     * @param locator The mapped UI element.
     */
    public void click(Locator locator) {
        WebElement webElement = elementFinder.findWhenClickable(locator);
        webElement.click();
    }
    
    /**
     * Double-clicks on the given element.
     *
     * @param locator The mapped UI element.
     */
    public void doubleClick(Locator locator) {
        WebElement webElement = elementFinder.findWhenClickable(locator);
        Actions actions = new Actions(webDriver);
        actions.doubleClick(webElement).perform();
    }
    
    /**
     * Enters text into an input field.
     *
     * @param locator The mapped UI element.
     * @param input The text input.
     */
    public void enterText(Locator locator, String input) {
        WebElement webElement = elementFinder.findWhenVisible(locator);
        webElement.sendKeys(input);
    }
    
    /**
     * Hovers the mouse over an element.
     *
     * @param locator The mapped UI element.
     */
    public void hover(Locator locator) {
        WebElement webElement = elementFinder.findWhenVisible(locator);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement);
    }
    
    /**
     * Selects an option from a dropdown menu implemented as a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param option The option to be selected.
     */
    public void select(Locator locator, String option) {
        WebElement webElement = elementFinder.findWhenVisible(locator);
        Select select = new Select(webElement);
        select.deselectByVisibleText(option);
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
}
