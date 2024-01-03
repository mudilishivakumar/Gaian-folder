package pageObjects;

import baseLibrary.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

public class LoginPageObjects extends BaseLibrary {

	public LoginPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	//Login Page Objects
	public By mobiusLogo                    = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ImageView");
	public By WkarLogo                      = By.id("ll_top");
	public By LoginUserName 				= By.id("etEmail");
	public By LoginPassword 				= By.id("etPasswordLogin");
	public By LoginButton 					= By.id("tvLogin");
	public By ToastMsg                      = By.id("snackbar_text");
	public By Skip 							= By.id("com.gaian.ngapp:id/tvSkip");
	public By GoButtoninMap 				= By.id("com.gaian.ngapp:id/llBtnGo");				//Earlier : ll_send
	public By progressBar 					= By.id("progress");
	public By profileSelectionAfterLogin	= By.xpath("//android.widget.TextView[contains('recycler_view')]");
	public By signUp						= By.id("tvSignUp");
	public By forgotPassword				= By.id("tvForgotPwd");
	public By facebook                      =By.id("llFacebook");
	public By google                        = By.id("llGoogle");
	// Navigation in HomePage
	public By HomeMenu 						= By.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView");
	public By NextGenApps 					= By.id("navigation_store");
	public By LiveChannels 					= By.xpath("//android.widget.FrameLayout[3]/android.widget.ImageView");
	public By ProfileMenu		 			= By.xpath("//android.widget.FrameLayout[5]/android.widget.ImageView");
	public By VOD							= By.xpath("//android.widget.FrameLayout[4]/android.widget.ImageView");
	// Video Thumbnails in Home page
	public By videoTxt1						= By.id("com.gaian.ngapp:id/tv_btm_title");
	// Unchecked  Xpaths
	public By MenuFrame						= By.id("nav_view");
	public By BackButton 					= By.id("ivBack");
	public By Channelslist					= By.id("channels_recycler_view");
	public By Programslist 					= By.id("epg_recycler_view");
	public By Recommended_Videos 			= By.xpath("//*[@text='Recommended for you']");
	public By RecentUploaded_Videos 		= By.xpath("//*[@text='Recently Uploaded']");
	public By TrendingVideos 				= By.xpath("//*[@text='Trending Now']");
	public By VideoinVOD 					= By.id("thumbnailIV");
	public By VideosinHomeMenu				= By.id("ivPoster"); // tried using ("ll_home") but same problem
	public By CuriousCrewinNextGenApps		= By.xpath("//android.widget.TextView[@text='Curious Crew']");
	public By TrendingApps					= By.xpath("//android.widget.TextView[@text='Trending']");
	public By TrendingApp					= By.id("cvItem");
	public By TodaysDateinLiveChannels		= By.id("android:id/text1");
	public By SearchButton					= By.id("btn_search");
	public By PlayButton					= By.id("btnAction");
	public By Logout						= By.id("com.gaian.ngapp:id/ivLogout");
	public By DevicesinProfile				= By.xpath("//android.widget.TextView[@text='Devices']");
	public By LoggedinProfileUserName		= By.id("ivProfileName");
	public By SettingsinProfile				= By.id("cvSettings");
	public By VersioninProfile				= By.id("tvVersionName");
	public By ContinueLogout				= By.id("com.gaian.ngapp:id/btnContinue");
	public By CancelLogout					= By.id("btnCancel");
	public By logoutPopUp					= By.id("com.gaian.ngapp:id/design_bottom_sheet");
	public By pageObjects 					= By.id("flLoader");
	public By pageLoadObjects 				= By.id("llLoader");
	public By personalizingLoader 			= By.id("tv_username");

	public void waitTillPageLoads(int seconds) {
		if (seconds <= 0)
			seconds = 10;
		int timeout = seconds;
		List<AndroidElement> listLoaders = driver.findElements(pageObjects);
		if(listLoaders.size()>0){
			while (listLoaders.size()>0 && timeout-->0){
				System.out.println("pageLoader still being displayed");
				Utilities.staticWait(1);
				listLoaders = driver.findElements(pageObjects);
			}
		}
		List<AndroidElement> listLoadersOthers = driver.findElements(pageLoadObjects);
		if(listLoaders.size()>0){
			while (listLoadersOthers.size()>0 && timeout-->0){
				System.out.println("pageLoader still being displayed");
				Utilities.staticWait(1);
				listLoadersOthers = driver.findElements(pageLoadObjects);
			}
		}
//		List<AndroidElement> atscLoaders = driver.findElements(personalizingLoader);
//		if(listLoaders.size()>0){
//			while (atscLoaders.size()>0 && timeout-->0){
//				System.out.println("pageLoader still being displayed");
//				Utilities.staticWait(1);
//				atscLoaders = driver.findElements(personalizingLoader);
//			}
//		}
		System.out.println("Checked for the availability of any loading objects");
	}
}
