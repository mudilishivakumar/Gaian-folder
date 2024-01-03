package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.util.List;

public class VoDPageObjects extends BaseLibrary {

	public VoDPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public By VideoinVOD = By.id("thumbnailIV");
	public By releasein2019 = By.xpath("//*[@text='Releases in 2019']");
	public By getTopRated = By.xpath("//*[@text='Top Rated']");
	public By layoutView = By.id("relativeLayout");
	public By resumeBtn = By.id("btnAction");
	public By seeAll = By.id("seeAllTextView");
	public By toprated = By.xpath("//*[@text='Top rated']");
	public By RecentUploaded_Videos = By.xpath("//*[@text='Recently Uploaded']");
	public By Releasesin2019 = By.id("relativeLayout");
	public By BackButton = By.id("ivBack");
	public By writeReview = By.id("ivAddReview");
	public By rating = By.id("rate");
	public By writeDescription = By.id("etWriteReview");
	public By submitReview = By.id("btnAction");
	public By episodes = By.id("btnEpisodes");
	public By dropDownOnVODSeason = By.id("spEpisodes");
	public By AboutInVOD = By.id("btnAbout");
	public By TextBoxSeason = By.id("tvTxtAbout");
	public By ButtonNGS = By.id("btnNGs");
	public By titleOnThumbnails = By.id("tvTitle");
	public By bookmark = By.id("ivAddList");

	public By writtenReview(String text) {
		return By.xpath("//*[@text='" + text + "']");
	}

	public By seasonsDropDown(String text) {
		return By.xpath("//*[@text='" + text + "']");
	}

	public By getNameOfTheVideo(String text) {
		return By.xpath("//android.widget.TextView[contains(@text,'" + text + "')]");
	}

	public By courousels = By.id("activityMainRecyclerViewHorizontal");

	public AndroidElement corouselViews(String text) {
		List<AndroidElement> listofCorousels = driver.findElements(courousels);
		System.out.println("SIze of list" + listofCorousels.size());
		switch (text.toUpperCase()) {
			case "TOPRATED":
				return listofCorousels.get(2);
			case "RELEASESIN2019":
				return listofCorousels.get(1);
			case "CONTINUETOWATCH":
				return listofCorousels.get(0);
			default:
				throw new AssertionError("Required swipe not fund");
		}
	}

	public AndroidElement ListOfTitlesOnThumbnails(String inWhichCourousel) {
		List<AndroidElement> beforeSwipe = driver.findElements(titleOnThumbnails);
		switch (inWhichCourousel.toUpperCase()) {
			case "RELEASESIN2019":
				return beforeSwipe.get(3);
			case "CONTINUETOWATCH":
				return beforeSwipe.get(0);
			case "TOPRATED":
				return beforeSwipe.get(3);
			default:
				break;
		}
		return null;
	}

/**********************************************************************************************************/

	public By VOD = By.id("navigation_vod");
	public By TopRated = By.id("relativeLayout");
	public By PlayButton = By.id("btnAction");
	public By SearchVOD = By.id("iv_scan");
	public By ShareOption = By.id("iv_scan");
	public By Toprated = By.xpath("//*[@text='Top rated']");

}