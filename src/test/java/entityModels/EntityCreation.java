package entityModels;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.TabableView;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import groovyjarjarantlr4.v4.codegen.model.Action;

public class EntityCreation extends MyRunner {
	
	//info
	
	public void addButton() throws InterruptedException {
		//Add Button
		
		  test=report.startTest("Click on add button");
		
		
		
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
    driver.navigate().refresh();
    WebElement buttonAdd =driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']"));
	  
		  if( buttonAdd.isDisplayed()) {
		
			  test.log(LogStatus.PASS, "Add button  is visible");
			  //captureScreenshot(driver);	
			
			  if( buttonAdd.isEnabled()) {
				  
				  test.log(LogStatus.PASS, "Add button  is enabled"); 
				  //captureScreenshot(driver);
				  test.log(LogStatus.PASS, "Click on Add Button");
				  buttonAdd.click();		
				  //captureScreenshot(driver);
		  }
			  else {
				
				  test.log(LogStatus.FAIL, "Add button is not enabled");
				  //captureScreenshot(driver);
			  }
	     }
		  else {
			  test.log(LogStatus.FAIL, "Add button is not visible");
			  //captureScreenshot(driver);
		  }
		  
	}
	public void entityModel() throws Exception {

		// construct dropDown validation and selection
		
		test=report.startTest("Select construct as a entity model");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	WebElement btnConstructArrow=driver.findElement(By.xpath("//div[@class='selectbutton']/."));
		 
		  if(btnConstructArrow.isDisplayed()) {
			  test.log(LogStatus.PASS, "construct dropDown arrow  is visible");  
			  //captureScreenshot(driver);
			  
			  if(btnConstructArrow.isEnabled()) {
			
				  test.log(LogStatus.PASS, "construct dropDown arrow is enable");  
				  //captureScreenshot(driver);
				  btnConstructArrow.click();
				  
				  test.log(LogStatus.PASS, "Click on arrow button");
				  //captureScreenshot(driver);
				  
				  driver.findElement(By.xpath("//div[@class='selectbutton']/.")).click();
				
					test.log(LogStatus.PASS, "Select construct as a entity model");
					 //captureScreenshot(driver);
					 
		
					List<WebElement> elementsList= driver.findElements(By.xpath("//div[@class='dropdown-item ng-star-inserted']"));

					 List<WebElement> ListofconstructDropdown = driver.findElements(By.xpath("//div[@class='dropdown-item ng-star-inserted']"));
					  elementsList.forEach(s->System.out.println(s.getText()));
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					 String data=getDataFromExcel("Data1",1,0);
					 elementsList.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
					 
					 System.out.println(data);
					 test.log(LogStatus.PASS, "Select construct as a entity model is matched"+"-"+data);
					 //captureScreenshot(driver);
//					// identify dropdown
//					  WebElement d = driver.findElement(By.xpath("//div[@class='dropdown-item ng-star-inserted']"));
//				          //Select class to get options in dropdown
//                                 Select l = new Select(d);
//					  List<WebElement> m = l.getOptions();
//					  System.out.println("Drodown list items are: ");
//					 //iterate through options till list size
//                            for (int j = 0; j < m.size(); j++) {
//					 String s = m.get(j).getText();
//                              System.out.println(s);
//                              
//                              }
			  }
							 
			  else {
			  test.log(LogStatus.FAIL, "constructs  dropDown arrow is not enable");
			  //captureScreenshot(driver);
		  }
	            }
	          else {
		  
			  test.log(LogStatus.FAIL, "constructs dropDown arrow is not visible");
			  //captureScreenshot(driver);
		  }
		  
}
  public void selectUniverse() throws Exception {
	  
	  //select universe
	  
	  test=report.startTest("Select Universe");
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	  
	   WebElement ele= driver.findElement(By.xpath("//app-search-select[@name='universeName']//div[@class='selectbutton']"));
	  if(ele.isDisplayed())
	       {
	  
	 test.log(LogStatus.PASS, "Universe button is visible");
	   //captureScreenshot(driver);
	   if(ele.isEnabled())
	   {
	    test.log(LogStatus.PASS, "Universe button is enabled");
	   //captureScreenshot(driver);
	   
	   
	   ele.click();
	   WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search']"));
	  	          String data1=getDataFromExcel("Data1", 14, 0);
	  	  search.sendKeys(data1);
	  	  List<WebElement> multipleElement=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
	  	  multipleElement.forEach(s->System.out.println(s.getText()));
	  	   String data2=getDataFromExcel("Data1",15, 0);
	  	  multipleElement.stream().filter(s->s.getText().equalsIgnoreCase(data2)).forEach(S->S.click());
	  //	driver.findElement(By.xpath("//div[normalize-space()='Gaian-freshers']"));
	  	          test.log(LogStatus.PASS, "Universe Selected");
	  	           //captureScreenshot(driver);
	       }
	 else {
		 test.log(LogStatus.FAIL, "Universe button is not enabled");
	   //captureScreenshot(driver);
	  }
  }
            else
           {
                test.log(LogStatus.FAIL, "Universe button is not visible");
                    //captureScreenshot(driver);
	  }
               
}

  
	public void enterEntityModuleName() throws InterruptedException, EncryptedDocumentException, IOException {
		
		// Write Entity Module Name 
		
		test=report.startTest("Enter a name of an entity model");
		
		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		 
		 WebElement textboxEntityModelName =driver.findElement(By.cssSelector(":nth-child(2) > .inputWithStar > .inputfield']"));
		  if(textboxEntityModelName .isDisplayed()) {
		
			  test.log(LogStatus.PASS, "Enter entity model name field is visible");
			  //captureScreenshot(driver);	
			 
			  if(textboxEntityModelName .isEnabled()) {
				  
				  test.log(LogStatus.PASS, "Enter entity model name field  is enabled"); 
				  //captureScreenshot(driver);
				  
				  
				//  driver.findElement(By.cssSelector("input[placeholder='Enter here']")).clear();
				  
				  
				  driver.findElement(By.cssSelector(":nth-child(2) > .inputWithStar > .inputfield")).click();
				  test.log(LogStatus.PASS, "Click on Enter entity model name field");
				  //captureScreenshot(driver);
				  
			
				  FileInputStream fsi = new FileInputStream("data/testData.xlsx");
				  
					Workbook book = WorkbookFactory.create(fsi);
					
					Sheet sh = book.getSheet("PI");
					
					DataFormatter df = new DataFormatter();
					
					String entityname = df.formatCellValue((sh.getRow(0).getCell(1)));
					
					driver.findElement(By.cssSelector("input[placeholder='Enter here']")).sendKeys(entityname);
					
					
					test.log(LogStatus.PASS, "Enter a name of an entity model");
					 //captureScreenshot(driver);
				 
		  }
			  else {
				  
				  test.log(LogStatus.FAIL, "Enter entity model name field is not enabled");
				  //captureScreenshot(driver);
			  }
	     }
		  else {
			
			  test.log(LogStatus.FAIL, "Enter entity model name field not enabled");
			  //captureScreenshot(driver);
		  }
		 
		}
		
