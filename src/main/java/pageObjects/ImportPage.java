package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportPage {

	WebDriver driver;
	WebDriverWait wait;
	// Create an instance of Actions class

	private By importButton = By.linkText("Import");
	private By namedDataObject = By.id("ndo-named-checkbox");
	private By revisionedObject = By.id("ndo-revisioned-checkbox");
	private By matrix = By.id("ndo-matrix-checkbox");
	private By importTitle= (By.xpath("//div[contains(@class, '_welcome-message_os3g7_231')]"));
    private By chooseObject = By.cssSelector("input[placeholder=\"Choose an object...\"]");
  //  private By selectObject=By.id("object-dropdown-typeahead-item-0");
    //id="select-object-item-0"
    private By selectObject=By.id("select-object-item-0");

    private By LoadButton=By.cssSelector("button[class='btn btn-outline-primary btn-sm']");


	public ImportPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout

	}

	// Method to click the Import button with exception handling
	public void clickImport() {
		try {
			// Locate the Import button
			WebElement importButton = driver.findElement(By.linkText("Import"));
			// Click the Import button
			importButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}

	public boolean isImportPageDisplayed() {
		try {
			// Switch to the default content
			driver.switchTo().defaultContent();

			// Wait until the Import Page title is visible
			WebElement importPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(importTitle));
			System.out.println(importPageTitle.getText());

			// Return true if the Import Page title is displayed
			return importPageTitle.isDisplayed();
		} catch (NoSuchElementException e) {
			// Log and throw an error if the Import Page title is not found
			System.out.println("Error: The Import Page title element was not found. " + e.getMessage());
			throw new RuntimeException("Import page title not found.");
		} catch (Exception e) {
			// Log and handle any other unexpected errors
			System.out.println("Unexpected error occurred while checking the import page: " + e.getMessage());
			throw new RuntimeException("Unexpected error while verifying the import page.");
		}
	}

	// Method to click the Import Named Data Object button with exception handling
	public void clickImportNamedDataObject() {
		try {
			// Wait for the Named Data Object button to be clickable
			WebElement importNamedDataObject = wait.until(ExpectedConditions.elementToBeClickable(namedDataObject));

			// Click the Named Data Object button
			importNamedDataObject.click();
		} catch (NoSuchElementException e) {
			// Log error if the element is not found
			System.out.println("Error: The 'Import Named Data Object' button was not found. " + e.getMessage());
			throw new RuntimeException("Named Data Object button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import Named Data Object' button: "
					+ e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import Named Data Object button.");
		}
	}

	public void clickChooseObject() {
		try {
			// Wait for the Export Matrix element to be clickable
			WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(chooseObject));

			// Click on the Export Matrix link
			clickChooseObject.click();
		} catch (NoSuchElementException e) {
			// Handle the case where the Export Choose object element is not found
			System.out.println("object not found: " + e.getMessage());
			throw new RuntimeException("Export Choose object  not found, unable to click.");
		} catch (Exception e) {
			// Handle any other unexpected errors
			System.out.println("Unexpected error: " + e.getMessage());
			throw new RuntimeException("Unexpected error during clicking Export Choose object dropdown.");
		}
	}
	// Method to click the Import Revisioned Data Object button with exception
	// handling
	public void importRevisionedDataObject() {
		try {
			// Wait for the Revisioned Data Object button to be clickable
			WebElement importRevisionedDataObject = wait
					.until(ExpectedConditions.elementToBeClickable(revisionedObject));

			// Click the Revisioned Data Object button
			importRevisionedDataObject.click();
		} catch (NoSuchElementException e) {
			// Log error if the element is not found
			System.out.println("Error: The 'Import Revisioned Data Object' button was not found. " + e.getMessage());
			throw new RuntimeException("Revisioned Data Object button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import Revisioned Data Object' button: "
					+ e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import Revisioned Data Object button.");
		}
	}
	
	public void selectObject(String objName) {
	    try {
	        // Wait for the input field to be clickable
	        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(chooseObject));
	        
	        // Type the object name into the input field (simulate typing)
	        inputField.clear();
	        inputField.sendKeys(objName);
	        
	        // Optionally, wait for the dropdown to load (if the options are dynamically generated)
	        WebElement selectOption = wait.until(ExpectedConditions.elementToBeClickable(selectObject));
	        
	        // Click the desired option in the dropdown
	        selectOption.click();
	    } catch (NoSuchElementException e) {
	        // Handle the case where the object is not found
	        System.out.println("Object not found: " + e.getMessage());
	        throw new RuntimeException("Object not found in dropdown.");
	    } catch (Exception e) {
	        // Handle any other unexpected errors
	        System.out.println("Unexpected error: " + e.getMessage());
	        throw new RuntimeException("Unexpected error during selecting object.");
	    }
	}

	public void clickLoadButton() {
		try {
			// Locate the Import button
	        WebElement LoadButtonClick = wait.until(ExpectedConditions.elementToBeClickable(LoadButton));
			// Click the Import button
	        LoadButtonClick.click();
	
	     //   LoadButtonClick.sendKeys("C:\\Users\\DCCPL\\Downloads\\Operation_BlankExport (5).xlsx");
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}

	// Method to click the Import Matrix button with exception handling
	public void importMatrix() {
		try {
			// Wait for the Matrix button to be clickable
			WebElement importMatrix = wait.until(ExpectedConditions.elementToBeClickable(matrix));

			// Click the Matrix button
			importMatrix.click();
		} catch (NoSuchElementException e) {
			// Log error if the element is not found
			System.out.println("Error: The 'Import Matrix' button was not found. " + e.getMessage());
			throw new RuntimeException("Matrix button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out
					.println("Unexpected error occurred while clicking the 'Import Matrix' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import Matrix button.");
		}
	}
	
	public void clickNewButton() {
		try {
			// Locate the Import button
			WebElement NewButton = driver.findElement(By.cssSelector("input[id=\"new\"]"));
			// Click the Import button
			NewButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	
	public void clickExportDataGridButton() {
		try {
			// Locate the Import button
			WebElement ExportDataGrid =driver.findElement(By.xpath("//button[text()='Export Data Grid']"));

			// Click the Import button
			ExportDataGrid.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	
	public void clickupdateButton() {
		try {
			// Locate the Import button
			WebElement UpdateButton =driver.findElement(By.id("update"));

			// Click the Import button
			UpdateButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	
	public void clickSyncButton() {
		try {
			// Locate the Import button
			WebElement SyncButton =driver.findElement(By.id("sync"));

			// Click the Import button
			SyncButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	
	public void clickCopyButton() {
		try {
			// Locate the Import button
			WebElement CopyButton = driver.findElement(By.cssSelector("input[id=\"copy\"]"));

			// Click the Import button
			CopyButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}

	public void clickdeleteButton() {
		try {
			// Locate the Import button
			WebElement DeleteButton =driver.findElement(By.id("delete"));

			// Click the Import button
			DeleteButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	public void clickrenameButton() {
		try {
			// Locate the Import button
			WebElement RenameButton = driver.findElement(By.id("rename"));

			// Click the Import button
			RenameButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	public void clickCopyUpdateButton() {
		try {
			// Locate the Import button
			WebElement CopyupdateButton = driver.findElement(By.id("copyUpdate"));

			// Click the Import button
			CopyupdateButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
	
	public void clickImportButton() {
		try {
			// Locate the Import button
			WebElement ImportButton = driver.findElement(By.id("export-btn"));

			// Click the Import button
			ImportButton.click();
		} catch (NoSuchElementException e) {
			// Log the error if the Import button is not found
			System.out.println("Error: The 'Import' button was not found. " + e.getMessage());
			throw new RuntimeException("Import button not found.");
		} catch (Exception e) {
			// Log any other unexpected errors
			System.out.println("Unexpected error occurred while clicking the 'Import' button: " + e.getMessage());
			throw new RuntimeException("Unexpected error while clicking the Import button.");
		}
	}
}
