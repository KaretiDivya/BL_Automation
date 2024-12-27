package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void captureScreenshot(WebDriver driver, String screenshotName, ExtentTest test) {
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";

            // Create directory if it doesn't exist
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Save the screenshot
            FileHandler.copy(source, new File(destination));

            // Attach the screenshot to the ExtentTest report
            if (test != null) {
                test.addScreenCaptureFromPath(destination);
            }

            System.out.println("Screenshot captured: " + destination);
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
