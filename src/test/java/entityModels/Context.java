package entityModels;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class Context extends MyRunner {
	
	public void addicon() {
             test=report.startTest("PI_visible add icon");
             driver.navigate().refresh();
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
			boolean addiconvis= driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).isDisplayed();
			System.out.println("addbutton is visible");
			if(addiconvis) {
				test.log(LogStatus.PASS, "add button is visible");
//				 captureScreenshot(driver); 
			}
			else {
				test.log(LogStatus.FAIL, "add button is not visible");
//				 captureScreenshot(driver); 
			}
			
			
			//assertionMethod(addiconvis, true);
			 //assertEquals(addiconvis, true);
			//test.log(LogStatus.PASS, "visible add icon");
			// captureScreenshot(driver); 
	}




	public void enableadd1() throws Throwable {
		
		
		test=report.startTest("PI_enable add icon");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean enableadd= driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).isEnabled();
	   System.out.println("addicon is enable");
	  //assertionMethod(enableadd, true);
		if(enableadd) {
			test.log(LogStatus.PASS, "add button is enable");
//			captureScreenshot(driver); 
		}else 
		{
	   test.log(LogStatus.FAIL, "add button is not enable");
//	   captureScreenshot(driver);
		}
	  // captureScreenshot(driver); 
	}
		
	public void clickadd1() throws Throwable {
		
		
		test=report.startTest("PI_click on add button");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("[src='../../../assets/images/add.png']")).click();
	   System.out.println("clicks add icon");
	   //assertionMethod(enableadd, true);
		test.log(LogStatus.PASS, "clicks on add Button");
