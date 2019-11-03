package io.slifer.automation.conditions;

import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import io.slifer.automation.ui.LocatorGroup;
import org.hamcrest.Matcher;
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
            final Matcher<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String attributeValue;
            
            @Override
            public String description() {
                return "Attribute [" + attributeName + "] of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                attributeValue = elementInspector.getAttributeOfElement(locator, attributeName);
                
                match = expectation.matches(attributeValue);
                
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
    public static Condition countOfElement(final Locator locator, final Matcher<Integer> expectation) {
        
        return new Condition() {
            
            Boolean match;
            int elementCount;
            
            @Override
            public String description() {
                return "Count of element [" + locator.getName() + " ] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                elementCount = elementInspector.getCountOfElement(locator);
                
                match = expectation.matches(elementCount);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + elementCount + "].";
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
    public static Condition textOfElement(Locator locator, Matcher<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Text of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                elementText = elementInspector.getTextOfElement(locator);
                
                match = expectation.matches(elementText);
                
                return match;
            }
            
            @Override
            public String output() {
                return "";
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
    public static Condition textOfElements(final Locator locator, final Matcher<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            List<String> elementValues;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Text of each instance of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                elementValues = elementInspector.getTextOfElements(locator);
                
                for (int i = 0; i < elementValues.size(); i++) {
                    String instanceValue = elementValues.get(i);
                    Boolean instanceMatch = expectation.matches(instanceValue);
                    
                    if (!instanceMatch) {
                        failures.append("--> at index [" + i + "], found [" + instanceValue + "].<br>");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: <br>" + failures;
            }
        };
    }
    
    /**
     * A Condition to evaluate the text of each {@code <option>} child of a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown for each {@code <option>}.
     *
     * @return The Condition.
     */
    public static Condition textOfOptions(final Locator locator, final Matcher<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            List<String> elementValues;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Text of the options of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                elementValues = elementInspector.getTextOfOptions(locator);
                
                for (int i = 0; i < elementValues.size(); i++) {
                    String instanceValue = elementValues.get(i);
                    Boolean instanceMatch = expectation.matches(instanceValue);
                    
                    if (!instanceMatch) {
                        failures.append("--> at index [" + i + "], found [" + instanceValue + "].<br>");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: <br>" + failures;
            }
        };
    }
    
    /**
     * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the
     * DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the visibility of the element.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElement(final Locator locator, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean visible;
            
            @Override
            public String description() {
                return "Visibility of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                visible = elementInspector.getVisibilityOfElement(locator);
                
                match = expectation.matches(visible);
                
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
     * @param expectation The expectation for the visibility of each element.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElements(final LocatorGroup locatorGroup, Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Visibility of elements [" + locatorGroup.getName() + "] " + expectation + ">";
            }
            
            @Override
            public Boolean result(WebDriver webDriver) {
                ElementInspector elementInspector = new ElementInspector(webDriver);
                
                for (Locator locator : locatorGroup) {
                    boolean visible = elementInspector.getVisibilityOfElement(locator);
                    Boolean instanceMatch = expectation.matches(visible);
                    
                    if (!instanceMatch) {
                        failures.append("--> Element [" + locator.getName() + "] was [" + visible + "].<br>");
                    }
                    
                    if (match == null || match) {
                        match = instanceMatch;
                    }
                }
                
                return match;
            }
            
            @Override
            public String output() {
                return "Discrepancies: <br>" + failures;
            }
        };
    }
}
