/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    public LocatorGroup(String name, List<Locator> locators) {
        this.name = name;
        this.addAll(locators);
    }
    
    public String getName() {
        return name;
    }
    
    public static void main(String[] args) {
        LocatorGroup group = new LocatorGroup("name", List.of(new Locator("", ""), new Locator("", "")));
    }
}
