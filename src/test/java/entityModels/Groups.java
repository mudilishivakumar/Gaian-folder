package entityModels;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class Groups extends MyRunner{
	public void add() throws Throwable  {
		
		 test=report.startTest("PI_visible add icon");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean addiconvis= driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).isDisplayed();
		System.out.println("add button  visible");
		if(addiconvis) {
			test.log(LogStatus.PASS, "add button  visible");
			 //captureScreenshot(driver); 

		}else {
			test.log(LogStatus.FAIL, "add button not visible");
			 //captureScreenshot(driver); 

		}
		
		//assertionMethod(addiconvis, true);
		 //assertEquals(addiconvis, true);
		//test.log(LogStatus.PASS, "visible add icon");
		// //captureScreenshot(driver); 
}




public void enableadd() throws Throwable {
	
	
	test=report.startTest("PI_enable add icon");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	 boolean enableadd= driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).isEnabled();
   System.out.println("addicon is enable");
  //assertionMethod(enableadd, true);
	if(enableadd) {
		test.log(LogStatus.PASS, "add icon is enable");
		//captureScreenshot(driver); 
	}else 
	{
   test.log(LogStatus.FAIL, "add icon is not enable");
   //captureScreenshot(driver);
	}
  // //captureScreenshot(driver); 
}
	
public void clickadd() throws Throwable {
	
	
	test=report.startTest("PI_click on add Button");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).click();
   System.out.println("clicks add icon");
   //assertionMethod(enableadd, true);
	test.log(LogStatus.PASS, "clicks on add Button");
	 //captureScreenshot(driver); 
}


