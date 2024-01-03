package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;

public class LiveStreamingPageObjects extends BasePageObjects {
	public LiveStreamingPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public By ChannelMenuIcon                       = By.id("epg_menu");
	public By ProfileImage                          = By.id("cvImage");
	public By PbsIcon                               = By.id("ivPBS");
	public By CurrentDate                           = By.id("programguide_current_date");
	public By TimeRow                               = By.id("programguide_time_row");
	public By QrCode                                = By.id("imageViewResult");

	// Returns multiple elements
	public By ChannelNameslist                      = By.id("programguide_channel_name");
	public By ChannelLogoslist                      = By.id("programguide_channel_logo");
	public By ChannelLayoutList                     = By.id("programguide_channel_container");

}