		public void enterEntityModuleDescription() throws InterruptedException, EncryptedDocumentException, IOException {
			
			// Enter entity model name Description
			
			test=report.startTest(" Enter entity model name Description ");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		
		 
		 WebElement txtboxEntityModuleNameDescription =driver.findElement(By.xpath("//textarea[@placeholder='Enter here']"));
		 
		  if(txtboxEntityModuleNameDescription.isDisplayed()) {
		
			  test.log(LogStatus.PASS, "Enter entity model name Description field  is visible");
			  //captureScreenshot(driver);	
			  
			  if(txtboxEntityModuleNameDescription.isEnabled()) {
				  
				  
				  test.log(LogStatus.PASS, "Enter entity model name Description field  enabled"); 
				  //captureScreenshot(driver);
				  
				  
				  
				  driver.findElement(By.cssSelector("input[placeholder='Enter here']")).click();	
				  
				  test.log(LogStatus.PASS, "Click on Enter entity model name Description field ");
				  //captureScreenshot(driver);
				  
				  
				  driver.findElement(By.cssSelector("input[placeholder='Enter here']")).clear();
				  
				  FileInputStream fsi = new FileInputStream("data/testData.xlsx");
				  
					Workbook book = WorkbookFactory.create(fsi);
					
					Sheet sh = book.getSheet("PI");
					
					DataFormatter df = new DataFormatter();
					
					String entityDescription = df.formatCellValue((sh.getRow(1).getCell(1)));
					
					driver.findElement(By.xpath("//textarea[@placeholder='Enter here']")).sendKeys(entityDescription);
					
					test.log(LogStatus.PASS, "Enter entity model name Description"); 
					 //captureScreenshot(driver);
					 
				 
		  }
			  else {
				 
				  test.log(LogStatus.FAIL, "Enter entity model name Description field is not enabled");
				  //captureScreenshot(driver);
			  }
	     }
		  else {
			
			  test.log(LogStatus.FAIL, "Enter entity model name Description field is not visible");
			  //captureScreenshot(driver);
		  }	 
		 
		}
		
			
	  public void uplodefile() throws InterruptedException {
		  
		  //Upload File
		  
		  test=report.startTest(" Upload File ");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		  
		WebElement UploadIcon=driver.findElement(By.id("upload_file"));
		  if(UploadIcon.isDisplayed())
		  {
			  test.log(LogStatus.PASS, "Upload element icon is visible"); 
			  //captureScreenshot(driver);
			  
			  
			  if(UploadIcon.isEnabled()) {
				  
				  
				  test.log(LogStatus.PASS, "upload file select icon is enabled"); 
				  //captureScreenshot(driver);
				
				  
				  driver.findElement(By.id("upload_file")).click();	
				  test.log(LogStatus.PASS, " click on uplode file select icon");
				  
				  //captureScreenshot(driver);
	  WebElement UploadElement=driver.findElement(By.id("upload_file"));
					
					 File file = new File("/PI_UI/data/Q1.png");
					 
					 System.out.println(file.getAbsolutePath());
					 
			         UploadElement.sendKeys(file.getAbsolutePath());
					
					test=report.startTest("Select file");
					//captureScreenshot(driver); 
					
//				  String file = System.getProperty("user.dir");
//				  test.log(LogStatus.PASS, " file selected "+ file);
//				  
				  
//			       String file = getClass().getClassLoader().getResource("Q1.png").getFile();
//			       UploadElement.sendKeys(file);
//				  
					  
					  
					  
		  }
			  else {
				  
				  test.log(LogStatus.FAIL, "upload file select icon is enabled");
				  //captureScreenshot(driver);
			  }
			  
	  }
		  else {
			  
			  test.log(LogStatus.FAIL, "Upload element icon is not visible");
			  //captureScreenshot(driver);
		  }
		  
	  }
		
