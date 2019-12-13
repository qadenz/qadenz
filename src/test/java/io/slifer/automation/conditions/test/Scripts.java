package io.slifer.automation.conditions.test;

import io.slifer.automation.config.WebDriverHolder;
import org.openqa.selenium.JavascriptExecutor;

/**
 * A few utility methods to massage the DOM into a state needed for testing Conditions.
 *
 * @author Tim Slifer
 */
public class Scripts {
    
    public static void disableCheckboxes() {
        String script = "for(var i = 0; i < 2; i++) { document.getElementsByTagName('input')[i].disabled = true; }";
        ((JavascriptExecutor) WebDriverHolder.getWebDriver()).executeScript(script);
    }
    
    public static void hideCheckboxesByDisplayNone() {
        String script =
                "for(var i = 0; i < 2; i++) { document.getElementsByTagName('input')[i].style.display = 'none'; }";
        ((JavascriptExecutor) WebDriverHolder.getWebDriver()).executeScript(script);
    }
    
    public static void hideCheckboxesByVisibilityHidden() {
        String script =
                "for(var i = 0; i < 2; i++) { document.getElementsByTagName('input')[i].style.visibility = 'hidden'; }";
        ((JavascriptExecutor) WebDriverHolder.getWebDriver()).executeScript(script);
    }
    
    public static void hideCheckboxesByAngularDirective() {
        String script =
                "for(var i = 0; i < 2; i++) { document.getElementsByTagName('input')[i].className += 'ng-hide'; }";
        ((JavascriptExecutor) WebDriverHolder.getWebDriver()).executeScript(script);
    }
    
    public static void hideLoadedImageByZeroHeight() {
        String script = "document.getElementsByTagName('img')[3].style.height = '0px';";
        ((JavascriptExecutor) WebDriverHolder.getWebDriver()).executeScript(script);
    }
}