//		 captureScreenshot(driver); 
	}

	public void clickcontructdropdown2() throws Throwable {
		test=report.startTest("PI_click on dropdown");
		//driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String data=getDataFromExcel("Group", 1, 1);
		WebElement ele=driver.findElement(By.xpath("//div[@class='selectbutton']/img"));

		if(ele.isDisplayed())
		{
		test.log(LogStatus.PASS, "Dropdown is  visible");
//		captureScreenshot(driver);
		}
		else {
		test.log(LogStatus.FAIL, " Dropdown is not visible");
//		captureScreenshot(driver);       
		}
		if(ele.isEnabled())
		{
			test.log(LogStatus.PASS, " Dropdown is enabled");
//			captureScreenshot(driver);
		}else{

		     

		 
		test.log(LogStatus.FAIL, "Dropdown is disabled ");
//		captureScreenshot(driver);
		}

		    ele.click();

		   

		List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
		 ele1.forEach(s->System.out.println(s.getText()));
		 ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
		}

	
				/*public void contextpresent1() throws Throwable  {
			WebElement element;
			test=report.startTest("PI_ existing constructs");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String data=getDataFromExcel("sheet1", 1, 0);
			element= driver.findElement(By.xpath("//div[@class='selectbutton']/img"));
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
		           System.out.println(construct_present +"contextconstruct is present");
		           test.log(LogStatus.PASS, "group construct is present");
				}else {
					System.out.println(construct_present +"group construct is not present");
			           test.log(LogStatus.FAIL, "group construct is not present");

				}
			
		}*/
	
		
		public void contextconstructvisible() throws Throwable {
			test=report.startTest("PI_ context construct visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean context=driver.findElement(By.xpath(" //label[text()=' Context ']")).isDisplayed();
			System.out.println(" context construct is visible");
			if(context) {
				test.log(LogStatus.PASS, "context construct  is visible");
//				captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "context construct  is  not visible");
//				captureScreenshot(driver);
			}
			//assertionMethod(group, true);
			//test.log(LogStatus.PASS, "group construct  is visible");
			//captureScreenshot(driver);
		}

		
		
	public void contextconstruct() throws Throwable {
			test=report.startTest("PI_ context construct is clickable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath(" //label[text()=' Context ']")).click();
			//System.out.println("group construct is clickable");
			//assertionMethod(group, true);
			test.log(LogStatus.PASS, "context construct  is clickable");
//			captureScreenshot(driver);
		}
	public void infoseesion1() throws Throwable {
		test=report.startTest("PI_ info session visible");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean infosession=driver.findElement(By.xpath("//button[contains(text(),'INFO')]")).isDisplayed();
		System.out.println("info session is visible");
		//assertionMethod(info, true);
		if(infosession) {
			test.log(LogStatus.PASS, "info sesssion is visible");
//			captureScreenshot(driver);
		}else {
		
		test.log(LogStatus.FAIL, "info sesssion is  not visible");
//		captureScreenshot(driver);
		}
		//captureScreenshot(driver);
	}

	public void infoseesionEnable() throws Throwable {
		test=report.startTest("PI_ info session enable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean infosession1=driver.findElement(By.className("info-acl toggle-button")).isEnabled();
		System.out.println("info session is visible");
		//assertionMethod(info, true);
		if(infosession1) {
			test.log(LogStatus.PASS, "info sesssion is enable");
//			captureScreenshot(driver);
		}else {
		
		test.log(LogStatus.FAIL, "info sesssion is  not enable");
//		captureScreenshot(driver);
		}
	}
		public void selectuniverse1(){
			test=report.startTest("PI_ select universe dropdown visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean universe=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).isDisplayed();
			System.out.println("universe dropdown is visible");
			//assertionMethod(universe, true);
			if(universe) {
				test.log(LogStatus.PASS, "select universe dropdown is visible");
//				captureScreenshot(driver);
			}else {
			test.log(LogStatus.FAIL, "select universe dropdown is not visible");
//			captureScreenshot(driver);
			}
		}
		public void universeenble1() throws Throwable {
			test=report.startTest("PI_ select universe dropdown is enable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean universedropdown=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).isEnabled();
			System.out.println("universedropdown is enable");
			//assertionMethod(universefield, true);
			if(universedropdown) 
			{
			test.log(LogStatus.PASS, "select universe dropdown is visible");
//			captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "select universe dropdown is not visible");
//				captureScreenshot(driver);
			}


		}
		public void clickuniverse1() throws Throwable {
			test=report.startTest("PI_ select universe dropdown is clickable");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("(//div[@ class='selectbutton'])[2]")).click();
			//System.out.println(universefield);
			//assertionMethod(universefield, true);
			
			test.log(LogStatus.PASS, "select universe dropdown is clickable");
//			captureScreenshot(driver);

		}

		public void selectschema1()  {
			test=report.startTest("PI_ select schema dropdown is visible");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean schema=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[3]")).isDisplayed();
			System.out.println("schema dropdown is visible");
			//assertionMethod(schema, true);
			if(schema) {
			test.log(LogStatus.PASS, "select schema dropdown is visible");
			captureScreenshot(driver);
			}else {
				test.log(LogStatus.FAIL, "select schema dropdown is not visible");
//				captureScreenshot(driver);
			}
		}
			public void selectschemaenable1() throws Throwable {
				test=report.startTest("PI_ select schema dropdown is enable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean schemadropdown=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[3]")).isEnabled();
				System.out.println("schema dropdown is enable");
				//assertionMethod(schema, true);
				if(schemadropdown) {
				test.log(LogStatus.PASS, "select schema dropdown is enable");
//				captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "select schema dropdown is not enable");
//captureScreenshot(driver);
				}
			}

			public void contextName() throws Throwable {
				test=report.startTest("PI_ context name field is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean groupname=driver.findElement(By.xpath("//label[contains(text(),'Context Name')]")).isDisplayed();
				System.out.println("context name field is visible ");
				//assertionMethod(schema, true);
				if(groupname) {
				test.log(LogStatus.PASS, "context name field is visible");
//				captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "context name is not visible");
//captureScreenshot(driver);
				}
			}
				public void contextNameEnable() throws Throwable {
					test=report.startTest("PI_ context name field is enable");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					boolean contextname=driver.findElement(By.cssSelector("[formcontrolname='name']")).isEnabled();
					System.out.println("context name field is enable ");
					//assertionMethod(schema, true);
					if(contextname) {
					test.log(LogStatus.PASS, "context name field is enable");
					captureScreenshot(driver);
					}else {
						test.log(LogStatus.FAIL, "context name is not enable");
//						captureScreenshot(driver);
					}


	}
				public void contextDescriptrion() throws Throwable {
					test=report.startTest("PI_ context description field is visible");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					boolean contextdes=driver.findElement(By.xpath("//label[contains(text(),'Context Description')]")).isDisplayed();
					System.out.println("context description field is enable ");
					//assertionMethod(schema, true);
					if(contextdes) {
					test.log(LogStatus.PASS, "context description field is visible");
//captureScreenshot(driver);
					}else {
						test.log(LogStatus.FAIL, "context description is not visible");
//						captureScreenshot(driver);
					}

				}
				public void contextDescriptrionEnable() throws Throwable {
					test=report.startTest("PI_ context description field is enable");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					boolean contextdes=driver.findElement(By.cssSelector("[formcontrolname='desc']")).isDisplayed();
					System.out.println("context description field is enable ");
					//assertionMethod(schema, true);
					if(contextdes) {
					test.log(LogStatus.PASS, "context description field is enable");
//captureScreenshot(driver);
					}else {
						test.log(LogStatus.FAIL, "context description is not enable");
//						captureScreenshot(driver);
					}
				}
					public void sourceattributeType1() throws Throwable {
						test=report.startTest("PI_ context source attribute is visible");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						boolean sourceattribute=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).isDisplayed();
						System.out.println("source attribute is visible ");
						//assertionMethod(schema, true);
						if(sourceattribute) {
						test.log(LogStatus.PASS, "source attribute is visible");
//						captureScreenshot(driver);
						}else {
							test.log(LogStatus.FAIL, "source attribute is not visible");
//							captureScreenshot(driver);
						}
							

		}
					public void sourceattributeEnable1() throws Throwable {
						test=report.startTest("PI_ context source attribute is visible");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						boolean sourceattributeEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).isEnabled();
						System.out.println("source attribute is visible ");
						//assertionMethod(schema, true);
						if(sourceattributeEnable) {
						test.log(LogStatus.PASS, "source attribute is enable");
//						captureScreenshot(driver);
						}else {
							test.log(LogStatus.FAIL, "source attribute is not enable");
//							captureScreenshot(driver);
						}
					}
					/*public void clickSourceattribute1(){
						test=report.startTest("PI_ context source attribute  dropdown is clickable");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
						driver.findElement(By.xpath("(//div[@ class='selectbutton'])[4]")).click();
						//System.out.println(universefield);
						//assertionMethod(universefield, true);
						
						test.log(LogStatus.PASS, "context source attribute type dropdown is clickable");
						captureScreenshot(driver);


		}*/
			public void sourceCondition1() {
				test=report.startTest("PI_context source condition is visible");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean sourcecondition=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).isDisplayed();
				System.out.println("source attribute is visible ");
				//assertionMethod(schema, true);
				if(sourcecondition) {
				test.log(LogStatus.PASS, "context source condition is visible");
//				captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "context source condition is not visible");
//captureScreenshot(driver);
				}
			
			}
			public void sourceConditionEnable1() {
				test=report.startTest("PI_context  sourcecondition is enable");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				boolean sourceconditionEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).isEnabled();
				System.out.println("context source attribute is visible ");
				//assertionMethod(schema, true);
				if(sourceconditionEnable) {
				test.log(LogStatus.PASS, "source condition is enable");
//				captureScreenshot(driver);
				}else {
					test.log(LogStatus.FAIL, "source condition is not enable");
//captureScreenshot(driver);
				}
			

		}


				public void clickSourceCondition1() 
				{
					
					test=report.startTest("PI_ context source condition dropdown is clickable");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					driver.findElement(By.xpath("(//div[@ class='selectbutton'])[5]")).click();
					//System.out.println(universefield);
					//assertionMethod(universefield, true);
					
					test.log(LogStatus.PASS, "context source condition dropdown is clickable");
//captureScreenshot(driver);
		          }

				public void destinationAttribute1() {
					test=report.startTest("PI_context destination attribute is visible");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					boolean destinationattribute=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).isDisplayed();
					System.out.println("context destination attribute is visible ");
					//assertionMethod(schema, true);
					if(destinationattribute) {
					test.log(LogStatus.PASS, "context destination attribute is visible");
//captureScreenshot(driver);
					}else {
						test.log(LogStatus.FAIL, "context destination attribute is not visible");
//						captureScreenshot(driver);
				

					}


				}
		       public void destinationAttributeEnable1(){
		       
						test=report.startTest("PI_context destination attribute is enable");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
						boolean destinationattributeEnable=driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).isEnabled();
						System.out.println("destination attribute is visible ");
						//assertionMethod(schema, true);
						if(destinationattributeEnable) {
						test.log(LogStatus.PASS, "context destination attribute is enable");
//						captureScreenshot(driver);
						}else {
							test.log(LogStatus.FAIL, "context destination attribute is not enable");
//							captureScreenshot(driver);
						}


		}

		       /*public void clickDestinationattribute1() 
				{
					
					test=report.startTest("PI_ destination attribute dropdown is clickable");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
					driver.findElement(By.xpath("(//div[@ class='selectbutton'])[6]")).click();
					//System.out.println(universefield);
					//assertionMethod(universefield, true);
					
					test.log(LogStatus.PASS, "Source condition dropdown is clickable");
					captureScreenshot(driver);
		       
				
					
					}*/
				
		       public void schudleType() {
		    		test=report.startTest("PI_schudleType dropdown is visible");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean schudleType=driver.findElement(By.xpath("(//img[@src='../../assets/images/Vector 45.png'])[7]")).isDisplayed();
		    		System.out.println("acl dropdown is enable");
		    		//assertionMethod(schema, true);
		    		if(schudleType) {
		    		test.log(LogStatus.PASS, "schudleType dropdown is visible");
//		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "schudleType  dropdown is not visible");
//		    			captureScreenshot(driver);
		    		}
		    	}	

		       public void schudleType1() {
		    		test=report.startTest("PI_schudleType dropdown is enable");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean schudleType1=driver.findElement(By.xpath("(//img[@src='../../assets/images/Vector 45.png'])[7]")).isEnabled();
		    		System.out.println(" schudleType dropdown is enable");
		    		//assertionMethod(schema, true);
		    		if(schudleType1) {
		    		test.log(LogStatus.PASS, "schudleType dropdown is enable");
		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "schudleType  dropdown is not enable");
//		    			captureScreenshot(driver);
		    		}
		    	}	

		       
		       
		       
		       
		       
		       
		       public void aclbutton1(){
		    	   
		    		test=report.startTest("PI_acl button is visible");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean acl=driver.findElement(By.xpath("//button[text()='ACL']")).isDisplayed();
		    		System.out.println("add rule button is enable ");
		    		//assertionMethod(schema, true);
		    		if(acl) {
		    		test.log(LogStatus.PASS, "acl button is visible");
//		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "acl  button is not visible");
//		    			captureScreenshot(driver);
		    		}
		    	}
		    	  public void aclbuttonEnable1(){
		    		    
		    			test=report.startTest("PI_acl button is enable");
		    			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    			boolean aclbutton=driver.findElement(By.xpath("//button[text()='ACL']")).isEnabled();
		    			System.out.println("acl button is enable ");
		    			//assertionMethod(schema, true);
		    			if(aclbutton) {
		    			test.log(LogStatus.PASS, "acl button is enable");
		    			captureScreenshot(driver);
		    			}else {
		    				test.log(LogStatus.FAIL, "acl  button is not enable");
//		    				captureScreenshot(driver);
		    			}
		    		}

		    	  
		    	  
		    	  
		    	  
		    	  
		    	  
		    	  public void acldropdownvisible1() {
			    		test=report.startTest("PI_acl dropdown is visible");
			    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			    		boolean acldropdownEnable=driver.findElement(By.xpath("(//img[@src='../../assets/images/Vector 45.png'])[7]")).isDisplayed();
			    		System.out.println("acl dropdown is enable");
			    		//assertionMethod(schema, true);
			    		if(acldropdownEnable) {
			    		test.log(LogStatus.PASS, "acl dropdown is enable");
			    		captureScreenshot(driver);
			    		}else {
			    			test.log(LogStatus.FAIL, "acl  dropdown is not enable");
//			    			captureScreenshot(driver);
			    		}
			    	}	

		       
		       
		       public void acldropdownEnable1() {
		    		test=report.startTest("PI_acl dropdown is enable");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean acldropdownEnable=driver.findElement(By.xpath("(//img[@src='../../assets/images/Vector 45.png'])[7]")).isEnabled();
		    		System.out.println("acl dropdown is enable");
		    		//assertionMethod(schema, true);
		    		if(acldropdownEnable) {
		    		test.log(LogStatus.PASS, "acl dropdown is enable");
//		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "acl  dropdown is not enable");
//		    			captureScreenshot(driver);
		    		}
		    	}	
		    
		      
		       
		       
		       
		       public void readAcess1(){
		    	      
		    	   test=report.startTest("PI_read access dropdown is visible");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    		boolean acldropdown=driver.findElement(By.xpath("//label[contains(text(),'Read Access')]")).isDisplayed();
		    		System.out.println("read access dropdown is visible");
		    		//assertionMethod(schema, true);
		    		if(acldropdown) {
		    		test.log(LogStatus.PASS, "read access dropdown is visible");
//		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "read access  dropdown is not visible");
//		    			captureScreenshot(driver);
		    		}
		    	}	
		    public void readaccessEnable1() {
		    	test=report.startTest("PI_read access dropdown is enable");
		    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    	boolean acldropdownEnable=driver.findElement(By.xpath("(//img[@src=\"../../assets/images/Vector 45.png\"])[8]")).isEnabled();
		    	System.out.println("acl dropdown is enable");
		    	//assertionMethod(schema, true);
		    	if(acldropdownEnable) {
		    	test.log(LogStatus.PASS, "read access dropdown is enable");
//		    	captureScreenshot(driver);
		    	}else {
		    		test.log(LogStatus.FAIL, "read access  dropdown is not enable");
//		    		captureScreenshot(driver);
		    	}
		    }	
		       
		       
		       
		       
		       
		       
		       public void discardbutton1() {
		    		test=report.startTest("PI_discard  button is visible");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean discard=driver.findElement(By.xpath("(//div[@class='discard col-6 d-flex justify-content-center align-items-center'])")).isDisplayed();
		    		System.out.println("discard button is visible");
		    		//assertionMethod(schema, true);
		    		if(discard) {
		    		test.log(LogStatus.PASS, "discard button is visible");
//		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "discard button is not visible");
//		    			captureScreenshot(driver);
		    		}
		    	}	
		    	public void discardbuttonEnable1() {
		    		test=report.startTest("PI_discard  button is enable");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean discardbutton=driver.findElement(By.xpath("(//div[@class='discard col-6 d-flex justify-content-center align-items-center'])")).isEnabled();
		    		System.out.println("discard button is enable");
		    		//assertionMethod(schema, true);
		    		if(discardbutton) {
		    		test.log(LogStatus.PASS, "discard button is enable");
		    		captureScreenshot(driver);
		    		}else {
		    			test.log(LogStatus.FAIL, "discard button is not enable");
//		    			captureScreenshot(driver);

		    	}
		    	}
		    	public void savebutton1() {
		    		test=report.startTest("PI_save  button is visible");
		    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    		boolean savebutton=driver.findElement(By.xpath("(//div[@class='save col-6 d-flex justify-content-center align-items-center'])")).isDisplayed();
		    		System.out.println("save button is visible");
		    		//assertionMethod(schema, true);
		    		if(savebutton) {
		    		test.log(LogStatus.PASS, "save button is visible");
		    		}else {
		    			test.log(LogStatus.FAIL, "save button is not visible");
		    		}
		    	}

		    		public void savebuttonEnable1() {
		    			test=report.startTest("PI_save  button is enable");
		    			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		    			boolean savebuttonEnable=driver.findElement(By.xpath("(//div[@class='save col-6 d-flex justify-content-center align-items-center'])")).isEnabled();
		    			System.out.println("save button is enable");
		    			//assertionMethod(schema, true);
		    			if(savebuttonEnable) {
		    			test.log(LogStatus.PASS, "save button is enable");
		    			}else {
		    				test.log(LogStatus.FAIL, "save button is not enable");
//		    				captureScreenshot(driver);


		    			}
		    		

		    	}
		    	


		 

	}



