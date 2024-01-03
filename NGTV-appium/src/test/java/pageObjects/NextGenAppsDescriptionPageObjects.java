package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class NextGenAppsDescriptionPageObjects extends BasePageObjects {

	public By LaunchButton          = By.id("btnAction");
	public By AddToListButton       = By.id("tvAddBookmark");
	public By WriteReview           = By.id("tvWriteReview");


	// Multiple elements
	public By UserProfileImage      = By.id("ivUserProfile");
	public By UserName              = By.id("tvUserName");
	public By UserRating            = By.id("tvRating");
	public By UserReview            = By.id("tvReview");

	public NextGenAppsDescriptionPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
}