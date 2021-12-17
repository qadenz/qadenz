/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter.model;

import java.util.List;

/**
 * Model that stores individual method results on each test class.
 *
 * @author Tim Slifer
 */
public class JsonClass {
    
    private String className;
    
    private List<JsonMethod> methods;
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public List<JsonMethod> getMethods() {
        return methods;
    }
    
    public void setMethods(List<JsonMethod> methods) {
        this.methods = methods;
    }
}