public void contructdropdown() throws Throwable {
	 test=report.startTest("PI_ constructdropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean dropdown=driver.findElement(By.xpath("//div[@class='selectbutton']/img")).isDisplayed();
		System.out.println("construct dropdown is visible");
		if(dropdown) {
			test.log(LogStatus.PASS, "construct dropdown is visible");
			//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "construct dropdown is visible");
			//captureScreenshot(driver);
			
		}
		//assertionMethod(dropdown, true);
		//test.log(LogStatus.PASS, "construct dropdown is visible");
		////captureScreenshot(driver);
}

public void enablecontructdropdown() throws Throwable {
	 test=report.startTest("PI_ enableconstructdropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	 boolean  clickdrop=driver.findElement(By.xpath("//div[@class='selectbutton']/img")).isEnabled();
     System.out.println("construct dropdown is enable");
     if(clickdrop) 
     {
   	  test.log(LogStatus.PASS, "construct dropdown is enabled");
   	  //captureScreenshot(driver);
     }else {
   	  test.log(LogStatus.FAIL, "construct dropdown is  not enabled");
   	  //captureScreenshot(driver);
     }

   	  //assertionMethod(clickdrop, true);
		//test.log(LogStatus.PASS, "construct dropdown is enabled");
		////captureScreenshot(driver);
}

/*public void clickcontructdropdown() throws Throwable {
	 test=report.startTest("PI_ clickconstructdropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	 driver.findElement(By.xpath("(//div[@ class='selectbutton'])[1]")).click();

	 //System.out.println(clickdrop);
	// assertionMethod(clickdrop, true);
		test.log(LogStatus.PASS, "construct dropdown is clickable");
		//captureScreenshot(driver);

}*/

/*public void contructsdropdown() throws Throwable {
	 WebElement element;
	test=report.startTest("PI_ existing constructs");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
       
	List<WebElement> ele1=driver.findElements(By.xpath("//(div[@class='dropdown-item ng-star-inserted'])"));
	ele1.forEach(s->System.out.println(s.getText()));
	//String data=getDataFromExcel("Data",4, 0);
	//ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
}
		


	public void grouppresent() throws Throwable  {
		WebElement element;
		test=report.startTest("PI_ existing constructs");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		String data=getDataFromExcel("sheet1", 1, 1);
		element= driver.findElement(By.xpath("(//div[@class='selectcontent2 dropdown-menu show']/div"));
		
		Select elements=new Select(element);
		List<WebElement>elementlist=elements.getOptions();
		String construct=data;
		boolean construct_present=false;
		for(WebElement e: elementlist) {
			if(e.getText().equalsIgnoreCase(data)) {
			   construct_present=true;
			   break;
			}
				 
					}
			if(construct_present) {
	           System.out.println(construct_present +"group construct is present");
	           test.log(LogStatus.PASS, "group construct is present");
			}else {
				System.out.println(construct_present +"group construct is not present");
		           test.log(LogStatus.FAIL, "group construct is not present");

			}
		
	}
	
	

	 

	 //System.out.println(clickdrop);
	// assertionMethod(clickdrop, true);
		//test.log(LogStatus.PASS, "existing constructs visible");
		////captureScreenshot(driver);*/
public void clickcontructdropdown1() throws Throwable {
	

test=report.startTest("PI_click on dropdown");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
String data=getDataFromExcel("Group", 1, 0);
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
}else{

     

 
test.log(LogStatus.FAIL, "Dropdown is disabled ");
//captureScreenshot(driver);
}

    ele.click();

   

List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
 ele1.forEach(s->System.out.println(s.getText()));
 ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
}

public void groupconstructvisible() throws Throwable {
	test=report.startTest("PI_ group construct");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	boolean group=driver.findElement(By.xpath(" //label[text()=' Group ']")).isDisplayed();
	System.out.println(" group construct is visible");
	if(group) {
		test.log(LogStatus.PASS, "group construct  is visible");
		//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "group construct  is  not visible");
		//captureScreenshot(driver);
	}
	//assertionMethod(group, true);
	//test.log(LogStatus.PASS, "group construct  is visible");
	//captureScreenshot(driver);
}

public void groupconstruct() throws Throwable {
	test=report.startTest("PI_ group construct is clickable");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	driver.findElement(By.xpath(" //label[text()=' Group ']")).click();
	//System.out.println("group construct is clickable");
	//assertionMethod(group, true);
	test.log(LogStatus.PASS, "group construct  is clickable");
	//captureScreenshot(driver);
}

public void infoseesion() throws Throwable {
	test=report.startTest("PI_ info session");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	boolean info=driver.findElement(By.xpath(" //button[contains(text(),'INFO')]")).isDisplayed();
	System.out.println("info session is visible");
	//assertionMethod(info, true);
	if(info) {
		test.log(LogStatus.PASS, "info sesssion is visible");
		//captureScreenshot(driver);
	}else {
	
	test.log(LogStatus.FAIL, "info sesssion is  not visible");
	//captureScreenshot(driver);
	}
	////captureScreenshot(driver);
}
public void selectuniverse() throws Throwable {
	test=report.startTest("PI_ select universe dropdown");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	boolean universe=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).isDisplayed();
	System.out.println("universe dropdown is visible");
	//assertionMethod(universe, true);
	if(universe) {
		test.log(LogStatus.PASS, "select universe dropdown is visible");
		//captureScreenshot(driver);
	}else {
	test.log(LogStatus.FAIL, "select universe dropdown is not visible");
	//captureScreenshot(driver);
	}
}
public void universeenble() throws Throwable {
	test=report.startTest("PI_ select universe dropdown is enable");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	boolean universedropdown=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).isEnabled();
	System.out.println("universedropdown is enable");
	//assertionMethod(universefield, true);
	if(universedropdown) 
	{
	test.log(LogStatus.PASS, "select universe dropdown is visible");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "select universe dropdown is not visible");
		//captureScreenshot(driver);
	}


}
public void clickuniverse() throws Throwable {
	test=report.startTest("PI_ select universe dropdown is clickable");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).click();
	//System.out.println(universefield);
	//assertionMethod(universefield, true);
	
	test.log(LogStatus.PASS, "select universe dropdown is clickable");
	//captureScreenshot(driver);

}

