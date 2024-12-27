package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.qsy7.file.api.model.File;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	// Create an instance of Actions class

	private By usernameField = By.name("username");
	private By passwordField = By.id("formPassword");
	private By loginButton = By.id("login-button");
	private By batchLoaderButton = By.id("launch-Batch Loader");
	private By connectButton = By.id("connect-button");
	private By selectServer = By.cssSelector("[aria-label='192.168.1.71 [80] [DEV-01]']");
	private By chooseServer = By.cssSelector("input[placeholder='Choose a server...']");
	private By totalTransactions = By.xpath("//h6[text()='Total Transactions']");
	private By iconBatchLoader = By.id("launch-Batch Loader");
	private By loginerrorMessage =By.xpath("//div[@class='Toastify__toast-body']//div[text()='You are not authorized to login.']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout

	}
	
	// Method to enter the username with explicit wait
	public void enterUsername(String username) {
	    try {
	        // Wait for the username field to be visible on the page
	        WebElement usernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
	        
	        // Clear any existing text in the username field
	        usernameElem.clear();
	        
	        // Enter the provided username into the username field
	        usernameElem.sendKeys(username);
	    } catch (Exception e) {
	        // Log and throw a runtime exception if an error occurs during username entry
	        System.out.println("Error: Unable to enter the username. " + e.getMessage());
	        throw new RuntimeException("Username entry failed: " + e.getMessage());
	    }
	}


	// Method to enter the password with explicit wait
	public void enterPassword(String password) {
	    try {
	        // Wait for the password field to be visible on the page
	        WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
	        
	        // Clear any existing text in the password field
	        passwordElem.clear();
	        
	        // Enter the provided password into the password field
	        passwordElem.sendKeys(password);
	    } catch (Exception e) {
	        // Log and throw a runtime exception if an error occurs during password entry
	        System.out.println("Error: Unable to enter the password. " + e.getMessage());
	        throw new RuntimeException("Password entry failed: " + e.getMessage());
	    }
	}

	 
	// Method to click the login button
	 public void clickLogin() {
	     try {
	         // Wait until the login button is clickable
	         WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	         
	         // Click the login button to submit the login form
	         loginBtn.click();
	     } catch (Exception e) {
	         // Log and throw an exception with the error message
	         System.out.println("Error: Unable to click the login button. " + e.getMessage());
	         throw new RuntimeException("Login button click failed: " + e.getMessage());
	     }
	 }


	

	 // Method to perform login using provided username and password
	    public void login(String username, String password) {
	        // Enter the username into the username field
	        enterUsername(username);
	        
	        // Enter the password into the password field
	        enterPassword(password);
	        
	        // Click the login button to submit the login form
	        clickLogin();
	    }

	
	// Method to check if the BatchLoader Icon is displayed
	public boolean isLoginSuccessful() {
	    try {
	        // Wait for the BatchLoader icon to be visible on the page
	        WebElement batchLoader = wait.until(ExpectedConditions.visibilityOfElementLocated(iconBatchLoader));
	        
	        // Return true if the BatchLoader icon is displayed
	        return batchLoader.isDisplayed();
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

    
    
 // Method to click the BatchLoader button
    public void clickBatchloader() {
        try {
            // Wait for the BatchLoader button to become clickable
            WebElement batchLoaderBtn = wait.until(ExpectedConditions.elementToBeClickable(batchLoaderButton));
            
            // Click the BatchLoader button
            batchLoaderBtn.click();
        } catch (NoSuchElementException e) {
            // Log and throw an exception when the element is not found
            System.out.println("Error: The BatchLoader button element was not found. " + e.getMessage());
            throw new RuntimeException("Failed to locate and click the BatchLoader button.");
        } catch (Exception e) {
            // Log and throw an exception for any unexpected errors
            System.out.println("Unexpected error occurred while clicking the BatchLoader button: " + e.getMessage());
            throw new RuntimeException("Unexpected error while attempting to click the BatchLoader button.");
        }
    }

    
    
 // Method to switch to a specified iframe
    public void switchToIframe(By iframeLocator) throws TimeoutException {
        try {
            // Wait for the iframe to be available and switch to it
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
            System.out.println("Successfully switched to the iframe.");
        } catch (Exception e) {
            // Log any other unexpected exceptions
            System.out.println("Unexpected error while switching to iframe: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred while trying to switch to the iframe.");
        }
    }

    

    
	/*public void switchToIframe(By iframeLocator) throws TimeoutException {
		// Wait for the iframe to be available and switch to it
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));

		// If needed, you can perform actions inside the iframe here
		System.out.println("Switched to iframe successfully.");
	}*/
	
	// Method to choose the server from a dropdown
	public void chooseServer() {
	    try {
	        WebElement chooseServerDropdown = wait.until(ExpectedConditions.elementToBeClickable(chooseServer));
	        chooseServerDropdown.click();
	    } catch (NoSuchElementException e) {
	        // Log and throw a descriptive exception for element not found
	        System.out.println("Error: 'Choose Server' dropdown element not found. " + e.getMessage());
	        throw new RuntimeException("Failed to locate the 'Choose Server' dropdown.");
	    } catch (Exception e) {
	        // Log and throw a descriptive exception for any other unexpected errors
	        System.out.println("Unexpected error occurred while interacting with the 'Choose Server' dropdown: " + e.getMessage());
	        throw new RuntimeException("Unexpected issue while clicking the 'Choose Server' dropdown.");
	    }
	}

		

	
	// Method to click the required server
	public void clickServer() {
	    try {
	        WebElement selectReqServer = wait.until(ExpectedConditions.elementToBeClickable(selectServer));
	        selectReqServer.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception and throw a clear runtime exception
	        System.out.println("Error: Unable to find the 'Select Server' element. " + e.getMessage());
	        throw new RuntimeException("Failed to locate and click on the required server.");
	    } catch (Exception e) {
	        // Log any unexpected errors with a more descriptive message
	        System.out.println("Unexpected error occurred while selecting the server: " + e.getMessage());
	        throw new RuntimeException("Unexpected issue while attempting to select the server.");
	    }
	}


	// Method to click the connect button
		public void clickConnect() {
		    try {
		        WebElement connectBtn = wait.until(ExpectedConditions.elementToBeClickable(connectButton));
		        connectBtn.click();
		    } catch (NoSuchElementException e) {
		        // Log the exception with a clear message and rethrow it
		        System.out.println("Error: Connect button element not found. " + e.getMessage());
		        throw new RuntimeException("Failed to locate the 'Connect' button.");
		    } catch (Exception e) {
		        // Log any unexpected error
		        System.out.println("Unexpected error while clicking the 'Connect' button: " + e.getMessage());
		        throw new RuntimeException("Unexpected issue while attempting to click the 'Connect' button.");
		    }
		}


	//Method to verify Transactions text in Dashboard	
	public void viewDashBoard() {
	    try {
	        WebElement transactions = wait.until(ExpectedConditions.visibilityOfElementLocated(totalTransactions));
	    } catch (NoSuchElementException e) {
	        // Log the exception and rethrow a custom message for the missing element
	        System.out.println("Error: Element 'Total Transactions' not found on the page. " + e.getMessage());
	        throw new RuntimeException("Unable to locate the 'Total Transactions' text element on the page.");
	    } catch (Exception e) {
	        // Log unexpected errors and rethrow with a custom message
	        System.out.println("Unexpected error occurred while verifying 'Total Transactions': " + e.getMessage());
	        throw new RuntimeException("Unexpected error while verifying 'Total Transactions' element visibility.");
	    }
	}
	
	
	// Method to check if isServerConnectSuccess
		public boolean isServerConnectSuccess() {
		    try {
		        // Wait for the BatchLoader icon to be visible on the page
		        WebElement transactions = wait.until(ExpectedConditions.visibilityOfElementLocated(totalTransactions));
		        
		        // Return true if the BatchLoader icon is displayed
		        return transactions.isDisplayed();
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

		public boolean isLoginErrorMessageDisplayed() {
			

		    try {
		        // Wait for the BatchLoader icon to be visible on the page
		        WebElement ErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(loginerrorMessage));
		        
		        // Return true if the BatchLoader icon is displayed
		        return ErrorMessage.isDisplayed();
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


}
