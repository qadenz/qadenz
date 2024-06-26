/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter;

import dev.qadenz.automation.config.WebDriverProvider;
import dev.qadenz.automation.logs.Loggers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.UUID;

/**
 * Captures screenshots with WebDriver, then stores the images along with an identifier for later retrieval.
 *
 * @author Tim Slifer
 */
public class Screenshot {
    
    private static final Logger LOG = Loggers.getSuiteLogger();
    
    /**
     * A convenience boolean to pass to {@code check()} or {@code verify()} when disabling screenshots, to enhance
     * readability of the code.
     */
    public static final boolean SKIP = false;
    
    /**
     * Captures and stores a screenshot of the current browser.
     */
    public void capture() {
        String uuid = UUID.randomUUID().toString();
        
        String screenshot;
        try {
            File rawCapture = ((TakesScreenshot) WebDriverProvider.getWebDriver()).getScreenshotAs(OutputType.FILE);
            BufferedImage convertedCapture = ImageIO.read(rawCapture);
            screenshot = convertToBase64(convertedCapture);
        }
        catch (Exception e) {
            LOG.error("Error capturing image.", e);
            
            throw new RuntimeException(e);
        }
        
        Reporter.log(uuid);
        ScreenshotData.getInstance().put(uuid, screenshot);
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