	  public void enterAttributeName() throws InterruptedException, EncryptedDocumentException, IOException {

		  //Write Atribute Name 
		  
			test=report.startTest("Enter a atrribute name");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
			WebElement enterAttributeName=driver.findElement(By.xpath("//input[@formcontrolname='name']"));
			  if(enterAttributeName.isDisplayed()) {
			
				  test.log(LogStatus.PASS, "Enter attribute field is visible");
				  //captureScreenshot(driver);	
				 
				  if(enterAttributeName.isEnabled()) {
					  test=report.startTest("Enter attribute  field is enabled");
					  test.log(LogStatus.PASS, "Enter attribute  field  is enabled"); 
					  //captureScreenshot(driver);
					  test.log(LogStatus.PASS, "Click on enter attribute  field");
					  driver.findElement(By.xpath("//input[@formcontrolname='name']")).click();	
					  //captureScreenshot(driver);
					  driver.findElement(By.xpath("//input[@formcontrolname='name']")).clear();
					  FileInputStream fsi = new FileInputStream("data\\testData.xlsx");
					  
						Workbook book = WorkbookFactory.create(fsi);
						
						Sheet sh = book.getSheet("PI");
						
						DataFormatter df = new DataFormatter();
						
						String entityname = df.formatCellValue((sh.getRow(2).getCell(1)));
						
						driver.findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys(entityname);
					
						test.log(LogStatus.PASS, "Enter a attribute name");
						 //captureScreenshot(driver);
					  
				  }
				  else {
					  test.log(LogStatus.FAIL, "Enter atrribute name is not enabled");
					  //captureScreenshot(driver);
				  }
		     }
			  else {
				  test.log(LogStatus.FAIL, "Enter atrribute name field is not visible");
				  //captureScreenshot(driver);
			  }	 
			 
			
			  }
		  
	 
	  
