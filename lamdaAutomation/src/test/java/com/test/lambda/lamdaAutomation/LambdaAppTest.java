package com.test.lambda.lamdaAutomation;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

/**
 * Unit test for simple App.
 */
public class LambdaAppTest 
{

	public RemoteWebDriver driver = null;
	String username = "anupam.bhattacharya";
	String accessKey = "7qF6HQpidQs31SZux4y4FGN32aA25K3w3j07bdeKA0570gGPiq";

	private static String strGlobalUserName = "lambda";
	private static String stGlobalPassword = "lambda123";
	private static String stGlobalEmail = "anupam.bhattacharya@hcl.com";
	private static String stGlobalParentURL = "https://www.lambdatest.com/automation-demos/";	
	private static String stGlobalTabURL = "https://www.lambdatest.com/selenium-automation";
	
	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", "Chrome");		
		capabilities.setCapability("version", "87.0");
		capabilities.setCapability("resolution","1024x768");
		capabilities.setCapability("build", "First Test");
		capabilities.setCapability("name", "Sample Test");
		capabilities.setCapability("network", true); 
		capabilities.setCapability("visual", true);
		capabilities.setCapability("video", true);
		capabilities.setCapability("console", true);


		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		capabilities.setCapability(ChromeOptions.CAPABILITY,options);

		try {       
			driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities); 

		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		}
	}

	@Test
	public void testScript() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(stGlobalParentURL);

			WebElement wePageLabel = driver.findElement(By.xpath("//h2[contains(text(),'Login to Selenium Playground')]"));

			if("Login to Selenium Playground" .equals(wePageLabel.getText()))
				assertTrue(true, "Login to Selenium Playground page appeared"); 
			else
				assertTrue(true, "Login to Selenium Playground page not appeared");
			

			WebElement weInputUserName = driver.findElement(By.id("username"));
			WebElement weInputPassword = driver.findElement(By.xpath("//input[@id='password']"));
			WebElement weBtnLogin = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

			weInputUserName.sendKeys(strGlobalUserName);
			weInputPassword.sendKeys(stGlobalPassword);
			weBtnLogin.click();

			Thread.sleep(2000);

			WebElement wePageLogginLabel = driver.findElement(By.xpath("//h1[contains(text(),'Selenium Playground')]"));

			if("Login to Selenium Playground" .equals(wePageLogginLabel.getText()))
				assertTrue(true, "Able to Login to Selenium Playground");
			else
				assertTrue(true, "Not Able to Login to Selenium Playground");


			
			WebDriverWait wait= new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#developer-name")));

			WebElement weInputEmail = driver.findElement(By.cssSelector("#developer-name"));
			weInputEmail.sendKeys(stGlobalEmail );

			Thread.sleep(5000);
			WebElement weBtnPopulate = driver.findElement(By.xpath("//input[@id='populate']"));
			weBtnPopulate.click();		

			Alert alert = driver.switchTo().alert();
			String alertPopupMessage = driver.switchTo().alert().getText();
			System.out.println(alertPopupMessage); 
			Thread.sleep(2000);
			alert.accept();		

			
			WebElement weRadiobtnFreqTime = driver.findElement(By.xpath("//input[@id='6months']"));
			weRadiobtnFreqTime.click();

			WebElement weChkBoxCustomerService = driver.findElement(By.xpath("//input[@id='customer-service']"));
			weChkBoxCustomerService.click();

			WebElement weChkBoxDiscounts = driver.findElement(By.xpath("//input[@id='discounts']"));
			weChkBoxDiscounts.click();

			WebElement weChkBoxDeliveryTime= driver.findElement(By.xpath("//input[@id='delivery-time']"));
			weChkBoxDeliveryTime.click();

			WebElement weChkBoxOthers = driver.findElement(By.xpath("//input[@id='others']"));
			weChkBoxOthers.click();
			
			WebElement weDropdownModePayment = driver.findElement(By.xpath("//select[@id='preferred-payment']"));
			if(weDropdownModePayment.isSelected())
				weDropdownModePayment.clear();

			Select dropdownSelect = new Select(weDropdownModePayment);
			dropdownSelect.selectByVisibleText("Wallets");

			WebElement weChkBoxDeclaration= driver.findElement(By.xpath("//input[@id='tried-ecom']"));
			weChkBoxDeclaration.click();			

			WebElement slider= driver.findElement(By.xpath("//*[@class='disablebar']/div/div/div/div[12]"));
			Actions action= new Actions(driver);
			int width=slider.getSize().width;
			action.dragAndDropBy(slider, width*12, 0).build().perform();

			WebElement toElement = driver.findElement(By.xpath("//div[contains(text(),'9')]"));
			if(toElement.getText().equals("9"))
				assertTrue(true, "9");
			

			Thread.sleep(3000);

			WebElement weFeedback = driver.findElement(By.xpath("//textarea[@id='comments']"));
			weFeedback.sendKeys("Thanks");
			weFeedback.sendKeys(Keys.RETURN);
			weFeedback.sendKeys("Thanks again");

			((JavascriptExecutor)driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1)); driver.navigate().to(stGlobalTabURL);

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			try
			{
				WebElement wbJenkinsimg = 	driver.findElement(By.xpath("//img[@title='Jenkins']"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@title='Jenkins']")));
				driver.findElement(By.cssSelector("#img")).click();
			}
			catch(NoSuchElementException e)
			{
				Thread.sleep(4000);
				String imageURL= System.getProperty("user.dir")+"\\Resource\\Jenkins.png";
				

				driver.switchTo().window(tabs.get(0));

				String autoIT= System.getProperty("user.dir")+"\\Resource\\autoIT.exe";
				Thread.sleep(2000);
				ProcessBuilder pb= new ProcessBuilder(autoIT,imageURL);
				{
					pb.start();
					Thread.sleep(4000);
				}


				WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submit-button']"));
				btnSubmit.click();
				Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You have successfully submitted the form.')]")).getText().contains("successfully submitted the form"));
				Thread.sleep(3000);
				driver.quit();	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
