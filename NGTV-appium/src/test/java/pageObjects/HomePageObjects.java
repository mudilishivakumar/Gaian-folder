package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import java.util.List;

public class HomePageObjects extends BasePageObjects {
	public By channelName = By.id("tvName");
	public By pbsIcon = By.id("ivPBS");
	public By playButton = By.id("ivPlay");
	public By channelMenuIcon = By.id("epg_menu");
	public By liveText = By.id("live_layout");
	public By recommendsText = By.xpath("//android.widget.TextView[@text='Mobius Recommends']");
	public By QrCodeImage = By.id("imageViewResult");
	public By ToggleBar = By.id("ivToggle");
	// Returns multiple elements
	public By ChannelThumbnails = By.id("cvRecommendsCard");
	public By Channels = By.xpath("//android.widget.RelativeLayout");
	public HomePageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public AndroidElement getChannel(String channelSelector) {
		List<AndroidElement> imgList = driver.findElements(Channels);
		switch (channelSelector) {
			case "WKAR NewsTalk":
				return imgList.get(3);
			case "CURIOUS CREW":
				return imgList.get(4);
			case "WKAR":
				return imgList.get(5);
			case "WKAR FM":
				return imgList.get(6);
			case "WKAR Classical":
				return imgList.get(7);
			case "WKAR NewsTalk1":
				return imgList.get(8);
			case "WKAR JAZZ":
				return imgList.get(9);
			case "WKAR Radio Reading Service":
				return imgList.get(10);
			default:
				throw new IllegalArgumentException("Channel of type " + channelSelector + " not present, please check");
		}
	}
}