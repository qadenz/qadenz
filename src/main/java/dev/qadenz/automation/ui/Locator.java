/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.ui;

/**
 * Defines a mapped UI Element.
 *
 * @author Tim Slifer
 */
public class Locator {
    
    private String name;
    private String selector;
    
    private String hiddenAttribute;
    private String selectedAttribute;
    private String unselectedAttribute;
    
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
    
    public String getHiddenAttribute() {
        return hiddenAttribute;
    }
    
    public void setHiddenAttribute(String hiddenAttribute) {
        this.hiddenAttribute = hiddenAttribute;
    }
    
    public String getSelectedAttribute() {
        return selectedAttribute;
    }
    
    public void setSelectedAttribute(String selectedAttribute) {
        this.selectedAttribute = selectedAttribute;
    }
    
    public String getUnselectedAttribute() {
        return unselectedAttribute;
    }
    
    public void setUnselectedAttribute(String unselectedAttribute) {
        this.unselectedAttribute = unselectedAttribute;
    }
}
