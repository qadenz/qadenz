// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package io.slifer.selenium.support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Canned {@link WebElementExpectedCondition}s which are generally useful within webdriver tests.
 */
public class WebElementExpectedConditions {
    
    private final static Logger log = Logger.getLogger(WebElementExpectedConditions.class.getName());
    
    private WebElementExpectedConditions() {
        // Utility class
    }
    
    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
     * the element is visible.
     *
     * @param locator used to find the element
     *
     * @return the WebElement once it is located
     */
    public static WebElementExpectedCondition<WebElement> presenceOfElementLocated(final By locator) {
        return new WebElementExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebElement webElement) {
                return webElement.findElement(locator);
            }
            
            @Override
            public String toString() {
                return "presence of element located by: " + locator;
            }
        };
    }
    
    /**
     * An expectation for checking that an element is present on the DOM of a page and visible. Visibility means that
     * the element is not only displayed but also has a height and width that is greater than 0.
     *
     * @param locator used to find the element
     *
     * @return the WebElement once it is located and visible
     */
    public static WebElementExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
        return new WebElementExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebElement webElement) {
                try {
                    return elementIfVisible(webElement.findElement(locator));
                }
                catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            
            @Override
            public String toString() {
                return "visibility of element located by " + locator;
            }
        };
    }
    
    /**
     * An expectation for checking that all elements present on the web page that match the locator are visible.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than
     * 0.
     *
     * @param locator used to find the element
     *
     * @return the list of WebElements once they are located
     */
    public static WebElementExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedBy(
            final By locator) {
        return new WebElementExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebElement webElement) {
                List<WebElement> elements = webElement.findElements(locator);
                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return null;
                    }
                }
                return elements.size() > 0 ? elements : null;
            }
            
            @Override
            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }
    
    /**
     * @return the given element if it is visible and has non-zero size, otherwise null.
     */
    private static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }
    
    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param locator used to find the element
     *
     * @return the list of WebElements once they are located
     */
    public static WebElementExpectedCondition<List<WebElement>> presenceOfAllElementsLocatedBy(
            final By locator) {
        return new WebElementExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebElement webElement) {
                List<WebElement> elements = webElement.findElements(locator);
                return elements.size() > 0 ? elements : null;
            }
            
            @Override
            public String toString() {
                return "presence of any elements located by " + locator;
            }
        };
    }
    
    /**
     * An expectation for checking if the given text is present in the element that matches the given locator.
     *
     * @param locator used to find the element
     * @param text to be present in the element found by the locator
     *
     * @return true once the first element located by locator contains the given text
     */
    public static WebElementExpectedCondition<Boolean> textToBePresentInElementLocated(final By locator,
            final String text) {
        
        return new WebElementExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    String elementText = webElement.findElement(locator).getText();
                    return elementText.contains(text);
                }
                catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            
            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element found by %s",
                        text, locator);
            }
        };
    }
    
    /**
     * An expectation for checking if the given text is present in the specified elements value attribute.
     *
     * @param locator used to find the element
     * @param text to be present in the value attribute of the element found by the locator
     *
     * @return true once the value attribute of the first element located by locator contains the given text
     */
    public static WebElementExpectedCondition<Boolean> textToBePresentInElementValue(final By locator,
            final String text) {
        
        return new WebElementExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    String elementText = webElement.findElement(locator).getAttribute("value");
                    if (elementText != null) {
                        return elementText.contains(text);
                    }
                    return false;
                }
                catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            
            @Override
            public String toString() {
                return String.format("text ('%s') to be the value of element located by %s",
                        text, locator);
            }
        };
    }
    
    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     *
     * @return true if the element is not displayed or the element doesn't exist or stale element
     */
    public static WebElementExpectedCondition<Boolean> invisibilityOfElementLocated(final By locator) {
        return new WebElementExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    return !(webElement.findElement(locator).isDisplayed());
                }
                catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                }
                catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }
            
            @Override
            public String toString() {
                return "element to no longer be visible: " + locator;
            }
        };
    }
    
    /**
     * An expectation for checking that an element with text is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @param text of the element
     *
     * @return true if no such element, stale element or displayed text not equal that provided
     */
    public static WebElementExpectedCondition<Boolean> invisibilityOfElementWithText(final By locator,
            final String text) {
        return new WebElementExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    return !webElement.findElement(locator).getText().equals(text);
                }
                catch (NoSuchElementException e) {
                    // Returns true because the element with text is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                }
                catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }
            
            @Override
            public String toString() {
                return String.format("element containing '%s' to no longer be visible: %s",
                        text, locator);
            }
        };
    }
    
    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param locator used to find the element
     *
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public static WebElementExpectedCondition<WebElement> elementToBeClickable(final By locator) {
        return new WebElementExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebElement webElement) {
                WebElement element = visibilityOfElementLocated(locator).apply(webElement);
                try {
                    if (element != null && element.isEnabled()) {
                        return element;
                    }
                    return null;
                }
                catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            
            @Override
            public String toString() {
                return "element to be clickable: " + locator;
            }
        };
    }
    
    public static WebElementExpectedCondition<Boolean> elementToBeSelected(final By locator) {
        return elementSelectionStateToBe(locator, true);
    }
    
    public static WebElementExpectedCondition<Boolean> elementSelectionStateToBe(final By locator,
            final boolean selected) {
        return new WebElementExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    WebElement element = webElement.findElement(locator);
                    return element.isSelected() == selected;
                }
                catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            
            @Override
            public String toString() {
                return String.format("element found by %s to %sbe selected",
                        locator, (selected ? "" : "not "));
            }
        };
    }
    
    /**
     * An expectation for checking WebElement with given locator has attribute with a specific value
     *
     * @param locator used to find the element
     * @param attribute used to define css or html attribute
     * @param value used as expected attribute value
     *
     * @return Boolean true when element has css or html attribute with the value
     */
    public static WebElementExpectedCondition<Boolean> attributeToBe(final By locator, final String attribute,
            final String value) {
        return new WebElementExpectedCondition<Boolean>() {
            private String currentValue = null;
            
            @Override
            public Boolean apply(WebElement webElement) {
                WebElement element = webElement.findElement(locator);
                currentValue = element.getAttribute(attribute);
                if (currentValue == null || currentValue.isEmpty()) {
                    currentValue = element.getCssValue(attribute);
                }
                return value.equals(currentValue);
            }
            
            @Override
            public String toString() {
                return String.format("element found by %s to have value \"%s\". Current value: \"%s\"",
                        locator, value, currentValue);
            }
        };
    }
    
    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value used as expected text
     *
     * @return Boolean true when element has text value equal to @value
     */
    public static WebElementExpectedCondition<Boolean> textToBe(final By locator, final String value) {
        return new WebElementExpectedCondition<Boolean>() {
            private String currentValue = null;
            
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    currentValue = webElement.findElement(locator).getText();
                    return currentValue.equals(value);
                }
                catch (Exception e) {
                    return false;
                }
            }
            
            @Override
            public String toString() {
                return String.format("element found by %s to have text \"%s\". Current text: \"%s\"",
                        locator, value, currentValue);
            }
        };
    }
    
    /**
     * An expectation for checking WebElement with given locator has text with a value as a part of it
     *
     * @param locator used to find the element
     * @param pattern used as expected text matcher pattern
     *
     * @return Boolean true when element has text value containing @value
     */
    public static WebElementExpectedCondition<Boolean> textMatches(final By locator, final Pattern pattern) {
        return new WebElementExpectedCondition<Boolean>() {
            private String currentValue = null;
            
            @Override
            public Boolean apply(WebElement webElement) {
                try {
                    currentValue = webElement.findElement(locator).getText();
                    return pattern.matcher(currentValue).find();
                }
                catch (Exception e) {
                    return false;
                }
            }
            
            @Override
            public String toString() {
                return String
                        .format("text found by %s to match pattern \"%s\". Current text: \"%s\"",
                                locator, pattern.pattern(), currentValue);
            }
        };
    }
    
    /**
     * An expectation for checking number of WebElements with given locator being more than defined number
     *
     * @param locator used to find the element
     * @param number used to define minimum number of elements
     *
     * @return Boolean true when size of elements list is more than defined
     */
    public static WebElementExpectedCondition<List<WebElement>> numberOfElementsToBeMoreThan(final By locator,
            final Integer number) {
        return new WebElementExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;
            
            @Override
            public List<WebElement> apply(WebElement webElement) {
                List<WebElement> elements = webElement.findElements(locator);
                currentNumber = elements.size();
                return currentNumber > number ? elements : null;
            }
            
            @Override
            public String toString() {
                return String.format("number of elements found by %s to be more than \"%s\". Current number: \"%s\"",
                        locator, number, currentNumber);
            }
        };
    }
    
    /**
     * An expectation for checking number of WebElements with given locator being less than defined number
     *
     * @param locator used to find the element
     * @param number used to define maximum number of elements
     *
     * @return Boolean true when size of elements list is less than defined
     */
    public static WebElementExpectedCondition<List<WebElement>> numberOfElementsToBeLessThan(final By locator,
            final Integer number) {
        return new WebElementExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;
            
            @Override
            public List<WebElement> apply(WebElement webElement) {
                List<WebElement> elements = webElement.findElements(locator);
                currentNumber = elements.size();
                return currentNumber < number ? elements : null;
            }
            
            @Override
            public String toString() {
                return String.format("number of elements found by %s to be less than \"%s\". Current number: \"%s\"",
                        locator, number, currentNumber);
            }
        };
    }
    
    /**
     * An expectation for checking number of WebElements with given locator
     *
     * @param locator used to find the element
     * @param number used to define number of elements
     *
     * @return Boolean true when size of elements list is equal to defined
     */
    public static WebElementExpectedCondition<List<WebElement>> numberOfElementsToBe(final By locator,
            final Integer number) {
        return new WebElementExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;
            
            @Override
            public List<WebElement> apply(WebElement webElement) {
                List<WebElement> elements = webElement.findElements(locator);
                currentNumber = elements.size();
                return currentNumber.equals(number) ? elements : null;
            }
            
            @Override
            public String toString() {
                return String
                        .format("number of elements found by %s to be \"%s\". Current number: \"%s\"",
                                locator, number, currentNumber);
            }
        };
    }
    
    /**
     * An expectation for checking WebElement with given locator has attribute which contains specific value
     *
     * @param locator used to define WebElement to check its parameters
     * @param attribute used to define css or html attribute
     * @param value used as expected attribute value
     *
     * @return Boolean true when element has css or html attribute which contains the value
     */
    public static WebElementExpectedCondition<Boolean> attributeContains(final By locator,
            final String attribute,
            final String value) {
        return new WebElementExpectedCondition<Boolean>() {
            private String currentValue = null;
            
            @Override
            public Boolean apply(WebElement webElement) {
                return getAttributeOrCssValue(webElement.findElement(locator), attribute)
                        .map(seen -> seen.contains(value))
                        .orElse(false);
            }
            
            @Override
            public String toString() {
                return String.format("value found by %s to contain \"%s\". Current value: \"%s\"",
                        locator, value, currentValue);
            }
        };
    }
    
    private static Optional<String> getAttributeOrCssValue(WebElement element, String name) {
        String value = element.getAttribute(name);
        if (value == null || value.isEmpty()) {
            value = element.getCssValue(name);
        }
        
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(value);
    }
}
