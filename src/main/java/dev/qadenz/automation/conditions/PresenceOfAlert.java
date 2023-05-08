package dev.qadenz.automation.conditions;

import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.expectations.Expectation;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * A Condition for evaluating whether a JavaScript Alert is present.
 *
 * @author Tim Slifer
 */
public class PresenceOfAlert implements Condition {
    
    private Expectation<Boolean> expectation;
    
    private Boolean match;
    private boolean present;
    
    public PresenceOfAlert(Expectation<Boolean> expectation) {
        this.expectation = expectation;
    }
    
    public String description() {
        return "Presence of Alert " + expectation.description() + ".";
    }
    
    public Boolean result() {
        try {
            WebDriver webDriver = WebDriverProvider.getWebDriver();
            webDriver.switchTo().alert();
            present = true;
        }
        catch (NoAlertPresentException exception) {
            present = false;
        }
        
        match = expectation.matcher().matches(present);
        return match;
    }
    
    public String output() {
        return "Found [" + present + "].";
    }
}