public void selectschema()  {
	test=report.startTest("PI_ select schema dropdown is visible");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	boolean schema=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[3]")).isDisplayed();
	System.out.println("schema dropdown is visible");
	//assertionMethod(schema, true);
	if(schema) {
	test.log(LogStatus.PASS, "select schema dropdown is visible");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "select schema dropdown is not visible");
		//captureScreenshot(driver);
	}
}
	public void selectschemaenable() throws Throwable {
		test=report.startTest("PI_ select schema dropdown is enable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean schemadropdown=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[3]")).isEnabled();
		System.out.println("schema dropdown is enable");
		//assertionMethod(schema, true);
		if(schemadropdown) {
		test.log(LogStatus.PASS, "select schema dropdown is enable");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "select schema dropdown is not enable");
			//captureScreenshot(driver);
		}
	}
		public void groupName() throws Throwable {
			test=report.startTest("PI_ Group name field is visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			boolean groupname=driver.findElement(By.xpath("//label[contains(text(),'Group Name')]")).isDisplayed();
			System.out.println("group name field is visible ");
			//assertionMethod(schema, true);
			if(groupname) {
			test.log(LogStatus.PASS, "groupname field is visible");
			//captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "group name is not visible");
				//captureScreenshot(driver);
			}
		}
			public void groupdNameEnable() throws Throwable {
				test=report.startTest("PI_ Group name field is enable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean groupname=driver.findElement(By.xpath("(input[@ class='inputfield ng-pristine ng-invalid ng-touched'])[1]")).isEnabled();
				System.out.println("group name field is enable ");
				//assertionMethod(schema, true);
				if(groupname) {
				test.log(LogStatus.PASS, "groupname field is enable");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "group name is not enable");
					//captureScreenshot(driver);
				}


}
			public void groupdDescriptrion() throws Throwable {
				test=report.startTest("PI_ Group description field is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean groupdes=driver.findElement(By.xpath("//label[contains(text(),'Group Description')]")).isDisplayed();
				System.out.println("group desctiption field is visible ");
				//assertionMethod(schema, true);
				if(groupdes) {
				test.log(LogStatus.PASS, "group description field is visible");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "group description is not visible");
					//captureScreenshot(driver);
				}

			}
			public void groupdDescriptrionEnable() throws Throwable {
				test=report.startTest("PI_ Group description field is enable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean groupdescr=driver.findElement(By.cssSelector("[formcontrolname='desc']")).isEnabled();
				System.out.println("group description field is enable ");
				//assertionMethod(schema, true);
				if(groupdescr) {
				test.log(LogStatus.PASS, "group description field is enable");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "group description is not enable");
					//captureScreenshot(driver);
				}
	
}
		
			
			
			
			
			
			public void radioBtn() throws Throwable {
				test=report.startTest("PI_ Manual condition button is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean btn=driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isDisplayed();
				System.out.println("manual conditions button is visible ");
				//assertionMethod(schema, true);
				if(btn) {
				test.log(LogStatus.PASS, "manual condition radio button is visible");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "manual condition radio button is not visible");
					//captureScreenshot(driver);
				}
	
}
				
			
			public void manualconditionselected() throws Throwable {
				test=report.startTest("PI_ Manual condition button is selected");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean manualcondition=driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isSelected();
				System.out.println("manual conditions button is selected ");
				//assertionMethod(schema, true);
				if(manualcondition) {
				test.log(LogStatus.PASS, "manual condition button is selected");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "manual condition button is not selected");
					//captureScreenshot(driver);
				}
	
			
}
			public void query() throws Throwable {
				test=report.startTest("PI_query button is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean querybutton=driver.findElement(By.xpath("//label[text()='Query']")).isDisplayed();
				System.out.println("query button is visible ");
				//assertionMethod(schema, true);
				if(querybutton) {
				test.log(LogStatus.PASS, "query is visible");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "query button is not visible");
					//captureScreenshot(driver);
				}
			
			
			
			
			
			}
			
			
			
			public void querybutton() throws Throwable {
				test=report.startTest("PI_query button is selected");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean query=driver.findElement(By.xpath("//label[text()='Query']")).isSelected();
				System.out.println("query button is selected ");
				//assertionMethod(schema, true);
				if(query) {
				test.log(LogStatus.PASS, "query is selected");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "query button is not selected");
					//captureScreenshot(driver);
				}
				
	
}
			public void sourceattributeType() throws Throwable {
				test=report.startTest("PI_source attribute is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean sourceattribute=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).isDisplayed();
				System.out.println("source attribute is visible ");
				//assertionMethod(schema, true);
				if(sourceattribute) {
				test.log(LogStatus.PASS, "source attribute is visible");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "source attribute is not visible");
					//captureScreenshot(driver);
				}
					

}
			public void sourceattributeEnable() throws Throwable {
				test=report.startTest("PI_source attribute is Enable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean sourceattributeEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).isEnabled();
				System.out.println("source attribute is visible ");
				//assertionMethod(schema, true);
				if(sourceattributeEnable) {
				test.log(LogStatus.PASS, "source attribute is enable");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "source attribute is not enable");
					//captureScreenshot(driver);
				}
			}
			/*public void clickSourceattribute(){
				test=report.startTest("PI_ Source attribute  dropdown is clickable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).click();
				//System.out.println(universefield);
				//assertionMethod(universefield, true);
				
				test.log(LogStatus.PASS, "Source attribute type dropdown is clickable");
				//captureScreenshot(driver);*/


