package io.slifer.automation.conditions.test;

import io.slifer.automation.ui.Locator;
import io.slifer.automation.ui.LocatorGroup;

/**
 * Modeling the UI at the-internet.herokuapp.com.
 *
 * @author Tim Slifer
 */
public class HerokuPage {
    
    public static Locator exampleLinks() {
        return new Locator("Example Links", "#content a");
    }
    
    public static Locator exampleLink(String name) {
        return new Locator(name + " link", "#content a:contains(" + name + ")");
    }
    
    public static Locator loadedImage() {
        return new Locator("Loaded Image", ".example img:last");
    }
    
    public static Locator checkbox1() {
        return new Locator("Checkbox 1", "#checkboxes input:first");
    }
    
    public static Locator checkbox2() {
        return new Locator("Checkbox 2", "#checkboxes input:last");
    }
    
    public static LocatorGroup checkboxes() {
        return new LocatorGroup("Checkboxes", checkbox1(), checkbox2());
    }
    
    public static Locator dropdownMenu() {
        return new Locator("Dropdown Menu", "#dropdown");
    }
    
    public static Locator jsAlertButtons() {
        return new Locator("JS Alert Buttons", ".example button");
    }
    
    public static Locator jsAlertButton() {
        return new Locator("JS Alert Button", ".example button:contains('Click for JS Alert')");
    }
    
    public static Locator pageFooter() {
        return new Locator("Page Footer", "#page-footer > div > div");
    }
    
    public static LocatorGroup exampleLinksGroup() {
        return new LocatorGroup("Example Links",
                exampleLink("Checkboxes"),
                exampleLink("Dropdown"),
                exampleLink("Frame"));
    }
}
