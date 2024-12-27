package testScenarios;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions Option = new ChromeOptions();
	Option.setAcceptInsecureCerts(true);
		WebDriver driver = new ChromeDriver(Option);
		
		
		
		// EdgeOptions options = new EdgeOptions();
		// WebDriver driver = new EdgeDriver(options);

		//WebDriver driver = new ChromeDriver(Option);
		driver.manage().window().maximize();

		String baseUrl = "https://192.168.1.33:4450/";
		driver.get(baseUrl);
		//formPassword//formBasicUsername//Choose a domain...//login-button//launch-Batch Loader
		//rbt-input-main form-control rbt-input//192.168.1.33 [443] [PRD-01]
		Thread.sleep(5000);
		//driver.findElement(By.linkText("About")).click();
		Thread.sleep(5000);
		//driver.findElement(By.linkText("Login")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("formBasicUsername")).sendKeys("Dhruv1");
		Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
		driver.findElement(By.id("formPassword")).sendKeys("Dhruv@1");
		 actions.sendKeys(Keys.TAB).perform();
		Thread.sleep(5000);
		//driver.findElement(By.cssSelector("[placeholder='Choose a domain...']")).click();
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("launch-Batch Loader")).click();
		Thread.sleep(8000);
		

		// Switch to the iframe
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        //assert iframe != null : "Iframe not found!";
        driver.switchTo().frame(iframe);
        
        
        
        driver.findElement(By.cssSelector("input[placeholder='Choose a server...']")).click();
		//driver.findElement(By.linkText("Import")).click();
        driver.findElement(By.cssSelector("[aria-label='192.168.1.33 [DEV-01]']")).click();;
      // driver.findElement(By.cssSelector("[aria-label='192.168.1.89 [443] [New_User_Environment]']")).click();


      
		//Thread.sleep(8000);
		driver.findElement(By.id("connect-button")).click();
		Thread.sleep(18000);
		 driver.findElement(By.xpath("//h6[contains(text(), 'Transactions')]"));
		driver.findElement(By.linkText("Export")).click();
		//Thread.sleep(18000);
		//driver.quit();
		driver.findElement(By.id("ndo-custom-checkbox")).click();
		//.Thread.sleep(8000);
        driver.findElement(By.cssSelector("input[placeholder='Choose an object...']")).click();
        driver.findElement(By.linkText("Factory")).click();
        //copy-template-btn
        driver.findElement(By.id("copy-template-btn")).click();
        driver.findElement(By.id("delete-template-btn")).click();
        driver.findElement(By.id("blank-export-btn")).click();
        driver.findElement(By.id("export-btn")).click();
        driver.findElement(By.id("refresh-page-btn")).click();
        //blank-export-btn//delete-template-btn//export-btn//refresh-page-btn
    	Thread.sleep(8000);
        //rdo-custom-checkbox
      /*  driver.findElement(By.id("rdo-custom-checkbox")).click();
		Thread.sleep(8000);
        driver.findElement(By.cssSelector("input[placeholder='Choose an object...']")).click();
        driver.findElement(By.linkText("Workflow")).click();
		//matrix-custom-checkbox
        driver.findElement(By.id("matrix-custom-checkbox")).click();
		Thread.sleep(8000);
        driver.findElement(By.cssSelector("input[placeholder='Choose an object...']")).click();
        driver.findElement(By.linkText("KPI Matrix")).click();
        //blank-export-btn*/
	}
}