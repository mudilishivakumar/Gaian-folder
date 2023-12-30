package test.cases;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.repository.LoginPage;
import java.time.Duration;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class SaucedemoValidation {
	public static  String report1;

	
	WebDriver driver;
	LoginPage RHP;
	static ExtentTest test;
	static ExtentReports report;


	@BeforeClass
	public void setUp(){
		 Date d = new Date();
		    String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
		     report1="Sauce Automation test report_"+fileName;
		     System.out.println(report1);
		    report = new ExtentReports(System.getProperty("user.dir") + "" + report1);
		    
		    report = new ExtentReports(System.getProperty("user.dir")+ "/"+report1);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://www.saucedemo.com");
	    
	   driver.manage().window().maximize();

       RHP = PageFactory.initElements(driver, LoginPage.class);
		test = report.startTest("Sauce Lab Login");

     
	}
	

	@AfterMethod
	public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
		//Taking screenshot in case of failure
		if(testResult.getStatus() == ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-" 
					+ Arrays.toString(testResult.getParameters()) +  ".jpg"));	
			}
		
		//Deleting cookies
		driver.manage().deleteAllCookies();
	}
 

    @AfterSuite
    public void suiteTearDown() {
    	report.endTest(test);
        report.flush();
    	driver.quit();
    }


    @Test(priority = 1)

	@Parameters("username")
    public void user(String username) {

        RHP.username(driver).sendKeys(username);
        // Assert that the username input box is clickable and editable
        Assert.assertTrue(RHP.username(driver).isEnabled(), "Username input box is not clickable");
		test.log(LogStatus.PASS, "User Enters UserName",username);

    }
    

    @Test(priority = 2)
    @Parameters("password")
    public void password(String password) {
        RHP.password(driver).sendKeys(password);
        Assert.assertTrue(RHP.password(driver).isEnabled(), "password input box is not clickable");
		test.log(LogStatus.PASS, "User Enters Password",password);

    }

	
	@Test (priority=3)
	public void lgnBtn(){
        Assert.assertTrue(RHP.lgnBtn(driver).isEnabled(), "LoginButton  is  clickable");

		RHP.lgnBtn(driver).click();
		driver.getTitle();
		test.log(LogStatus.PASS, "User Login Succesfully");

	
	}
}
