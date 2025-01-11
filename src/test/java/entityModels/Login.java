package entityModels;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.OutputType;


public class Login extends MyRunner {
	@BeforeClass
	public void launchBrowser() {
		test=report.startTest("PI_launch chrome browser");

		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Enable headless mode

		WebDriverManager.chromedriver().setup();
//	    driver = new ChromeDriver();
      driver = new ChromeDriver(options);


		 test.log(LogStatus.PASS, "Lunch Chrome Browser");

	}
	public void navigateURL() {
		test=report.startTest("PI_lunch PI URL");

		driver.get("https://pi.gaiansolutions.com/login");

		driver.manage().window().maximize();
		 test.log(LogStatus.PASS, "lunch PI URL","URL: " + driver.getCurrentUrl());
	        //captureScreenshot(driver);


		}
	
	public void enterUsername() throws InterruptedException {
		test=report.startTest("PI_Enter's UserName");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.cssSelector("[placeholder='Enter username']")).sendKeys("sadiq_gaian@gatestautomation.com");
		 test.log(LogStatus.PASS, "Enter's UserName");
	        //captureScreenshot(driver);


	}
	
	public void enterPassword() throws InterruptedException {
		test=report.startTest("PI_Enter's Password");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.cssSelector("[placeholder='Enter password']")).sendKeys("Gaian@123");
		 test.log(LogStatus.PASS, "Enter's Password");
	        //captureScreenshot(driver);

	}
	
	public void clickLogin() throws InterruptedException {
		test=report.startTest("PI_click on Login Button");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.cssSelector("[value='Login']")).click();
		 test.log(LogStatus.PASS, "clicks on login Button");
	        //captureScreenshot(driver);

//         driver.close();
	}
	
	public void pageTitle() throws InterruptedException {
		test=report.startTest("PI_pageTitle");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		 String actualTitle = driver.getTitle();
	        System.out.println("The title of the web page is " + actualTitle);
	        Assert.assertEquals(actualTitle, actualTitle);

	        String expectedTitle = "Pascal Intelligence";
	        Assert.assertEquals(expectedTitle, actualTitle);
	        test.log(LogStatus.PASS, "Login Title", "expectedTitle: " + actualTitle);

//	        driver.quit();
	 		 test.log(LogStatus.PASS, "Verfies Page Title");
	        //captureScreenshot(driver);
	        //driver.close();
	}
//	 public static void captureScreenshot(WebDriver driver) {
//	        // Capture the screenshot and add it to the ExtentReports log
//	        if (driver instanceof TakesScreenshot) {
//	            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
//	            String screenshotBase64 = screenshotDriver.getScreenshotAs(OutputType.BASE64);
//	            test.log(LogStatus.INFO, "Screenshot:",
//	                    test.addBase64ScreenShot("data:image/png;base64," + screenshotBase64));
//	        }
//      }
}


