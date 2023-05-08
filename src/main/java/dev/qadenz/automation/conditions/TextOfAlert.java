package dev.qadenz.automation.conditions;

import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.expectations.Expectation;
import org.openqa.selenium.WebDriver;

/**
 * A Condition for evaluating the text shown on a JavaScript Alert.
 *
 * @author Tim Slifer
 */
public class TextOfAlert implements Condition {
    
    private Expectation<String> expectation;
    
    private Boolean match;
    private String alertText;
    
    public TextOfAlert(Expectation<String> expectation) {
        this.expectation = expectation;
    }
    
    @Override
    public String description() {
        return "Text of Alert " + expectation.description() + ".";
    }
    
    @Override
    public Boolean result() {
        WebDriver webDriver = WebDriverProvider.getWebDriver();
        alertText = webDriver.switchTo().alert().getText();
        
        match = expectation.matcher().matches(alertText);
        
        return match;
    }
    
    @Override
    public String output() {
        return "Found [" + alertText + "].";
    }
}