	  public void enter_defaultvalue() throws InterruptedException, EncryptedDocumentException, IOException {

		  
		  //Enter Default value
		  
			test=report.startTest("Enter a default value");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
			WebElement enter_defaultvalue=driver.findElement(By.xpath("//label[normalize-space()='Default Value']"));
			  if(enter_defaultvalue.isDisplayed()) {
			
				  test.log(LogStatus.PASS, "Enter default value field is visible");
				  //captureScreenshot(driver);	
				 
				  if(enter_defaultvalue.isEnabled()) {
					  
				  
					  
					  test.log(LogStatus.PASS, "Enter default value  field is enabled"); 
					  //captureScreenshot(driver);
					  test.log(LogStatus.PASS, "Click on default name  field");
					  driver.findElement(By.xpath("//input[@formcontrolname='name']")).click();	
					  //captureScreenshot(driver);
					  driver.findElement(By.xpath("//input[@formcontrolname='default']")).clear();
					  
					  
					  FileInputStream fsi = new FileInputStream("data\\testData.xlsx");
					  
						Workbook book = WorkbookFactory.create(fsi);
						
						Sheet sh = book.getSheet("PI");
						
						DataFormatter df = new DataFormatter();
						
						String entityname = df.formatCellValue((sh.getRow(3).getCell(1)));
						
						driver.findElement(By.xpath("//input[@formcontrolname='default']")).sendKeys(entityname);
						
						
						test.log(LogStatus.PASS, "Enter a default value");
						 //captureScreenshot(driver);
					  
				  }
				  else {
					 
					  test.log(LogStatus.FAIL, " Enter  default value field is not enabled ");
					  //captureScreenshot(driver);
				  }
	  }
			  else {
				 
				  test.log(LogStatus.FAIL, " Enter default name value is not visible");
				  //captureScreenshot(driver);
			  }
			  WebElement source = driver.findElement(By.xpath("//input[@formcontrolname='name']"));
			//  WebElement Destination = driver.findElement(By.xpath("//input[@formcontrolname='default']"));
			 // Actions action = new Actions(driver);
			//  action.dragAndDropBy(source, 100,0).build().perform();
			  
			  test.log(LogStatus.FAIL, " Enter default value field is not visible");
			  
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  js.executeScript("window.scrollBy(0,300);");
			  //captureScreenshot(driver);
	  
	  }
	  
	  public  void requiredDropdown() throws Exception
	  {
		  // Selection of Required Dropdown
		  
		  test=report.startTest("Select required value");
	  
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
		WebElement requiredDropdown = driver.findElement(By.xpath("//app-select[@name='required']//div[@class='selectbutton']"));
		  if(requiredDropdown.isDisplayed()) {
		
			  test.log(LogStatus.PASS, "Enter required value button is visible");
			  //captureScreenshot(driver);	
			  
			  if(requiredDropdown.isEnabled()) {
				  
			  
				  
				  test.log(LogStatus.PASS, "Select required value button is enabled"); 
				  //captureScreenshot(driver);
				  requiredDropdown.click();	
				  test.log(LogStatus.PASS, "Click on required value button");
				  //captureScreenshot(driver);
				  
					
					 driver.findElement(By.xpath("//div[@class='selectcontent2 dropdown-menu show']//div[1]")).click();
					
					 test.log(LogStatus.PASS, "required value is Selected  ");
					 //captureScreenshot(driver);
				  
					
					
			  }
			  else {
				 
				  test.log(LogStatus.FAIL, " Enter required value button is not enabled ");
				  //captureScreenshot(driver);
			  }
}
		  else {
			 
			  test.log(LogStatus.FAIL, "Enter required value button  is not visible");
			  //captureScreenshot(driver);
		  }  
	  
	  }
	  
