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

public class FunctionalTestSuiteExport extends Base {

	private WebDriver driver;
	private LoginPage loginPage;
	private ExportPage exportPage;
	private ImportPage importPage;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(FunctionalTestSuiteExport.class);
	private SoftAssertions softly = new SoftAssertions();

	@BeforeClass
	public void initializeDriver() throws IOException {
		ReportManager.initReport("FunctionaltestExport");
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
	public void testExport() {
		ReportManager.startTest("Export Test");

		try {
			exportPage = new ExportPage(driver);
			exportPage.clickExport();
			boolean isExportSuccess = exportPage.isExportPage();
			softly.assertThat(isExportSuccess).as("Export Page Check").isTrue();
			ReportManager.logPass("Navigated to Export Page successfully.");
			loginPage.switchToIframe(By.cssSelector("iframe"));

			exportPage.clickExportNamedDataObject(); // Select ndo control
			exportPage.clickChooseObject(); // Click select object dropdown
			exportPage.selectObject("Job Model"); // selectobjectfrom dropdown

			exportPage.ClickcopyTemplateButton();

			boolean isCopytemplateDownloaded = exportPage.isCopyTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isCopytemplateDownloaded).as("Copy Template Download Success Check").isTrue();
			ReportManager.logPass("Copy Template download was successful.");
			exportPage.ClickCloseMessage();

			exportPage.ClickdeleteTemplateButton();

			boolean isdeletetemplateDownloaded = exportPage.isDeleteTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isdeletetemplateDownloaded).as("delete Template Download Success Check").isTrue();
			ReportManager.logPass("delete Template download was successful.");
			exportPage.ClickCloseMessage();

			exportPage.ClickblankTemplateButton();

			boolean isblanktemplateDownloaded = exportPage.isBlankTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isblanktemplateDownloaded).as("blank Template Download Success Check").isTrue();
			ReportManager.logPass("blank Template download was successful.");
			exportPage.ClickCloseMessage();

			// exportPage.AssignInstance(); //assign the ndo object and export data template
			// excel
			exportPage.checkboxSelector(); // selectExportInstancesfrom grid
			exportPage.AssignInstance();

			exportPage.ClickdataTemplateButton();

			boolean isdatatemplateDownloaded = exportPage.isDataTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isdatatemplateDownloaded).as("data Template Download Success Check").isTrue();
			ReportManager.logPass("data Template download was successful.");
			exportPage.ClickCloseMessage();

			exportPage.exportRevisionedDataObject();
			// exportPage.clickChooseObject(); //Click select object dropdown
			exportPage.selectObject("Wafer WIP Spec"); // selectobjectfrom dropdown

			exportPage.ClickblankTemplateButton();

			boolean isRDOblanktemplateDownloaded = exportPage.isBlankTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isRDOblanktemplateDownloaded).as("blank Template Download Success Check").isTrue();
			ReportManager.logPass("blank Template download was successful.");
			exportPage.ClickCloseMessage();

			// exportPage.AssignInstance(); //assign the ndo object and export data template
			// excel
			exportPage.checkboxSelector(); // selectExportInstancesfrom grid
			exportPage.AssignInstance();

			exportPage.ClickdataTemplateButton();

			boolean isRDOdatatemplateDownloaded = exportPage.isDataTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isRDOdatatemplateDownloaded).as("data Template Download Success Check").isTrue();
			ReportManager.logPass("data Template download was successful.");
			exportPage.ClickCloseMessage();

			exportPage.exportMatrix();

			exportPage.clickChooseObject(); // Click select object dropdown
			exportPage.selectObject("Check Sheet Matrix"); // selectobjectfrom dropdown

			exportPage.ClickblankTemplateButton();

			boolean isMatrixblanktemplateDownloaded = exportPage.isBlankTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isMatrixblanktemplateDownloaded).as("blank Template Download Success Check").isTrue();
			ReportManager.logPass("blank Template download was successful.");
			exportPage.ClickCloseMessage();

			// exportPage.AssignInstance(); //assign the ndo object and export data template
			// excel
			exportPage.checkboxSelector(); // selectExportInstancesfrom grid
			exportPage.AssignInstance();

			exportPage.ClickdataTemplateButton();

			boolean isMatrixdatatemplateDownloaded = exportPage.isDataTemplateDownloaded();
			// Validation using SoftAssertions
			softly.assertThat(isMatrixdatatemplateDownloaded).as("data Template Download Success Check").isTrue();
			ReportManager.logPass("data Template download was successful.");
			exportPage.ClickCloseMessage();
			ReportManager.logPass("Export operations completed successfully.");

		} catch (Exception e) {
			ReportManager.logFail("Export test failed: " + e.getMessage());
			logger.error("Test failed due to an exception: " + e.getMessage());
			softly.fail("Test failed due to an unexpected exception: " + e.getMessage());
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
				String attachmentPath = "C:\\Users\\DCCPL\\Desktop\\DAM_FuncionalityTesting\\TestReports\\FunctionaltestExport.html";
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