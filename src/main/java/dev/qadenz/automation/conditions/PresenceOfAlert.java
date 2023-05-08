package dev.qadenz.automation.conditions;

import dev.qadenz.automation.config.WebDriverProvider;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * A Condition for evaluating whether a JavaScript Alert is present.
 *
 * @author Tim Slifer
 */
public class PresenceOfAlert implements Condition {
    
    private Expectation<Boolean> expectation;
    
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
        
        return expectation.matcher().matches(present);
    }
    
    public String output() {
        return "Found [" + present + "].";
    }
}
