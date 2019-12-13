package io.slifer.automation.ui;

import io.slifer.automation.config.WebDriverHolder;
import io.slifer.selenium.support.ui.WebElementExpectedConditions;
import io.slifer.selenium.support.ui.WebElementWait;
import io.slifer.sizzlecss.BySizzle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Initializes {@link WebElement}s.
 *
 * @author Tim Slifer
 */
public class ElementFinder {
    
    private static final Logger LOG = LoggerFactory.getLogger(ElementFinder.class);
    
    /**
     * Initializes the first matching instance of the given locator, using no waits or synchronization.
     *
     * @param locator The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement find(Locator locator) {
        LOG.debug("Finding element [{}].", locator.getName());
        WebElement parent = null;
        
        try {
            if (locator.getParent() != null) {
                LOG.debug("Parent element detected, initializing [{}].", locator.getParent().getName());
                parent = WebDriverHolder.getWebDriver().findElement(bySizzle(locator.getParent()));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize parent element [{}].", locator.getParent().getName());
            throw e;
        }
        
        try {
            if (parent == null) {
                LOG.debug("Initializing element [{}].", locator.getName());
                return WebDriverHolder.getWebDriver().findElement(bySizzle(locator));
            }
            else {
                LOG.debug("Initializing nested element [{}].", locator.getName());
                return parent.findElement(bySizzle(locator));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize element [{}].", locator.getName());
            throw e;
        }
    }
    
    /**
     * Initializes all matching instances of the given locator, using no waits or synchronization.
     *
     * @param locator The mapped UI element.
     *
     * @return A List of WebElements.
     */
    public List<WebElement> findAll(Locator locator) {
        LOG.debug("Finding elements [{}].", locator.getName());
        WebElement parent = null;
        
        try {
            if (locator.getParent() != null) {
                LOG.debug("Parent element detected, initializing [{}].", locator.getParent().getName());
                parent = WebDriverHolder.getWebDriver().findElement(bySizzle(locator.getParent()));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize parent element [{}].", locator.getParent().getName());
            
            throw e;
        }
        
        try {
            if (parent == null) {
                LOG.debug("Initializing elements [{}].", locator.getName());
                
                return WebDriverHolder.getWebDriver().findElements(bySizzle(locator));
            }
            else {
                LOG.debug("Initializing nested elements [{}].", locator.getName());
                
                return parent.findElements(bySizzle(locator));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize elements [{}].", locator.getName());
            
            throw e;
        }
    }
    
    /**
     * Initializes the first matching instance of the given locator, once visible on the UI.
     *
     * @param locator The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement findWhenVisible(Locator locator) {
        LOG.debug("Finding element [{}] when visible.", locator.getName());
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverHolder.getWebDriver(), 60);
        WebElement parent = null;
        
        try {
            if (locator.getParent() != null) {
                LOG.debug("Parent element detected, initializing [{}] when visible.", locator.getParent().getName());
                parent = webDriverWait.until(
                        ExpectedConditions.visibilityOfElementLocated(bySizzle(locator.getParent())));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize parent element [{}].", locator.getParent().getName());
            
            throw e;
        }
        
        try {
            if (parent == null) {
                LOG.debug("Initializing element [{}] when visible.", locator.getName());
                
                return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
            }
            else {
                LOG.debug("Initializing nested element [{}] when visible.", locator.getName());
                WebElementWait webElementWait = new WebElementWait(parent, 60);
                
                return webElementWait.until(WebElementExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize element [{}].", locator.getName());
            
            throw e;
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
        LOG.debug("Finding elements [{}] when visible.", locator.getName());
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverHolder.getWebDriver(), 60);
        WebElement parent = null;
        
        try {
            if (locator.getParent() != null) {
                LOG.debug("Parent element detected, initializing [{}] when visible.", locator.getParent().getName());
                parent = webDriverWait.until(
                        ExpectedConditions.visibilityOfElementLocated(bySizzle(locator.getParent())));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize parent element [{}].", locator.getParent().getName());
            
            throw e;
        }
        
        try {
            if (parent == null) {
                LOG.debug("Initializing elements [{}] when visible.", locator.getName());
                
                return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySizzle(locator)));
            }
            else {
                LOG.debug("Initializing nested elements [{}] when visible.", locator.getName());
                WebElementWait webElementWait = new WebElementWait(parent, 60);
                
                return webElementWait.until(
                        WebElementExpectedConditions.visibilityOfAllElementsLocatedBy(bySizzle(locator)));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize elements [{}].", locator.getName());
            
            throw e;
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
        LOG.debug("Finding element [{}] when clickable.", locator.getName());
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverHolder.getWebDriver(), 60);
        WebElement parent = null;
        
        try {
            if (locator.getParent() != null) {
                LOG.debug("Parent element detected, initializing [{}] when clickable.", locator.getParent().getName());
                parent = webDriverWait.until(ExpectedConditions.elementToBeClickable(bySizzle(locator.getParent())));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize parent element [{}].", locator.getParent().getName());
            
            throw e;
        }
        
        try {
            if (parent == null) {
                LOG.debug("Initializing element [{}] when clickable.", locator.getName());
                
                return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
            }
            else {
                LOG.debug("Initializing nested element [{}] when clickable.", locator.getName());
                WebElementWait webElementWait = new WebElementWait(parent, 60);
                
                return webElementWait.until(WebElementExpectedConditions.elementToBeClickable(bySizzle(locator)));
            }
        }
        catch (Exception e) {
            LOG.error("Could not initialize element [{}].", locator.getName());
            
            throw e;
        }
    }
    
    private By bySizzle(Locator locator) {
        return BySizzle.css(locator.getSelector());
    }
}
