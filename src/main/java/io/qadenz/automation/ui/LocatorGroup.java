package io.qadenz.automation.ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines a group of mapped UI Elements.
 *
 * @author Tim Slifer
 */
public class LocatorGroup extends ArrayList<Locator> {
    
    private String name;
    
    public LocatorGroup(String name, Locator... locators) {
        this.name = name;
        this.addAll(Arrays.asList(locators));
    }
    
    public String getName() {
        return name;
    }
}
