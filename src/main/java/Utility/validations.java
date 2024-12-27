package Utility;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class validations {

    // Normalize text for comparison
    private static String normalizeText(String text) {
        return text.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    // Validate success messages
    public static void validateMessage(WebDriver driver, String xpath, String expectedMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            String actualMessage = successMessage.getText();
            System.out.println("Expected: " + expectedMessage);
            System.out.println("Actual: " + actualMessage);

            if (normalizeText(actualMessage).equals(normalizeText(expectedMessage))) {
                System.out.println("Success message validated successfully!");
            } else {
                System.out.println("Validation failed. Actual message: " + actualMessage);
            }
        } catch (Exception e) {
            System.err.println("Error during validation: " + e.getMessage());
        }
    }

    // Copy rev template
    public static void validateCopyCopyRevTemplate(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until the success message is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Successfully exported')]")));
        validateMessage(driver, "//div[contains(text(), 'Successfully exported')]", 
                        "Successfully exported Copy/CopyRev/Rename blank template.");
    }

    // Delete blank template
    public static void validateDeleteTemplate(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until the success message is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Successfully exported blank delete template')]")));
        validateMessage(driver, "//div[contains(text(), 'Successfully exported blank delete template')]", 
                        "Successfully exported blank delete template.");
    }

    // Blank template
    public static void validateBlankTemplate(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Wait until the success message is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Successfully exported') and contains(text(), 'blank') and contains(text(), 'template')]")));
        validateMessage(driver, "//div[contains(text(), 'Successfully exported') and contains(text(), 'blank') and contains(text(), 'template')]",
                        "Successfully exported blank * template");
    }

    // Data export
    public static void validateDataTemplate(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Wait until the success message is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Successfully exported') and contains(text(), 'data')]")));
        validateMessage(driver, "//div[contains(text(), 'Successfully exported') and contains(text(), 'data')]", 
                        "Successfully exported data");
    }
}
