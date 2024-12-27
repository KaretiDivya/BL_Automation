package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ExportPage {

	WebDriver driver;
	WebDriverWait wait;
	// Create an instance of Actions class

	private By exportButton = By.linkText("Export");
	private By namedDataObject = By.id("ndo-custom-checkbox");
	private By revisionedObject = By.id("rdo-custom-checkbox");
	private By matrix = By.id("matrix-custom-checkbox");
	private By dashboard = By.xpath("//div[contains(@class, '_welcome-message')]");
	private By iframeLocator = By.cssSelector("iframe");
	private By exportTitle = (By.xpath("//div[text()='Batch Loader / Export']"));
    private By chooseObject = By.cssSelector("input[placeholder=\"Choose an object...\"]");
    private By selectObject=By.id("object-dropdown-typeahead-item-0");
    private By assignInstance = By.cssSelector("button[class=' ms-1 me-1 btn btn-primary']");
    private By checkboxSelector = By.xpath("//table[@id='react-table']//tr[td[text()='2']]//input[@type='checkbox']");
    private By copyTemplateButton = By.id("copy-template-btn");
    private By deleteTemplate = By.id("delete-template-btn");
    private By blankTemplate = By.id("blank-export-btn");
    private By exporttemplate = By.id("export-btn");
    private By refresh = By.id("refresh-page-btn");
    private By closeMessage = By.cssSelector("button[aria-label=\"close\"]");
    private By CopyTemplatedownloaded = (By.xpath("//div[contains(text(), 'Successfully exported')]"));
    private By DeleteTemplatedownloaded = (By.xpath("//div[contains(text(), 'Successfully exported blank delete template')]"));
    private By BlankTemplatedownloaded = (By.xpath("//div[contains(text(), 'Successfully exported') and contains(text(), 'blank') and contains(text(), 'template')]"));
    private By DataTemplatedownloaded = (By.xpath("//div[contains(text(), 'Successfully exported') and contains(text(), 'data')]"));

	SoftAssert softAssert = new SoftAssert();

	public ExportPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 10 seconds timeout

	}

	// Method to click the Export page with explicit wait and exception handling
	public void clickExport() {
		try {
			// Wait for the Export link to be clickable
			WebElement export = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Export")));

			// Click on the Export link
			export.click();
		} catch (NoSuchElementException e) {
			// Handle the case where the Export link is not found
			System.out.println("Element not found: " + e.getMessage());
			throw new RuntimeException("Export link not found, unable to click.");
		} catch (Exception e) {
			// Handle any other unexpected errors
			System.out.println("Unexpected error: " + e.getMessage());
			throw new RuntimeException("Unexpected error during clicking Export link.");
		}
	}

	// Method to click the Export Named Data Object page with explicit wait and
	// exception handling
	public void clickExportNamedDataObject() {
		try {
			// Wait for the Named Data Object element to be clickable
			WebElement exportNamedDataObject = wait.until(ExpectedConditions.elementToBeClickable(namedDataObject));

			// Click on the Named Data Object button
			exportNamedDataObject.click();
		} catch (NoSuchElementException e) {
			// Handle the case where the Named Data Object element is not found
			System.out.println("Element not found: " + e.getMessage());
			throw new RuntimeException("Named Data Object button not found, unable to click.");
		} catch (Exception e) {
			// Handle any other unexpected errors
			System.out.println("Unexpected error: " + e.getMessage());
			throw new RuntimeException("Unexpected error during clicking Named Data Object button.");
		}
	}

	// Method to click the Export Revisioned Data Object page with explicit wait and
	// exception handling
	public void exportRevisionedDataObject() {
		try {
			// Wait for the Revisioned Data Object element to be clickable
			WebElement exportRevisionedDataObject = wait
					.until(ExpectedConditions.elementToBeClickable(revisionedObject));

			// Click on the Revisioned Data Object button
			exportRevisionedDataObject.click();
		} catch (NoSuchElementException e) {
			// Handle the case where the Revisioned Data Object element is not found
			System.out.println("Element not found: " + e.getMessage());
			throw new RuntimeException("Revisioned Data Object button not found, unable to click.");
		} catch (Exception e) {
			// Handle any other unexpected errors
			System.out.println("Unexpected error: " + e.getMessage());
			throw new RuntimeException("Unexpected error during clicking Revisioned Data Object button.");
		}
	}

	// Method to click the Export Matrix page with explicit wait and exception
	// handling
	public void exportMatrix() {
		try {
			// Wait for the Export Matrix element to be clickable
			WebElement exportMatrix = wait.until(ExpectedConditions.elementToBeClickable(matrix));

			// Click on the Export Matrix link
			exportMatrix.click();
		} catch (NoSuchElementException e) {
			// Handle the case where the Export Matrix element is not found
			System.out.println("Element not found: " + e.getMessage());
			throw new RuntimeException("Export Matrix button not found, unable to click.");
		} catch (Exception e) {
			// Handle any other unexpected errors
			System.out.println("Unexpected error: " + e.getMessage());
			throw new RuntimeException("Unexpected error during clicking Export Matrix button.");
		}
	}

	// Method to check if the Export Page is displayed
	public boolean isExportPage() {
		try {

			driver.switchTo().defaultContent();
			// Wait for the BatchLoader icon to be visible on the page
			WebElement exportPageMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(exportTitle));
			System.out.println(exportPageMessage.getText());

			// Return true if the BatchLoader icon is displayed
			return exportPageMessage.isDisplayed();
		} catch (NoSuchElementException e) {
			// Log the error if the BatchLoader icon is not found
			System.out.println("Error: The BatchLoader Icon element was not found. " + e.getMessage());
			throw new RuntimeException("Login failed: BatchLoaderIcon not found.");
		} catch (Exception e) {
			// Log any unexpected errors
			System.out.println("Unexpected error occurred during login verification: " + e.getMessage());
			throw new RuntimeException("Unexpected error while verifying login success.");
		}
	}

	// Method to verify Batch Loader / Dashboard Page
	public void dashboardPage() throws TimeoutException {

		// Switch to default content if in an iframe
		driver.switchTo().defaultContent();

		// Wait for the dashboard element to be visible
		WebElement dashboardPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));

		// Expected and actual title
		String expectedTitle = "Batch Loader / Dashboard";
		String actualTitle = dashboardPage.getText(); // Get text from the element

		// Log the actual title
		System.out.println(actualTitle);

		// Perform the soft assertion
		softAssert.assertEquals(actualTitle, expectedTitle, "Title doesn't match");

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


