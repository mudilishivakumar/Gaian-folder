package pageObjects;

import baseLibrary.Utilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import javax.rmi.CORBA.Util;

public class VideoDescriptionPageObjects extends BasePageObjects {

	public By BackgroundImage                       = By.id("ivImage");
	public By VideoTitle                            = By.id("tvText");
	public By AboutVideoText                        = By.id("tvTxtAbout");
	public By Rating                                = By.id("rate");
	public By PlayButton                            = By.id("btnAction");
	public By AddBookmarkIcon                       = By.id("ivAdd");
	public By AddBookmarkText                       = By.id("tvAddBookmark");

	/// Review elements
	public By WriteReviewButton                     = By.id("tvWriteReview");
	public By WriteReviewTitle                      = By.id("tvAddBookmark");
	public By WriteReviewRating                     = By.id("rate");
	public By WriteReviewTextArea                   = By.id("etWriteReview");
	public By WriteReviewSubmit                     = By.id("btnAction");
	public By ResumeButton							= By.id("btnAction");

	// Returns multiple elements
	public By TvSeasonNumbers                       = By.id("tvSeasonNum");
	public By EpisodeThumbnail                      = By.id("ivEpisodeBg");
	public By EpisodeTitle                          = By.id("tvTitle");
	public By EpisodeDescription                    = By.id("tvDescription");
	public By ReviewCard                            = By.id("card_view");
	public By ReviewUserProfile                     = By.id("ivUserProfile");
	public By ReviewUserName                        = By.id("tvUserName");
	public By ReviewRating                          = By.id("tvRating");
	public By ReviewText                            = By.id("tvReview");

	public void selectStarRating(String starRatingValue) {
		float starRating = (Float.parseFloat(starRatingValue) > 5) ? 5F : Float.parseFloat(starRatingValue);
		double starRatingDecimal = starRating % 1;
		int starRatingInt = new Double(Math.floor(starRating)).intValue();
		int starRatingInteger = (starRatingInt * 3) + (starRatingInt - 1);
		starRatingInteger = starRatingInteger + ((starRatingDecimal <= 0.4) ? 1: (starRatingDecimal <= 0.75 ? 2 : 3));

		driver.getKeyboard().pressKey(Keys.RIGHT);
		driver.getKeyboard().pressKey(Keys.DOWN);
		while(starRatingInteger-- >= 0) {
			driver.getKeyboard().pressKey(Keys.RIGHT);
		}
	}

	public boolean checkIfReviewIsPresent(String starRatingValue, String reviewText){

		System.out.println("Star rating values >> ");
		driver.findElements(ReviewRating).stream().map(RemoteWebElement::getText).forEach(System.out::println);
		System.out.println("Review values >> ");
		driver.findElements(ReviewText).stream().map(RemoteWebElement::getText).forEach(System.out::println);

		boolean isRatingPresent = driver.findElements(ReviewRating).stream().map(RemoteWebElement::getText).anyMatch(val -> Double.parseDouble(val) == Double.parseDouble(starRatingValue));
		boolean isReviewPresent = driver.findElements(ReviewText).stream().map(RemoteWebElement::getText).anyMatch(val -> val.equals(reviewText));

		return isRatingPresent && isReviewPresent;
	}

	public VideoDescriptionPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
}