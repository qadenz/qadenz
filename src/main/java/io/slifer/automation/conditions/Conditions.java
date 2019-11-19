package io.slifer.automation.conditions;

import io.slifer.automation.config.RunContext;
import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import io.slifer.automation.ui.LocatorGroup;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

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
            final Expectation expectation) {
        
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
                ElementInspector elementInspector = new ElementInspector();
                attributeValue = elementInspector.getAttributeOfElement(locator, attributeName);
                
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
    public static Condition countOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            int elementCount;
            
            @Override
            public String description() {
                return "Count of element [" + locator.getName() + " ] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                elementCount = elementInspector.getCountOfElement(locator);
                
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
     * A Condition for evaluating element to be enabled.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean enabled;
            
            @Override
            public String description() {
                return "Enabled state of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                enabled = elementInspector.getEnabledStateOfElement(locator);
                
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
     * A Condition for evaluating whether or not a group of elements is enabled.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether or not each element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElements(final LocatorGroup locatorGroup, final Expectation expectation) {
        
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
                ElementInspector elementInspector = new ElementInspector();
                
                for (Locator locator : locatorGroup) {
                    boolean enabled = elementInspector.getEnabledStateOfElement(locator);
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
                return "Discrepancies: \n" + failures;
            }
        };
    }
    
    /**
     * A Condition for evaluating whether or not a JavaScript Alert is present.
     *
     * @param expectation The expectation of whether or not the alert is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfAlert(final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean present;
            
            public String description() {
                return "Presence of Alert " + expectation.description() + ".";
            }
            
            public Boolean result() {
                try {
                    WebDriver webDriver = RunContext.getWebDriver();
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
     * A Condition for evaluating whether or not an element is present on the DOM, regardless of if the element is
     * visible on the page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean present;
            
            @Override
            public String description() {
                return "Presence of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementFinder elementFinder = new ElementFinder();
                present = elementFinder.findAll(locator).size() > 0;
                
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
     * A Condition for evaluating whether or not a group of elements is present on the DOM, regardless of if the
     * elements are visible on the page.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether or not each element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElements(final LocatorGroup locatorGroup, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Presence of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementFinder elementFinder = new ElementFinder();
                
                for (Locator locator : locatorGroup) {
                    boolean present = elementFinder.findAll(locator).size() > 0;
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
                return "Discrepancies: \n" + failures;
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
    public static Condition selectedMenuOption(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            String selectedOption;
            
            public String description() {
                return "Selected option of menu element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                selectedOption = elementInspector.getSelectedMenuOption(locator);
                
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
     * @param expectation The expectation of whether or not the element is to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedStateOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean selected;
            
            @Override
            public String description() {
                return "Selected state of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                selected = elementInspector.getSelectedStateOfElement(locator);
                
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
    public static Condition textOfAlert(final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            String alertText;
            
            public String description() {
                return "Text of Alert " + expectation.description() + ".";
            }
            
            public Boolean result() {
                WebDriver webDriver = RunContext.getWebDriver();
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
    public static Condition textOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Text of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                elementText = elementInspector.getTextOfElement(locator);
                
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
    public static Condition textOfElements(final Locator locator, final Expectation expectation) {
        
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
                ElementInspector elementInspector = new ElementInspector();
                elementValues = elementInspector.getTextOfElements(locator);
                
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
                return "Discrepancies: \n" + failures;
            }
        };
    }
    
    /**
     * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the
     * DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElement(final Locator locator, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean visible;
            
            @Override
            public String description() {
                return "Visibility of element [" + locator.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                visible = elementInspector.getVisibilityOfElement(locator);
                
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
     * @param expectation The expectation of whether or not each element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElements(final LocatorGroup locatorGroup, final Expectation expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Visibility of elements [" + locatorGroup.getName() + "] " + expectation.description() + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                
                for (Locator locator : locatorGroup) {
                    boolean visible = elementInspector.getVisibilityOfElement(locator);
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
                return "Discrepancies: \n" + failures;
            }
        };
    }
}
