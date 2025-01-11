package entityModels;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class Groupcreation extends MyRunner {
	public void add()  {
     	test=report.startTest("PI_click on Add Button");
     	driver.navigate().refresh();
     	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

     WebElement ele=driver.findElement(By.xpath("//div[text()='Add']"));
     if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "Add button is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, " Add button is not visible");
	        //captureScreenshot(driver);       
	}
     if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, " Add button is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "Add button is disabled ");
	        //captureScreenshot(driver);
	       
	}
     
    
     ele.click();
     
     }
   
 public void constructordropdown() throws Throwable{
	 test=report.startTest("PI_click on dropdown");
	 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 	String data=getDataFromExcel("Data", 1, 0);
	 	WebElement ele=driver.findElement(By.className("selectbutton"));
	 	
	 	 if(ele.isDisplayed())
			{
		 		 test.log(LogStatus.PASS, "Dropdown is  visible");
		 		//captureScreenshot(driver);
			}
		else {
			test.log(LogStatus.FAIL, " Dropdown is not visible");
		        //captureScreenshot(driver);       
		}
	  if(ele.isEnabled())
			{
		 		 test.log(LogStatus.PASS, " Dropdown is enabled");
		 		//captureScreenshot(driver);
		
			}
		else {
			test.log(LogStatus.FAIL, "Dropdown is disabled ");
		        //captureScreenshot(driver);
		       
		}
	  
		     ele.click();
		     
		     
	  
	  List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
	  ele1.forEach(s->System.out.println(s.getText()));
	   ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
//	 	for(WebElement i : ele1) {
//	 		String data1=i.getText();
//	 		
// 		if(data==data1) {
//	 			i.click();
//	 			test.log(LogStatus.FAIL, "Group is  clickable");
//	 	 		//captureScreenshot(driver);
// 			break;
//	 		}
//	 		else {
// 			test.log(LogStatus.FAIL, "Group is not clickable");
//	 	 		//captureScreenshot(driver);
//	 		}
 	
	 	}
	 	 
	 
 public void infosection()  {
  	test=report.startTest("PI_click on Info Button");
  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

  WebElement ele=driver.findElement(By.xpath(" //button[contains(text(),'INFO')]"));
  if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "Info button is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, " Info button is not visible");
	        //captureScreenshot(driver);       
	}
  if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, " Info button is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "Info button is disabled ");
	        //captureScreenshot(driver);
	       
	}
  
  
  ele.click();
  
  
	}

public void selectUniverse() throws Throwable {
	test=report.startTest("PI_click on select universe");
 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 	String data=getDataFromExcel("Data", 1, 1);
 	
 	WebElement ele=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]"));
 	
 	 if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "select universe is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, " select universe is not visible");
	        //captureScreenshot(driver);       
	}
  if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, " select universe is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "select universe is disabled ");
	        //captureScreenshot(driver);
	       
	}
  
	  ele.click();
	  
	  
  
  List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
  ele1.forEach(s->System.out.println(s.getText()));
  ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