	  public  void dataTypeSelection() throws Exception
	  {
		  // Data Type Selection
		  
		  test=report.startTest("Select data type");
	  
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
		WebElement btndatatypeDropdown = driver.findElement(By.xpath("//app-select[@name='type']//div[@class='selectbutton']"));
		  if(btndatatypeDropdown.isDisplayed()) {
		
			  test.log(LogStatus.PASS, "Select data type arrow button is visible");
			  //captureScreenshot(driver);	
			  
			  if(btndatatypeDropdown.isEnabled()) {
				  
			  
				  
				  test.log(LogStatus.PASS, "Select a Data type arrow button is enable"); 
				  //captureScreenshot(driver);
				  
				  btndatatypeDropdown.click();	
				  test.log(LogStatus.PASS, "Click on Select a Data type arrow button");
				  //captureScreenshot(driver);
				  
					List<WebElement> elementsList= driver.findElements(By.xpath("//app-select[@name='type']//div[@class='selectbutton']"));
					
					 List<WebElement> ListofrequiredValue = driver.findElements(By.xpath("//app-select[@name='type']//div[@class='selectbutton']"));
					  elementsList.forEach(s->System.out.println(s.getText()));
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					 String data=getDataFromExcel("Data1",23,0);
					 elementsList.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
					 
					
					 
					 test.log(LogStatus.PASS, " Data type is Selected");
					 //captureScreenshot(driver);			
					
			  }
			  else {
				 
				  test.log(LogStatus.FAIL, " Select a Data type arrow button is not enabled ");
				  //captureScreenshot(driver);
			  }
}
		  else {
			 
			  test.log(LogStatus.FAIL, "Select a Data type arrow button is not visible");
			  //captureScreenshot(driver);
		  }  
	  
	  } 
	  
	  
	  public  void primaryKeySelection() throws Exception{
		  
		  // primary Key Selection
		  
		  test=report.startTest("Primary key selection");
		  
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
		 
		//WebElement dropdownPrimarykeyselection=  driver.findElement(By.xpath("//img[@class='imagerotated']"));
		WebElement dropdownPrimarykeyArrow =driver.findElement(By.xpath("//div[@aria-expanded='false']//img[@class='image']"));
		
		if(dropdownPrimarykeyArrow.isDisplayed()) {
			
			  test.log(LogStatus.PASS, "Primary key selection dropDown is visible");
			  //captureScreenshot(driver);	
			  
			  if(dropdownPrimarykeyArrow.isEnabled()) {
				  dropdownPrimarykeyArrow.click();
				  
				  test.log(LogStatus.PASS, "Primary key selection dropDown is enable"); 
				  //captureScreenshot(driver);
					 WebElement chkPrimarykey = driver.findElement(By.xpath("//input[@type='checkbox']"));
					 chkPrimarykey.click();

				  if(chkPrimarykey.isSelected()) { 
				 
				 test.log(LogStatus.PASS, "Primary key selected"); 
				  //captureScreenshot(driver);
				  }
			  
				  else 
					  test.log(LogStatus.PASS, "Primary key is not  selected"); 
					  //captureScreenshot(driver);  
				  }  
			  
			  else 
				 
				  test.log(LogStatus.FAIL, "Primary key selection dropDown is not enabled ");
				  //captureScreenshot(driver);
			  
				  }
		  else 
			 
			  test.log(LogStatus.FAIL, "Primary key selection dropDown is not visible");
			  //captureScreenshot(driver);
		}  
	  
	  
	  
	 
	  //ACL
	  
