package io.slifer.automation.commands;

import io.slifer.automation.reporter.Screenshots;
import io.slifer.automation.ui.Locator;
import io.slifer.automation.ui.WebFinder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Examines elements and retrieves values and information.
 *
 * @author Tim Slifer
 */
public class WebInspector {
    
    private Logger LOG;
    
    private WebFinder webFinder = new WebFinder();
    
    public WebInspector() {
        LOG = LoggerFactory.getLogger(WebInspector.class);
    }
    
    public WebInspector(Class<?> proxyLogger) {
        LOG = LoggerFactory.getLogger(proxyLogger);
    }
    
    /**
     * Retrieves the value of the given attribute on an element.
     *
     * @param locator The mapped UI element.
     * @param attributeName The name of the attribute to inspect.
     *
     * @return The attribute value.
     */
    public String getAttributeOfElement(Locator locator, String attributeName) {
        LOG.info("Retrieving attribute [{}] of element [{}].", attributeName, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return webElement.getAttribute(attributeName);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving attribute :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the the value of an attribute from each instance of an element on the DOM.
     *
     * @param locator The mapped UI element.
     * @param attributeName The name of the attribute to inspect.
     *
     * @return The list of values.
     */
    public List<String> getAttributeOfElements(Locator locator, String attributeName) {
        LOG.info("Retrieving attribute [{}] of elements [{}].", attributeName, locator.getName());
        try {
            List<WebElement> webElements = webFinder.findAll(locator);
            
            return getAttributeValuesFromElements(webElements, attributeName);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving attribute :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
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
            List<WebElement> webElements = webFinder.findAll(locator);
            
            return webElements.size();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving count :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the value of the given CSS property on an element.
     *
     * @param locator The mapped UI element.
     *
     * @return The CSS property value.
     */
    public String getCssPropertyOfElement(Locator locator, String cssProperty) {
        LOG.info("Retrieving CSS property [{}] of element [{}].", cssProperty, locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return webElement.getCssValue(cssProperty);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving CSS property :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the enabled state of an element. Generally useful for determining whether or not input elements are
     * enabled.
     *
     * @param locator The mapped UI element.
     *
     * @return True if the element is enabled, false otherwise.
     */
    public boolean getEnabledStateOfElement(Locator locator) {
        LOG.info("Retrieving the enabled state of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return webElement.isEnabled();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving state :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the instance of an element with an attribute that contains the expected value.
     *
     * @param locator The mapped UI element.
     * @param attributeName The attribute to be examined.
     * @param expectedValue The value to be identified.
     *
     * @return The element instance.
     */
    public int getInstanceOfElementAttribute(Locator locator, String attributeName, String expectedValue) {
        LOG.info("Finding instance of element [{}] with attribute [{}] containing value [{}].",
                locator.getName(), attributeName, expectedValue);
        
        List<String> attributeValues;
        try {
            List<WebElement> webElements = webFinder.findAllWhenVisible(locator);
            
            attributeValues = getAttributeValuesFromElements(webElements, attributeName);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving instance :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
        
        return getIndexOfValue(attributeValues, expectedValue);
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
            List<WebElement> webElements = webFinder.findAllWhenVisible(locator);
            
            elementValues = getTextValuesFromElements(webElements);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving instance :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
        
        return getIndexOfValue(elementValues, expectedText);
    }
    
    /**
     * Retrieves the value of the currently selected option on a a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     *
     * @return The text of the selected option.
     */
    public String getSelectedMenuOption(Locator locator) {
        LOG.info("Retrieving the currently selected option of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            
            return select.getFirstSelectedOption().getText();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving option :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the selected state of an element. This applies only elements such as checkboxes, radio options, and
     * {@code <option>} child of a {@code <select>} elements.
     *
     * @param locator The mapped UI element.
     *
     * @return True if the element is selected, false otherwise.
     */
    public boolean getSelectedStateOfElement(Locator locator) {
        LOG.info("Retrieving the selected state of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return webElement.isSelected();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving state :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
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
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return webElement.getText();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text from each instance of an element (and any descendants) on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The list of values.
     */
    public List<String> getTextOfElements(Locator locator) {
        LOG.info("Retrieving text from elements [{}].", locator.getName());
        try {
            List<WebElement> webElements = webFinder.findAllWhenVisible(locator);
            
            return getTextValuesFromElements(webElements);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
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
        LOG.info("Retrieving the options of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            Select select = new Select(webElement);
            List<WebElement> options = select.getOptions();
            
            return getTextValuesFromElements(options);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving options :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visibility state of an element. An element is determined to be visible if it is present on the DOM,
     * has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locator The mapped UI element.
     *
     * @return True if the element is found to be visible, false otherwise.
     */
    public boolean getVisibilityOfElement(Locator locator) {
        LOG.info("Retrieving visible state of element [{}].", locator.getName());
        try {
            List<WebElement> webElements = webFinder.findAll(locator);
            boolean visible = (webElements.size() > 0);
            LOG.debug("Found [{}] instances - Visibility is [{}].", webElements.size(), visible);
            
            try {
                if (visible) {
                    Dimension dimension = webElements.get(0).getSize();
                    visible = (dimension.getHeight() > 0 && dimension.getWidth() > 0);
                    LOG.debug("Checked dimensions - Visibility is [{}].", visible);
                }
                
                if (visible) {
                    visible = (!webElements.get(0).getAttribute("style").contains("display: none;"));
                    LOG.debug("Checked style for 'display: none;' - Visibility is [{}].", visible);
                }
                
                if (visible) {
                    visible = (!webElements.get(0).getAttribute("style").contains("visibility: hidden;"));
                    LOG.debug("Checked style for 'visibility: hidden;' - Visibility is [{}].", visible);
                }
                
                if (visible) {
                    visible = (!webElements.get(0).getAttribute("class").contains("ng-hide"));
                    LOG.debug("Checked class 'ng-hide' - Visibility is [{}].", visible);
                }
            }
            catch (StaleElementReferenceException e) {
                visible = false;
                LOG.debug("Element has gone stale - Visibility is [{}].", visible);
            }
            
            return visible;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving state :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            Screenshots.captureScreen();
            
            throw exception;
        }
    }
    
    private List<String> getAttributeValuesFromElements(List<WebElement> webElements, String attributeName) {
        List<String> values = new ArrayList<>();
        
        for (WebElement element : webElements) {
            values.add(element.getAttribute(attributeName));
        }
        
        return values;
    }
    
    private List<String> getTextValuesFromElements(List<WebElement> webElements) {
        List<String> values = new ArrayList<>();
        
        for (WebElement element : webElements) {
            values.add(element.getText());
        }
        
        return values;
    }
    
    private int getIndexOfValue(List<String> elementValues, String expectedText) {
        for (int i = 0; i < elementValues.size(); i++) {
            if (elementValues.get(i).equals(expectedText)) {
                LOG.debug("Found value at index [{}].", i);
                
                return i;
            }
        }
        
        LOG.error("Could not find instance with expected value.");
        Screenshots.captureScreen();
        
        throw new IllegalArgumentException("Value [" + expectedText + "] was not found.");
    }
}