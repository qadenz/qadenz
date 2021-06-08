package io.slifer.automation.reporter.model;

import java.util.List;

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