	  public void aCLbutton() throws InterruptedException {
		  test=report.startTest("click on ACL button");
			
	// click on ACL button	  
		  
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
	   
	    WebElement buttonACL =driver.findElement(By.xpath("//button[normalize-space()='ACL']"));
		  
			  if( buttonACL.isDisplayed()) {
			
				  test.log(LogStatus.PASS, "ACL button  is visible");
				  //captureScreenshot(driver);	
				
				  if( buttonACL.isEnabled()) {
					  
					  test.log(LogStatus.PASS, "ACL button  is enabled"); 
					  //captureScreenshot(driver);
					  test.log(LogStatus.PASS, "Click on ACL Button");
					  buttonACL.click();		
					  //captureScreenshot(driver);
			  }
				  else {
					  test.log(LogStatus.FAIL, "ACL button is not enable");
					  //captureScreenshot(driver);
				  }
		     }
			  else {
				  test.log(LogStatus.FAIL, "ACL button is not visible");
				  //captureScreenshot(driver);
			  }
			  
		}

	  public void entityModelReadAccess() throws Exception {
		  
		  // Entity model read access selection
		  
			test=report.startTest("Entity model read access");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement btnEMRA=driver.findElement(By.xpath("//app-select[@name='schemaReadAccess']//div[@class='selectbutton']"));
			

			if(btnEMRA.isDisplayed()) {
			 	   test.log(LogStatus.PASS,"Entity Model Read Access is visible");
			 	   //captureScreenshot(driver);
			 	  if(btnEMRA.isEnabled()) {
				 	   test.log(LogStatus.PASS,"Entity Model Read Access is Enabled");
				 	   //captureScreenshot(driver);
				 	   
				 	   
				 	   
				 	  driver.findElement(By.xpath("//app-select[@name='schemaReadAccess']//div[@class='selectbutton']")).click();
						List<WebElement> dropDownEMRA=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
						dropDownEMRA.forEach(s->System.out.println(s.getText()));
						String data=getDataFromExcel("Data1",29, 0);
						dropDownEMRA.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
						test.log(LogStatus.PASS,"Data Read Access is visible");
						
				 	    }
				    else
				    {
				 	   test.log(LogStatus.FAIL," Selected access for Entity model ");
				 	   //captureScreenshot(driver);
				    }
			}
			    else
			    {   test.log(LogStatus.FAIL,"Entity model read access  is not visible");
			    //captureScreenshot(driver);
			    }
			     
		
		 
		    }
	 
	  
	  
	  public void dataReadAccess() throws Exception {
		  
		  //Data Read Access selection
		  
			test=report.startTest("Data read access");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement btnEMRA=driver.findElement(By.xpath("//app-select[@name='schemaReadAccess']//div[@class='selectbutton']"));
			

			if(btnEMRA.isDisplayed()) {
			 	   test.log(LogStatus.PASS,"Data Read Access is visible");
			 	   //captureScreenshot(driver);
			 	  if(btnEMRA.isEnabled()) {
				 	   test.log(LogStatus.PASS,"Data  Read Access is Enabled");
				 	   //captureScreenshot(driver);
				 	   
				 	   
				 	   
				 	  driver.findElement(By.xpath("//app-select[@name='schemaReadAccess']//div[@class='selectbutton']")).click();
						List<WebElement> dropDownDRA=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
						dropDownDRA.forEach(s->System.out.println(s.getText()));
						String data=getDataFromExcel("Data1",29, 0);
						dropDownDRA.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
						test.log(LogStatus.PASS,"Data read access is selected");
					 	   //captureScreenshot(driver);
				 	    }
				    else
				    {
				 	   test.log(LogStatus.FAIL,"Data Read read Access is not enabled");
				 	   //captureScreenshot(driver);
				    }
			}
			    else
			    {   test.log(LogStatus.FAIL,"Data Read read access  is not visible");
			    //captureScreenshot(driver);
			    }
			     
		
		 
	  }
	 
