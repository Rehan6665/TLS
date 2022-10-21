package tls_backend;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EmbraceBackendB2B {
	
	
	    String firstName = "Rockybhai";
		String lastName = "kgf";
		String subscriptionname = "Sahyadri Hospital Subscription(Don't Delete)";
		String nursingHome = "Sahyadri Hospital";
		String email = "Himanshuuugmail.com";
		String pass = "Mogamo@123";
		String contactNumber = "AAA";
		String dob = "12-02-2001";
		String gender ="Male";
		String position = "Nurse";
		String city = "Pune";
		String state = "maharashtra";
		String zipCode = "411028";
		
		public static WebDriver driver=null;
		
	@BeforeSuite(description = "Open TLS backend in chrome")
	public void launchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}
	
	@Test(priority = 1,description = "Login Test")
	public void login()
	{
		
		driver.get("https://tranquility-management.azurewebsites.net/");
		driver.manage().window().maximize();
		Reporter.log("Login started<br>");
		driver.findElement(By.id("Email")).sendKeys("Admin@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[3]/div/input")).click();
		String invalidLogin = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[1]/ul/li")).getText();
		Assert.assertEquals("actual_invalidLogin", invalidLogin);
		Reporter.log("Logged in successfully<br>");
		
	}
	
	@Test(priority = 2,description = "Create User")
	public void createUser()
	{
		
		
		Reporter.log("Creating user started<br>");
		driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul[1]/li[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul[1]/li[1]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("/html/body/div[2]/p/a")).click();
		

	   
		Reporter.log("Entering required data for user<br>");
	
		
		if(nursingHome.equals("Sahyadri Hospital"))
		{
		WebElement nhome = driver.findElement(By.xpath("//*[@id=\"HospitalID\"]"));
		Select select_nhome = new Select(nhome);
		
		select_nhome.selectByVisibleText(nursingHome);
		
		
		}
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"ConfirmEmail\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"PhoneNumber\"]")).sendKeys(contactNumber);
		driver.findElement(By.xpath("//*[@id=\"DateOfBirth\"]")).sendKeys(dob);
		
		
		
		
		WebElement gender_dropDown =  driver.findElement(By.id("Gender"));
		Select select_gdropDown = new Select(gender_dropDown);
		if(gender.equals("Male"))
		{
			
			select_gdropDown.selectByValue("1");
		}
		else {select_gdropDown.selectByValue("2");}
		

		WebElement position_dropDown =  driver.findElement(By.xpath("//*[@id=\"Type\"]"));
		Select select_pdropDown = new Select(position_dropDown);
		
		if(position.equals("Doctor"))
		{
			
			select_pdropDown.selectByValue("1");
		}
		else if(position.equals("Nurse"))
		
		{
			select_pdropDown.selectByValue("2");
		}
		else if(position.equals("Attendant")){
			select_pdropDown.selectByValue("3");
		}
		
		else
		{
			
			select_gdropDown.selectByValue("4");
		}
		
		driver.findElement(By.xpath("//*[@id=\"City\"]")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id=\"State\"]")).sendKeys(state);
		driver.findElement(By.xpath("//*[@id=\"Zipcode\"]")).sendKeys(zipCode);
		
		
		
		Reporter.log("Entered all mandatory fields<br>");
		
		driver.findElement(By.xpath("//*[@id=\"main-body\"]/form/div/div[25]/div/input")).click();
		
		
		Reporter.log("Created user Successfully");
	
		
	}
	
	
	@Test(priority = 3,description = "Linking user to the subscription",enabled = false)
	public void addUserToSubsription()
	{
		Reporter.log("Going to subscription page<br>");
		Reporter.log("Assigning newly created user to the subscription<br>");
		driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul[1]/li[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul[1]/li[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"SearchString\"]")).sendKeys(subscriptionname);
		driver.findElement(By.xpath("//*[@id=\"main-body\"]/form/div[1]/div[1]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"main-body\"]/table/tbody/tr/td[6]/a")).click();
		
		String fullName = firstName +" "+ lastName ;
		Select s = new Select(driver.findElement(By.id("Users")));
	
		
		if(s.isMultiple()) {
			
			s.selectByVisibleText(fullName);
		}
		
		driver.findElement(By.xpath("//*[@id=\"main-body\"]/div[2]/div/form/div[2]/div[2]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"main-body\"]/div[3]/div[2]/div/div[1]/a"));
		Reporter.log("Subsription assigned successfully");
		
		
	}
	
	@AfterSuite(description = "Closing the browser")
	public void closeBrowser()
	{
		
		driver.close();
	}
	

}