//}
	public void sourceCondition() {
		test=report.startTest("PI_source condition is visible");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean sourcecondition=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).isDisplayed();
		System.out.println("source attribute is visible ");
		//assertionMethod(schema, true);
		if(sourcecondition) {
		test.log(LogStatus.PASS, "source condition is visible");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "source condition is not visible");
			//captureScreenshot(driver);
		}
	
	}
	public void sourceConditionEnable() {
		test=report.startTest("PI_source condition is Enable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean sourceconditionEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).isEnabled();
		System.out.println("source attribute is visible ");
		//assertionMethod(schema, true);
		if(sourceconditionEnable) {
		test.log(LogStatus.PASS, "source condition is enable");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "source condition is not enable");
			//captureScreenshot(driver);
		}
	

}


		public void clickSourceCondition() 
		{
			
			test=report.startTest("PI_ Source condition dropdown is clickable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).click();
			//System.out.println(universefield);
			//assertionMethod(universefield, true);
			
			test.log(LogStatus.PASS, "Source condition dropdown is clickable");
			//captureScreenshot(driver);
         }

		/*public void clickSourceattribute(){
			test=report.startTest("PI_ Source attribute  dropdown is clickable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).click();
			//System.out.println(universefield);
			//assertionMethod(universefield, true);
			
			test.log(LogStatus.PASS, "Source attribute type dropdown is clickable");
			//captureScreenshot(driver);

		
		}*/
		
		
		
		
		
		public void destinationAttribute() {
			test=report.startTest("PI_destination attribute is visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			boolean destinationattribute=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).isDisplayed();
			System.out.println("destination attribute is visible ");
			//assertionMethod(schema, true);
			if(destinationattribute) {
			test.log(LogStatus.PASS, "destination attribute is visible");
			//captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "destination attribute is not visible");
				//captureScreenshot(driver);
		

			}


		}
      public void destinationAttributeEnable(){
      
				test=report.startTest("PI_destination attribute is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				boolean destinationattributeEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).isEnabled();
				System.out.println("destination attribute is visible ");
				//assertionMethod(schema, true);
				if(destinationattributeEnable) {
				test.log(LogStatus.PASS, "destination attribute is enable");
				//captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "destination attribute is not enable");
					//captureScreenshot(driver);
				}


}

     /* public void clickDestinationattribute() 
		{
			
			test=report.startTest("PI_ destination attribute dropdown is clickable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).click();
			//System.out.println(universefield);
			//assertionMethod(universefield, true);
			
			test.log(LogStatus.PASS, "Source condition dropdown is clickable");
			//captureScreenshot(driver);
      
		
			
			}*/
		
      public void addRule(){
          
			test=report.startTest("PI_Add rule button is visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			boolean addrule=driver.findElement(By.xpath("(//button[@class='addRuleButton mb-4 ng-star-inserted'])")).isDisplayed();
			System.out.println("add rule button is visible ");
			//assertionMethod(schema, true);
			if(addrule) {
			test.log(LogStatus.PASS, "add rule button is visible");
			//captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "add rule button is not visible");
				//captureScreenshot(driver);
			}



      }

      public void addRuleEnable(){
          
			test=report.startTest("PI_Add rule button is Enable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean addruleEnable=driver.findElement(By.xpath("(//button[@class='addRuleButton mb-4 ng-star-inserted'])")).isEnabled();
			System.out.println("add rule button is enable ");
			//assertionMethod(schema, true);
			if(addruleEnable) {
			test.log(LogStatus.PASS, "add rule button is enable");
			//captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "add rule button is not enable");
				//captureScreenshot(driver);
			}



     }

      public void clcikaddRule(){
          
			test=report.startTest("PI_click add rule button");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("(//button[@class='addRuleButton mb-4 ng-star-inserted'])")).click();
			System.out.println("add rule button is clickable ");
			//assertionMethod(schema, true);
				test.log(LogStatus.PASS, "add rule button is clickable");
				//captureScreenshot(driver);
   }




  public void aclbutton(){
   
	test=report.startTest("PI_acl button is visible");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	boolean acl=driver.findElement(By.xpath("//button[text()='ACL']")).isDisplayed();
	System.out.println("add rule button is enable ");
	//assertionMethod(schema, true);
	if(acl) {
	test.log(LogStatus.PASS, "acl button is visible");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "acl  button is not visible");
		//captureScreenshot(driver);
	}
}
  public void aclEnable(){
	    
		test=report.startTest("PI_acl button is enable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		boolean aclbutton=driver.findElement(By.xpath("//button[text()='ACL']")).isEnabled();
		System.out.println("acl button is enable ");
		//assertionMethod(schema, true);
		if(aclbutton) {
		test.log(LogStatus.PASS, "acl button is enable");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "acl  button is not enable");
			//captureScreenshot(driver);
		}
	}


  public void clickAclbtn(){
      
		test=report.startTest("PI_click acl");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[text()='ACL']")).click();
		System.out.println("add rule button is clickable ");
		//assertionMethod(schema, true);
			test.log(LogStatus.PASS, "add rule button is clickable");
			//captureScreenshot(driver);
}

  public void readAcess(){
      
	   test=report.startTest("PI_read access dropdown is visible");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean acldropdown=driver.findElement(By.xpath("//label[contains(text(),'Read Access')]")).isDisplayed();
		System.out.println("read access dropdown is visible");
		//assertionMethod(schema, true);
		if(acldropdown) {
		test.log(LogStatus.PASS, "read access dropdown is visible");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "read access  dropdown is not visible");
			//captureScreenshot(driver);
		}
	}	
public void readaccessEnable() {
	test=report.startTest("PI_acl readaccess is Enable");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	boolean acldropdownEnable=driver.findElement(By.xpath("(//img[@src=\"../../assets/images/Vector 45.png\"])[8]")).isEnabled();
	System.out.println("acl dropdown is enable");
	//assertionMethod(schema, true);
	if(acldropdownEnable) {
	test.log(LogStatus.PASS, "read access dropdown is enable");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "read access  dropdown is not enable");
		//captureScreenshot(driver);
	}
}	
public void discardbutton() {
	test=report.startTest("PI_discard  button is visible");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	boolean discard=driver.findElement(By.xpath("(//div[@class='discard col-6 d-flex justify-content-center align-items-center'])")).isDisplayed();
	System.out.println("discard button is visible");
	//assertionMethod(schema, true);
	if(discard) {
	test.log(LogStatus.PASS, "discard button is visible");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "discard button is not visible");
		//captureScreenshot(driver);
	}
}	
public void discardbuttonEnable() {
	test=report.startTest("PI_discard  button is enable");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	boolean discardbutton=driver.findElement(By.xpath("(//div[@class='discard col-6 d-flex justify-content-center align-items-center'])")).isEnabled();
	System.out.println("discard button is enable");
	//assertionMethod(schema, true);
	if(discardbutton) {
	test.log(LogStatus.PASS, "discard button is enable");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "discard button is not enable");
		//captureScreenshot(driver);

}
}
public void savebutton() {
	test=report.startTest("PI_save  button is visible");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	boolean savebutton=driver.findElement(By.xpath("(//div[@class='save col-6 d-flex justify-content-center align-items-center'])")).isDisplayed();
	System.out.println("save button is visible");
	//assertionMethod(schema, true);
	if(savebutton) {
	test.log(LogStatus.PASS, "save button is visible");
	//captureScreenshot(driver);
	}else {
		test.log(LogStatus.FAIL, "save button is not visible");
		//captureScreenshot(driver);
	}
}

	public void savebuttonEnable() {
		test=report.startTest("PI_save  button is enable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean savebuttonEnable=driver.findElement(By.xpath("(//div[@class='save col-6 d-flex justify-content-center align-items-center'])")).isEnabled();
		System.out.println("save button is enable");
		//assertionMethod(schema, true);
		if(savebuttonEnable) {
		test.log(LogStatus.PASS, "save button is enable");
		//captureScreenshot(driver);
		}else {
			test.log(LogStatus.FAIL, "save button is not enable");
			//captureScreenshot(driver);


		}
	

}
}


