package entityModels;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;


public class DataIngest extends MyRunner{
 public void add() throws Throwable
 {
	 driver.navigate().refresh();
	 test=report.startTest("PI_Visible Add Button");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	 boolean addicon= driver.findElement(By.cssSelector(".menu-list > :nth-child(1) > img")).isDisplayed();
	 System.out.println("Add Button is Visible");
	 //assertEquals(addicon, true);
	 //test.log(LogStatus.PASS, "visible add icon");
	 ////captureScreenshot(driver);
	 if(addicon)
	 {
		 test.log(LogStatus.PASS, "Add button is visible");
//		 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Add button is not visible"); 
//		 //captureScreenshot(driver);
	 }

}
 
 public void enableadd()throws Throwable
 {
	 test=report.startTest("PI_Enable Add Button");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean addicon= driver.findElement(By.cssSelector(".menu-list > :nth-child(1) > img")).isEnabled();
	 System.out.println("Add Button is Enable");
	 //assertEquals(addicon, true);
	 //test.log(LogStatus.PASS, "Enable add icon");
	 ////captureScreenshot(driver);
	 if(addicon)
	 {
		 test.log(LogStatus.PASS, "Add button is enable");
//		 //captureScreenshot(driver);
		 
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Add button is not enable");
//		 //captureScreenshot(driver);
	 }
	 
}
 public void clickadd()throws Throwable
 {
	 test=report.startTest("PI_Click on Add Button");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 driver.findElement(By.cssSelector(".menu-list > :nth-child(1) > img")).click();
	 System.out.println("Click on Add Button");
	 test.log(LogStatus.PASS, "Add button is clickable");
//	 //captureScreenshot(driver);
	 
	 
	 
	 
 }
  