// 	for(WebElement i : ele) {
// 		String data1=i.getText();
// 		
// 		if(data==data1) {
// 			i.click();
// 			test.log(LogStatus.FAIL, "Support is  clickable");
// 	 		//captureScreenshot(driver);
// 			break;
// 		}
// 		else {
// 			test.log(LogStatus.FAIL, "Support is not clickable");
// 	 		//captureScreenshot(driver);
// 		}
 			
 	//}
 	 
 }
	public void selectschema() throws Throwable {
		test=report.startTest("PI_click on select schema");
	 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 	String data=getDataFromExcel("Data", 1, 2);
	 	WebElement ele=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[3]"));
	 	
	 	 if(ele.isDisplayed())
			{
		 		 test.log(LogStatus.PASS, "select schema is  visible");
		 		//captureScreenshot(driver);
			}
		else {
			test.log(LogStatus.FAIL, " select schema is not visible");
		        //captureScreenshot(driver);       
		}
	  if(ele.isEnabled())
			{
		 		 test.log(LogStatus.PASS, " select schema is enabled");
		 		//captureScreenshot(driver);
		
			}
		else {
			test.log(LogStatus.FAIL, "select schema is disabled ");
		        //captureScreenshot(driver);
		       
		}
	  
		  ele.click();
		  
		  
	  List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
	  ele1.forEach(s->System.out.println(s.getText()));
	  ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
//	 	for(WebElement i : ele) {
//	 		String data1=i.getText();
//	 		
//	 		if(data==data1) {
//	 			i.click();
//	 			test.log(LogStatus.FAIL, "Demo_Computer is  clickable");
//	 	 		//captureScreenshot(driver);
//	 			break;
//	 		}
//	 		else {
//	 			test.log(LogStatus.FAIL, "Democomputer is not clickable");
//	 	 		//captureScreenshot(driver);
//	 		}
	 			
	 	}
	 	 
	
	
	
 
 public void groupName() throws Throwable {
	 test=report.startTest("PI_Enter's group name");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	String data=getDataFromExcel("Data", 1, 3);
   	
   WebElement ele=	driver.findElement(By.cssSelector("[formcontrolname='name']"));
   if(ele.isDisplayed())
	{
		 test.log(LogStatus.PASS, "Group name is  visible");
		//captureScreenshot(driver);
	}
else {
	test.log(LogStatus.FAIL, "Group name is not visible");
       //captureScreenshot(driver);       
}
if(ele.isEnabled())
	{
		 test.log(LogStatus.PASS, " Group name is enabled");
		//captureScreenshot(driver);

	}
else {
	test.log(LogStatus.FAIL, "Group name is disabled ");
       //captureScreenshot(driver);
      
}


ele.click();
ele.sendKeys("abcd");
ele.clear();
ele.sendKeys(data);

 
 }
 public void groupdescription() throws Throwable {
	 test=report.startTest("PI_Enter's group description");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	String data=getDataFromExcel("Data", 1, 4);
   WebElement ele=	driver.findElement(By.cssSelector("[formcontrolname='desc']"));
   if(ele.isDisplayed())
	{
		 test.log(LogStatus.PASS, "Group description is  visible");
		//captureScreenshot(driver);
	}
else {
	test.log(LogStatus.FAIL, "Group description is not visible");
       //captureScreenshot(driver);       
}
if(ele.isEnabled())
	{
		 test.log(LogStatus.PASS, " Group description is enabled");
		//captureScreenshot(driver);

	}
else {
	test.log(LogStatus.FAIL, "Group description is disabled ");
       //captureScreenshot(driver);
      
}


ele.click();
ele.sendKeys("abcd");
ele.clear();

ele.sendKeys(data);
 
 }
 public void queryradiobutton()  {
  	test=report.startTest("PI_click on query radiobutton");
  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

  WebElement ele=driver.findElement(By.xpath("//div[@class='d-flex ng-star-inserted']/div/following-sibling::div/input"));
  if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "Query radiobutton is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, "Query radiobutton is not visible");
	        //captureScreenshot(driver);       
	}
  if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, "Query radiobutton is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "Query radiobutton is disabled ");
	        //captureScreenshot(driver);
	       
	}
  
  
  ele.click();
  
	}
 public void query() throws Throwable {
	 test=report.startTest("PI_Enter's query");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	String data=getDataFromExcel("Data", 1, 5);
   WebElement ele=	driver.findElement(By.cssSelector("[formcontrolname='query']"));
   if(ele.isDisplayed())
	{
		 test.log(LogStatus.PASS, "Query is  visible");
		//captureScreenshot(driver);
	}
else {
	test.log(LogStatus.FAIL, "Query is not visible");
       //captureScreenshot(driver);       
}
if(ele.isEnabled())
	{
		 test.log(LogStatus.PASS, "Query is enabled");
		//captureScreenshot(driver);

	}
else {
	test.log(LogStatus.FAIL, "Query is disabled ");
       //captureScreenshot(driver);
      
}

if(ele.isSelected()) {
ele.click();
ele.sendKeys("abcd");
ele.clear();

ele.sendKeys(data);

}  
 }
 public void aclsection()  {
	  	test=report.startTest("PI_click on ACL Button");
	  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	  WebElement ele=driver.findElement(By.xpath("//button[text()='ACL']"));
	  if(ele.isDisplayed())
			{
		 		 test.log(LogStatus.PASS, "ACL button is  visible");
		 		//captureScreenshot(driver);
			}
		else {
			test.log(LogStatus.FAIL, " ACL button is not visible");
		        //captureScreenshot(driver);       
		}
	  if(ele.isEnabled())
			{
		 		 test.log(LogStatus.PASS, " ACL button is enabled");
		 		//captureScreenshot(driver);
		
			}
		else {
			test.log(LogStatus.FAIL, "ACL button is disabled ");
		        //captureScreenshot(driver);
		       
		}
	  
	 
	  ele.click();
	  
		}
 public void acldropdown() throws Throwable {
 test=report.startTest("PI_click on ACL dropdown");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String data=getDataFromExcel("Data", 1, 6);
	WebElement ele=driver.findElement(By.xpath("//label[contains(text(),'Read Access')]"));
	 if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "ACL dropdown is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, " ACL dropdown is not visible");
	        //captureScreenshot(driver);       
	}
if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, "ACL dropdown is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "ACL dropdown is disabled ");
	        //captureScreenshot(driver);
	       
	}
	  ele.click();
	  
List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
ele1.forEach(s->System.out.println(s.getText()));
ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());

//	for(WebElement i : ele) {
//		String data1=i.getText();
//		
//		if(data==data1) {
//			i.click();
//			test.log(LogStatus.FAIL, "Private is  clickable");
//	 		//captureScreenshot(driver);
//			break;
//		}
//		else {
//			test.log(LogStatus.FAIL, "Private is not clickable");
//	 		//captureScreenshot(driver);
//		}
	//}	
	}
 public void save() {
 test=report.startTest("PI_click on Save Button");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

WebElement ele=driver.findElement(By.xpath("(//div[@class='save col-6 d-flex justify-content-center align-items-center'])"));
if(ele.isDisplayed())
		{
	 		 test.log(LogStatus.PASS, "Save button is  visible");
	 		//captureScreenshot(driver);
		}
	else {
		test.log(LogStatus.FAIL, " Save button is not visible");
	        //captureScreenshot(driver);       
	}
if(ele.isEnabled())
		{
	 		 test.log(LogStatus.PASS, " Save button is enabled");
	 		//captureScreenshot(driver);
	
		}
	else {
		test.log(LogStatus.FAIL, "Save button is disabled ");
	        //captureScreenshot(driver);
	       
	}


ele.click();

 }
 }


