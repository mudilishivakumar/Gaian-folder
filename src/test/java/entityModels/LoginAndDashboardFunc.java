package entityModels;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

public class LoginAndDashboardFunc extends MyRunner{
	
	public void mobiusLogoVisibility() throws InterruptedException
	{
	    test=report.startTest("Mobius_Logo_Visiblity");	
	    boolean mobiusIconVis =driver.findElement(By.xpath("//img[@class='Mobius-logo']")).isDisplayed();
	    System.out.println(mobiusIconVis);
	    if(mobiusIconVis)
		{
			test.log(LogStatus.PASS, "Mobius logo is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Mobius logo is not visible");
			//captureScreenshot(driver);
		}
	}
	
 public void userNameFieldVis()
 {
	 test=report.startTest("Username_Field_Visiblity");
	 boolean userNameVis = driver.findElement(By.xpath("//input[@placeholder='Enter username']")).isDisplayed();
		System.out.println(userNameVis);
		if(userNameVis)
		{
			test.log(LogStatus.PASS, "Username field is visible");
			//captureScreenshot(driver);
		}else 
		{
			test.log(LogStatus.FAIL, "Username field is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void userNameFieldCli()
 {
	 test=report.startTest("Username_Field_Enabled");
	 boolean UserNameCli = driver.findElement(By.xpath("//input[@placeholder='Enter username']")).isEnabled();
		System.out.println(UserNameCli);
		if(UserNameCli)
		{
			test.log(LogStatus.PASS, "Username field is enabled");
			//captureScreenshot(driver);
		}else 
		{
			test.log(LogStatus.FAIL, "Username field is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void passwordFieldVis()
 {
	 test=report.startTest("Password_Field_Visiblity");
	 boolean passwordVis = driver.findElement(By.xpath("//input[@placeholder='Enter password']")).isDisplayed();
	 System.out.println(passwordVis);
	 if(passwordVis)
		{
			test.log(LogStatus.PASS, "Password field is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Password field is not visible");
			//captureScreenshot(driver);
		}
		
 }
 
 public void passwordFieldCli()
 {
	 test=report.startTest("Password_Field_Enabled");
	 boolean passwordCli = driver.findElement(By.xpath("//input[@placeholder='Enter password']")).isEnabled();
		System.out.println(passwordCli);
		if(passwordCli)
		{
			test.log(LogStatus.PASS, "Password field is enabled");
			//captureScreenshot(driver);
		}else 
		{
			test.log(LogStatus.FAIL, "Password field is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void rememberMeVis()
 {
	 test=report.startTest("RememberMe_Checkbox_Visiblity");
	 boolean rememberMeVis = driver.findElement(By.xpath("//span[text()=' Remember me ']")).isDisplayed();
		System.out.println(rememberMeVis);
		if(rememberMeVis)
		{
			test.log(LogStatus.PASS, "Remember me checkbox is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Remember me checkbox is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void rememberMeCli()
 {
	 test=report.startTest("RememberMe_Checkbox_Enabled");
	 boolean rememberMeCli = driver.findElement(By.xpath("//span[text()=' Remember me ']")).isDisplayed();
		System.out.println(rememberMeCli);
		if(rememberMeCli)
		{
			test.log(LogStatus.PASS, "Remember me checkbox is enabled");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Remember me checkbox is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void forgotPasswordVis()
 {
	 test=report.startTest("Forgot_Password_Link_Visiblity");
	 boolean forgotpassVis = driver.findElement(By.xpath("//a[text()='Forgot password']")).isDisplayed();
		System.out.println(forgotpassVis);
		if(forgotpassVis)
		{
			test.log(LogStatus.PASS, "Forgot password link is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Forgot password link is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void forgotPasswordCli()
 {
	 test=report.startTest("Forgot_Password_Link_Enabled");
	 boolean forgotpassCli = driver.findElement(By.xpath("//a[text()='Forgot password']")).isDisplayed();
		System.out.println(forgotpassCli);
		if(forgotpassCli)
		{
			test.log(LogStatus.PASS, "Forgot password link is enabled");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Forgot password link is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void loginButtonVis()
 {
	 test=report.startTest("Login_Button_Visiblity");
	 boolean loginVis = driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed();
		System.out.println(loginVis);
		if(loginVis)
		{
			test.log(LogStatus.PASS, "Login button is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Login button is not visible");
			//captureScreenshot(driver);
		}

 }
 
 public void loginButtonCli()
 {
	 test=report.startTest("Login_Button_Enabled");
	 boolean loginCli = driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed();
		System.out.println(loginCli);
		if(loginCli)
		{
			test.log(LogStatus.PASS, "Login button is enabled");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Login button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void registerNowVis()
 {
	 test=report.startTest("RegisterNow_Visiblity");
	 boolean registerVis = driver.findElement(By.xpath("//a[text()='Register Now']")).isDisplayed();
		System.out.println(registerVis);
		if(registerVis)
		{
			test.log(LogStatus.PASS, "Register now link is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Register now link is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void registerNowCli()
 {
	 test=report.startTest("RegisterNow_Enabled");
	 boolean registerVis = driver.findElement(By.xpath("//a[text()='Register Now']")).isDisplayed();
		System.out.println(registerVis);
		if(registerVis)
		{
			test.log(LogStatus.PASS, "Register now link is enabled");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "Register now link is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void numberOfLinks()
 {
	 test=report.startTest("Links_Available");
	 int links =driver.findElements(By.tagName("a")).size();
	 System.out.println(links);
	test.log(LogStatus.PASS, "Number of links fetched successfully");
	//captureScreenshot(driver);
 }
 
 public void loginToastMessage() throws InterruptedException
 {
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	 test=report.startTest("Login_Success_Message_Visiblity");
	 boolean blnLoginMsg = driver.findElement(By.xpath("//body/app-root[1]/app-toaster[1]/div[1]/div[1]/app-toast[1]/div[1]/div[1]/div[1]/p[2]")).isDisplayed();
	 if(blnLoginMsg)
	 {
	 test.log(LogStatus.PASS, "Login success message is displayed");
	 //captureScreenshot(driver);
	 }
	 else
	 {
		 test.log(LogStatus.FAIL, "Login success message is not displayed");
		 //captureScreenshot(driver);
	 }

 }
 
 public void piLogoVis()
	{
	    test=report.startTest("PI_Logo_Visiblity");
		boolean piLogoVis =driver.findElement(By.xpath("//img[@class='pi-img']")).isDisplayed();
		System.out.println(piLogoVis);
		if(piLogoVis)
		{
			test.log(LogStatus.PASS, "PI logo is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "PI logo is not visible");
			//captureScreenshot(driver);
		}
	} 
 
 public void piTextVis()
	{
	    test=report.startTest("PI_Logo_Visiblity");
		boolean piTextVis =driver.findElement(By.xpath("//span[text()='PI']")).isDisplayed();
		System.out.println(piTextVis);
		if(piTextVis)
		{
			test.log(LogStatus.PASS, "PI text is visible");
			//captureScreenshot(driver);
		}
	 else 
		{
			test.log(LogStatus.FAIL, "PI text is not visible");
			//captureScreenshot(driver);
		}
	}
 
 public void universeButtonVis()
 {
	 test=report.startTest("Universe_Button_Visiblity");
		boolean universebutVis = driver.findElement(By.xpath("//span[text()='Universe']")).isDisplayed();
		System.out.println(universebutVis);
		if(universebutVis)
		{
			test.log(LogStatus.PASS, "Universe button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Universe button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void universeButtonCli()
 {
	 test=report.startTest("Universe_Button_Enabled");
		boolean universebutCli = driver.findElement(By.xpath("//span[text()='Universe']")).isEnabled();
		System.out.println(universebutCli);
		if(universebutCli)
		{
			test.log(LogStatus.PASS, "Universe button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Universe button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void entityButtonVis()
 {
	 test=report.startTest("Entity_Button_Visiblity");
		boolean entityButVis = driver.findElement(By.xpath("//span[text()='Universe']")).isDisplayed();
		System.out.println(entityButVis);
		if(entityButVis)
		{
			test.log(LogStatus.PASS, "Entity button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Enity button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void entityButtonCli()
 {
	 test=report.startTest("Entity_Button_Enabled");
		boolean entityButVis = driver.findElement(By.xpath("//span[text()='Universe']")).isEnabled();
		System.out.println(entityButVis);
		if(entityButVis)
		{
			test.log(LogStatus.PASS, "Entity button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Enity button button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void groupsButtonVis()
 {
	 test=report.startTest("Groups_Button_Visiblity");
		boolean groupsButVis = driver.findElement(By.xpath("//span[text()='Groups']")).isDisplayed();
		System.out.println(groupsButVis);
		if(groupsButVis)
		{
			test.log(LogStatus.PASS, "Groups button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Groups button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void groupsButtonCli()
 {
	 test=report.startTest("Entity_Button_Enabled");
		boolean entityButVis = driver.findElement(By.xpath("//span[text()='Groups']")).isEnabled();
		System.out.println(entityButVis);
		if(entityButVis)
		{
			test.log(LogStatus.PASS, "Group button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Group button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void bigQueriesButtonVis()
 {
	 test=report.startTest("Big_Queries_Button_Visiblity");
		boolean bigQueriesButVis = driver.findElement(By.xpath("//span[text()='Big Queries']")).isDisplayed();
		System.out.println(bigQueriesButVis);
		if(bigQueriesButVis)
		{
			test.log(LogStatus.PASS, "Big queries button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Big queries button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void bigQueriesButtonCli()
 {
	 test=report.startTest("Big_Queries_Button_Enabled");
		boolean bigQueriesButCli = driver.findElement(By.xpath("//span[text()='Big Queries']")).isEnabled();
		System.out.println(bigQueriesButCli);
		if(bigQueriesButCli)
		{
			test.log(LogStatus.PASS, "Big queries button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Big queries button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void VisualsButtonVis()
 {
	 test=report.startTest("Visualisations_Button_Visiblity");
		boolean visualButVis = driver.findElement(By.xpath("//span[text()='Visualisations']")).isDisplayed();
		System.out.println(visualButVis);
		if(visualButVis)
		{
			test.log(LogStatus.PASS, "Visualisations button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Visualisations button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void VisualsButtonCli()
 {
	 test=report.startTest("Visualisation_Button_Enabled");
		boolean visualButCli = driver.findElement(By.xpath("//span[text()='Visualisations']")).isEnabled();
		System.out.println(visualButCli);
		if(visualButCli)
		{
			test.log(LogStatus.PASS, "Visualisations button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Visualisations button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void contextsButtonVis()
 {
	 test=report.startTest("Contexts_Button_Visiblity");
		boolean contextButVis = driver.findElement(By.xpath("//span[text()='Contexts']")).isDisplayed();
		System.out.println(contextButVis);
		if(contextButVis)
		{
			test.log(LogStatus.PASS, "Contexts button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Contexts button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void contextsButtonCli()
 {
	 test=report.startTest("Contexts_Button_Enabled");
		boolean contextButCli = driver.findElement(By.xpath("//span[text()='Contexts']")).isEnabled();
		System.out.println(contextButCli);
		if(contextButCli)
		{
			test.log(LogStatus.PASS, "Contexts button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Contexts button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void engagaementsButtonVis()
 {
	 test=report.startTest("Engagements_Button_Visiblity");
		boolean engagementsButVis = driver.findElement(By.xpath("//span[text()='Engagements']")).isDisplayed();
		System.out.println(engagementsButVis);
		if(engagementsButVis)
		{
			test.log(LogStatus.PASS, "Engagements button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Engagements button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void engagaementsButtonCli()
 {
	 test=report.startTest("Engagements_Button_Enabled");
		boolean contextButCli = driver.findElement(By.xpath("//span[text()='Engagements']")).isEnabled();
		System.out.println(contextButCli);
		if(contextButCli)
		{
			test.log(LogStatus.PASS, "Engagements button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Engagements button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void dataIngestsButtonVis()
 {
	 test=report.startTest("Data_Ingests_Button_Visiblity");
		boolean dataingesButVis = driver.findElement(By.xpath("//span[text()='Data Ingests']")).isDisplayed();
		System.out.println(dataingesButVis);
		if(dataingesButVis)
		{
			test.log(LogStatus.PASS, "Data ingests button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Data ingests button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void dataIngestsButtonCli()
 {
	 test=report.startTest("Data_Ingests_Button_Enabled");
		boolean dataingesButCli = driver.findElement(By.xpath("//span[text()='Data Ingests']")).isEnabled();
		System.out.println(dataingesButCli);
		if(dataingesButCli)
		{
			test.log(LogStatus.PASS, "Data ingests button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Data ingests button is not enabled");
			//captureScreenshot(driver);
		}
 }
 
 public void logOutButtonVis()
 {
	 test=report.startTest("Log_Out_Button_Visiblity");
	  Actions act = new Actions(driver);
	    WebElement movetopi = driver.findElement(By.xpath("//span[text()='PI']"));
	    act.moveToElement(movetopi).perform();
		boolean logOutButVis = driver.findElement(By.xpath("//span[text()=' Logout ']")).isDisplayed();
		System.out.println(logOutButVis);
		if(logOutButVis)
		{
			test.log(LogStatus.PASS, "Log out button is visible");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Log out button is not visible");
			//captureScreenshot(driver);
		}
 }
 
 public void logOutButtonCli()
 {
	 test=report.startTest("Log_Out_Button_Enabled");
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		boolean logOutButCli = driver.findElement(By.xpath("//span[@class='logout']")).isEnabled();
		System.out.println(logOutButCli);
		if(logOutButCli)
		{
			test.log(LogStatus.PASS, "Log out button is enabled");
			//captureScreenshot(driver);
		}
	    else 
		{
			test.log(LogStatus.FAIL, "Log out button is not enabled");
			//captureScreenshot(driver);
		}
 }
		
		public void annotateButtonVis()
		 {
			 test=report.startTest("Annotate_Button_Visiblity");
				boolean AnnotateButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/anotate.svg']")).isDisplayed();
				System.out.println(AnnotateButVis);
				if(AnnotateButVis)
				{
					test.log(LogStatus.PASS, "Annotate button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Annotate button is not visible");
					//captureScreenshot(driver);
				}
		 }
		
		 public void annotateButtonCli()
		 {
			 test=report.startTest("Annotate_Button_Enabled");
				boolean AnnotateButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/anotate.svg']")).isEnabled();
				System.out.println(AnnotateButCli);
				if(AnnotateButCli)
				{
					test.log(LogStatus.PASS, "Annotate button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Annotate button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void dataButtonVis()
		 {
			 test=report.startTest("Data_MetaData_Preview_Button_Visiblity");
				boolean dataButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/Book Close 4.svg']")).isDisplayed();
				System.out.println(dataButVis);
				if(dataButVis)
				{
					test.log(LogStatus.PASS, "Data/metadata preview button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Data/metadata preview button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void dataButtonCli()
		 {
			 test=report.startTest("Data_MetaData_Preview_Button_Enabled");
				boolean dataButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/Book Close 4.svg']")).isEnabled();
				System.out.println(dataButCli);
				if(dataButCli)
				{
					test.log(LogStatus.PASS, "Data/metadata preview button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Data/metadata preview button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void playButtonVis()
		 {
			 test=report.startTest("Play_Button_Visiblity");
				boolean playButVis = driver.findElement(By.xpath("//div[@class='pauseplay clear']")).isDisplayed();
				System.out.println(playButVis);
				if(playButVis)
				{
					test.log(LogStatus.PASS, "Play button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Play button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 
		 public void playButtonCli()
		 {
			 test=report.startTest("Play_Button_Enabled");
				boolean playButCli = driver.findElement(By.xpath("//div[@class='pauseplay clear']")).isEnabled();
				System.out.println(playButCli);
				if(playButCli)
				{
					test.log(LogStatus.PASS, "Play button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Play button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void timelineButtonVis()
		 {
			 test=report.startTest("Timeline_View_Button_Visiblity");
				boolean timelineButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/timerlogo.svg']")).isDisplayed();
				System.out.println(timelineButVis);
				if(timelineButVis)
				{
					test.log(LogStatus.PASS, "Timeline view button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Timeline view button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void timelineButtonCli()
		 {
			 test=report.startTest("Timeline_View_Button_Enabled");
				boolean timelineButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/timerlogo.svg']")).isEnabled();
				System.out.println(timelineButCli);
				if(timelineButCli)
				{
					test.log(LogStatus.PASS, "Timeline view button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Timeline view button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void openAIButtonVis()
		 {
			 test=report.startTest("OpenAI_Button_Visiblity");
				boolean openAIButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/ai.svg']")).isDisplayed();
				System.out.println(openAIButVis);
				if(openAIButVis)
				{
					test.log(LogStatus.PASS, "OpenAI button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "OpenAI button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void openAIButtonCli()
		 {
			 test=report.startTest("OpenAI_Button_Enabled");
				boolean openAIButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/ai.svg']")).isEnabled();
				System.out.println(openAIButCli);
				if(openAIButCli)
				{
					test.log(LogStatus.PASS, "OpenAI button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "OpenAI button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void serendipityButtonVis()
		 {
			 test=report.startTest("Serendipity_Button_Visiblity");
				boolean SerendipityButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/visual.svg']")).isDisplayed();
				System.out.println(SerendipityButVis);
				if(SerendipityButVis)
				{
					test.log(LogStatus.PASS, "Serendipity button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Serendipity button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void serendipityButtonCli()
		 {
			 test=report.startTest("Serendipity_Button_Enabled");
				boolean SerendipityopenAIButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/visual.svg']")).isEnabled();
				System.out.println(SerendipityopenAIButCli);
				if(SerendipityopenAIButCli)
				{
					test.log(LogStatus.PASS, "Serendipity button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Serendipity button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void d2d3ButtonVis()
		 {
			 test=report.startTest("2D/3D_Button_Visiblity");
				boolean d2d3ButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/2dlogo.svg']")).isDisplayed();
				System.out.println(d2d3ButVis);
				if(d2d3ButVis)
				{
					test.log(LogStatus.PASS, "2D/3D button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "2D/3D button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void d2d3ButtonCli()
		 {
			 test=report.startTest("2D/3D_Button_Enabled");
				boolean d2d3ButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/2dlogo.svg']")).isEnabled();
				System.out.println(d2d3ButCli);
				if(d2d3ButCli)
				{
					test.log(LogStatus.PASS, "2D/3D button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "2D/3D button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void infinityButtonVis()
		 {
			 test=report.startTest("Infinity_Button_Visiblity");
				boolean infinityButVis = driver.findElement(By.xpath("//div[@class='listViews clear2']")).isDisplayed();
				System.out.println(infinityButVis);
				if(infinityButVis)
				{
					test.log(LogStatus.PASS, "Infinity button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Infinity button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void infinityButtonCli()
		 {
			 test=report.startTest("Infinity_Button_Enabled");
				boolean infinityButCli = driver.findElement(By.xpath("//div[@class='listViews clear2']")).isEnabled();
				System.out.println(infinityButCli);
				if(infinityButCli)
				{
					test.log(LogStatus.PASS, "Infinity button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Infinity button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 
		 public void fitToViewButtonVis()
		 {
			 test=report.startTest("Fit_To_View_Button_Visiblity");
				boolean fitButVis = driver.findElement(By.xpath("//img[@src='../../assets/images/capture.svg']")).isDisplayed();
				System.out.println(fitButVis);
				if(fitButVis)
				{
					test.log(LogStatus.PASS, "Fit to view button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Fit to view button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void fitToViewButtonCli()
		 {
			 test=report.startTest("Fit_To_View_Button_Enabled");
				boolean fitButCli = driver.findElement(By.xpath("//img[@src='../../assets/images/capture.svg']")).isEnabled();
				System.out.println(fitButCli);
				if(fitButCli)
				{
					test.log(LogStatus.PASS, "Fit to view button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Fit to view button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void dragOpenButtonVis()
		 {
			 test=report.startTest("Drag_Button_Visiblity");
				boolean dragButVis = driver.findElement(By.xpath("(//img[@class='drag-logoopen'])[1]")).isDisplayed();
				System.out.println(dragButVis);
				if(dragButVis)
				{
					test.log(LogStatus.PASS, "Drag button is visible");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Drag button is not visible");
					//captureScreenshot(driver);
				}
		 }
		 
		 public void dragOpenButtonCli()
		 {
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 test=report.startTest("Drag_Button_Enabled");
			 
				boolean dragButCli = driver.findElement(By.xpath("(//img[@class='drag-logoopen'])[1]")).isEnabled();
				System.out.println(dragButCli);
				if(dragButCli)
				{
					test.log(LogStatus.PASS, "Drag button is enabled");
					//captureScreenshot(driver);
				}
			    else 
				{
					test.log(LogStatus.FAIL, "Drag button is not enabled");
					//captureScreenshot(driver);
				}
		 }
		
		 
}
