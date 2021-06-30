package io.slifer.automation.reporter;

import io.slifer.automation.config.RunContext;
import org.imgscalr.Scalr;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * Captures screenshots with WebDriver, then stores the images along with a given identifier for later retrieval.
 *
 * @author Tim Slifer
 */
public class Screenshots extends HashMap<String, String> {
    
    private static final Logger LOG = LoggerFactory.getLogger("SUITE");
    
    private static Screenshots instance;
    
    private Screenshots() {
        // Singleton
    }
    
    public static Screenshots getInstance() {
        if (instance == null) {
            instance = new Screenshots();
        }
        
        return instance;
    }
    
    public void captureScreen() {
        String uuid = UUID.randomUUID().toString();
        
        File rawCapture = ((TakesScreenshot) RunContext.getWebDriver()).getScreenshotAs(OutputType.FILE);
        BufferedImage resizedCapture = resize(rawCapture);
        String screenshot = convertToBase64(resizedCapture);
        
        Reporter.log(uuid);
        this.put(uuid, screenshot);
    }
    
    private BufferedImage resize(File rawCapture) {
        LOG.debug("Resizing screen capture.");
        try {
            BufferedImage original = ImageIO.read(rawCapture);
            int originalWidth = original.getWidth();
            LOG.debug("Detected original capture width [{}].", originalWidth);
            
            BufferedImage resized = Scalr.resize(original, 1145);
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
