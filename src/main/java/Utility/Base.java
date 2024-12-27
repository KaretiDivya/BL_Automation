package Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Base {

    public FileInputStream fis;
    public Properties prop;
    public WebDriver driver;


    public WebDriver initializeWebDriver() throws IOException {
        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\configuration");
        prop = new Properties();
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if ("edge".equalsIgnoreCase(browserName)) {
         //   System.setProperty("webdriver.edge.driver", "path/to/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if ("chrome".equalsIgnoreCase(browserName)) {
         //   System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }
        return driver;
    }
}