//public void selectObject(String objName) {
//	try {
//		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(chooseObject));
//
//		// Click on the Export Matrix link
//		clickChooseObject.click();
//		
//		Select dropdown = new Select(clickChooseObject);
//        dropdown.selectByVisibleText(objName);
//        
//		// Wait for the Export Matrix element to be clickable
//		WebElement clickChooseObject1 = wait.until(ExpectedConditions.elementToBeClickable(selectObject));
//
//		// Click on the Export Matrix link
//		clickChooseObject1.click();
//	} catch (NoSuchElementException e) {
//		// Handle the case where the Export Choose object element is not found
//		System.out.println("object not found: " + e.getMessage());
//		throw new RuntimeException("Export Choose object  not found, unable to click.");
//	} catch (Exception e) {
//		// Handle any other unexpected errors
//		System.out.println("Unexpected error: " + e.getMessage());
//		throw new RuntimeException("Unexpected error during clicking Export Choose object dropdown.");
//	}
//}

public void checkboxSelector() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(checkboxSelector));

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

public void AssignInstance() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(assignInstance));

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

public void ClickcopyTemplateButton() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(copyTemplateButton));

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

public void ClickCloseMessage() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(closeMessage));

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

public void ClickdeleteTemplateButton() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(deleteTemplate));

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
public void ClickblankTemplateButton() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(blankTemplate));

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

