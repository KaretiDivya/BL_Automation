package testScenarios;

import java.io.File;
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

import Utility.Base;
import Utility.Email_trigger;
import Utility.ReportManager;
import Utility.ScreenshotUtils;
import Utility.WebDriverUtils;
import pageObjects.ExportPage;
import pageObjects.ImportPage;
import pageObjects.LoginPage;

import org.assertj.core.api.SoftAssertions;

public class FunctionalTestImport extends Base {

	private WebDriver driver;
	private LoginPage loginPage;
	private ImportPage importPage;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(FunctionalTestImport.class);
	private SoftAssertions softly = new SoftAssertions();

	@BeforeClass
	public void initializeDriver() throws IOException {
		ReportManager.initReport("FunctionaltestImport");
		ReportManager.startTest("Initialize Browser");

		try {

			this.driver = initializeWebDriver();
			driver = WebDriverUtils.configureChromeDownloadPath();
			driver.manage().window().maximize();
			logger.info("Launching the browser.");
			ReportManager.logInfo("Browser launched successfully.");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception e) {
			ReportManager.logFail("Failed to initialize the browser: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 0)

	public void testLoginValidCredentials() {
		ReportManager.startTest("Login Test with Valid Credentials");

		try {
			String username = prop.getProperty("standarduser_username");
			String password = prop.getProperty("standarduser_password");

			driver.get(prop.getProperty("url"));
			logger.info("Navigating to the login page.");
			ReportManager.logInfo("Navigated to the login page.");
			driver.manage().window().maximize();

			loginPage = new LoginPage(driver);
			loginPage.enterUsername(username);
			loginPage.enterPassword(password);
			loginPage.clickLogin();

			boolean isLoginSuccess = loginPage.isLoginSuccessful();
			softly.assertThat(isLoginSuccess).as("Login Success Check").isTrue();
			ReportManager.logPass("Login test passed with valid credentials.");

		} catch (Exception e) {
			ReportManager.logFail("Login test failed: " + e.getMessage());
			logger.error("Test failed due to an exception: " + e.getMessage());
			softly.fail("Test failed due to an unexpected exception: " + e.getMessage());
		} finally {
			softly.assertAll();
		}
	}

	@Test(priority = 1)
	public void testLoginBatchLoader() throws TimeoutException {
		ReportManager.startTest("Batch Loader Login Test");

		try {
			loginPage.clickBatchloader();
			loginPage.switchToIframe(By.cssSelector("iframe"));
			loginPage.chooseServer();
			loginPage.clickServer();
			loginPage.clickConnect();
			loginPage.viewDashBoard();

			boolean isServerConnectSuccess = loginPage.isServerConnectSuccess();
			softly.assertThat(isServerConnectSuccess).as("Server Connect Success Check").isTrue();
			ReportManager.logPass("Batch Loader launched and connected successfully.");

		} catch (Exception e) {
			ReportManager.logFail("Batch Loader login failed: " + e.getMessage());
			logger.error("Test failed due to an exception: " + e.getMessage());
		} finally {
			softly.assertAll();
		}
	}

	@Test(priority = 2)
	public void testImport() {
	    ReportManager.startTest("ImportTest");

	    try {
	        importPage = new ImportPage(driver);
	        importPage.clickImport();
	        ReportManager.logPass("Clicked on Import button successfully.");

	        boolean isImportSuccess = importPage.isImportPageDisplayed();
	        softly.assertThat(isImportSuccess).as("Import Page Check").isTrue();
	        ReportManager.logPass("Navigated to Import Page successfully.");

	        loginPage.switchToIframe(By.cssSelector("iframe"));
	        ReportManager.logPass("Switched to iframe successfully.");

	        importPage.clickImportNamedDataObject();
	        ReportManager.logPass("Clicked on Import Named Data Object.");

	        importPage.clickChooseObject();
	        ReportManager.logPass("Clicked on Choose Object.");

	        importPage.selectObject("Job Model");
	        ReportManager.logPass("Selected 'Job Model' object.");

	        importPage.clickLoadButton();
	        ReportManager.logPass("Clicked Load button.");

	        System.out.println("Clicked Load button.");
	        Thread.sleep(2000);

	        Runtime.getRuntime().exec("C:\\Users\\DCCPL\\Documents\\AutoItCustomScript.exe");
	        ReportManager.logPass("Executed AutoIt script for file upload.");
	        System.out.println("Executed AutoIt script for file upload.");
	        Thread.sleep(2000);

	        importPage.clickNewButton();
	        ReportManager.logPass("Clicked New button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickupdateButton();
	        ReportManager.logPass("Clicked Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button again.");

	        importPage.clickSyncButton();
	        ReportManager.logPass("Clicked Sync button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyButton();
	        ReportManager.logPass("Clicked Copy button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickdeleteButton();
	        ReportManager.logPass("Clicked Delete button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickrenameButton();
	        ReportManager.logPass("Clicked Rename button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyUpdateButton();
	        ReportManager.logPass("Clicked Copy Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickImportButton();
	        ReportManager.logPass("Clicked Import button.");

	        importPage.importRevisionedDataObject();
	        ReportManager.logPass("Imported Revisioned Data Object.");

	        importPage.clickChooseObject();
	        ReportManager.logPass("Clicked on Choose Object again.");

	        Thread.sleep(2000);
	        importPage.selectObject("Wafer Inventory Spec");
	        ReportManager.logPass("Selected 'Wafer Inventory Spec' object.");

	        importPage.clickLoadButton();
	        ReportManager.logPass("Clicked Load button.");

	        Thread.sleep(2000);

	        Runtime.getRuntime().exec("C:\\Users\\DCCPL\\Documents\\AutoItCustomScript.exe");
	        ReportManager.logPass("Executed AutoIt script for file upload.");
	        System.out.println("Executed AutoIt script for file upload.");
	        importPage.clickNewButton();
	        ReportManager.logPass("Clicked New button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickupdateButton();
	        ReportManager.logPass("Clicked Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button again.");

	        importPage.clickSyncButton();
	        ReportManager.logPass("Clicked Sync button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyButton();
	        ReportManager.logPass("Clicked Copy button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickdeleteButton();
	        ReportManager.logPass("Clicked Delete button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickrenameButton();
	        ReportManager.logPass("Clicked Rename button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyUpdateButton();
	        ReportManager.logPass("Clicked Copy Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickImportButton();
	        ReportManager.logPass("Clicked Import button.");

	        importPage.importMatrix();
	        ReportManager.logPass("Imported Matrix.");

	        importPage.clickChooseObject();
	        ReportManager.logPass("Clicked on Choose Object once more.");

	        importPage.selectObject("WIP Instructions");
	        ReportManager.logPass("Selected 'WIP Instructions' object.");

	        importPage.clickLoadButton();
	        ReportManager.logPass("Clicked Load button.");

	        Thread.sleep(2000);

	        Runtime.getRuntime().exec("C:\\Users\\DCCPL\\Documents\\AutoItCustomScript.exe");
	        ReportManager.logPass("Executed AutoIt script for file upload.");
	        System.out.println("Executed AutoIt script for file upload.");
	        Thread.sleep(2000);

	        Runtime.getRuntime().exec("C:\\Users\\DCCPL\\Documents\\AutoItCustomScript.exe");
	        ReportManager.logPass("Executed AutoIt script for file upload.");
	        System.out.println("Executed AutoIt script for file upload.");
	        importPage.clickNewButton();
	        ReportManager.logPass("Clicked New button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickupdateButton();
	        ReportManager.logPass("Clicked Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button again.");

	        importPage.clickSyncButton();
	        ReportManager.logPass("Clicked Sync button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyButton();
	        ReportManager.logPass("Clicked Copy button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickdeleteButton();
	        ReportManager.logPass("Clicked Delete button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickrenameButton();
	        ReportManager.logPass("Clicked Rename button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickCopyUpdateButton();
	        ReportManager.logPass("Clicked Copy Update button.");

	        importPage.clickExportDataGridButton();
	        ReportManager.logPass("Clicked Export Data Grid button.");

	        importPage.clickImportButton();
	        ReportManager.logPass("Clicked Import button.");
	    } catch (Exception e) {
	        ReportManager.logFail("Import test failed: " + e.getMessage());
	        logger.error("Test failed due to an exception: " + e.getMessage());
	    } finally {
	        softly.assertAll();
	    }
	}

	@AfterClass
	public void tearDown() {
		ReportManager.startTest("Tear Down");

		try {
			if (driver != null) {
				// Send email after test execution
				String to = "divya.k@dhruvts.com"; // Replace with recipient email
				String subject = "Test Execution Report";
				String htmlContent = "<html><body>" + "<h1>Test Execution Report</h1>"
						+ "<p>The test execution has completed successfully. Please find the attached report.</p>"
						+ "</body></html>";

				// Path to the generated report
				String attachmentPath = "C:\\Users\\DCCPL\\Desktop\\DAM_FuncionalityTesting\\TestReports\\FunctionaltestImport.html";
                //C:\Users\DCCPL\Desktop\DAM_FuncionalityTesting\TestReports\FunctionaltestExport.html
				// Send the email
				Email_trigger.sendEmail(to, subject, htmlContent, attachmentPath);

				ReportManager.logPass("Test execution completed, and email sent successfully.");
			}
		} catch (Exception e) {
			ReportManager.logFail("Failed to close the browser or send email: " + e.getMessage());
		} finally {
			ReportManager.flushReport();
		}
	}
}


