package io.slifer.automation.conditions;

import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
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
}
