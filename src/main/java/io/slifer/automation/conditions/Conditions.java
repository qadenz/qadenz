package io.slifer.automation.conditions;

import io.slifer.automation.ui.ElementFinder;
import io.slifer.automation.ui.ElementInspector;
import io.slifer.automation.ui.Locator;
import io.slifer.automation.ui.LocatorGroup;
import org.hamcrest.Matcher;

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
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
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
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
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
     * A Condition for evaluating element to be enabled.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElement(final Locator locator, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean enabled;
            
            @Override
            public String description() {
                return "Enabled state of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                enabled = elementInspector.getEnabledStateOfElement(locator);
                
                match = expectation.matches(enabled);
                
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
    public static Condition enabledStateOfElements(final LocatorGroup locatorGroup,
            final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Enabled state of elements [" + locatorGroup.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                
                for (Locator locator : locatorGroup) {
                    boolean enabled = elementInspector.getEnabledStateOfElement(locator);
                    Boolean instanceMatch = expectation.matches(enabled);
                    
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
     * A Condition for evaluating whether or not an element is present on the DOM, regardless of if the element is
     * visible on the page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElement(final Locator locator, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean present;
            
            @Override
            public String description() {
                return "Presence of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementFinder elementFinder = new ElementFinder();
                present = elementFinder.findAll(locator).size() > 0;
                
                match = expectation.matches(present);
                
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
    public static Condition presenceOfElements(final LocatorGroup locatorGroup, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Presence of elements [" + locatorGroup.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementFinder elementFinder = new ElementFinder();
                
                for (Locator locator : locatorGroup) {
                    boolean present = elementFinder.findAll(locator).size() > 0;
                    Boolean instanceMatch = expectation.matches(present);
                    
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
     * A Condition for evaluating an element to be selected. This applies only elements such as checkboxes, radio
     * options, and {@code <option>} child of a {@code <select>} elements.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether or not the element is to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedStateOfElement(final Locator locator, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean selected;
            
            @Override
            public String description() {
                return "Selected state of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                selected = elementInspector.getSelectedStateOfElement(locator);
                
                match = expectation.matches(selected);
                
                return match;
            }
            
            @Override
            public String output() {
                return "Found [" + selected + "].";
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
    public static Condition textOfElement(final Locator locator, final Matcher<String> expectation) {
        
        return new Condition() {
            
            Boolean match;
            String elementText;
            
            @Override
            public String description() {
                return "Text of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                elementText = elementInspector.getTextOfElement(locator);
                
                match = expectation.matches(elementText);
                
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
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                elementValues = elementInspector.getTextOfElements(locator);
                
                for (int i = 0; i < elementValues.size(); i++) {
                    String instanceValue = elementValues.get(i);
                    Boolean instanceMatch = expectation.matches(instanceValue);
                    
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
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                elementValues = elementInspector.getTextOfOptions(locator);
                
                for (int i = 0; i < elementValues.size(); i++) {
                    String instanceValue = elementValues.get(i);
                    Boolean instanceMatch = expectation.matches(instanceValue);
                    
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
    public static Condition visibilityOfElement(final Locator locator, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            boolean visible;
            
            @Override
            public String description() {
                return "Visibility of element [" + locator.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
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
     * @param expectation The expectation of whether or not each element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElements(final LocatorGroup locatorGroup, final Matcher<Boolean> expectation) {
        
        return new Condition() {
            
            Boolean match;
            StringBuilder failures = new StringBuilder();
            
            @Override
            public String description() {
                return "Visibility of elements [" + locatorGroup.getName() + "] " + expectation + ".";
            }
            
            @Override
            public Boolean result() {
                ElementInspector elementInspector = new ElementInspector();
                
                for (Locator locator : locatorGroup) {
                    boolean visible = elementInspector.getVisibilityOfElement(locator);
                    Boolean instanceMatch = expectation.matches(visible);
                    
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
