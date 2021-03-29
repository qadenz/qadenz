package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.imgscalr.Scalr;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.HashMap;

public class Screenshots extends HashMap<String, String> {
    
    public static final Logger LOG = RunContext.SUITE_LOG;
    
    public void captureScreenshot() {
        File rawCapture = ((TakesScreenshot) RunContext.getWebDriver()).getScreenshotAs(OutputType.FILE);
        
        BufferedImage resizedCapture = resize(rawCapture);
        
        String screenshot = convertToBase64(resizedCapture);
        
        this.put(RunContext.getTestId(), screenshot);
    }
    
    private BufferedImage resize(File rawCapture) {
        LOG.debug("Resizing screen capture.");
        try {
            BufferedImage original = ImageIO.read(rawCapture);
            int originalWidth = original.getWidth();
            LOG.debug("Detected original capture width [{}].", originalWidth);
            int targetWidth = originalWidth / 2;
            
            BufferedImage resized = Scalr.resize(original, targetWidth);
            LOG.debug("Resized to width [{}].", resized.getWidth());
            
            return resized;
        }
        catch (Exception e) {
            LOG.error("Error resizing image.", e);
            
            throw new RuntimeException(e);
        }
    }
    
    private String convertToBase64(BufferedImage resizedCapture) {
        LOG.debug("Converting to Base64.");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedCapture, "png", outputStream);
            byte[] bytes = outputStream.toByteArray();
            
            return Base64.getEncoder().encodeToString(bytes);
        }
        catch (Exception e) {
            LOG.error("Error converting image.", e);
            
            throw new RuntimeException(e);
        }
    }
}
