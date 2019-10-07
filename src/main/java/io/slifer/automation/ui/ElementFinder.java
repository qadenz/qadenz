package io.slifer.automation.ui;

import io.slifer.selenium.support.ui.WebElementExpectedConditions;
import io.slifer.selenium.support.ui.WebElementWait;
import io.slifer.sizzlecss.BySizzle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Initializes {@link WebElement}s.
 *
 * @author Tim Slifer
 */
public class ElementFinder {
    
    private WebDriver webDriver;
    
    public ElementFinder(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    
    /**
     * Initializes the first matching instance of the given locator, using no waits or synchronization.
     *
     * @param locator - The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement find(Locator locator) {
        WebElement parent = null;
        if (locator.getParent() != null) {
            parent = webDriver.findElement(bySizzle(locator.getParent()));
        }
        
        if (parent == null) {
            return webDriver.findElement(bySizzle(locator));
        }
        else {
            return parent.findElement(bySizzle(locator));
        }
    }
    
    /**
     * Initializes all matching instances of the given locator, using no waits or synchronization.
     *
     * @param locator - The mapped UI element.
     *
     * @return A List of WebElements.
     */
    public List<WebElement> findAll(Locator locator) {
        WebElement parent = null;
        if (locator.getParent() != null) {
            parent = webDriver.findElement(bySizzle(locator.getParent()));
        }
        
        if (parent == null) {
            return webDriver.findElements(bySizzle(locator));
        }
        else {
            return parent.findElements(bySizzle(locator));
        }
    }
    
    /**
     * Initializes the first matching instance of the given locator, once visible on the UI.
     *
     * @param locator - The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement findWhenVisible(Locator locator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 60);
        
        WebElement parent = null;
        if (locator.getParent() != null) {
            parent = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator.getParent())));
        }
        
        if (parent == null) {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
        }
        else {
            WebElementWait webElementWait = new WebElementWait(parent, 60);
            
            return webElementWait.until(WebElementExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
        }
    }
    
    /**
     * Initializes all matching instances of the given locator, once visible on the UI .
     *
     * @param locator - The mapped UI element.
     *
     * @return A List of WebElements.
     */
    public List<WebElement> findAllWhenVisible(Locator locator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 60);
        
        WebElement parent = null;
        if (locator.getParent() != null) {
            parent = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator.getParent())));
        }
        
        if (parent == null) {
            return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySizzle(locator)));
        }
        else {
            WebElementWait webElementWait = new WebElementWait(parent, 60);
            
            return webElementWait.until(
                    WebElementExpectedConditions.visibilityOfAllElementsLocatedBy(bySizzle(locator)));
        }
    }
    
    /**
     * Initializes the first matching instance of the given locator, once visible and enabled to receive a click.
     *
     * @param locator - The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement findWhenClickable(Locator locator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 60);
        
        WebElement parent = null;
        if (locator.getParent() != null) {
            parent = webDriverWait.until(ExpectedConditions.elementToBeClickable(bySizzle(locator.getParent())));
        }
        
        if (parent == null) {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
        }
        else {
            WebElementWait webElementWait = new WebElementWait(parent, 60);
            
            return webElementWait.until(WebElementExpectedConditions.elementToBeClickable(bySizzle(locator)));
        }
    }
    
    private By bySizzle(Locator locator) {
        return BySizzle.css(locator.getSelector());
    }
}
