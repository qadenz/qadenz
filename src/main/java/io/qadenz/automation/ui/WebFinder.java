/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.ui;

import io.qadenz.automation.config.WebConfig;
import io.qadenz.automation.config.WebDriverProvider;
import io.slifer.sizzlecss.BySizzle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Initializes {@link WebElement}s.
 *
 * @author Tim Slifer
 */
public class WebFinder {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebFinder.class);
    
    /**
     * Initializes the first matching instance of the given locator, using no waits or synchronization.
     *
     * @param locator The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement find(Locator locator) {
        LOG.debug("Finding element [{}].", locator.getName());
        
        try {
            LOG.debug("Initializing element [{}].", locator.getName());
            
            return WebDriverProvider.getWebDriver().findElement(bySizzle(locator));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize element [{}].", locator.getName());
            
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
        
        try {
            LOG.debug("Initializing elements [{}].", locator.getName());
            
            return WebDriverProvider.getWebDriver().findElements(bySizzle(locator));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize elements [{}].", locator.getName());
            
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
        WebDriverWait webDriverWait =
                new WebDriverWait(WebDriverProvider.getWebDriver(), Duration.ofSeconds(WebConfig.timeout));
        
        try {
            LOG.debug("Initializing element [{}] when visible.", locator.getName());
            
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySizzle(locator)));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize element [{}].", locator.getName());
            
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
        WebDriverWait webDriverWait =
                new WebDriverWait(WebDriverProvider.getWebDriver(), Duration.ofSeconds(WebConfig.timeout));
        
        try {
            LOG.debug("Initializing elements [{}] when visible.", locator.getName());
            
            return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySizzle(locator)));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize elements [{}].", locator.getName());
            
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
        WebDriverWait webDriverWait =
                new WebDriverWait(WebDriverProvider.getWebDriver(), Duration.ofSeconds(WebConfig.timeout));
        
        try {
            LOG.debug("Initializing element [{}] when clickable.", locator.getName());
            
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(bySizzle(locator)));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize element [{}].", locator.getName());
            
            throw e;
        }
    }
    
    /**
     * Initializes the first matching instance of the given locator, once present on the DOM.
     *
     * @param locator - The mapped UI element.
     *
     * @return A WebElement.
     */
    public WebElement findWhenPresent(Locator locator) {
        LOG.debug("Finding element [{}] when present.", locator.getName());
        WebDriverWait webDriverWait =
                new WebDriverWait(WebDriverProvider.getWebDriver(), Duration.ofSeconds(WebConfig.timeout));
        
        try {
            LOG.debug("Initializing element [{}] when present.", locator.getName());
            
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(bySizzle(locator)));
        }
        catch (Exception e) {
            LOG.debug("Could not initialize element [{}].", locator.getName());
            
            throw e;
        }
    }
    
    private By bySizzle(Locator locator) {
        return BySizzle.css(locator.getSelector());
    }
}
