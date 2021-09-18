package io.qadenz.automation.ui;

/**
 * Defines a mapped UI Element.
 *
 * @author Tim Slifer
 */
public class Locator {
    
    private String name;
    private String selector;
    
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
}
