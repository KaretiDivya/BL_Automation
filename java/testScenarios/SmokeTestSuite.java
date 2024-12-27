package testScenarios;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ExportPage;
import pageObjects.ImportPage;
import pageObjects.LoginPage;
import utils.TestReports;
import utils.Base;
import org.assertj.core.api.SoftAssertions;

public class SmokeTestSuite extends Base {

    private WebDriver driver;
    private LoginPage loginPage;
    private ExportPage exportPage;
    private ImportPage importPage;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(SmokeTestSuite.class);
    private SoftAssertions softly = new SoftAssertions();

    @BeforeClass
    public void initializeDriver() throws IOException {
        TestReports.initReport("SmokeTestReport");
        TestReports.startTest("Initialize Browser");

        try {
            this.driver = initializeWebDriver();
            logger.info("Launching the browser.");
            TestReports.logInfo("Browser launched successfully.");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            TestReports.logFail("Failed to initialize the browser: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 0)
    public void testLoginValidCredentials() {
        TestReports.startTest("Login Test with Valid Credentials");

        try {
            String username = prop.getProperty("standarduser_username");
            String password = prop.getProperty("standarduser_password");

            driver.get(prop.getProperty("url"));
            logger.info("Navigating to the login page.");
            TestReports.logInfo("Navigated to the login page.");
            driver.manage().window().maximize();

            loginPage = new LoginPage(driver);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLogin();

            boolean isLoginSuccess = loginPage.isLoginSuccessful();
            softly.assertThat(isLoginSuccess).as("Login Success Check").isTrue();
            TestReports.logPass("Login test passed with valid credentials.");

        } catch (Exception e) {
            TestReports.logFail("Login test failed: " + e.getMessage());
            logger.error("Test failed due to an exception: " + e.getMessage());
            softly.fail("Test failed due to an unexpected exception: " + e.getMessage());
        } finally {
            softly.assertAll();
        }
    }

    @Test(priority = 1)
    public void testLoginBatchLoader() throws TimeoutException {
        TestReports.startTest("Batch Loader Login Test");

        try {
            loginPage.clickBatchloader();
            loginPage.switchToIframe(By.cssSelector("iframe"));
            loginPage.chooseServer();
            loginPage.clickServer();
            loginPage.clickConnect();
            loginPage.viewDashBoard();

            boolean isServerConnectSuccess = loginPage.isServerConnectSuccess();
            softly.assertThat(isServerConnectSuccess).as("Server Connect Success Check").isTrue();
            TestReports.logPass("Batch Loader launched and connected successfully.");

        } catch (Exception e) {
            TestReports.logFail("Batch Loader login failed: " + e.getMessage());
            logger.error("Test failed due to an exception: " + e.getMessage());
        } finally {
            softly.assertAll();
        }
    }

    @Test(priority = 2)
    public void testExport() {
        TestReports.startTest("Export Test");

        try {
            exportPage = new ExportPage(driver);
            exportPage.clickExport();
            boolean isExportSuccess = exportPage.isExportPage();
            softly.assertThat(isExportSuccess).as("Export Page Check").isTrue();
            TestReports.logPass("Navigated to Export Page successfully.");
            loginPage.switchToIframe(By.cssSelector("iframe"));
          
            exportPage.clickExportNamedDataObject();
            exportPage.exportRevisionedDataObject();
            exportPage.exportMatrix();
            TestReports.logPass("Export operations completed successfully.");

        } catch (Exception e) {
            TestReports.logFail("Export test failed: " + e.getMessage());
            logger.error("Test failed due to an exception: " + e.getMessage());
            softly.fail("Test failed due to an unexpected exception: " + e.getMessage());
        } finally {
            softly.assertAll();
        }
    }

    @Test(priority = 3)
    public void testImport() {
        TestReports.startTest("Import Test");

        try {
            importPage = new ImportPage(driver);
            importPage.clickImport();

            boolean isImportSuccess = importPage.isImportPageDisplayed();
            softly.assertThat(isImportSuccess).as("Import Page Check").isTrue();
            TestReports.logPass("Navigated to Import Page successfully.");

            loginPage.switchToIframe(By.cssSelector("iframe"));
            importPage.clickImportNamedDataObject();
            importPage.importRevisionedDataObject();
            importPage.importMatrix();
            TestReports.logPass("Import operations completed successfully.");

        } catch (Exception e) {
            TestReports.logFail("Import test failed: " + e.getMessage());
            logger.error("Test failed due to an exception: " + e.getMessage());
            softly.fail("Test failed due to an unexpected exception: " + e.getMessage());
        } finally {
            softly.assertAll();
        }
    }

    @AfterClass
    public void tearDown() {
        TestReports.startTest("Tear Down");

        try {
            if (driver != null) {
                driver.quit();
                TestReports.logPass("Browser closed successfully.");
                logger.info("Test completed. Browser closed.");
            }
        } catch (Exception e) {
            TestReports.logFail("Failed to close the browser: " + e.getMessage());
        } finally {
            TestReports.flushReport();
        }
    }
}
