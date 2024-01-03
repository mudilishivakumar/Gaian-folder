package test.cases;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
		//driver.manage().deleteAllCookies();
	}
 

    @AfterSuite
    public void suiteTearDown() {
    	report.endTest(test);
        report.flush();
    	//driver.quit();
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
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch");

		test.log(LogStatus.PASS, "User Login Succesfully");

	
	}
	@Test(priority = 4)
	public void testDropdownVisibility() {
	    // Assuming RHP.dropDownButton(driver) returns a valid WebElement
	    WebElement dropDownButton = RHP.dropDownButton(driver);

	    // Check if the dropdown button is visible
	    boolean isDropdownVisible = dropDownButton.isDisplayed();
	    assertTrue(isDropdownVisible, "Dropdown is not visible");

	    // Assuming the dropdown values are inside the container with class "product_sort_container"
	    List<WebElement> dropDownValues = dropDownButton.findElements(By.className("product_sort_container"));

	    ArrayList<String> listValues = new ArrayList<String>();
	    for (WebElement value : dropDownValues) {
	        System.out.println("values are: " + value.getText());
	        listValues.add(value.getText());
	        String text = value.getText();

	        listValues.add(text);

	    }

	    // Assuming sortedOrNot is a method you have that checks if the list is sorted
	    boolean sortedOrNot = sortedOrNot(listValues);
	    assertTrue(sortedOrNot, "Dropdown values are not sorted");
	    String dropDownButtonString = String.join(", ", listValues);

	    test.log(LogStatus.PASS, "User Assert dropdown values:"+dropDownButtonString);

	    // Assuming you want to interact with the dropdown using the Select class
//	    Select dropdown = new Select(dropDownButton);
//	    String selectedOptionText = "Price (high to low)";
//
//	    dropdown.selectByVisibleText("Price (high to low)");
//	    test.log(LogStatus.PASS, "User selected dropdown value",selectedOptionText);


	}
	
//	@Test(priority = 5 ,enabled = false))
	@Test(priority = 5)
	public void selectedTheDropDownValue() {
	    WebElement dropDownButton = RHP.dropDownButton(driver);

		 Select dropdown = new Select(dropDownButton);
		    String selectedOptionText = "Price (high to low)";

		    dropdown.selectByVisibleText("Price (high to low)");
		    test.log(LogStatus.PASS, "User selected dropdown value",selectedOptionText);

	}


	public boolean sortedOrNot(ArrayList<String> dropDownValues) {
	    System.out.println("number of values " + dropDownValues.size());
	    for (int i = 0; i < dropDownValues.size() - 1; i++) {
	        int temp = dropDownValues.get(i).compareTo(dropDownValues.get(i + 1));
	        if (temp > 1) {
	            System.out.println("i value " + i);
	            return false;
	        }
	    }
	    return true;
	}
}