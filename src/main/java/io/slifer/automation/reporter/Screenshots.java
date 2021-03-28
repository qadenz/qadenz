package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.imgscalr.Scalr;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Screenshots extends HashMap<String, String> {
    
    public void captureScreenshot() {
        File rawCapture = ((TakesScreenshot) RunContext.getWebDriver()).getScreenshotAs(OutputType.FILE);
        
        BufferedImage resizedCapture = resize(rawCapture);
        
        String screenshot = convertToBase64(resizedCapture);
        
        this.put(RunContext.getTestId(), screenshot);
    }
    
    private BufferedImage resize(File rawCapture) {
        try {
            BufferedImage original = ImageIO.read(rawCapture);
            int originalWidth = original.getWidth();
            int targetWidth = originalWidth / 2;
            
            Scalr.resize(original, targetWidth);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private String convertToBase64(BufferedImage resizedCapture) {
        return "";
    }
}
