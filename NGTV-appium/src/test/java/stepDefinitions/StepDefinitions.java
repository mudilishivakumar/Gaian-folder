package stepDefinitions;

import baseLibrary.Utilities;
import controller.APIController;
import controller.DBController;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Criteria;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.RTC;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class StepDefinitions extends Utilities {

	private static final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);
	private static DBController dbc;
	private LoginPageObjects lpo;
	private HomePageObjects hpo;
	private ProfilePageObjects ppo;
	private LiveStreamingPageObjects lipo;
	private VoDPageObjects vpo;
	private MenuPageObjects mpo;
	private NextGenPageObjects nextGenPage;
	private VideoDescriptionPageObjects videoDescPage;
	private VideoPlayerObjects videoPlayer;
	private EditProfileDetailsObjects profileDetailsObjects;
	private SignupPageObjects signupPageObjects;
	private ForgotPasswordObjects forgotPasswordObjects;
	private Response responseObj;
	private String accessToken = null;
	private String scenarioUserId = null;
	private String scenarioAppConsumerId = null;
	private int scenarioDbUserId = -999;
	private String scenarioUsername = null;
	private String scenarioEmail = null;
	private String scenarioPassword = null;
	private String scenarioUpdatedPassword = null;
	private String scenarioFirstName = null;
	private String scenarioLastName = null;
	private String scenarioAge = null;
	private String scenarioCardNumber = null;
	private String scenarioExpiryDate = null;
	private String scenarioCvv = null;
	private String scenarioRatingReview = null;

	@Before
	public void getScenarioName() {
		logger.info("Scenario starts here >>>>>>>>>>>>");
		dbc = new DBController();
		try {
			setUp();
			logger.debug("------------------------------------------------------------- TV App installed without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Does_the_TV_APK_install_without_issues);
		} catch (MalformedURLException e) {
			logger.error("------------------------------------------------------------- TV App did not install -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Does_the_TV_APK_install_without_issues);
			e.printStackTrace();
		}
	}

	@After(order = 2)
	public void saveScenarioDeviceLogs(Scenario scenario) {
		try {
			saveDeviceLogs(toCamelCase(scenario.getName(), " "));
		} catch (IOException e) {
			logger.error("Could not create Device log file...");
			e.printStackTrace();
		}
	}

	@After(order = 1)
	public void closeReport(Scenario scenario) {
		// dbc.closeConnection();
		if (!System.getProperty("localRun").equalsIgnoreCase("true"))
			((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (scenario.isFailed() ? "failed" : "passed"));
		tearDown(scenario);
	}

	@Given("^As a user I want to open the NGTV$")
	public void as_a_user_I_want_to_open_the_NGTV() {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpo = new LoginPageObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(lpo.LoginTxt));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in Username field$")
	public void user_enters_in_Username_field(String username) {
		try {
			if (username.trim().equalsIgnoreCase("RANDOM")) {
				if (scenarioUsername == null) {
					if (scenarioEmail == null) {
						username = Utilities.getRandomValues("username");
					} else {
						username = scenarioEmail;
					}
				} else {
					username = scenarioUsername;
				}
			}
			if (username.trim().equalsIgnoreCase("FROM DB")) {
				if (scenarioUsername == null) {
					if (scenarioEmail == null) {
						Map<String, Object> userRecord = dbc.getUserDetails(0, null);
						username = userRecord.get("username").toString();
						scenarioPassword = userRecord.get("password").toString();
					} else {
						username = scenarioEmail;
					}
				} else {
					username = scenarioUsername;
				}
			}
			enterValue(lpo.LoginUserName, username);
			scenarioUsername = username;
			getLogger("Entered Username: " + scenarioUsername);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in Password field$")
	public void user_enters_in_Password_field(String password) {
		try {
			if (password.trim().equalsIgnoreCase("RANDOM")) {
				if (scenarioUpdatedPassword != null) {
					password = scenarioUpdatedPassword;
				} else {
					if (scenarioPassword != null) {
						password = scenarioPassword;
					} else {
						password = Utilities.getRandomValues("password");
					}
				}
			}
			if (password.trim().equalsIgnoreCase("FROM DB")) {
				if (scenarioPassword == null) {
					Map<String, Object> userRecord = dbc.getUserDetails(0, null);
					password = userRecord.get("password").toString();
					scenarioUsername = userRecord.get("username").toString();
				} else {
					password = scenarioPassword;
				}
			}
			enterValue(lpo.LoginPassword, password);
			scenarioPassword = password;
			getLogger("Entered Password: " + scenarioPassword);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^Clicks on login button$")
	public void clicks_on_login_button() {
		try {
			Assert.assertTrue(verifyElementDisplayed(lpo.LoginButton));
			click(lpo.LoginButton);
			click(lpo.LoginButton);
		} catch (AssertionError e) {
			getLogger( "Login button is not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to Sign up page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		}finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Home Page")
	public void user_is_navigated_to_Home_Page() {
		try {
			hpo = new HomePageObjects(driver);
			waitForElement(hpo.QrCodeImage);
			Assert.assertTrue(verifyElementDisplayed(hpo.pbsIcon));
			getLogger("Logged into NgTV");
			logger.debug("------------------------------------------------------------- Logged in without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Can_We_Login_without_issues_in_TV);
		} catch (AssertionError e) {
			logger.error("------------------------------------------------------------- Could not log in -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Can_We_Login_without_issues_in_TV);
			throw new AssertionError(e.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User is able to see the List of videos available$")
	public void user_is_able_to_see_the_List_of_videos_available() {
		try {
			Assert.assertTrue(verifyElementDisplayed(hpo.ChannelThumbnails));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on NextGenApps$")
	public void user_clicks_on_NextGenApps() {
		try {
			waitForElement(mpo.nextGenIcon, 5);
			click(mpo.nextGenIcon);
			click(mpo.nextGenIcon);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is able to see list of videos under Live Now, Trending$")
	public void User_is_able_to_see_list_of_videos_under_Live_Now_Trending() {
		try {
			nextGenPage = new NextGenPageObjects(driver);
			nextGenPage.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.LiveNowTxt));
			getLogger("Live now text is visible");
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.LiveNowThumbnail));
			getLogger("Live Now thumbnails are visible");
			scrollToElement(nextGenPage.TrendingThumbnail, true);
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.TrendingText));
			getLogger("Trending Now text is visible");
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.TrendingThumbnail));
			getLogger("Trending Now thumbnails are visible");

			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Some elements are not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on device back button$")
	public void User_clicks_on_device_back_button() {
		try {
			driver.navigate().back();
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on LiveChannel Menu$")
	public void user_clicks_on_LiveChannels() {
		try {
			waitForElement(hpo.channelMenuIcon, 5);
			click(hpo.channelMenuIcon);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is able to see available channels$")
	public void user_is_able_to_see_available_channels() {
		try {
			lipo = new LiveStreamingPageObjects(driver);
			lipo.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(lipo.ChannelLogoslist));
			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Available channels are not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^Play button is displayed to play the video$")
	public void play_button_is_displayed_to_play_the_video() {
		try {
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.PlayButton));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on profile menu$")
	public void user_clicks_on_profile_menu() {
		try {
			waitForElement(hpo.pbsIcon, 5);
			click(hpo.pbsIcon);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	// Video On Demand Page

	@When("^User clicks on Logout Button$")
	public void user_clicks_on_Logout_Button() {
		try {
			waitForElement(ppo.signOut, 5);
			click(ppo.signOut);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}

	}

	@When("^User clicks on Profile Button$")
	public void user_clicks_on_Profile_Button() {
		try {
			waitForElement(ppo.profileDetailsButton, 5);
			click(ppo.profileDetailsButton);
			ppo.waitTillPageLoads(1);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to profile details screen$")
	public void User_is_navigated_to_profile_details_screen() {
		try {
			profileDetailsObjects = new EditProfileDetailsObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(profileDetailsObjects.saveBtn));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^Verify user is logged out$")
	public void verify_user_is_logged_out() {
		try {
			lpo.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(lpo.LoginUserName));
			logger.debug("------------------------------------------------------------- Logged out without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_User_Able_to_Logout_from_TV_App);
		} catch (AssertionError e) {
			getLogger("Username field is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not log out -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_User_Able_to_Logout_from_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}

	}

	@When("^User clicks on VOD$")
	public void User_clicks_on_VOD() {
		try {
			waitForElement(mpo.vodIcon, 2);
			click(mpo.vodIcon);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is able to see videos under Releases in 2019, Top rated$")
	public void user_is_able_to_see_videos_under_Releases_in_2019_Top_rated() {
		try {
			vpo = new VoDPageObjects(driver);
			vpo.waitTillPageLoads(1);
			if (verifyElementDisplayed(vpo.TopRatedVideoThumbnails, 5)) {
				Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedText, 5));
				getLogger("Top Rated text is visible");
				Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedVideoThumbnails, 5));
				getLogger("Top Rated video thumbnails are visible");
				scrollToElement(vpo.ReleasesIn2019VideoThumbnails, true);
				Assert.assertTrue(verifyElementDisplayed(vpo.ReleasesIn2019Text, 5));
				getLogger("Releases in 2019 text is visible");
				Assert.assertTrue(verifyElementDisplayed(vpo.ReleasesIn2019VideoThumbnails, 5));
				getLogger("Releases in 2019 video thumbnails are visible");
				scrollToElement(vpo.TopRatedText, false);
			} else {
				Assert.assertTrue(verifyElementDisplayed(vpo.ReleasesIn2019Text, 5));
				getLogger("Releases in 2019 text is visible");
				Assert.assertTrue(verifyElementDisplayed(vpo.ReleasesIn2019VideoThumbnails, 5));
				getLogger("Releases in 2019 video thumbnails are visible");
				scrollToElement(vpo.TopRatedVideoThumbnails, true);
				Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedText, 5));
				getLogger("Top Rated text is visible");
				Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedVideoThumbnails, 5));
				getLogger("Top Rated video thumbnails are visible");
				scrollToElement(vpo.ReleasesIn2019Text, false);
			}
			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Some elements are not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is able to see Search, NextGenApps, Live TV, VOD, Account$")
	public void User_is_able_to_see_Search_NextGenApps_Live_TV_VOD_Account() {

		try {
			mpo = new MenuPageObjects(driver);
			waitForElement(mpo.profileImg, 10);
			Assert.assertTrue(verifyElementDisplayed(mpo.search));
			getLogger("Search Icon is visible");
			Assert.assertTrue(verifyElementDisplayed(mpo.nextGenIcon));
			getLogger("NextGen icon is visible");
			Assert.assertTrue(verifyElementDisplayed(mpo.liveTvIcon));
			getLogger("Live TV icon is visible");
			Assert.assertTrue(verifyElementDisplayed(mpo.vodIcon));
			getLogger("VoD icon is visible");
			Assert.assertTrue(verifyElementDisplayed(mpo.accountIcon));
			getLogger("Profile page icon is visible");

			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Some elements are not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Account$")
	public void User_clicks_on_Account() {
		try {
			waitForElement(mpo.accountIcon, 5);
			click(mpo.accountIcon);
			click(mpo.accountIcon);
			mpo.waitTillPageLoads(1);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to profile screen$")
	public void User_is_navigated_to_profile_screen() {
		try {
			ppo = new ProfilePageObjects(driver);
			ppo.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(ppo.profileTxt));
			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Profile text is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Yes on confirmation popup$")
	public void User_clicks_on_Yes_on_confirmation_popup() {
		try {
			waitForElement(ppo.verifyLogoutDialog, 5);
			click(ppo.continueBtn);
			click(ppo.continueBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Live TV Icon$")
	public void User_clicks_on_Live_TV_Icon() {
		try {
			waitForElement(mpo.liveTvIcon, 5);
			click(mpo.liveTvIcon);
			click(mpo.liveTvIcon);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on a channel thumbnail on LiveChannels$")
	public void User_clicks_on_a_channel_thumbnail_on_LiveChannels() {
		try {
			waitForElement(lipo.ChannelLogoslist, 5);
			click(lipo.ChannelLogoslist);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is able to see search button$")
	public void User_is_able_to_see_search_button() {
		try {
			if (vpo == null) vpo = new VoDPageObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(vpo.searchIcon));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on a video thumbnail from NextGenApps screen$")
	public void User_clicks_on_a_video_thumbnail_from_NextGenApps_screen() {
		try {
			waitForElement(nextGenPage.LiveNowThumbnail, 10);
			click(nextGenPage.LiveNowThumbnail);
			nextGenPage.waitTillPageLoads(1);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on a video thumbnail from VoD Page$")
	public void User_clicks_on_a_video_thumbnail_from_VoD_Page() {
		try {
			click(vpo.vodVideoThumbnailImages);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on the third video thumbnail from Top Rated section on VoD Page$")
	public void User_clicks_on_the_third_video_thumbnail_from_Top_Rated_section_on_VoD_Page() {
		try {
			if (!verifyElementDisplayed(vpo.TopRatedText))
				scrollToElement(vpo.TopRatedText, true);
			driver.findElements(vpo.TopRatedVideoThumbnails).get(2).click();
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^Video Description Page is displayed$")
	public void Video_Description_Page_is_displayed() {
		try {
			videoDescPage = new VideoDescriptionPageObjects(driver);
			videoDescPage.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.VideoTitle));
			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Video Description page is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks play button$")
	public void User_clicks_play_button() {
		try {
			waitForElement(videoDescPage.PlayButton, 2);
			click(videoDescPage.PlayButton);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^Video player opens up in \"([^\"]*)\" and video is playing$")
	public void Video_player_opens_up_and_video_is_playing(String pageName) {
		try {
			videoPlayer = new VideoPlayerObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(videoPlayer.VideoPlayerElement));
			getLogger("Video player has opened on " + pageName + " page");
			logger.debug("------------------------------------------------------------- Played video on {} -------------------------------------------------------------", pageName);
			switch (pageName) {
				case "VoD":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_VOD_Screen_in_TV_App);
					break;
				case "Live TV":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_Live_Screen_in_TV_App);
					break;
				case "Home Screen":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_HomeScreen_in_TV_App);
					break;
				default:
					throw new IllegalArgumentException("The page name \"" + pageName + "\" is not yet supported");
			}
			Assert.assertFalse(verifyElementDisplayed(videoPlayer.VideoPlayerNonPlayingVideoOverlay));
			getLogger("Video is playing on " + pageName + " page");
			logger.debug("------------------------------------------------------------- Played video on {} -------------------------------------------------------------", pageName);
			switch (pageName) {
				case "VoD":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_VOD_Screen_in_TV_App);
					break;
				case "Live TV":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_Live_Screen_in_TV_App);
					break;
				case "Home Screen":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Video_playing_from_HomeScreen_in_TV_App);
					break;
				default:
					throw new IllegalArgumentException("The page name \"" + pageName + "\" is not yet supported");
			}
		} catch (AssertionError e) {
			getLogger("Something is wrong with the video playback in " + pageName + " page, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not play video on {} -------------------------------------------------------------", pageName);
			switch (pageName) {
				case "VoD":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Video_playing_from_VOD_Screen_in_TV_App);
					break;
				case "Live TV":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Video_playing_from_Live_Screen_in_TV_App);
					break;
				case "Home Screen":
					dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Video_playing_from_HomeScreen_in_TV_App);
					break;
				default:
					throw new IllegalArgumentException("The page name \"" + pageName + "\" is not yet supported");
			}
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^Video player opens up in \"([^\"]*)\" and video is not playing$")
	public void Video_player_opens_up_and_video_is_not_playing(String pageName) {
		try {
			videoPlayer = new VideoPlayerObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(videoPlayer.VideoPlayerElement));
			Assert.assertTrue(verifyElementDisplayed(videoPlayer.VideoPlayerNonPlayingVideoOverlay));
		} catch (AssertionError e) {
			getLogger("Something is wrong with the video playback in " + pageName + " page, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Unexpected video playback on {} -------------------------------------------------------------", pageName);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("User is able to see Paired Devices, Location, My List, Settings, Profile, Download, Logout")
	public void User_is_able_to_see_PairedDevices_Location_MyList_Settings_Profile_Download_Logout() {
		try {
			Assert.assertTrue(verifyElementDisplayed(ppo.pairedDevicesTxt));
			getLogger("Paired devices text is visible");
			Assert.assertTrue(verifyElementDisplayed(ppo.locationTxt));
			getLogger("Location text is visible");
			Assert.assertTrue(verifyElementDisplayed(ppo.listTxt));
			getLogger("List text is visible");
			Assert.assertTrue(verifyElementDisplayed(ppo.settingsTxt));
			getLogger("Settings text is visible");
			Assert.assertTrue(verifyElementDisplayed(ppo.profileTxt));
			getLogger("Profile text is visible");
			Assert.assertTrue(verifyElementDisplayed(ppo.signOut));
			getLogger("Sign Out text is visible");
			logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
		} catch (AssertionError e) {
			getLogger("Some elements are not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate -------------------------------------------------------------");
			dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 0.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in First Name field$")
	public void user_enters_in_First_name_field(String firstName) {
		try {
			if (firstName.trim().equalsIgnoreCase("RANDOM")) {
				firstName = Utilities.getRandomValues("firstName");
			}
			enterValue(profileDetailsObjects.firstNameInput, firstName);
			scenarioFirstName = firstName;
			getLogger("Entered firstName: " + firstName);

		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in Last Name field$")
	public void user_enters_in_Last_name_field(String lastName) {
		try {
			if (lastName.trim().equalsIgnoreCase("RANDOM")) {
				lastName = Utilities.getRandomValues("lastName");
			}
			enterValue(profileDetailsObjects.lastNameInput, lastName);
			scenarioLastName = lastName;
			getLogger("Entered lastName: " + lastName);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in Age field$")
	public void user_enters_in_Age_field(String ageValue) {
		try {
			if (ageValue.trim().equalsIgnoreCase("RANDOM")) {
				ageValue = Utilities.getRandomValues("age");
			}
			enterValue(profileDetailsObjects.ageInput, ageValue);
			scenarioAge = ageValue;
			getLogger("Entered ageValue: " + ageValue);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Save Button$")
	public void user_clicks_on_Save_Button() {
		try {
			click(profileDetailsObjects.saveBtn);
			click(profileDetailsObjects.saveBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User sees the value updated to \"([^\"]*)\" in \"([^\"]*)\" field$")
	public void User_sees_the_value_updated_in_page(String updatedValue, String pageName) {
		try {
			switch (pageName) {
				case "First Name":
					if (updatedValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioFirstName != null) {
							updatedValue = scenarioFirstName;
						} else {
							updatedValue = Utilities.getRandomValues("firstName");
						}
					}
					Assert.assertTrue(getFieldValue(profileDetailsObjects.firstNameInput).equalsIgnoreCase(updatedValue));
					break;
				case "Last Name":
					if (updatedValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioLastName != null) {
							updatedValue = scenarioLastName;
						} else {
							updatedValue = Utilities.getRandomValues("lastName");
						}
					}
					Assert.assertTrue(getFieldValue(profileDetailsObjects.lastNameInput).equalsIgnoreCase(updatedValue));
					break;
				case "Age":
					if (updatedValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioAge != null) {
							updatedValue = scenarioAge;
						} else {
							updatedValue = Utilities.getRandomValues("age");
						}
					}
					Assert.assertTrue(getFieldValue(profileDetailsObjects.ageInput).equalsIgnoreCase(updatedValue));
					break;
				default:
					throw new IllegalArgumentException("Field " + pageName + " not supported");
			}
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Sign up Button$")
	public void user_clicks_on_Sign_up_Button() {
		try {
			Assert.assertTrue(verifyElementDisplayed(lpo.Signup));
			click(lpo.Signup);
			click(lpo.Signup);
		}catch (AssertionError e) {
			getLogger("Could not find Sign up button, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not click to Sign up page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Email Page$")
	public void User_is_navigated_to_Email_page() {
		try {
			signupPageObjects = new SignupPageObjects(driver);
			Assert.assertTrue(verifyElementDisplayed(signupPageObjects.EmailInput));
			getLogger("Opened Sign up Page");
		} catch (AssertionError e) {
			getLogger("Could not navigate to Sign up page, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to Sign up page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" as Sign up email$")
	public void User_enters_sign_up_email(String signUpEmail) {
		try {
			if (signUpEmail.trim().equalsIgnoreCase("RANDOM")) {
				signUpEmail = Utilities.getRandomValues("email");
			}
			enterValue(signupPageObjects.EmailInput, signUpEmail);
			scenarioEmail = signUpEmail;
			getLogger("Entered signUpEmail: " + signUpEmail);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Next button in the Email Page$")
	public void User_clicks_on_Next_Button_in_the_email_page() {
		try {
			click(signupPageObjects.NextBtn);
			click(signupPageObjects.NextBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Next button in the Verify OTP page$")
	public void User_clicks_on_Next_Button_in_the_Verify_OTP_page() {
		try {
			click(signupPageObjects.OTPNextBtn);
			click(signupPageObjects.OTPNextBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Verify OTP page$")
	public void User_is_navigated_to_verify_otp_page() {
		try {
			Assert.assertTrue(verifyElementDisplayed(signupPageObjects.OtpInput));
			getLogger("Opened Verify OTP Page");
		} catch (AssertionError e) {
			getLogger("Could not navigate to Verify OTP Page, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to Verify OTP Page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Sign Up Details Page$")
	public void User_is_navigated_to_Sgn_up_details_page() {
		try {
			Assert.assertTrue(verifyElementDisplayed(signupPageObjects.UsernameInput));
			getLogger("Opened Sign Up Details Page");
		} catch (AssertionError e) {
			getLogger("Could not navigate to Sign Up Details Page, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to Sign Up Details Page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" as \"([^\"]*)\" in Details Page$")
	public void User_enters_values_in_Sign_up_page(String enteredValue, String inputField) {
		try {
			switch (inputField) {
				case "Username":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioUsername != null) {
							enteredValue = scenarioUsername;
						} else {
							enteredValue = Utilities.getRandomValues("username");
						}
					}
					enterValue(signupPageObjects.UsernameInput, enteredValue);
					scenarioUsername = enteredValue;
					getLogger("Entered username: " + enteredValue);
					break;
				case "Password":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioPassword != null) {
							enteredValue = scenarioPassword;
						} else {
							enteredValue = Utilities.getRandomValues("password");
						}
					}
					enterValue(signupPageObjects.PasswordInput, enteredValue);
					scenarioPassword = enteredValue;
					getLogger("Entered password: " + enteredValue);
					break;
				case "Confirm Password":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioPassword != null) {
							enteredValue = scenarioPassword;
						} else {
							enteredValue = Utilities.getRandomValues("password");
						}
					}
					enterValue(signupPageObjects.ConfirmPasswordInput, enteredValue);
					scenarioPassword = enteredValue;
					getLogger("Entered confirmPassword: " + enteredValue);
					break;
				default:
					throw new IllegalArgumentException("Input Field [" + inputField + "] is not supported");
			}
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Sign Up button in the Sign Up Details page$")
	public void User_clicks_on_Sign_up_Button_in_the_Sign_up_details_page() {
		try {
			click(signupPageObjects.DetailsSignUpButton);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Payments Page$")
	public void User_is_navigated_to_Payments_page() {
		try {
			Assert.assertTrue(verifyElementDisplayed(signupPageObjects.CreditCardBtn));
			getLogger("Opened Payments Page");
		} catch (AssertionError e) {
			getLogger("Could not navigate to Payments Page, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to Payments Page -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on \"([^\"]*)\" in Payments Page$")
	public void user_clicks_on_Payment_Mode_in_Payments_Page(String paymentMode) {
		try {
			switch (paymentMode) {
				case "Credit Card":
					click(signupPageObjects.CreditCardBtn);
					break;
				case "PayPal":
					click(signupPageObjects.PayPalBtn);
					break;
				case "PayU":
					click(signupPageObjects.PayUBtn);
					break;
				case "Google Pay":
					click(signupPageObjects.GooglePayBtn);
					break;
				case "PhonePe":
					click(signupPageObjects.PhonePeBtn);
					break;
				default:
					throw new IllegalArgumentException("Payment Type [" + paymentMode + "] is not supported");
			}
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" as \"([^\"]*)\" in Payments Page$")
	public void user_clicks_on_Payment_Mode_in_Payments_Page(String enteredValue, String inputField) {
		try {
			switch (inputField) {
				case "Card Number":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioCardNumber != null) {
							enteredValue = scenarioCardNumber;
						} else {
							enteredValue = Utilities.getRandomValues("cardNo");
						}
					}
					enterValue(signupPageObjects.CardNumberInput, enteredValue);
					scenarioCardNumber = enteredValue;
					getLogger("Entered cardNumber: " + enteredValue);
					break;
				case "Card Holder":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioFirstName != null) {
							if (scenarioLastName == null) {
								scenarioLastName = Utilities.getRandomValues("lastName");
							}
						} else {
							if (scenarioLastName != null) {
								scenarioFirstName = Utilities.getRandomValues("firstName");
							} else {
								scenarioFirstName = Utilities.getRandomValues("firstName");
								scenarioLastName = Utilities.getRandomValues("lastName");
							}
						}
						enteredValue = scenarioFirstName + " " + scenarioLastName;
					}
					enterValue(signupPageObjects.CardHolderNameInput, enteredValue);
					getLogger("Entered Card Holder Name: " + enteredValue);
					break;
				case "Expiry Date":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioExpiryDate != null) {
							enteredValue = scenarioExpiryDate;
						} else {
							enteredValue = Utilities.getRandomValues("expiry");
						}
					}
					enterValue(signupPageObjects.CardExpiryDateInput, enteredValue);
					scenarioExpiryDate = enteredValue;
					getLogger("Entered Expiry Date: " + enteredValue);
					break;
				case "CVV":
					if (enteredValue.equalsIgnoreCase("RANDOM")) {
						if (scenarioCvv != null) {
							enteredValue = scenarioCvv;
						} else {
							enteredValue = Utilities.getRandomValues("cvv");
						}
					}
					enterValue(signupPageObjects.CardCVVInput, enteredValue);
					scenarioCvv = enteredValue;
					getLogger("Entered CVV: " + enteredValue);
					break;
				default:
					throw new IllegalArgumentException("Input Field [" + inputField + "] is not supported");
			}
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Pay Now button in the Payments Page$")
	public void User_clicks_on_Pay_now_Button_in_the_Payments_page() {
		try {
			click(signupPageObjects.PayNowBtn);
			click(signupPageObjects.PayNowBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User is navigated to Login Page after \"([^\"]*)\"$")
	public void User_is_navigated_to_login_page(String performedOperation) {
		try {
			lpo = new LoginPageObjects(driver);
			lpo.waitTillPageLoads(1);
			Assert.assertTrue(verifyElementDisplayed(lpo.LoginUserName));
			logger.debug("------------------------------------------------------------- Logged out without issues -------------------------------------------------------------");
			scenarioPassword = scenarioUpdatedPassword;
			accessToken = getAccessToken(scenarioUsername, scenarioPassword);
			getLogger("Access token was generated successfully");
			scenarioUserId = getUserId(scenarioUsername, scenarioPassword);
			switch (performedOperation) {
				case "Sign Up":
					scenarioDbUserId = dbc.insertNewCredentials(scenarioUsername, scenarioEmail, scenarioPassword, scenarioUserId);
					scenarioUserId = getUserId(scenarioUsername, scenarioPassword);
					scenarioAppConsumerId = getAppConsumerId(scenarioUsername, scenarioPassword);
					dbc.updateUserAppConsumerId(scenarioDbUserId, scenarioEmail, scenarioAppConsumerId);
					break;
				case "Forgot Password":
					if (scenarioDbUserId == -999) {
						Map<String, Object> userDetails = dbc.getUserDetails(0, scenarioUsername);
						scenarioDbUserId = Integer.parseInt(userDetails.get("dbUserId").toString());
						scenarioUserId = userDetails.get("userId").toString();
					}
					dbc.updateUserPassword(scenarioDbUserId, scenarioEmail, scenarioUpdatedPassword);
					scenarioUpdatedPassword = null;
				case "Logout":
					break;
			}
		} catch (AssertionError e) {
			getLogger("Username field is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not log out -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}

	}

	@When("^User enters \"([^\"]*)\" as OTP in Sign Up$")
	public void User_enters_otp_in_Sign_Up(String otpValue) {
		try {
			signupPageObjects.enterOTP(otpValue);
			getLogger("Added OTP: " + otpValue);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User triggers an API call$")
	public void User_triggers_an_API_call() {
		String url = "http://dev-ingress-gateway.gaiansolutions.com/engagements-web/v1.0/606fe8042cf0760001832be3/engagements";
		if (accessToken == null)
			throw new IllegalArgumentException("Please login before making API call");
		String authorizationValue = "Bearer " + accessToken;
		Headers headersList = new Headers(new Header("Authorization", authorizationValue), new Header("Content-Type", ContentType.JSON.toString()));
		responseObj = APIController.makePostRequest(url, headersList, RTC.getVolumeControl());
	}

	@Then("^API call succeeds$")
	public void API_call_succeeds() {
		Assert.assertEquals(200, responseObj.getStatusCode());
	}

	@Then("^API call fails$")
	public void API_call_fails() {
		Assert.assertEquals(401, responseObj.getStatusCode());
	}

	@When("^User triggers a BA call$")
	public void User_triggers_a_BA_call() {
		String url = getBaseURI() + getTestData().getProperty("BA_DELIVERY_URL");
		if (accessToken == null)
			throw new IllegalArgumentException("Please login before making API call");
		String authorizationValue = "Bearer " + accessToken;
		Headers headersList = new Headers(new Header("Authorization", authorizationValue), new Header("Content-Type", ContentType.JSON.toString()));
		responseObj = APIController.makePostRequest(url, headersList, RTC.getBA(getTestData().getProperty("PACKAGE_NAME"), getTestData().getProperty("MSGBRD_FULLSCR_BA_ID")));
	}

	@When("^User clicks on Forgot Password Button$")
	public void User_clicks_on_Forgot_Password_Button() {
		try {
			Assert.assertTrue(verifyElementDisplayed(lpo.ForgotPassword));
			click(lpo.ForgotPassword);
			click(lpo.ForgotPassword);
		} catch(AssertionError e){
			getLogger("Forgot password button is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not log out -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		}finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User sees the prompt to enter email$")
	public void User_sees_the_prompt_to_enter_email() {
		try {
			forgotPasswordObjects = new ForgotPasswordObjects(driver);
		}finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" as Reset Password email$")
	public void User_enters_reset_email(String resetEmail) {
		try {
			Map<String, Object> userDetails = null;
			if (resetEmail.equalsIgnoreCase("FROM DB") || resetEmail.equalsIgnoreCase("RANDOM")) {
				if (scenarioEmail != null) {
					userDetails = dbc.getUserDetails(0, scenarioEmail);
				} else {
					userDetails = dbc.getUserDetails(0, "");
				}
				scenarioDbUserId = Integer.parseInt(userDetails.get("dbUserId").toString());
				scenarioUsername = userDetails.get("username").toString();
				scenarioEmail = userDetails.get("emailId").toString();
				scenarioPassword = userDetails.get("password").toString();
				scenarioUserId = userDetails.get("userId").toString();
				resetEmail = scenarioEmail;
			}

			/*if (resetEmail.equalsIgnoreCase("FROM DB") || resetEmail.equalsIgnoreCase("RANDOM")) {
				if (scenarioEmail != null) {
					resetEmail = scenarioEmail;
				} else {
					resetEmail = Utilities.getRandomValues("email");
				}
			}*/

			enterValue(forgotPasswordObjects.emailID, resetEmail);
			scenarioEmail = resetEmail;
			getLogger("Entered reset email: " + resetEmail);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Submit in Reset Password page$")
	public void User_clicks_on_Submit_in_Reset_Password_Page() {
		try {
			Assert.assertTrue(verifyElementDisplayed(forgotPasswordObjects.submitBtn));
			click(forgotPasswordObjects.submitBtn);
			click(forgotPasswordObjects.submitBtn);
		} catch (AssertionError e){
			getLogger("Submit button is not visible, please check");
			getLogger(e.toString());
			logger.error("------------------------------------------------------------- Could not log out -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		}
			finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User sees the prompt for OTP and New Password$")
	public void User_sees_the_prompt_for_OTP_and_new_Password() {
		try {
			Assert.assertTrue(verifyElementDisplayed(forgotPasswordObjects.OtpInput));
			Assert.assertTrue(verifyElementDisplayed(forgotPasswordObjects.NewPassword));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" as OTP in Reset Password page$")
	public void User_enters_otp_in_Reset_Password_page(String otpValue) {
		try {
			forgotPasswordObjects.enterOTP(otpValue);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User enters \"([^\"]*)\" in Reset Password page$")
	public void User_enters_values_in_Reset_password_page(String enteredValue) {
		try {
			if (enteredValue.equalsIgnoreCase("RANDOM")) {
				enteredValue = Utilities.getRandomValues("password");
			}
			enterValue(forgotPasswordObjects.NewPassword, enteredValue);
			scenarioUpdatedPassword = enteredValue;
			getLogger("Entered new Password: " + enteredValue);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@When("^User clicks on Verify Button in the Reset Password page$")
	public void User_clicks_on_Verify_button_in_Reset_Password_Page() {
		try {
			click(forgotPasswordObjects.VerifyOtpButton);
			click(forgotPasswordObjects.VerifyOtpButton);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User gets a toast message in \"([^\"]*)\"$")
	public void userGetsAToastMessageIn(String Toast) {
		try {
			switch (Toast) {
				case "Login page":
					Assert.assertTrue(verifyElementDisplayed(lpo.LoginToastMsg));
					getLogger("Toast message is visible");
					break;
				case "Signup page":
					Assert.assertTrue(verifyElementDisplayed(signupPageObjects.SignupToastMsg));
					getLogger("Toast message is visible");
					break;
				case "Forgot password":
					Assert.assertTrue((verifyElementDisplayed(forgotPasswordObjects.ForgotToastMsg)));
					getLogger("Toast message is visible");
					break;
				case "Profile page":
					Assert.assertTrue(verifyElementDisplayed(ppo.ToastMsg));
					getLogger("Toast message is visible");
					break;
			}
		} catch (AssertionError e) {
			throw new AssertionError(e.toString());
		} finally {
			takeScreenshot();
		}
	}

	@And("^User clicks on Back button in the Email Page$")
	public void userClicksOnBackButtonInTheEmailPage() {
		try {
			Assert.assertTrue(verifyElementDisplayed(signupPageObjects.BackBtn));
			click(signupPageObjects.BackBtn);
			click(signupPageObjects.BackBtn);
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@Then("^User checks the UI of \"([^\"]*)\"$")
	public void userChecksTheUIOf(String signUp) {
		try {
			staticWait(5);
			switch (signUp) {
				case "email textbox":
					Assert.assertTrue(verifyElementDisplayed(signupPageObjects.EmailInput));
					click(signupPageObjects.EmailInput);
					break;
				case "back button":
					Assert.assertTrue(verifyElementDisplayed(signupPageObjects.BackBtn));
					click(signupPageObjects.BackBtn);
					break;
				case "Next button":
					Assert.assertTrue(verifyElementDisplayed(signupPageObjects.NextBtn));
					click(signupPageObjects.NextBtn);
					break;
				case "login button":
					Assert.assertTrue(verifyElementDisplayed(signupPageObjects.LoginBtn));
					click(signupPageObjects.LoginBtn);
					break;
			}
		} finally {
			takeScreenshot();
		}
	}

	@When("^User wants to identify the \"([^\"]*)\" image$")
	public void userWantsToIdentifyTheImage(String imgName) {
		try {
			Assert.assertTrue(verifyElementDisplayed(lpo.getImg(imgName)));
		} finally {
			try {
				takeScreenshot();
			} catch (Exception ex) {
				getLogger("Exception while taking screenshot: " + ex.getMessage());
			}
		}
	}

	@And("^User clicks on the \"([^\"]*)\" channel under Mobius Recommends$")
	public void userClicksOnChannelUnderMobiusRecommends(String channelName) {
		try {
			Assert.assertTrue(verifyElementDisplayed(hpo.recommendsText));
			switch (channelName) {
				case "WKAR NewsTalk":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "CURIOUS CREW":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR FM":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR Classical":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR NewsTalk1":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR JAZZ":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "WKAR Radio Reading Service":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(hpo.getChannel(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + channelName);
			}
		} catch (AssertionError e) {
			getLogger( "channel is not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to the particular Channel -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		}finally {
			takeScreenshot();
		}
	}


	@Then("^Video player opens and video is not playing$")
	public void videoPlayerOpensAndVideoIsNotPlaying() {
		try {
			Assert.assertTrue(verifyElementDisplayed(hpo.ToggleBar));
		} catch (AssertionError e) {
			getLogger( "Tooglebar is not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to particular channel -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		}finally {
			takeScreenshot();
		}
	}

	@Then("^User able to see Live now Text$")
	public void userAbleToSeeLiveNowText() {
		try {
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.LiveNowTxt));
		} finally {
			takeScreenshot();
		}
	}

	@Then("^user clicks on \"([^\"]*)\" under Live Now$")
	public void userClicksOnUnderLiveNow(String channelName) {
		try {
			switch (channelName) {
				case "Quiz time":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getLiveNow(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "Responder":
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getLiveNow(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "Emoticons":
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getLiveNow(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "MB":
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getLiveNow(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
			}
		}catch (AssertionError e) {
			getLogger( "channel is not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to particular channel -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			takeScreenshot();
		}
	}

	@Then("^user able to see Trending Text$")
	public void userAbleToSeeTrendingText() {
		try {
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.TrendingText));
		} finally {
			takeScreenshot();
		}
	}

	@Then("^User clicks on \"([^\"]*)\" under Trending$")
	public void userClicksOnUnderTrending(String channelName) {
		try {
			staticWait(5);
			switch (channelName) {
				case "Quiz time":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.LEFT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getTrending(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "Responder":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getTrending(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "Emoticons":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getTrending(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				case "MB":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(nextGenPage.getTrending(channelName)));
					driver.getKeyboard().sendKeys(Keys.ENTER);
					break;
			}
		}catch (AssertionError e) {
			getLogger( "channel is not visible, please check");
			getLogger(e.toString());
			logger.debug("------------------------------------------------------------- Could not navigate to particular channel -------------------------------------------------------------");
			throw new AssertionError(e.toString());
		} finally {
			takeScreenshot();
		}
	}

	@Then("^User is able to see videos under Continue to watch$")
	public void userIsAbleToSeeVideosUnderContinueToWatch() {
		try {
			vpo = new VoDPageObjects(driver);
			vpo.waitTillPageLoads(1);
			if (verifyElementDisplayed(vpo.TopRatedText, 5)) {
				Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedText, 5));
				getLogger("Top rated text is visible");
				scrollToElement(vpo.TopRatedVideoThumbnails, true);
				scrollToElement(vpo.ContinueToWatchThumbnails, true);
				Assert.assertTrue(verifyElementDisplayed(vpo.continueToWatchText, 5));
				getLogger("continue to watch text is visible");
				Assert.assertTrue(verifyElementDisplayed(vpo.ContinueToWatchThumbnails, 5));
				getLogger("Continue To watch Thumbnails is visible");
				logger.debug("------------------------------------------------------------- Navigated without issues -------------------------------------------------------------");
				dbc.updateCriteria("NgTV", System.getProperty("APP_VERSION"), 10.0, Criteria.Is_Navigation_happening_properly_in_TV_App);
			}
		} finally {
			takeScreenshot();
		}
	}

	@And("^User clicks on \"([^\"]*)\" Channel$")
	public void userClicksOnChannel(String channel) {
		try {
			Assert.assertTrue(verifyElementDisplayed(vpo.getChannel(channel), 5));
			click(vpo.getChannel(channel));
		} finally {
			takeScreenshot();
		}
	}

	@And("^Resume button is displayed to play the video and Click$")
	public void resumeButtonIsDisplayedToPlayTheVideoAndClick() {
		try {
			Assert.assertTrue(verifyElementDisplayed(vpo.ResumeButton));
			click(vpo.ResumeButton);
			Assert.assertTrue(verifyElementDisplayed(vpo.videoPlayerToggle));
		} finally {
			takeScreenshot();
		}
	}

	@And("^User able to see the Resume button in channel page$")
	public void userAbleToSeeTheResumeButtonInChannelPage() {
		try {
			Assert.assertTrue(verifyElementDisplayed(vpo.ResumeButton));
		} finally {
			takeScreenshot();
		}
	}

	@Then("^user able to see Top rated Text in VOD Page$")
	public void userAbleToSeeTopRatedTextInVODPage() {
		try {
			Assert.assertTrue(verifyElementDisplayed(vpo.TopRatedText));
		} finally {
			takeScreenshot();
		}
	}

	@Then("^User is able to see today's date$")
	public void user_is_able_to_see_todays_date() {
		try {
			Assert.assertTrue(verifyElementDisplayed(lipo.CurrentDate));
			getLogger("User is able to see the current data");
		} finally {
			takeScreenshot();
		}
	}

	@Then("Write a Review button is visible")
	public void Write_a_review_button_is_visible() {
		try {
			scrollToElement(videoDescPage.WriteReviewButton, true);
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.WriteReviewButton));
		} finally {
			takeScreenshot();
		}
	}

	@When("User clicks on Write A Review button")
	public void User_clicks_on_Write_A_Review_button() {
		try {
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.WriteReviewButton));
			click(videoDescPage.WriteReviewButton);
			click(videoDescPage.WriteReviewButton);
		} finally {
			takeScreenshot();
		}
	}

	@Then("User sees Star rating input and review text input")
	public void User_sees_Star_rating_input_and_review_text_input() {
		try {
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.WriteReviewRating));
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.WriteReviewTextArea));
		} finally {
			takeScreenshot();
		}
	}

	@Then("User provides \"([^\"]*)\" as star rating value on User Review Page")
	public void User_provides_star_rating_value_on_user_review_page(String starRatingValue) {
		try {
			videoDescPage.selectStarRating(starRatingValue);
		} finally {
			takeScreenshot();
		}
	}

	@Then("User writes \"([^\"]*)\" as review text on User Review Page")
	public void User_writes_review_text_on_user_review_page(String ratingReview) {
		try {
			if (ratingReview.equalsIgnoreCase("RANDOM")) {
				ratingReview = Utilities.getRandomValues("text");
			}
			enterValue(videoDescPage.WriteReviewTextArea, ratingReview);
			scenarioRatingReview = ratingReview;
		} finally {
			takeScreenshot();
		}
	}

	@Then("User clicks on submit button on User Review Page")
	public void User_clicks_on_submit_button_on_User_Review_Page() {
		try {
			Assert.assertTrue(verifyElementDisplayed(videoDescPage.WriteReviewSubmit));
			click(videoDescPage.WriteReviewSubmit);
			click(videoDescPage.WriteReviewSubmit);
		} finally {
			takeScreenshot();
		}
	}

	@Then("Entered star rating \"([^\"]*)\" and review \"([^\"]*)\" is \"([^\"]*)\" on the Video description page")
	public void Entered_star_rating_and_review_is_present_on_Video_Description_page(String starRatingValue, String reviewText, String presenceValue) {
		try {
			if (reviewText.equalsIgnoreCase("RANDOM")) {
				if (scenarioRatingReview != null) {
					reviewText = scenarioRatingReview;
				} else {
					reviewText = Utilities.getRandomValues("text");
				}
			}
			scrollToElement(videoDescPage.WriteReviewButton, true);
			switch (presenceValue.toUpperCase()) {
				case "PRESENT":
					Assert.assertTrue(videoDescPage.checkIfReviewIsPresent(starRatingValue, reviewText));
					break;
				case "NOT PRESENT":
					Assert.assertFalse(videoDescPage.checkIfReviewIsPresent(starRatingValue, reviewText));
					break;
				default:
					throw new IllegalArgumentException("Presence Value [" + presenceValue + "] is not supported");
			}
		} finally {
			takeScreenshot();
		}
	}

	@Then("^User able to see Seasons text$")
	public void userAbleToSeeSeasonsText() {
		try {
			Assert.assertTrue(verifyElementDisplayed(vpo.seasonText));
		} finally {
			takeScreenshot();
		}
	}

	@And("^User clicks on \"([^\"]*)\" in VoD page$")
	public void userClicksOnInVoDPage(String season) {
		try {
			switch (season){
				case "Season-1":
					driver.getKeyboard().sendKeys(Keys.DOWN);
					driver.getKeyboard().sendKeys(Keys.DOWN);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-2":
                    driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-3":
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
                    driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-4":
					driver.getKeyboard().sendKeys(Keys.UP);
                    driver.getKeyboard().sendKeys(Keys.LEFT);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-5":
                    driver.getKeyboard().sendKeys(Keys.UP);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-6":
					staticWait(1);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
				case "Season-7":
					staticWait(1);
					driver.getKeyboard().sendKeys(Keys.UP);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					driver.getKeyboard().sendKeys(Keys.RIGHT);
					Assert.assertTrue(verifyElementDisplayed(vpo.getSeason(season)));
					break;
			}
		} catch(AssertionError e) {
					throw new AssertionError(e.toString());
		} finally
		{
			takeScreenshot();
		}
	}

	@Then("^User navigates to particular page and clicks on launch button$")
	public void userNavigatesToParticularPageAndClicksOnLaunchButton()  {
		try{
			takeScreenshot();
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.LaunchButton));
			click(nextGenPage.LaunchButton);
			click(nextGenPage.LaunchButton);
			staticWait(10);
			}finally{
			takeScreenshot();
		}
	}

	@And("^User able to see the launch button$")
	public void userAbleToSeeTheLaunchButton() {
		try{
			Assert.assertTrue(verifyElementDisplayed(nextGenPage.LaunchButton));
		}finally{
			takeScreenshot();
		}
	}

	@And("^User able see the videos updated or not$")
	public void userAbleSeeTheVideosUpdatedOrNot() {
		try{
			driver.getKeyboard().sendKeys(Keys.ENTER);
			staticWait(5);
			driver.getKeyboard().sendKeys(Keys.DOWN);
		}finally {
			takeScreenshot();
		}
	}
}