public void ClickdataTemplateButton() {
	try {
		// Wait for the Export Matrix element to be clickable
		WebElement clickChooseObject = wait.until(ExpectedConditions.elementToBeClickable(exporttemplate));

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
public boolean isCopyTemplateDownloaded() {
    try {
        // Wait for the element to be visible
        WebElement copyTemplateDownloadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(CopyTemplatedownloaded));
        
        // Log the text of the downloaded file element
        String fileText = copyTemplateDownloadedFile.getText();
        System.out.println(fileText);

        // Return true if the file element is displayed
        return copyTemplateDownloadedFile.isDisplayed();
        
    } catch (NoSuchElementException e) {
        // Log the error if the element is not found
        String errorMessage = "Error: The CopyTemplate downloaded element was not found. " + e.getMessage();
        System.out.println(errorMessage);
        throw new RuntimeException(errorMessage);
        
    } catch (Exception e) {
        // Log any unexpected errors
        String unexpectedErrorMessage = "Unexpected error occurred while verifying the CopyTemplate download: " + e.getMessage();
        System.out.println(unexpectedErrorMessage);
        throw new RuntimeException(unexpectedErrorMessage);
    }
}

public boolean isDeleteTemplateDownloaded() {
    try {
        // Wait for the element to be visible
        WebElement deleteTemplateDownloadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteTemplatedownloaded));
        
        // Log the text of the downloaded file element
        String fileText = deleteTemplateDownloadedFile.getText();
        System.out.println(fileText);

        // Return true if the file element is displayed
        return deleteTemplateDownloadedFile.isDisplayed();
        
    } catch (NoSuchElementException e) {
        // Log the error if the element is not found
        String errorMessage = "Error: The CopyTemplate downloaded element was not found. " + e.getMessage();
        System.out.println(errorMessage);
        throw new RuntimeException(errorMessage);
        
    } catch (Exception e) {
        // Log any unexpected errors
        String unexpectedErrorMessage = "Unexpected error occurred while verifying the CopyTemplate download: " + e.getMessage();
        System.out.println(unexpectedErrorMessage);
        throw new RuntimeException(unexpectedErrorMessage);
    }
}

public boolean isBlankTemplateDownloaded() {
    try {
        // Wait for the element to be visible
        WebElement blankTemplateDownloadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(BlankTemplatedownloaded));
        
        // Log the text of the downloaded file element
        String fileText = blankTemplateDownloadedFile.getText();
        System.out.println(fileText);

        // Return true if the file element is displayed
        return blankTemplateDownloadedFile.isDisplayed();
        
    } catch (NoSuchElementException e) {
        // Log the error if the element is not found
        String errorMessage = "Error: The CopyTemplate downloaded element was not found. " + e.getMessage();
        System.out.println(errorMessage);
        throw new RuntimeException(errorMessage);
        
    } catch (Exception e) {
        // Log any unexpected errors
        String unexpectedErrorMessage = "Unexpected error occurred while verifying the CopyTemplate download: " + e.getMessage();
        System.out.println(unexpectedErrorMessage);
        throw new RuntimeException(unexpectedErrorMessage);
    }
}

public boolean isDataTemplateDownloaded() {
    try {
        // Wait for the element to be visible
        WebElement dataTemplateDownloadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(DataTemplatedownloaded));
        
        // Log the text of the downloaded file element
        String fileText = dataTemplateDownloadedFile.getText();
        System.out.println(fileText);

        // Return true if the file element is displayed
        return dataTemplateDownloadedFile.isDisplayed();
        
    } catch (NoSuchElementException e) {
        // Log the error if the element is not found
        String errorMessage = "Error: The CopyTemplate downloaded element was not found. " + e.getMessage();
        System.out.println(errorMessage);
        throw new RuntimeException(errorMessage);
        
    } catch (Exception e) {
        // Log any unexpected errors
        String unexpectedErrorMessage = "Unexpected error occurred while verifying the CopyTemplate download: " + e.getMessage();
        System.out.println(unexpectedErrorMessage);
        throw new RuntimeException(unexpectedErrorMessage);
    }
}


}

