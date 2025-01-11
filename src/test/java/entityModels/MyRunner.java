package entityModels;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class MyRunner {
	public static String report1;
	public static WebDriver driver;
	@Test
	public void PI_UI() throws Throwable {
		
		Login keys= new Login();
		LoginAndDashboardFunc keys2 = new LoginAndDashboardFunc();
		Groups keys3 = new Groups();
		Context keys4 = new Context();
		DataIngest inject = new DataIngest();
		Groupcreation keys5 = new Groupcreation();
		EntityCreation Ec = new EntityCreation();
		//to specify Keywords file location
		FileInputStream fi= new FileInputStream("data/testData.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet("Runner");
		
		//to find number of rows
		int rowCount=ws.getLastRowNum();
		for(int i=1; i<= rowCount; i++) {
			XSSFRow row=ws.getRow(i);
			//to read Run mode 
			String runMode=row.getCell(4).getStringCellValue();
			System.out.println(runMode);
			if (runMode.equals("Y")) {
			
//to read Step DEscription
				String keyWord=row.getCell(2).getStringCellValue();
				System.out.println(keyWord);
				switch (keyWord) {
				case "Launch Browser":
					keys.launchBrowser();
					break;
				case "Navigate to Url":
					keys.navigateURL();
					break;	
				case "Verify Mobius Logo Visiblity" :
					keys2.mobiusLogoVisibility();
					break;
				case "Verify Username Field Visibility" :
					keys2.userNameFieldVis();
					break;
				case "Verify Username Field Clickable" :
					keys2.userNameFieldCli();
					break;
				case "Verify Password Field Visibility" :
					keys2.passwordFieldVis();
					break;
				case "Verify Password Field Clickable" :
					keys2.passwordFieldCli();
					break;
				case "Verify Rememberme Checkbox Visibility" :
					keys2.rememberMeVis();
					break;
				case "Verify Rememberme Checkbox Clickable" :
					keys2.rememberMeCli();
					break;
				case "Verify Forgot Password Link Visibility" :
					keys2.forgotPasswordVis();
					break;
				case "Verify Forgot Password Link Clickable" :
					keys2.forgotPasswordCli();
					break;
				case "Verify Login Button Visible" :
					keys2.loginButtonVis();
					break;
				case "Verify Login Button Clickable" :
					keys2.loginButtonCli();
					break;
				case "Verify Register Now Link Visibility" :
					keys2.registerNowVis();
					break;
				case "Verify Register Now Link Clickable" :
					keys2.registerNowCli();
					break;
				case "Verify Number of Links" :
					keys2.numberOfLinks();
					break;
				case "Enter the Username":
					keys.enterUsername();
					break;
				case "Enter the Password":
					keys.enterPassword();
					break;
				case "Click on Login button":
					keys.clickLogin();
					break;	
				case "Verify Login Success Toast" :
					keys2.loginToastMessage();
					break;
				case "Verify Page Title":
					keys.pageTitle();
					break;
				case "Verify PI logo" :
					keys2.piLogoVis();
					break;
				case "Verify Universe Button Visibility" :
					keys2.universeButtonVis();
					break;
				case "Verify Universe Button Clickable" :
					keys2.universeButtonCli();
					break;
				case "Verify Entity Button Visibility" :
					keys2.entityButtonVis();
					break;
				case "Verify Entity Button Clickable" :
					keys2.entityButtonCli();
					break;
				case "Verify Groups Button Visibility" :
					keys2.groupsButtonVis();
					break;
				case "Verify Groups Button Clickable" :
					keys2.groupsButtonCli();
					break;
				case "Verify Big Queries Button Visibility" :
					keys2.bigQueriesButtonVis();
					break;
				case "Verify Big Queries Button Clickable" :
					keys2.bigQueriesButtonCli();
					break;
				case "Verify Visualisations Button Visibility" :
					keys2.VisualsButtonVis();
					break;
				case "Verify Visualisations Button Clickable" :
					keys2.VisualsButtonCli();
					break;
				case "Verify Contexts Button Visibility" :
					keys2.contextsButtonVis();
					break;
				case "Verify Contexts Button Clickable" :
					keys2.contextsButtonCli();
					break;
				case "Verify Engagements Button Visibility" :
					keys2.engagaementsButtonVis();
					break;
				case "Verify Engagements Button Clickable" :
					keys2.engagaementsButtonCli();
					break;
				case "Verify Data Ingests Button Visibility" :
					keys2.dataIngestsButtonVis();
					break;
				case "Verify Data Ingests Button Clickable" :
					keys2.dataIngestsButtonCli();
					break;
				case "Verify Log Out Button Visibility" :
					keys2.logOutButtonVis();
					break;
				case "Verify Log Out Button Clickable" :
					keys2.logOutButtonCli();
					break;
				case "Verify Annotate Button Visibility" :
					keys2.annotateButtonVis();
					break;
				case "Verify Annotate Button Clickable" :
					keys2.annotateButtonCli();
					break;
				case "Verify Data Button Visibility" :
					keys2.dataButtonVis();
					break;
				case "Verify Data Button Clickable" :
					keys2.dataButtonCli();
					break;
				case "Verify Play Button Visibility" :
					keys2.playButtonVis();
					break;
				case "Verify Play Button Clickable" :
					keys2.playButtonCli();
					break;
				case "Verify Timeline Button Visibility" :
					keys2.timelineButtonVis();
					break;
				case "Verify Timeline Button Clickable" :
					keys2.timelineButtonCli();
					break;
				case "Verify OpenAI Button Visibility" :
					keys2.openAIButtonVis();
					break;
				case "Verify OpenAI Button Clickable" :
					keys2.openAIButtonCli();
					break;
				case "Verify Serendipity Button Visibility" :
					keys2.serendipityButtonVis();
					break;
				case "Verify Serendipity Button Clickable" :
					keys2.serendipityButtonCli();
					break;
				case "Verify Infinity Button Visibility" :
					keys2.infinityButtonVis();
					break;
				case "Verify Infinity Button Clickable" :
					keys2.infinityButtonCli();
					break;
				case "Verify Fit To View Button Visibility" :
					keys2.fitToViewButtonVis();
					break;
				case "Verify Fit To View Button Clickable" :
					keys2.fitToViewButtonCli();
					break;
				case "Verify 2D3D Button Visibility" :
					keys2.d2d3ButtonVis();
					break;
				case "Verify 2D3D Button Clickable" :
					keys2.d2d3ButtonCli();
					break;
				case "Verify Drag Button Visibility" :
					keys2.dragOpenButtonVis();
					break;
				case "Verify Drag Button Clickable" :
					keys2.dragOpenButtonCli();
					break;
				case "GroupAddBtn":
					keys3.add();
					break;
				case"EnableGroupAdd":
					keys3.enableadd();
					break;
				case "GroupClickAdd":
					keys3.clickadd();
					break;
				case "contructdropdown":
					keys3.contructdropdown();
					break;
				
				case "enableconstructdropdown":
					keys3.enablecontructdropdown();
					break;
				
				//case"clickconstructdropdown":
					//keys1.clickcontructdropdown();
					//break;
				//case"contructsdropdown":
					//keys1.contructsdropdown();
					//break;
				//case"grouppresent":
					//keys1.grouppresent();
					//break;
				case "clickcontructdropdown1":
					keys3.clickcontructdropdown1();
					break;
				case"groupconstructvisible":
					keys3.groupconstructvisible();
					break;
				case"groupconstruct":
					keys3.groupconstruct();
					break;
				case"infosession":
					keys3.infoseesion();
					break;
				case"selectuniverse":
					keys3.selectuniverse();
					break;
				case"universeenble":
					keys3.universeenble();
					break;
				case"clickuniverse":
					keys3.clickuniverse();
					break;
				case"selectschema":
					keys3.selectschema();
					break;
				case"selectschemaenable":
					keys3.selectschemaenable();
					break;
				case"groupName":
					keys3.groupName();
					break;
				case"groupdDescriptrion":
					keys3.groupdDescriptrion();
					break;
				
				case"groupdDescriptrionEnable":
					keys3.groupdDescriptrionEnable();
					break;
				case"radioBtn":
					keys3.radioBtn();
					break;
				case"manualconditionselected":
					keys3.manualconditionselected();
					break;
				case"query":
					keys3.query();
					break;
				case"querybutton":
					keys3.querybutton();
					break;
				case"sourceattributeType":
					keys3.sourceattributeType();
					break;
				case"sourceattributeEnable":
					keys3.sourceattributeEnable();
					break;
				case"sourceCondition":
					keys3.sourceCondition();
					break;
				case"sourceConditionEnable":
					keys3.sourceConditionEnable();
					break;
				case"clickSourceCondition":
					keys3.clickSourceCondition();
					break;
				//case"clickSourceattribute":
					//keys1.clickSourceattribute();
					//break;
					
				case"destinationAttribute":
					keys3.destinationAttribute();
					break;
				//case"clickDestinationattribute":
					//keys1.clickDestinationattribute();
					//break;
				
				case"addRule":
					keys3.addRule();
					break;
				case"addRuleEnable":
					keys3.addRuleEnable();
					break;
				case"clcikaddRule":
					keys3.clcikaddRule();
					break;
				case"aclbutton":
					keys3.aclbutton();
					break;
				case"aclEnable":
					keys3.aclEnable();
					break;
				case"clickAclbtn":
					keys3.clickAclbtn();
					break;
				case"readaccess":
					keys3.readAcess();
					break;
				case"readaccessEnable":
					keys3.readaccessEnable();
					break;
				case"discardbutton":
					keys3.discardbutton();
					break;
				
				case"discardbuttonEnable":
					keys3.discardbuttonEnable();
					break;
				case"savebutton":
					keys3.savebutton();
					break;
				case"savebuttonEnable":
					keys3.savebuttonEnable();
					break;
				case "ContextAddBtn":
					keys4.addicon();
					break;
				case"EnableContextAdd":
					keys4.enableadd1();
					break;
				case "ContextclickAdd":
					keys4.clickadd1();
					break;
				case"clickcontructdropdown2":
					keys4.clickcontructdropdown2();
						
				//case"contextpresent":
					//keys2.contextpresent1();
					//break;
				
				case"contextconstructvisible":
					keys4.contextconstructvisible();
					break;
				case"contextconstruct":
					keys4.contextconstruct();
					break;
				case"infosession1":
					keys4.infoseesion1();
					break;
				case"selectuniverse1":
					keys4.selectuniverse1();
					break;
				case"universeenble1":
					keys4.universeenble1();
					break;
				case"clickuniverse1":
					keys4.clickuniverse1();
					break;
				case"selectschema1":
					keys4.selectschema1();
					break;
				case"selectschemaenable1":
					keys4.selectschemaenable1();
					break;
				case"contextName":
					keys4.contextName();
					break;
				case"contextNameEnable":
					keys4.contextNameEnable();
				case"contextDescriptrion":
					keys4.contextDescriptrion();
					break;
				
				case"contextDescriptrionEnable":
					keys4.contextDescriptrionEnable();
					break;
				case"sourceattributeType1":
					keys4.sourceattributeType1();
					break;
				case"sourceattributeEnable1":
					keys4.sourceattributeEnable1();
					break;
				//case"clickSourceattribute1":
					//keys2.clickSourceattribute1();
					//break;
				case"sourceCondition1":
					keys4.sourceCondition1();
					break;
				case"sourceConditionEnable1":
					keys4.sourceConditionEnable1();
					break;
				case"clickSourceCondition1":
					keys4.clickSourceCondition1();
					break;
				case"destinationAttribute1":
					keys4.destinationAttribute1();
					break;
				//case"clickDestinationattribute1":
					//keys2.clickDestinationattribute1();
					//break;
				case"schudleType":
					keys4.schudleType();
					break;
				case"schudleType1":
					keys4.schudleType1();
					break;
				case"aclbutton1":
					keys4.aclbutton1();
					break;
				case"aclbuttonEnable1":
					keys4.aclbuttonEnable1();
					break;
				case"acldropdownvisible1":
					keys4.acldropdownvisible1();
					break;
				case"acldropdownEnable1":
					keys4.acldropdownEnable1();
					break;
				case"readAccess":
					keys4.readAcess1();
					break;
				case"readAccessEnable":
					keys4.readaccessEnable1();
					break;
				case"discardbutton1":
					keys4.discardbutton1();
					break;
				
				case"discardbuttonEnable1":
					keys4.discardbuttonEnable1();
					break;
				case"savebutton1":
					keys4.savebutton1();
					break;
				case"savebuttonEnable1":
					keys4.savebuttonEnable1();
					break;
				case "IngestAdd":
					inject.add();
					break;
				case "EnableIngestAdd":
					inject.enableadd();
					break;
				case "IngestClickAdd":
					inject.clickadd();
					break;
				case "constructdropdown":
					inject.constructdropdown();
					break;
				case "enableconstuctdropdown":
					inject.enableconstuctdropdown();
					break;
				case "clickconstructdropdown":
					inject.clickconstructdropdown();
					break;
				/*case "contructsdropdown":
					inject.contructsdropdown();
					break;
				case "DataIngestpresent":
					inject.DataIngestpresent();
					break;*/
				case "dropdownclick":
					inject.dropdownclick();
					break;
				/*case "DataIngestconstructvisible":
					inject.DataIngestconstructvisible();
					break;
				case "ClickDataIngestconstruct":
					inject.ClickDataIngestconstruct();
					break;*/
				case "Infovisible":
					inject.Infovisible();
					break;
				case "Infoenable":
					inject.Infoenable();
					break;
				case "Selectuniversevisible":
					inject.Selectuniversevisible();
					break;
				case "enableuniverse":
					inject.enableuniverse();
					break;
				case "Clickselectuniverse":
					inject.Clickselectuniverse();
					break;
				case "IngestionNamevisible":
					inject.IngestionNamevisible();
					break;
				case "enableIngestionName":
					inject. enableIngestionName();
					break;
				case "IngestionDescriptionvisible":
					inject.IngestionDescriptionvisible();
					break;
				case "enableIngestionDescription":
					inject.enableIngestionDescription();
					break;
				case "ConfigurationNamevisible":
					inject.ConfigurationNamevisible();
					break;
				case "enableConfigurationName":
					inject.enableConfigurationName();
					break;
				case "ConfigurationDescriptionvisible":
					inject.ConfigurationDescriptionvisible();
					break;
				case "enableConfigurationDescription":
					inject.enableConfigurationDescription();
					break;
				case "SelectSourcevisible":
					inject.SelectSourcevisible();
					break;
				case "enableselectsource":
					inject.enableselectsource();
					break;
				case "Clickselectsource":
					inject.Clickselectsource();
					break;
				case "ConfigureSourceMappingvisible":
					inject.ConfigureSourceMappingvisible();
					break;
				case "ConfigureSourceMappingselected":
					inject.ConfigureSourceMappingselected();
					break;
				case "CreateNewEnityvisible":
					inject. CreateNewEnityvisible();
					break;
				case "CreateNewEnityselected":
					inject.CreateNewEnityselected();
					break;
				case "ConfigureSourceMappingLinkvisible":
					inject.ConfigureSourceMappingLinkvisible();
					break;
				case "ConfigureSourceMappingLinkenable":
					inject.ConfigureSourceMappingLinkenable();
					break;
				case "ScheduleJobTypevisible":
					inject.ScheduleJobTypevisible();
					break;
				case "enableScheduleJobType":
					inject.enableScheduleJobType();
					break;
				case "ClickScheduleJobType":
					inject.ClickScheduleJobType();
					break;
				case "ACLvisible":
					inject.ACLvisible();
					break;
				case "ACLenable":
					inject.ACLenable();
					break;
				case "DiscardButtonVisible":
					inject.DiscardButtonVisible();
					break;
				case "DiscardButtonEnable":
					inject.DiscardButtonEnable();
					break;
				case "SaveButtonVisible":
					inject.SaveButtonVisible();
					break;
				case "SaveButtonEnable":
					inject.SaveButtonEnable();
					break;
				case "Add":
					keys5.add();
					break;
				
				case "Constructor Dropdown":
					keys5.constructordropdown();
					break;
				case "Info":
					keys5.infosection();
					break;
				case "Select Universe":
					keys5.selectUniverse();
					break;
				case "selectSchema":
					keys5.selectschema();
				break;
			
				case "Group Name":
					keys5.groupName();
					break;
				case "Group Description":
					keys5.groupdescription();
					break;
				case "Query Radiobutton":
					keys5.queryradiobutton();
					break;
				case "Query":
					keys5.query();
					break;
				case "ACL section":
					keys5.aclsection();
					break;
				case "ACL dropdown":
					keys5.acldropdown();
					break;
				case "Save":
					keys5.save();
					break;
				case "Click on add button":
				    Ec.addButton();
					break;	
				case "Click on EntityModel":
					Ec.entityModel();
					break;	
				case "Select Universe1":
					Ec.selectUniverse();
					break;	
				case "Enter a name of  an Entity Module":
					Ec.enterEntityModuleName();
					break;
				case "Enter entity description":
					Ec.enterEntityModuleDescription();
					break;
				case "Upload File":
					Ec.uplodefile();
					break;
				case "Enter a atrribute name":
					Ec.enterAttributeName();
					break;
					
				case "Enter a default value":
					Ec.enter_defaultvalue();
					break;
				case "Select required value":
					Ec.requiredDropdown();
					break;
				
				case "Select data type":
					Ec.dataTypeSelection();
					break;
				case "Primary key selection":
					Ec.primaryKeySelection();
					break;
				case "click on ACL button":
					Ec.aCLbutton();
					break;
					
				case "Click on Save Button":
					Ec.saveButton();
					break;
				case "Entity model read access ":
					Ec.entityModelReadAccess();
					break;
					
				case "Data read access":
					Ec.dataReadAccess();
					break;
				case "Data write access":
					Ec.dataWriteAccess();
					break;
				case "visiblity access":
					Ec.dataWriteAccess();
					break;

				}
		
			
		 }
	   }
	}
	
	public static void captureScreenshot(WebDriver driver) {
        // Capture the screenshot and add it to the ExtentReports log
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            String screenshotBase64 = screenshotDriver.getScreenshotAs(OutputType.BASE64);
            test.log(LogStatus.INFO, "Screenshot:",
                    test.addBase64ScreenShot("data:image/png;base64," + screenshotBase64));
        }
    } 
	
	 public static String getDataFromExcel(String sheetName, int rowNum, int colNum) throws Exception
	  {
		FileInputStream fi= new FileInputStream("data/testData.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet(sheetName);
		DataFormatter df=new DataFormatter();
		String data=df.formatCellValue(ws.getRow(rowNum).getCell(colNum));
		return data;
	  }



	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void startTest() {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
		report1 = "PI UI Automation test report_" + fileName;
		System.out.println(report1);
		report = new ExtentReports(System.getProperty("user.dir") + "" + report1);

		report = new ExtentReports(System.getProperty("user.dir") + "/" + report1);

	}

	@AfterClass
	public static void endTest() throws IOException {
		// End the test and generate the report
		report.endTest(test);
		report.flush();

//		try {
//			Sendmail.sendTestReportEmail();
//
//		} catch (EmailException e) {
//			e.printStackTrace();
//		}

	}
}