  public void constructdropdown()throws Throwable  
 {
	  
	 test=report.startTest("PI_Visible Construct Dropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean dropdown= driver.findElement(By.cssSelector(".construct-select > .ng-untouched > .mb-2 > .d-flex > .selectbutton")).isDisplayed();
	 System.out.println("Construct dropdown is visible");
	 if(dropdown)
	 {
		 test.log(LogStatus.PASS, "Construct dropdown is visible");
		 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Construct dropdown is not visible");
		 //captureScreenshot(driver);
	 }
 
	 
	 
	 //assertEquals(dropdown, true);
	 //test.log(LogStatus.PASS, "Add icon shows construct dropdown");
	 ////captureScreenshot(driver);

}
 public void enableconstuctdropdown()throws Throwable  
 {
	 test=report.startTest("PI_Enable Construct Dropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean clickdrop= driver.findElement(By.cssSelector(".construct-select > .ng-untouched > .mb-2 > .d-flex > .selectbutton")).isEnabled();
	 System.out.println("Construct dropdown is enable");
	 if(clickdrop)
	 {
		 test.log(LogStatus.PASS, "Construct dropdown is enable");
		 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Construct dropdown is not enable"); 
		 //captureScreenshot(driver);
	 }
	 
	 
	 //assertEquals(dropdown, true);
	 //test.log(LogStatus.PASS, "Construct dropdown is enabled");
	 ////captureScreenshot(driver);

}
 public void clickconstructdropdown()throws Throwable 
 {
	 test=report.startTest("PI_Click Construct Dropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 driver.findElement(By.cssSelector(".construct-select > .ng-untouched > .mb-2 > .d-flex > .selectbutton")).click();
	 //System.out.println(addicon);
	 //assertEquals(addicon, true);
	 test.log(LogStatus.PASS, "Construct dropdown is clickable");
	 //captureScreenshot(driver);

	 }
 public void dropdownclick() throws Exception
 {
 test=report.startTest("PI_Click on Dropdown");
 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 String data=getDataFromExcel("DataIngest", 1, 0);
 List<WebElement> ele1 =driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
 ele1.forEach(s->System.out.println(s.getText()));
 ele1.stream().filter(s->s.getText().equalsIgnoreCase(data)).forEach(S->S.click());
 test.log(LogStatus.PASS, "DataIngest dropdown is clickable");
 //captureScreenshot(driver);
 
 }
/* public void Searchdropdown() throws Exception
 {
 test=report.startTest("PI_click on Searchbox");
 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 
 WebElement search=driver.findElement(By.xpath("//input[@class='inputfield ng-star-inserted']"));
 String data1=getDataFromExcel("Data", 6, 1);
 search.sendKeys(data1);
 List<WebElement> multipleElement=driver.findElements(By.xpath("//div[@class='selectcontent2 dropdown-menu show']/div"));
 multipleElement.forEach(s->System.out.println(s.getText()));
 String data2=getDataFromExcel("Data",7, 1);
 multipleElement.stream().filter(s->s.getText().equalsIgnoreCase(data2)).forEach(S->S.click());
 
 }*/
 
 
 public void Infovisible() throws Throwable 
 {
	 test=report.startTest("PI_Visible Info Session");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean Info= driver.findElement(By.xpath("//button[text()='INFO']")).isDisplayed();
	 System.out.println("Info session is visible");
	 //assertEquals(Info, true);
	 //test.log(LogStatus.PASS, "Info is visible ");
	 ////captureScreenshot(driver);
	 if(Info)
	 {
		 test.log(LogStatus.PASS, "Info session is visible");
		 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Info session is not visible");
		 //captureScreenshot(driver);
	 }
	 }
 public void Infoenable() throws Throwable 
 {
	 test=report.startTest("PI_Enable Info Session ");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean Infoen= driver.findElement(By.xpath("//button[text()='INFO']")).isEnabled();
	 System.out.println("Info session is enabled");
	 //assertEquals(Info, true);
	 //test.log(LogStatus.PASS, "Info is visible ");
	 ////captureScreenshot(driver);
	 if(Infoen)
	 {
		 test.log(LogStatus.PASS, "Info session is enabled");
		 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Info session is not enabled");
		 //captureScreenshot(driver);
	 }
	 

}
 public void Selectuniversevisible() throws Throwable 
 {
	 test=report.startTest("PI_Visible Select Universe");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	 boolean universe= driver.findElement(By.xpath("(//div[@class='selectbutton'])[2]")).isDisplayed();
	 System.out.println("universe dropdown is visible");
	 //assertEquals(universe, true);
	 //test.log(LogStatus.PASS, "Select universe is visible ");
	 ////captureScreenshot(driver);
	 if (universe) 
	 {
		 test.log(LogStatus.PASS, "Select universe dropdown is visible ");	
		 //captureScreenshot(driver);
	} 
	 else
	 {
		 test.log(LogStatus.FAIL, "Select universe dropdown is  not visible ");	
		 //captureScreenshot(driver);
	}

}
 public void enableuniverse()throws Throwable
 {
	 test=report.startTest("PI_Enable Select Universe"); 
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean universedropdown= driver.findElement(By.xpath("(//div[@class='selectbutton'])[2]")).isEnabled();
	 System.out.println("Universe dropdown is enable");
	 if (universedropdown) 
	 {
		 test.log(LogStatus.PASS, "Select universe dropdown is enable ");
		 //captureScreenshot(driver);
	} 
	 else
	 {
		 test.log(LogStatus.FAIL, "Select universe dropdown is not enable ");
		 //captureScreenshot(driver);
	}
 }
 public void Clickselectuniverse() throws Throwable
 {
	 test=report.startTest("PI_Click Select Universe Dropdown ");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	 driver.findElement(By.xpath("(//div[@class='selectbutton'])[2]")).click();
	 //System.out.println(addicon);
	 //assertEquals(addicon, true);
	 test.log(LogStatus.PASS, "Select universe dropdown is clickable");
	 //captureScreenshot(driver);

	 }
 
	 public void IngestionNamevisible() throws Throwable 
	 {
		 test=report.startTest("PI_Visible Ingestion Name Field  ");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 boolean Ingestion= driver.findElement(By.cssSelector("input[formcontrolname='name']")).isDisplayed();
		 System.out.println("Ingestion name field is visible");
		 //assertEquals(universe, true);
		 //test.log(LogStatus.PASS, "Select universe is visible ");
		 ////captureScreenshot(driver);
		 if (Ingestion) 
		 {
			 test.log(LogStatus.PASS, "Ingestion name field is visible ");	
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Ingestion name field is  not visible ");	
			 //captureScreenshot(driver);
		}

	}
	 public void enableIngestionName()throws Throwable
	 {
		 test=report.startTest("PI_Enable Ingestion Name Field"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean Ingestionname= driver.findElement(By.cssSelector("input[formcontrolname='name']")).isEnabled();
		 System.out.println("Ingestion name filed is enable");
		 if (Ingestionname) 
		 {
			 test.log(LogStatus.PASS, "Ingestion name field is enable ");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Ingestion name field is not enable ");
			 //captureScreenshot(driver);
		}
	 }
	 
	 public void IngestionDescriptionvisible() throws Throwable 
	 {
		 test=report.startTest("PI_Visible Ingestion Description Field");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 boolean desc= driver.findElement(By.cssSelector("input[formcontrolname='description']")).isDisplayed();
		 System.out.println("Ingestion description field is visible");
		 //assertEquals(universe, true);
		 //test.log(LogStatus.PASS, "Select universe is visible ");
		 ////captureScreenshot(driver);
		 if (desc) 
		 {
			 test.log(LogStatus.PASS, "Ingestion description field is visible ");	
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Ingestion description field is  not visible ");	
			 //captureScreenshot(driver);
		}

	}
	 public void enableIngestionDescription()throws Throwable
	 {
		 test=report.startTest("PI_Enable Ingestion Description Field"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean Ingestiondesc= driver.findElement(By.cssSelector("input[formcontrolname='description']")).isEnabled();
		 System.out.println("Ingestion description field is enable");
		 if (Ingestiondesc) 
		 {
			 test.log(LogStatus.PASS, "Ingestion description field is enable ");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Ingestion description field is not enable ");
			 //captureScreenshot(driver);
		}
	 }
	 
	 public void ConfigurationNamevisible() throws Throwable 
	 {
		 test=report.startTest("PI_Visible Configuration Name Field");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 boolean config= driver.findElement(By.cssSelector("input[formcontrolname='configName']")).isDisplayed();
		 System.out.println("Configuration name field is visible");
		 //assertEquals(universe, true);
		 //test.log(LogStatus.PASS, "Select universe is visible ");
		 ////captureScreenshot(driver);
		 if (config) 
		 {
			 test.log(LogStatus.PASS, "Configuration name field is visible ");	
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Configuration name field is  not visible ");	
			 //captureScreenshot(driver);
		}

	}
	 public void enableConfigurationName()throws Throwable
	 {
		 test=report.startTest("PI_Enable Configuration Name Field"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean configure= driver.findElement(By.cssSelector("input[formcontrolname='configName']")).isEnabled();
		 System.out.println("Configuration name field is enable");
		 if (configure) 
		 {
			 test.log(LogStatus.PASS, "Configuration name field is enable ");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Configuration name field is not enable ");
			 //captureScreenshot(driver);
		}
	 }
		 public void ConfigurationDescriptionvisible() throws Throwable 
	 {
		 test=report.startTest("PI_Visible Configuration Description Field");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 boolean descri= driver.findElement(By.cssSelector("input[formcontrolname='configDesc']")).isDisplayed();
		 System.out.println("Configuration description field is visible");
		 //assertEquals(universe, true);
		 //test.log(LogStatus.PASS, "Select universe is visible ");
		 ////captureScreenshot(driver);
		 if (descri) 
		 {
			 test.log(LogStatus.PASS, "Configuration description field is visible ");	
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Configuration description field  is  not visible ");	
			 //captureScreenshot(driver);
		}

	}
	 public void enableConfigurationDescription()throws Throwable
	 {
		 test=report.startTest("PI_Enable Configuration Description Field"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean configurationdescri= driver.findElement(By.cssSelector("input[formcontrolname='configDesc']")).isEnabled();
		 System.out.println("Configuration description field is enable");
		 if (configurationdescri) 
		 {
			 test.log(LogStatus.PASS, "Configuration description field is enable ");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Configuration description field is not enable ");
			 //captureScreenshot(driver);
		}
	 }
	 
	 public void SelectSourcevisible() throws Throwable 
	 {
		 test=report.startTest("PI_Visible Select Source Dropdown");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean source= driver.findElement(By.xpath("(//div[@class='selectbutton'])[3]")).isDisplayed();
		 
		 System.out.println("Select source dropdown is visible");
		 //assertEquals(universe, true);
		 //test.log(LogStatus.PASS, "Select universe is visible ");
		 ////captureScreenshot(driver);
		 if (source) 
		 {
			 test.log(LogStatus.PASS, "Select source dropdown is visible ");	
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Select source dropdown is not visible ");	
			 //captureScreenshot(driver);
		}

	}
	 public void enableselectsource()throws Throwable
	 {
		 test=report.startTest("PI_Enable Select Source Dropdown"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean sources= driver.findElement(By.xpath("(//div[@class='selectbutton'])[3]")).isEnabled();
		 System.out.println("Select source dropdown is enable");
		 if (sources) 
		 {
			 test.log(LogStatus.PASS, "Select source dropdown is enable ");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Select source dropdown is not enable ");
			 //captureScreenshot(driver);
		}
	 }
	 public void Clickselectsource() throws Throwable
	 {
		 test=report.startTest("PI_Click Select Source Dropdown");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		 driver.findElement(By.xpath("(//div[@class='selectbutton'])[3]")).click();
		 //System.out.println(addicon);
		 //assertEquals(addicon, true);
		 test.log(LogStatus.PASS, "Select source dropdown is clickable");
		 //captureScreenshot(driver);

		 }
		 public void ConfigureSourceMappingvisible()throws Throwable
		 {
			 test=report.startTest("PI_Visible Configure Source Mapping Button"); 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 boolean mapping= driver.findElement(By.xpath("//label[text()='Configure Source Mapping']")).isDisplayed();
			 System.out.println("Configure Source Mapping button is visible");
			 if (mapping) 
			 {
				 test.log(LogStatus.PASS, "Configure source mapping button is visible");
				 //captureScreenshot(driver);
			} 
			 else
			 {
				 test.log(LogStatus.FAIL, "Configure source mapping button is not visible");
				 //captureScreenshot(driver);
			}
		 
	 }
		 public void ConfigureSourceMappingselected()throws Throwable
		 {
			 test=report.startTest("PI_Select Configure Source Mapping Button"); 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 boolean map= driver.findElement(By.xpath("//label[text()='Configure Source Mapping']")).isSelected();
			 System.out.println("Configure source mapping button is selected");
			 if (map) 
			 {
				 test.log(LogStatus.PASS, "Configure source mapping button is selected");
				 //captureScreenshot(driver);
			} 
			 else
			 {
				 test.log(LogStatus.FAIL, "Configure source mapping button is not selected");
				 //captureScreenshot(driver);
			}
		 
	 }
		 public void CreateNewEnityvisible()throws Throwable
		 {
			 test=report.startTest("PI_Visible Create New Enity Button"); 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 boolean mapping= driver.findElement(By.xpath("//label[text()='Create New Enity']")).isDisplayed();
			 System.out.println("Create new enity button is visible");
			 if (mapping) 
			 {
				 test.log(LogStatus.PASS, "Create new enity button is visible");
				 //captureScreenshot(driver);
			} 
			 else
			 {
				 test.log(LogStatus.FAIL, "Create new enity button is not visible");
				 //captureScreenshot(driver);
			}
		 
	 }
		 public void CreateNewEnityselected()throws Throwable
		 {
			 test=report.startTest("PI_Select Create New Enity Button"); 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 boolean map= driver.findElement(By.xpath("//label[text()='Create New Enity']")).isSelected();
			 System.out.println("Create new enity button is selected");
			 if (map) 
			 {
				 test.log(LogStatus.PASS, "Create new enity button is selected");
				 //captureScreenshot(driver);
			} 
			 else
			 {
				 test.log(LogStatus.FAIL, "Create new enity button is not selected");
				 //captureScreenshot(driver);
			}
		 
	 }
		 public void ConfigureSourceMappingLinkvisible()throws Throwable
	 {
		 test=report.startTest("PI_Visible Configure Source Mapping Link"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean mappingLink= driver.findElement(By.xpath("//a[text()='Configure Source Mapping ']")).isDisplayed();
		 System.out.println("Configure source mapping link is visible");
		 if (mappingLink) 
		 {
			 test.log(LogStatus.PASS, "Configure source mapping link is visible");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Configure source mapping link is not visible");
			 //captureScreenshot(driver);
		}
	 
 }
		 public void ConfigureSourceMappingLinkenable()throws Throwable
		 {
			 test=report.startTest("PI_Enable Configure Source Mapping Link"); 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 boolean map= driver.findElement(By.xpath("//a[text()='Configure Source Mapping ']")).isEnabled();
			 System.out.println("Configure source mapping link is enabled");
			 if (map) 
			 {
				 test.log(LogStatus.PASS, "Configure source mapping link is enabled");
				 //captureScreenshot(driver);
			   } 
			 else
			 {
				 test.log(LogStatus.FAIL, "Configure source mapping link is not enabled");
				 //captureScreenshot(driver);
		   }
	}
   public void ScheduleJobTypevisible() throws Throwable 
{
	 test=report.startTest("PI_Visible Schedule Job Type Dropdown");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 boolean job= driver.findElement(By.xpath("(//div[@class='selectbutton'])[4]")).isDisplayed();
	 
	 System.out.println("Schedule job type dropdown is visible");
	 //assertEquals(universe, true);
	 //test.log(LogStatus.PASS, "Select universe is visible ");
	 ////captureScreenshot(driver);
	 if (job) 
	 {
		 test.log(LogStatus.PASS, "Schedule job type dropdown is visible");	
		 //captureScreenshot(driver);
	} 
	 else
	 {
		 test.log(LogStatus.FAIL, "Schedule job type dropdown is not visible");	
		 //captureScreenshot(driver);
	}

}
   public void enableScheduleJobType()throws Throwable
	 {
		 test=report.startTest("PI_Enable Schedule Job Type Dropdown"); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 boolean jobtype= driver.findElement(By.xpath("(//div[@class='selectbutton'])[4]")).isEnabled();
		 System.out.println("Schedule job type dropdown is enable");
		 if (jobtype) 
		 {
			 test.log(LogStatus.PASS, "Schedule job type dropdown is enable");
			 //captureScreenshot(driver);
		} 
		 else
		 {
			 test.log(LogStatus.FAIL, "Schedule job type dropdown is not enable");
			 //captureScreenshot(driver);
		}
	 }
   public void ClickScheduleJobType() throws Throwable
   {
  	 test=report.startTest("PI_Click Schedule Job Type Dropdown");
  	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

  	 driver.findElement(By.xpath("(//div[@class='selectbutton'])[4]")).click();
  	 //System.out.println(addicon);
  	 //assertEquals(addicon, true);
  	 test.log(LogStatus.PASS, "Schedule job type dropdown is clickable");
  	 //captureScreenshot(driver);

  	 }
   
   public void ACLvisible() throws Throwable 
   {
  	 test=report.startTest("PI_Visible ACL Session ");
  	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  	 boolean Acl= driver.findElement(By.xpath("//button[text()='ACL']")).isDisplayed();
  	 System.out.println("ACL session is visible");
  	 //assertEquals(Info, true);
  	 //test.log(LogStatus.PASS, "Info is visible ");
  	 ////captureScreenshot(driver);
  	 if(Acl)
  	 {
  		 test.log(LogStatus.PASS, "ACL session is visible");
  		 //captureScreenshot(driver);
  	 }
  	 else
  	 {
  		 test.log(LogStatus.FAIL, "ACL session is not visible");
  		 //captureScreenshot(driver);
  	 }
  	 }
  	public void ACLenable() throws Throwable 
    {
   	 test=report.startTest("PI_Enable ACL Session");
   	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	 boolean Ac= driver.findElement(By.xpath("//button[text()='ACL']")).isEnabled();
   	 System.out.println("ACL session is enabled");
   	 //assertEquals(Info, true);
   	 //test.log(LogStatus.PASS, "Info is visible ");
   	 ////captureScreenshot(driver);
   	 if(Ac)
   	 {
   		 test.log(LogStatus.PASS, "ACL session is enabled");
   		 //captureScreenshot(driver);
   	 }
   	 else
   	 {
   		 test.log(LogStatus.FAIL, "ACL session is not enabled");
   		 //captureScreenshot(driver);
   	 }
    }
  	public void DiscardButtonVisible() throws Throwable 
    {
   	 test=report.startTest("PI_Visible Discard Button ");
   	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	 boolean discard= driver.findElement(By.xpath("//img[@src='./assets/images/discard.svg']")).isDisplayed();
   	 System.out.println("Discard button is visible");
   	 //assertEquals(Info, true);
   	 //test.log(LogStatus.PASS, "Info is visible ");
   	 ////captureScreenshot(driver);
   	 if(discard)
   	 {
   		 test.log(LogStatus.PASS, "Discard button is visible");
   		 //captureScreenshot(driver);
   	 }
   	 else
   	 {
   		 test.log(LogStatus.FAIL, "Discard button is not visible");
   		 //captureScreenshot(driver);
   	 }
   	 }
  	public void DiscardButtonEnable() throws Throwable 
    {
   	 test=report.startTest("PI_Enable Discard Button");
   	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	 boolean dis= driver.findElement(By.xpath("//img[@src='./assets/images/discard.svg']")).isEnabled();
   	 System.out.println("Discard button is enabled");
   	 //assertEquals(Info, true);
   	 //test.log(LogStatus.PASS, "Info is visible ");
   	 ////captureScreenshot(driver);
   	 if(dis)
   	 {
   		 test.log(LogStatus.PASS, "Discard button is enabled");
   		 //captureScreenshot(driver);
   	 }
   	 else
   	 {
   		 test.log(LogStatus.FAIL, "Discard button is not enabled");
   		 //captureScreenshot(driver);
   	 }
    }
  	public void SaveButtonVisible() throws Throwable 
    {
   	 test=report.startTest("PI_Visible Save Button ");
   	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	 boolean save= driver.findElement(By.xpath("//img[@src='./assets/images/save.svg']")).isDisplayed();
   	 System.out.println("Save button is visible");
   	 //assertEquals(Info, true);
   	 //test.log(LogStatus.PASS, "Info is visible ");
   	 ////captureScreenshot(driver);
   	 if(save)
   	 {
   		 test.log(LogStatus.PASS, "Save button is visible");
   		 //captureScreenshot(driver);
   	 }
   	 else
   	 {
   		 test.log(LogStatus.FAIL, "Save button is not visible");
   		 //captureScreenshot(driver);
   	 }
   	 }
  	public void SaveButtonEnable() throws Throwable 
    {
   	 test=report.startTest("PI_Enable Save Button");
   	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	 boolean dis= driver.findElement(By.xpath("//img[@src='./assets/images/save.svg']")).isEnabled();
   	 System.out.println("Save button is enabled");
   	 //assertEquals(Info, true);
   	 //test.log(LogStatus.PASS, "Info is visible ");
   	 ////captureScreenshot(driver);
   	 if(dis)
   	 {
   		 test.log(LogStatus.PASS, "Save button is enabled");
   		 //captureScreenshot(driver);
   	 }
   	 else
   	 {
   		 test.log(LogStatus.FAIL, "Save button is not enabled");
   		 //captureScreenshot(driver);
   	 }
    }
	
}

