package io.slifer.automation.ui;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Examines elements and retrieves values and information.
 *
 * @author Tim Slifer
 */
public class ElementInspector {
    
    private ElementFinder elementFinder;
    
    public ElementInspector(WebDriver webDriver) {
        this.elementFinder = new ElementFinder(webDriver);
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
        WebElement webElement = elementFinder.find(locator);
        
        return webElement.getAttribute(attributeName);
    }
    
    /**
     * Retrieves the total number of instance of an element on the page.
     *
     * @param locator The mapped UI element.
     *
     * @return The instance count.
     */
    public int getCountOfElement(Locator locator) {
        List<WebElement> webElements = elementFinder.findAll(locator);
        
        return webElements.size();
    }
    
    /**
     * Retrieves the visible inner text of an element and any descendants on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The text value.
     */
    public String getTextOfElement(Locator locator) {
        WebElement webElement = elementFinder.find(locator);
        
        return webElement.getText();
    }
    
    /**
     * Retrieves the visible inner text from each instance of an element (any any descendants) on the DOM.
     *
     * @param locator The mapped UI element.
     *
     * @return The list of values.
     */
    public List<String> getTextOfElements(Locator locator) {
        List<WebElement> webElements = elementFinder.findAll(locator);
        
        return getTextValuesFromElements(webElements);
    }
    
    /**
     * Retrieves the values of each {@code <option>} child of a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     *
     * @return The list of values.
     */
    public List<String> getTextOfOptions(Locator locator) {
        WebElement webElement = elementFinder.find(locator);
        Select select = new Select(webElement);
        List<WebElement> options = select.getOptions();
        
        return getTextValuesFromElements(options);
    }
    
    public boolean getVisibilityOfElement(Locator locator) {
        boolean visible = (getCountOfElement(locator) > 0);
        
        if (visible) {
            Dimension dimension = elementFinder.find(locator).getSize();
            visible = (dimension.getHeight() > 0 && dimension.getWidth() > 0);
        }
        
        if (visible) {
            visible = (!getAttributeOfElement(locator, "style").contains("display: none;"));
        }
        
        if (visible) {
            visible = (!getAttributeOfElement(locator, "style").contains("visibility: hidden;"));
        }
        
        if (visible) {
            visible = (!getAttributeOfElement(locator, "class").contains("ng-hide"));
        }
        
        return visible;
    }
    
    private List<String> getTextValuesFromElements(List<WebElement> webElements) {
        List<String> values = new ArrayList<>();
        
        for (WebElement element : webElements) {
            values.add(element.getText());
        }
        
        return values;
    }
}
