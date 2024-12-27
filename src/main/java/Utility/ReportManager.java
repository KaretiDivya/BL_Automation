package Utility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize ExtentReports
    public static void initReport(String reportName) {
        // Get the user directory path
        String userDir = System.getProperty("user.dir");
        
        // Create a new directory for the reports under the user directory
        String reportDir = userDir + File.separator + "TestReports";
        File reportFolder = new File(reportDir);
        if (!reportFolder.exists()) {
            reportFolder.mkdirs(); // Create the directory if it doesn't exist
        }
        
        // Create the report file path
        String reportPath = reportDir + File.separator + reportName + ".html";
        
        // Initialize the HTML reporter with the report path
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportName);
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // Create a new test in the report
    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }

    // Log information
    public static void logInfo(String message) {
        getTest().info(message);
    }

    // Log pass status
    public static void logPass(String message) {
        getTest().pass(message);
    }

    // Log fail status
    public static void logFail(String message) {
        getTest().fail(message);
    }

    // Add screenshot to the report
    public static void addScreenshot(String filePath) {
        try {
            getTest().addScreenCaptureFromPath(filePath);
        } catch (Exception e) {
            logFail("Failed to attach screenshot: " + e.getMessage());
        }
    }

    // Flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
