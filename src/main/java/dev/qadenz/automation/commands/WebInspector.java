/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.commands;

import dev.qadenz.automation.reporter.Screenshot;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.WebFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.internal.collections.Pair;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Examines elements and retrieves values and information.
 *
 * @author Tim Slifer
 */
public class WebInspector {
    
    private Logger LOG;
    
    protected WebFinder webFinder = new WebFinder();
    protected Screenshot screenshot = new Screenshot();
    
    public WebInspector() {
        LOG = LoggerFactory.getLogger(WebInspector.class);
    }
    
    public WebInspector(Class<?> logger) {
        LOG = LoggerFactory.getLogger(logger);
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the value of an attribute from each instance of an element on the DOM.
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the total number of instances of an element on the page.
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the value of the given CSS property on an element.
     *
     * @param locator The mapped UI element.
     * @param cssProperty The name of the CSS Property to inspect.
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The text value.
     */
    public String getDirectTextOfElement(Locator locator) {
        LOG.info("Retrieving direct text of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            return removeChildElementTextValues(webElement);
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM, formatted as a
     * LocalDate.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalDate representation of the element text.
     */
    public LocalDate getDirectTextOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving direct text of element [{}] as LocalDate.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = removeChildElementTextValues(webElement);
            
            return LocalDate.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalDate from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM, formatted as a
     * LocalDateTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalDateTime representation of the element text.
     */
    public LocalDateTime getDirectTextOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving direct text of element [{}] as LocalDateTime.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = removeChildElementTextValues(webElement);
            
            return LocalDateTime.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalDateTime from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM, formatted as a
     * LocalTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalTime representation of the element text.
     */
    public LocalTime getDirectTextOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving direct text of element [{}] as LocalTime.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = removeChildElementTextValues(webElement);
            
            return LocalTime.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalTime from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM, as a Double.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The format used for parsing.
     *
     * @return The Double representation of the element text.
     */
    public Double getDirectTextOfElementAsDouble(Locator locator, NumberFormat numberFormat) {
        LOG.info("Retrieving direct text of element [{}] as Double.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = removeChildElementTextValues(webElement);
            
            return numberFormat.parse(elementText).doubleValue();
        }
        catch (ParseException parseException) {
            LOG.error("Error parsing Double from element text :: {}: {}",
                    parseException.getClass().getSimpleName(), parseException.getMessage());
            screenshot.capture();
            
            throw new RuntimeException();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element, excluding the text of any descendants on the DOM, as an Integer.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The format used for parsing.
     *
     * @return The Integer representation of the element text.
     */
    public Integer getDirectTextOfElementAsInteger(Locator locator, NumberFormat numberFormat) {
        LOG.info("Retrieving direct text of element [{}] as Integer.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = removeChildElementTextValues(webElement);
            
            return numberFormat.parse(elementText).intValue();
        }
        catch (ParseException parseException) {
            LOG.error("Error parsing Integer from element text :: {}: {}",
                    parseException.getClass().getSimpleName(), parseException.getMessage());
            screenshot.capture();
            
            throw new RuntimeException();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Determines whether an element is enabled.
     *
     * @param locator The mapped UI element.
     *
     * @return True if the element is enabled, false otherwise.
     */
    public boolean getEnabledStateOfElement(Locator locator) {
        LOG.info("Retrieving the enabled state of element [{}].", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            
            boolean enabled = webElement.isEnabled();
            if (enabled) {
                enabled = !checkAttributePair(webElement, locator.getDisabledByAttribute());
            }
            
            return enabled;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving state :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
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
            screenshot.capture();
            
            throw exception;
        }
        
        return getIndexOfValue(attributeValues, expectedValue);
    }
    
    /**
     * Retrieves the instance of an element that contains the expected text.
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
            screenshot.capture();
            
            throw exception;
        }
        
        return getIndexOfValue(elementValues, expectedText);
    }
    
    /**
     * Retrieves the value of the currently selected option on a {@code <select>} element.
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Determines whether an element is selected. This applies only elements such as checkboxes, radio options, and
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
            
            boolean selected = webElement.isSelected();
            if (!selected) {
                selected = checkAttributePair(webElement, locator.getSelectedByAttribute());
            }
            
            return selected;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving state :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM, formatted as a LocalDate.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalDate representation of the element text.
     */
    public LocalDate getTextOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving text of element [{}] as LocalDate.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = webElement.getText();
            
            return LocalDate.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalDate from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM, formatted as a LocalDateTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalDate representation of the element text.
     */
    public LocalDateTime getTextOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving text of element [{}] as LocalDateTime.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = webElement.getText();
            
            return LocalDateTime.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalDateTime from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM, formatted as a LocalTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     *
     * @return The formatted LocalDate representation of the element text.
     */
    public LocalTime getTextOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter) {
        LOG.info("Retrieving text of element [{}] as LocalTime.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = webElement.getText();
            
            return LocalTime.parse(elementText, dateTimeFormatter);
        }
        catch (DateTimeParseException dateTimeParseException) {
            LOG.error("Error parsing LocalTime from element text :: {}: {}",
                    dateTimeParseException.getClass().getSimpleName(), dateTimeParseException.getMessage());
            screenshot.capture();
            
            throw dateTimeParseException;
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM, as a Double.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The format used for parsing.
     *
     * @return The Double representation of the element text.
     */
    public Double getTextOfElementAsDouble(Locator locator, NumberFormat numberFormat) {
        LOG.info("Retrieving text of element [{}] as Double.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = webElement.getText();
            
            return numberFormat.parse(elementText).doubleValue();
        }
        catch (ParseException parseException) {
            LOG.error("Error parsing Double from element text :: {}: {}",
                    parseException.getClass().getSimpleName(), parseException.getMessage());
            screenshot.capture();
            
            throw new RuntimeException();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM, as an Integer.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The format used for parsing.
     *
     * @return The Integer representation of the element text.
     */
    public Integer getTextOfElementAsInteger(Locator locator, NumberFormat numberFormat) {
        LOG.info("Retrieving text of element [{}] as Integer.", locator.getName());
        try {
            WebElement webElement = webFinder.findWhenVisible(locator);
            String elementText = webElement.getText();
            
            return numberFormat.parse(elementText).intValue();
        }
        catch (ParseException parseException) {
            LOG.error("Error parsing Integer from element text :: {}: {}",
                    parseException.getClass().getSimpleName(), parseException.getMessage());
            screenshot.capture();
            
            throw new RuntimeException();
        }
        catch (Exception exception) {
            LOG.error("Error retrieving text :: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
            screenshot.capture();
            
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
            screenshot.capture();
            
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
            screenshot.capture();
            
            throw exception;
        }
    }
    
    /**
     * Determines whether an element is visible. An element is visible if it is present on the DOM, has a height and
     * width greater than zero, and is not styled to be hidden.
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
                    visible = (webElements.get(0).getAttribute("hidden") == null);
                    LOG.debug("Checked for attribute 'hidden' - Visibility is [{}].", visible);
                }
                
                if (visible) {
                    visible = !checkAttributePair(webElements.get(0), locator.getHiddenByAttribute());
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
            screenshot.capture();
            
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
    
    private String removeChildElementTextValues(WebElement webElement) {
        String elementText = webElement.getText();
        List<String> childElementValues = getTextValuesFromElements(webElement.findElements(By.xpath("./*")));
        for (String child : childElementValues) {
            int lastIndex = elementText.lastIndexOf(child);
            if (lastIndex != -1) {
                String start = elementText.substring(0, lastIndex);
                String end = elementText.substring(lastIndex + child.length());
                
                elementText = (start + end).trim();
            }
        }
        
        return elementText;
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
        screenshot.capture();
        
        throw new IllegalArgumentException("Value [" + expectedText + "] was not found.");
    }
    
    private Boolean checkAttributePair(WebElement webElement, Pair<String, String> attribute) {
        boolean state = false;
        if (attribute != null) {
            if (attribute.second() == null) {
                state = (webElement.getAttribute(attribute.first()) == null);
                LOG.debug("Checked for presence of attribute [{}] - Found [{}].", attribute.first(), state);
            }
            else {
                state = (webElement.getAttribute(attribute.first()).contains(attribute.second()));
                LOG.debug("Checked for attribute [{}] to have value [{}] - Found [{}].",
                        attribute.first(), attribute.second(), state);
            }
        }
        
        return state;
    }
}