	  public void dataWriteAccess() throws Exception {
		  
		  // Data Write Access selection
		  
			test=report.startTest("Data write access");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement btnEMDWA=driver.findElement(By.xpath("//app-select[@name='dataWriteAccess']//img[@class='image']"));
			

			if(btnEMDWA.isDisplayed()) {
			 	   test.log(LogStatus.PASS,"Data Read access is visible");
			 	   //captureScreenshot(driver);
			 	  if(btnEMDWA.isEnabled()) {
				 	   test.log(LogStatus.PASS,"Data  Read access is Enabled");
				 	   //captureScreenshot(driver);
				 	   
				 	   
				 	   
				 	  driver.findElement(By.xpath("//app-select[@name='dataWriteAccess']//img[@class='image']")).click();
						List<WebElement> dropDowndataWriteAccess=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
						dropDowndataWriteAccess.forEach(s->System.out.println(s.getText()));
						String data=getDataFromExcel("Data1",29, 0);
						dropDowndataWriteAccess.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
						test.log(LogStatus.PASS,"data write access is selected");
					 	   //captureScreenshot(driver);
				 	    }
				    else
				    {
				 	   test.log(LogStatus.FAIL,"Data write read access is not enabled");
				 	   //captureScreenshot(driver);
				    }
			}
			    else
			    {   test.log(LogStatus.FAIL,"Data Read read access  is not visible");
			    //captureScreenshot(driver);
			    }
			     
		
		 
		    }

	  
	  
	  public void visiblityAccess() throws Exception {
		  
		  // Visibility Access selection
		  
			test=report.startTest("PI_visiblity access ");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement btnEMVA=driver.findElement(By.xpath("//app-select[@name='visibility']//div[@class='selectbutton']"));
			

			if(btnEMVA.isDisplayed()) {
			 	   test.log(LogStatus.PASS,"Visiblity access is visible");
			 	   //captureScreenshot(driver);
			 	  if(btnEMVA.isEnabled()) {
				 	   test.log(LogStatus.PASS,"Visiblity access is Enabled");
				 	   //captureScreenshot(driver);
				 	   
				 	   
				 	   
				 	  driver.findElement(By.xpath("//app-select[@name='visibility']//div[@class='selectbutton']")).click();
						List<WebElement> rdacpblc=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
						rdacpblc.forEach(s->System.out.println(s.getText()));
						String data=getDataFromExcel("Data1",29, 0);
						rdacpblc.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
						test.log(LogStatus.PASS,"Visiblity access is selected");
					 	   //captureScreenshot(driver);
				 	    }
				    else
				    {
				 	   test.log(LogStatus.FAIL,"Visiblity access is not enabled");
				 	   //captureScreenshot(driver);
				    }
			}
			    else
			    {   test.log(LogStatus.FAIL,"Visiblity access is not visible");
			    //captureScreenshot(driver);
			    }
	  }
	  
	  public void saveButton() throws Exception {
		  
		  // Save 
		  
			 test=report.startTest("Entity model Save");
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			  
			  
			 WebElement btnSave=driver.findElement(By.xpath("//div[@class='save col-6 d-flex justify-content-center align-items-center']"));
			 if(btnSave.isDisplayed()) {
			     test.log(LogStatus.PASS,"Save button is visible");
			     //captureScreenshot(driver);
			    if(btnSave.isEnabled()) {
			    test.log(LogStatus.PASS,"Save button is Enabled");
			   //captureScreenshot(driver);
			   
			   
			   btnSave.click();
			   
			  }
			  else
			  {
			     test.log(LogStatus.FAIL,"Save button is not Enabled");
			   //captureScreenshot(driver);
			  }
			 }
			  else
			  {
			     test.log(LogStatus.FAIL,"Save button is not visible");
			     //captureScreenshot(driver); 
			  }
			 driver.close();			  }
	 
}
	  
	  
	  
	  
	  
	  