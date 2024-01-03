package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

public class NextGenPageObjects extends BaseLibrary {
	public NextGenPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public By NextGenAppsScreen = By.id("navigation_store");
	public By NextGenApps = By.id("tvTitlevod");
	public By SearchinNextGenApps = By.id("iv_scan");
	public By CuriousCrewinNextGenApps = By.xpath("//android.widget.TextView[@text='Curious Crew']");
	public By TrendingAppsinNextGenApps = By.xpath("//android.widget.TextView[@text='Trending']");
	public By Poll = By.xpath("//android.widget.TextView[@text='Poll']");
	public By QandA = By.xpath("//android.widget.TextView[@text='Q&A']");
	public By Emoticons = By.xpath("//android.widget.TextView[@text='Emoticons']");
	public By Alerting = By.xpath("//android.widget.TextView[@text='Alerting']");
	public By MessageBoard = By.xpath("//android.widget.TextView[@text='MB']");
	public By Responder = By.xpath("//android.widget.TextView[@text='Responder']");
	public By Live = By.id("tvLive");
	public By More = By.id("ivMore");
	public By ABOUT = By.id("btnAbout");
	public By GALLERY = By.id("btnGallery");
	public By REVIEWS = By.id("btnReview");
	public By launchBA = By.id("btnAction");
	public By Back = By.id("ivBack");
	public By playvideoinNextgenApps = By.id("cvItem");
	public By baList = By.xpath("//android.widget.ImageView");
	public By PageTitle   = By.id("tvTitle");

	public AndroidElement getBAListItems(String nameOfTheBA){
		List<AndroidElement> listOfBAItems = driver.findElements(baList);
		switch(nameOfTheBA.toUpperCase()){
			case "Q&A":
				return listOfBAItems.get(1);
			case "RESPONDER":
				return listOfBAItems.get(2);
			case "MB":
				return  listOfBAItems.get(4);
			case "EMOTICONS":
				return listOfBAItems.get(3);
			default:
				throw new IllegalStateException("Unexpected value: " + nameOfTheBA.toUpperCase());
		}

	}
}