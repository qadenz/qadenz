/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.ui;

import org.testng.internal.collections.Pair;

/**
 * Defines a mapped UI Element.
 *
 * @author Tim Slifer
 */
public class Locator {
    
    private String name;
    private String selector;
    
    private Pair<String, String> hiddenAttribute;
    private Pair<String, String> selectedAttribute;
    
    public Locator(String name, String selector) {
        this.name = name;
        this.selector = selector;
    }
    
    public Locator(String name, Locator parent, String selector) {
        this.name = name;
        this.selector = parent.getSelector() + " " + selector;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSelector() {
        return selector;
    }
    
    public Pair<String, String> getHiddenAttribute() {
        return hiddenAttribute;
    }
    
    public Locator setHiddenAttribute(String attribute, String value) {
        this.hiddenAttribute = new Pair<>(attribute, value);
        
        return this;
    }
    
    public Pair<String, String> getSelectedAttribute() {
        return selectedAttribute;
    }
    
    public Locator setSelectedAttribute(String attribute, String value) {
        this.selectedAttribute = new Pair<>(attribute, value);
        
        return this;
    }
}
