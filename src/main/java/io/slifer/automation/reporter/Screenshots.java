package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;

public class Screenshots extends HashMap<String, String> {
    
    public void captureScreenshot() {
        String screenshot = ((TakesScreenshot) RunContext.getWebDriver()).getScreenshotAs(OutputType.BASE64);
        
        this.put(RunContext.getTestId(), screenshot);
    }
}
