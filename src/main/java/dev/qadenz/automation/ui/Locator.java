/*
Copyright Tim Slifer

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
    
    private Pair<String, String> disabledByAttribute;
    private Pair<String, String> hiddenByAttribute;
    private Pair<String, String> selectedByAttribute;
    
    public Locator(String name, String selector) {
        this.name = name;
        this.selector = selector;
    }
    
    public Locator(String name, Locator parent, String selector) {
        this.name = name;
        this.selector = parent.getSelector() + selector;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSelector() {
        return selector;
    }
    
    public Pair<String, String> getDisabledByAttribute() {
        return disabledByAttribute;
    }
    
    public Locator setDisabledByAttribute(String attribute) {
        setDisabledByAttribute(attribute, null);
        
        return this;
    }
    
    public Locator setDisabledByAttribute(String attribute, String value) {
        this.disabledByAttribute = new Pair<>(attribute, value);
        
        return this;
    }
    
    public Pair<String, String> getHiddenByAttribute() {
        return hiddenByAttribute;
    }
    
    public Locator setHiddenByAttribute(String attribute) {
        setHiddenByAttribute(attribute, null);
        
        return this;
    }
    
    public Locator setHiddenByAttribute(String attribute, String value) {
        this.hiddenByAttribute = new Pair<>(attribute, value);
        
        return this;
    }
    
    public Pair<String, String> getSelectedByAttribute() {
        return selectedByAttribute;
    }
    
    public Locator setSelectedByAttribute(String attribute) {
        setSelectedByAttribute(attribute, null);
        
        return this;
    }
    
    public Locator setSelectedByAttribute(String attribute, String value) {
        this.selectedByAttribute = new Pair<>(attribute, value);
        
        return this;
    }
}
