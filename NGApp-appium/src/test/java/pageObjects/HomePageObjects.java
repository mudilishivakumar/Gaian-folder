package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HomePageObjects extends BaseLibrary {
	public HomePageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public By videoTxt						= By.id("tv_btm_title");
	public By playInHomepage 				= By.id("ivPoster");	// returns multiple elements
	public By locationSearch				= By.id("edt_search");
	public By favourite						= By.id("btn_fav");
	public By Search						= By.id("ivSearch");
	public By Pair 							= By.id("ivPair");
	public By Remote 						= By.id("ivRemote");
	public By GoButtoninMap 				= By.id("com.gaian.ngapp:id/llBtnGo");
	public By skipInWalkThrough				= By.xpath("//*[@text='SKIP']");
	public By nextInWalkThrough				= By.id("showcase_button");
	public By videoPauseButton              = By.id("ivToggle");

	public By getSelectProfileInHomeScreen(String profileName){
		return By.xpath("//*[@text='"+profileName+"']");
	}

	public By getVideoLocation(String name){
		return By.xpath("//android.widget.TextView[contains(@text,'"+name+"')]");
	}

}



