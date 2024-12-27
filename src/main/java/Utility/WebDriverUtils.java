package Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.HashMap;
public class WebDriverUtils {
	
	   // Method to configure download path for Chrome
    public static WebDriver configureChromeDownloadPath() {
        // Get the current project directory for the download path
     //   String downloadPath = "C:\\WorkSpace\\EmployeeAutomationFramework\\DAM_FuncionalityTesting\\downloads";
           String downloadPath = System.getProperty("user.dir") + File.separator + "downloads"; // Using File.separator for portability
  
        // Configure Chrome preferences for downloads
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath);  // Set the download directory
        chromePrefs.put("profile.default_content_settings.popups", 0); // Disable download pop-ups

        // Apply preferences to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--start-maximized");

        // Return a new ChromeDriver instance with the configured options
        return new ChromeDriver(options);
    }
    
 // Method to configure download path for Edge
    public static WebDriver configureEdgeDownloadPath() {
        // Get the current project directory for the download path
        String downloadPath = "C:\\WorkSpace\\EmployeeAutomationFramework\\DAM_FuncionalityTesting\\downloads";
        
        // Configure Edge preferences for downloads
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("download.default_directory", downloadPath);  // Set the download directory
        edgePrefs.put("profile.default_content_settings.popups", 0); // Disable download pop-ups

        // Apply preferences to EdgeOptions
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", edgePrefs);
        options.addArguments("--start-maximized");

        // Return a new EdgeDriver instance with the configured options
        return new EdgeDriver(options);
    }
}


