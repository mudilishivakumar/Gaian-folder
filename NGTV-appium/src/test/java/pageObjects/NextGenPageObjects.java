package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import baseLibrary.BaseLibrary;
import java.util.List;

public class NextGenPageObjects extends BasePageObjects {
	public NextGenPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	public By ScreenName 		                    = By.id("tvScreenName");
	public By SearchIcon 		                    = By.id("ivSearch");
	public By MobiusIcon 		                    = By.id("ivApollo");
	public By LiveNowTxt 		                    = By.id("tvLive");
	public By TrendingText                          = By.id("tvTitle");
	// Returns multiple elements
	public By LiveNowThumbnail	                    = By.id("ivLive");
	public By TopScrollView		                    = By.id("ivMyListItem");
	public By TrendingThumbnail                     = By.id("cvItem");
	public By nextGenThumbnails						=By.xpath("//android.widget.LinearLayout");
	public By LaunchButton			           =By.id("btnAction");

	public AndroidElement getLiveNow(String channelSelector) {
		List<AndroidElement> channelList = driver.findElements(nextGenThumbnails);
		switch (channelSelector){
			case "Quiz time":
				return channelList.get(4);
			case "Responder":
				return channelList.get(5);
			case "Emoticons":
				return channelList.get(6);
			case "MB":
				return channelList.get(7);
			default:
				throw new IllegalArgumentException("Channel of type " + channelSelector + " not present, please check");
		}
	}
	public AndroidElement getTrending(String channelSelector1){
		List<AndroidElement> channelList1 = driver.findElements(nextGenThumbnails);
		switch (channelSelector1){
			case "Quiz time":
				return channelList1.get(11);
			case "Responder":
				return channelList1.get(12);
			case "Emoticons":
				return channelList1.get(13);
			case "MB":
				return channelList1.get(14);
			default:
				throw new IllegalArgumentException("Channel of type " + channelSelector1 + " not present, please check");
		}
	}
}

