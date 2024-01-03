package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;

import java.util.List;

public class VoDPageObjects extends BasePageObjects {

	public VoDPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public By screenName                       = By.id("tvScreenName");
	public By searchIcon                       = By.id("ivSearch");
	public By mobiusIcon                       = By.id("ivApollo");
	public By vodBannerElement                 = By.id("viewpager");
	public By ReleasesIn2019Text               = By.xpath("//android.widget.TextView[contains(@text, 'Releases in 2019')]");
	public By TopRatedText                     = By.xpath("//android.widget.TextView[contains(@text, 'Top Rated')]");
	public By videoPlayerLayoutElem            = By.id("playerLayout");
	public By videoPlayerBanner                = By.id("playerBannerLay");
	public By videoPlayerToggle                = By.id("ivToggle");
	public By videoPLayerProgress              = By.id("playerProgress");
	public By continueToWatchText			   =By.xpath("//android.widget.TextView[contains(@text,'Continue to watch')]");
	// Returns multiple elements
	public By vodVideoThumbnails               = By.id("rlVodItem");
	public By vodVideoThumbnailImages          = By.id("thumbnailIV");
	public By vodReleasesThumbnailElement      = By.id("rootCV");
	public By movieTypeTextElements            = By.id("movieTypeTextView");
	public By ReleasesIn2019VideoThumbnails    = By.xpath("//android.widget.TextView[contains(@text, 'Releases in 2019')]//following-sibling::androidx.recyclerview.widget.RecyclerView//descendant::android.widget.ImageView");
	public By TopRatedVideoThumbnails          = By.xpath("//android.widget.TextView[contains(@text, 'Top Rated')]//following-sibling::androidx.recyclerview.widget.RecyclerView//descendant::android.widget.ImageView");
	public By ContinueToWatchThumbnails        = By.xpath("//android.widget.TextView");
	public By ResumeButton			           =By.id("btnAction");
	public By seasonText   					   =By.id("tvSeason");
	public By SeasonsThumbnails						   =By.xpath("//android.widget.RelativeLayout");
	public AndroidElement getChannel(String channelSelector) {
		List<AndroidElement> channelList = driver.findElements(ContinueToWatchThumbnails);
		switch (channelSelector) {
			case "Channel No-1":
				return channelList.get(11);
			case "Channel No-2":
				return channelList.get(12);
			case "Channel No-3":
				return channelList.get(13);
			case "Channel No-4":
				return channelList.get(14);
			default:
				throw new IllegalArgumentException("Channel of type " + channelSelector + " not present, please check");
		}
	}
	public AndroidElement getSeason(String seasonSelector) {
		List<AndroidElement> seasonList = driver.findElements(SeasonsThumbnails);
		switch (seasonSelector) {
			case "Season-1":
				return seasonList.get(5);
			case "Season-2":
				return seasonList.get(6);
			case "Season-3":
				return seasonList.get(7);
			case "Season-4":
				return seasonList.get(8);
			case "Season-5":
				return seasonList.get(9);
			case "Season-6":
				return seasonList.get(10);
			case "Season-7":
				return seasonList.get(11);
			default:
				throw new IllegalArgumentException("Channel of type " + seasonSelector + " not present, please check");
		}
	}
}