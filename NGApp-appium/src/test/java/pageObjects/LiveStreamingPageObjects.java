package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LiveStreamingPageObjects extends BaseLibrary {
	public LiveStreamingPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		}
		public By LiveChannels = By.id("navigation_live");
		public By Channelslist = By.id("channel_logo");
		public By selectVideoInLivePage = By.xpath("//*[@text='News Hour']");
		public By timeLine	   = By.id("text_timeline");
		public By SearchButton = By.id("btn_search");
		public By videoThumbnail =	By.id("channel_logo");
		/************** 	Unused	 **********************/
		public By Programslist = By.id("epg_recycler_view");
		public By TodaysDateinLiveChannels = By.id("sp_date");
		public By OrientationButton = By.id("btn_orientation");
		public By Time = By.id("text_timeline");
		public By BackButton = By.id("ivBack");
		}