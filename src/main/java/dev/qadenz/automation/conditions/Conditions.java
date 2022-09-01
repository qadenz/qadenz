/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.commands.WebInspector;
import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.LocatorGroup;
import dev.qadenz.automation.ui.WebFinder;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.List;

/**
 * A collection of {@link Condition}s to be used for validation or synchronization operations.
 *
 * @author Tim Slifer
 */
public class Conditions {
    
    /**
     * A Condition to evaluate the value of an attribute on an element.
     *
     * @param locator The mapped UI element.
     * @param attributeName The name of the attribute to be evaluated.
     * @param expectation The expectation for the attribute value.
     *
     * @return The Condition.
     */
    public static Condition attributeOfElement(final Locator locator, final String attributeName,
            final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String attributeValue;
            
            @Override
            public String description() {
                return "Attribute [" + attributeName + "] of element [" + locator.getName() + "] " +
                        expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                attributeValue = webInspector.getAttributeOfElement(locator, attributeName);
                
                match = expectation.matcher().matches(attributeValue);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + attributeValue + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the number of times an element appears on a page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the number of instances of the element.
     *
     * @return The Condition.
     */
    public static Condition countOfElement(final Locator locator, final Expectation<Integer> expectation) {
        
        return new Condition() {
            
            Boolean match;
            int elementCount;
            
            @Override
            public String description() {
                return "Count of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                elementCount = webInspector.getCountOfElement(locator);
                
                match = expectation.matcher().matches(elementCount);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + elementCount + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the value of a CSS property on an element.
     *
     * @param locator The mapped UI element.
     * @param cssPropertyName The name of the property to be evaluated.
     * @param expectation The expectation for the property value.
     *
     * @return The Condition.
     */
    public static Condition cssPropertyOfElement(final Locator locator, final String cssPropertyName,
            final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String cssPropertyValue;
            
            @Override
            public String description() {
                return "CSS Property [" + cssPropertyName + "] of element [" + locator.getName() + "] " +
                        expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                cssPropertyValue = webInspector.getCssPropertyOfElement(locator, cssPropertyName);
                
                match = expectation.matcher().matches(cssPropertyName);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + cssPropertyValue + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating element to be enabled.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElement(final Locator locator, final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean enabled;
            
            @Override
            public String description() {
                return "Enabled state of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                enabled = webInspector.getEnabledStateOfElement(locator);
                
                match = expectation.matcher().matches(enabled);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + enabled + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating whether a group of elements is enabled.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElements(final LocatorGroup locatorGroup,
            final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Enabled state of elements [" + locatorGroup.getName() + "] " +
                        expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                
                for (Locator locator : locatorGroup) {
                    boolean enabled = webInspector.getEnabledStateOfElement(locator);
                    Boolean instanceMatch = expectation.matcher().matches(enabled);
                    
                    if (!instanceMatch) {
                        failures.append("--> Element [" + locator.getName() + "] was [" + enabled + "].\n");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: \n" + failures.toString();
            }
        };
    }
    
    /**
     * A Condition for evaluating whether a JavaScript Alert is present.
     *
     * @param expectation The expectation of whether the alert is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfAlert(final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean present;
            
            public String description() {
                return "Presence of Alert " + expectation.description() + ".";
            }
            
            public Boolean result() {
                try {
                    WebDriver webDriver = WebDriverProvider.getWebDriver();
                    webDriver.switchTo().alert();
                    present = true;
                }
                catch (NoAlertPresentException exception) {
                    present = false;
                }
                
                match = expectation.matcher().matches(present);
                return match;
            }
            
            public String output() {
                return "Found [" + present + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating whether an element is present on the DOM, regardless of if the element is visible on
     * the page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElement(final Locator locator, final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean present;
            
            @Override
            public String description() {
                return "Presence of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebFinder webFinder = new WebFinder();
                present = webFinder.findAll(locator).size() > 0;
                
                match = expectation.matcher().matches(present);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + present + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating whether a group of elements is present on the DOM, regardless of if the elements are
     * visible on the page.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElements(final LocatorGroup locatorGroup,
            final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Presence of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebFinder webFinder = new WebFinder();
                
                for (Locator locator : locatorGroup) {
                    boolean present = webFinder.findAll(locator).size() > 0;
                    Boolean instanceMatch = expectation.matcher().matches(present);
                    
                    if (!instanceMatch) {
                        failures.append("--> Element [" + locator.getName() + "] was [" + present + "].\n");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: \n" + failures.toString();
            }
        };
    }
    
    /**
     * A Condition for evaluating the currently selected option of a Select menu element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the option to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedMenuOption(final Locator locator, final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String selectedOption;
            
            public String description() {
                return "Selected option of menu element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                selectedOption = webInspector.getSelectedMenuOption(locator);
                
                match = expectation.matcher().matches(selectedOption);
                
                return match;
            }
            
            public String output() {
                return "Found [" + selectedOption + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating an element to be selected. This applies only elements such as checkboxes, radio
     * options, and {@code <option>} child of a {@code <select>} elements.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedStateOfElement(final Locator locator, final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean selected;
            
            @Override
            public String description() {
                return "Selected state of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                selected = webInspector.getSelectedStateOfElement(locator);
                
                match = expectation.matcher().matches(selected);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + selected + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating the text shown on a JavaScript Alert.
     *
     * @param expectation The expectation for the text to be shown in the alert.
     *
     * @return The Condition.
     */
    public static Condition textOfAlert(final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String alertText;
            
            public String description() {
                return "Text of Alert " + expectation.description() + ".";
            }
            
            public Boolean result() {
                WebDriver webDriver = WebDriverProvider.getWebDriver();
                alertText = webDriver.switchTo().alert().getText();
                
                match = expectation.matcher().matches(alertText);
                
                return match;
            }
            
            public String output() {
                return "Found [" + alertText + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElement(final Locator locator, final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Text of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                elementText = webInspector.getTextOfElement(locator);
                
                match = expectation.matcher().matches(elementText);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + elementText + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted Date value.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsDate(final Locator locator, final Expectation<Date> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Date-formatted text of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                elementText = webInspector.getTextOfElement(locator);
                
                match = expectation.matcher().matches(elementText);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + elementText + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElements(final Locator locator, final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            List<String> elementValues;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Text of each instance of element [" + locator.getName() + "] " +
                        expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                elementValues = webInspector.getTextOfElements(locator);
                
                for (int i = 0; i < elementValues.size(); i++) {
                    String instanceValue = elementValues.get(i);
                    Boolean instanceMatch = expectation.matcher().matches(instanceValue);
                    
                    if (!instanceMatch) {
                        failures.append("--> at index [" + i + "], found [" + instanceValue + "].\n");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: \n" + failures.toString();
            }
        };
    }
    
    /**
     * A Condition to evaluate the text shown inside an {@code <input>} element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfInputElement(final Locator locator, final Expectation<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Text of input element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                elementText = webInspector.getAttributeOfElement(locator, "value");
                
                match = expectation.matcher().matches(elementText);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + elementText + "].";
            }
        };
    }
    
    /**
     * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the
     * DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElement(final Locator locator, final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean visible;
            
            @Override
            public String description() {
                return "Visibility of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                visible = webInspector.getVisibilityOfElement(locator);
                
                match = expectation.matcher().matches(visible);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + visible + "].";
            }
        };
    }
    
    /**
     * A Condition to evaluate the visibility of a group of elements. An element determined to be visible is present on
     * the DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElements(final LocatorGroup locatorGroup,
            final Expectation<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Visibility of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                WebInspector webInspector = new WebInspector(Conditions.class);
                
                for (Locator locator : locatorGroup) {
                    boolean visible = webInspector.getVisibilityOfElement(locator);
                    Boolean instanceMatch = expectation.matcher().matches(visible);
                    
                    if (!instanceMatch) {
                        failures.append("--> Element [" + locator.getName() + "] was [" + visible + "].\n");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: \n" + failures.toString();
            }
        };
    }
    
    /**
     * Cleanly converts a List of Conditions into an Array, for passing to a verification.
     *
     * @param conditions The List of Conditions.
     *
     * @return The converted Array.
     */
    public static Condition[] toArray(List<Condition> conditions) {
        return conditions.toArray(new Condition[conditions.size()]);
    }
}
