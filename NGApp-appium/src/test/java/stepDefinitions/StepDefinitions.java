package stepDefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import controller.APIController;
import cucumber.api.java.en.And;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.RTC;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import baseLibrary.Utilities;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.appmanagement.ApplicationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.*;
import controller.DBController;
import enums.Criteria;

public class StepDefinitions extends Utilities {
    LoginPageObjects lpo;
    HomePageObjects hpo;
    ProfilePageObjects ppo;
    LiveStreamingPageObjects lipo;
    VoDPageObjects vpo;
    NextGenPageObjects npo;
    VideoPageObjects videoPlayer;
    SignupPageObjects spo;
    ForgotPasswordObjects fpo;
    Response responseObj;
    JsonPath parser;
    private String accessToken = null;
    private String scenarioUsername=null;
    private String scenarioPassword=null;
    private String scenarioEmail=null;
    private String scenarioUpdatedPassword = null;
    private String scenarioUserId = null;
    private String scenarioAppConsumerId = null;
    private int scenarioDbUserId = -999;
    private String scenarioFirstName = null;
    private String scenarioLastName = null;
    private String scenarioAge = null;
    private String scenarioCardNumber = null;
    private String scenarioExpiryDate = null;
    private String scenarioCvv = null;
    private String defaultUserName = "auto" + getTime("ddMMHHmm") + "@gatestautomation.com";
    private static final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);
    private static final String VOD = "VOD";
    private static final String LiveChannel = "LiveChannel";
    private static final String HomePage = "HomePage";
    private int noOfVideosPlaying = 0;
    private String bookmarkVideo;
    private String engagementId= null;

    @Before
    public void getScenarioName() throws Throwable, Throwable {
        System.out.println("----Scenario ");
        dbc = new DBController();
        try {
            setUp();
            dbc.updateCriteria("NGAPP", System.getProperty("APP_VERSION"), 10.0, Criteria.Does_the_APP_install_without_issues);
        } catch (MalformedURLException e) {
            dbc.updateCriteria("NGAPP", System.getProperty("APP_VERSION"), 0.0, Criteria.Does_the_APP_install_without_issues);
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
    public static void CloseReport(Scenario scenario) {
    //    dbc.CloseConnection();
        if (!System.getProperty("localRun").equalsIgnoreCase("true"))
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (scenario.isFailed() ? "failed" : "passed"));
        // takeScreenshot();
        tearDownBrowsers(scenario);
    }

    @Given("^I get data from DB$")
    public void getData() throws SQLException {
        System.out.println(">>> DUMMY TESTING. DONT RUN");
        updateCOECriteria(Criteria.Does_the_APP_install_without_issues, 10.0);
    }

    @Given("^As a user I want to open the NG Application$")
    public void as_a_user_I_want_to_open_the_NG_Application() throws Throwable {
        try {
            ApplicationState state = ((AndroidDriver<AndroidElement>) driver).queryAppState("com.gaian.ngapp");
            getLogger("----APPLICATION STATE >>  >>" + state);
            if (state == ApplicationState.NOT_INSTALLED || state == ApplicationState.NOT_RUNNING) {
                throw new Exception("App could not be installed");
            }
            if (state == ApplicationState.RUNNING_IN_BACKGROUND) {
                throw new Exception("App is not launched.");
            }
            ((AndroidDriver<AndroidElement>) driver).manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            lpo = new LoginPageObjects(driver);
            staticWait(2);
            click(lpo.Skip);
            staticWait(5);
            updateCOECriteria(Criteria.Does_the_APP_install_without_issues, 10.0);
        } catch (Exception e) {
            e.printStackTrace();
            updateCOECriteria(Criteria.Does_the_APP_install_without_issues, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User enters \"([^\"]*)\" in Username field$")
    public void user_enters_in_Username_field(String username) throws Throwable {
        try {
            if (username.trim().equalsIgnoreCase("RANDOM")) {
                if(scenarioUsername != null){
                    username =scenarioUsername;
                }
                else {
                    username = Utilities.getRandomValues("username");
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
            if (username.equalsIgnoreCase("AUTO")) {
                if(scenarioUsername != null){
                    username = scenarioUsername;
                } else{
                    username =defaultUserName;
                }
            }
            enterValue(lpo.LoginUserName, username);
            scenarioUsername = username;
            getLogger("----ScenarioUsername For Login : "+scenarioUsername);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User enters \"([^\"]*)\" in Password field$")
    public void user_enters_in_Password_field(String password) throws Throwable {
        try {
            getLogger("----ScenarioPassword in LoginPage is "+scenarioPassword+" ScenarioUpdatedPassword in LoginPageis "+scenarioUpdatedPassword);
            if (password.trim().equalsIgnoreCase("RANDOM")) {
                if (scenarioUpdatedPassword != null) {
                    password = scenarioUpdatedPassword;
                }   else {
                        if(scenarioPassword != null) {
                            password=scenarioPassword;
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
                } else if(scenarioUpdatedPassword!=null){
                    password = scenarioUpdatedPassword;
                } else{
                    password = scenarioPassword;
                }
            }
            enterValue(lpo.LoginPassword, password);
            scenarioPassword = password;
            getLogger("----Scenario Password Used "+scenarioPassword);
        } finally {
            takeScreenshot();
        }
    }

    @When("^Clicks on login button$")
    public void clicks_on_login_button() throws Throwable {
        try {
            click(lpo.LoginButton);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }
    }

    @Given("^User is navigated to Towers screen$")
    public void user_is_navigated_to_Towers_screen() throws Throwable {
        try {
            takeScreenshot();
            lpo.waitTillPageLoads(5);
            //getLogger("----User has been successfully navigated to the Tower Screen");
            hpo = new HomePageObjects(driver);
            Assert.assertTrue(VerifyElementDisplayed(hpo.GoButtoninMap));
            updateCOECriteria(Criteria.Can_We_Login_without_issues, 10.0);
            //getLogger(" Scenario Username : "+scenarioUsername+ " Scenario Email : "+scenarioEmail+" Scenario Password : "+scenarioPassword+" Scenario UserId : "+scenarioUserId);
            accessToken = getAccessToken(scenarioUsername, scenarioPassword);
//            if(accessToken!=null)
//                getLogger(" Access token is generated for the given credentials  ");
            scenarioUserId = getUserId(scenarioUsername, scenarioPassword);
            scenarioAppConsumerId = getAppConsumerId(scenarioUsername, scenarioPassword);
            getLogger("Scenario Id/ User Id "+scenarioUserId+" App Consumer Id " + scenarioAppConsumerId);
            if(scenarioAppConsumerId != null)
                dbc.updateUserAppConsumerId(scenarioDbUserId, scenarioEmail, scenarioAppConsumerId);
            } catch (Exception e) {
            updateCOECriteria(Criteria.Can_We_Login_without_issues, 0.0);
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on continue button in Towers screen$")
    public void user_clicks_on_continue_button_in_Towers_screen() throws Throwable {
        try {
            takeScreenshot();
            lpo.waitTillPageLoads(5);
            click(lpo.GoButtoninMap);
            hpo = new HomePageObjects(driver);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to view profile$")
    public void user_is_able_to_view_profile() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.profileSelectionAfterLogin));
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on profile$")
    public void user_clicks_on_profile() throws Throwable {
        try {
            click(lpo.profileSelectionAfterLogin);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see Home, NextGenApps, Live Channels, VOD, Account$")
    public void user_is_able_to_see_Home_NextGenApps_Live_Channels_VOD_Account() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.HomeMenu));
            Assert.assertTrue(VerifyElementDisplayed(lpo.NextGenApps));
            Assert.assertTrue(VerifyElementDisplayed(lpo.LiveChannels));
            Assert.assertTrue(VerifyElementDisplayed(lpo.VOD));
            Assert.assertTrue(VerifyElementDisplayed(lpo.ProfileMenu));
        } finally {
            takeScreenshot();
        }
    }

    @When("^User is able to see the List of videos available$")
    public void user_is_able_to_see_the_List_of_videos_available() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(hpo.videoTxt));
            getLogger("----User is able to see the list of videos available");
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on NextGenApps$")
    public void user_clicks_on_NextGenApps() throws Throwable {
        try {
            click(lpo.NextGenApps);
            npo = new NextGenPageObjects(driver);
            lpo.waitTillPageLoads(2);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see Trending Apps, Curious Crew sections$")
    public void user_is_able_to_see_Trending_Apps_Curious_Crew_sections() throws Throwable {
        try {
            lpo.waitTillPageLoads(2);
            Assert.assertTrue(VerifyElementDisplayed(npo.CuriousCrewinNextGenApps));
            Assert.assertTrue(VerifyElementDisplayed(npo.TrendingAppsinNextGenApps));
            getLogger("----User is able to see the TrendingApps and Curious Crew Section");
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (AssertionError e) {
            updateCOECriteria(Criteria.Has_Page_Loaded_Properly_On_Time, 0.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to open any Trending App$")
    public void user_is_able_to_open_any_Trending_App() throws Throwable {
        try {
            click(lpo.TrendingApp);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on LiveChannels$")
    public void user_clicks_on_LiveChannels() throws Throwable {
        try {
            click(lpo.LiveChannels);
            lipo = new LiveStreamingPageObjects(driver);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see available channels$")
    public void user_is_able_to_see_available_channels() throws Throwable {
        try {
            lpo.waitTillPageLoads(1);
            Assert.assertTrue(VerifyElementDisplayed(lipo.Channelslist));
            getLogger("----User is able to see the list of available channels");
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^Play button is displayed to play the video$")
    public void play_button_is_displayed_to_play_the_video() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(videoPlayer.playpauseBtn));
            click(videoPlayer.playpauseBtn);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^Back button is displayed$")
    public void back_button_is_displayed() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(vpo.BackButton));
            updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 10.0);
            click(vpo.BackButton);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on profile menu$")
    public void user_clicks_on_profile_menu() throws Throwable {
        try {
            click(lpo.ProfileMenu);
            ppo = new ProfilePageObjects(driver);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see devices, username, logout, version, settings$")
    public void user_is_able_to_see_devices_username_logout_version_settings_options() throws Throwable {
        try {
            lpo.waitTillPageLoads(10);
            Assert.assertTrue(VerifyElementDisplayed(ppo.DevicesinProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.LoggedinProfileUserName));
            Assert.assertTrue(VerifyElementDisplayed(ppo.VersioninProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.Logout));
            Assert.assertTrue(VerifyElementDisplayed(ppo.SettingsinProfile));
            getLogger("----User is able to see Devices, Profile Information, Version, Logout section");
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (AssertionError e) {
            updateCOECriteria(Criteria.Has_Page_Loaded_Properly_On_Time, 0.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on Logout Button$")
    public void user_clicks_on_Logout_Button() throws Throwable {
        try {
            click(lpo.Logout);
            lpo.waitTillPageLoads(1);
        } finally {
            takeScreenshot();
        }

    }

    @When("^user clicks on Yes on confirmation popup$")
    public void user_clicks_on_Yes_on_confirmation_popup() throws Throwable {
        try {
            click(lpo.ContinueLogout);
            click(lpo.ContinueLogout);
        } finally {
            takeScreenshot();
        }

    }

    @Then("^Verify user is logged out$")
    public void verify_user_is_logged_out() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginUserName));
            getLogger("----User has been successfully logged out of the application and has been redirected to Login Page");
            updateCOECriteria(Criteria.Is_User_Able_to_Logout_from_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_User_Able_to_Logout_from_App, 0.0);
        } finally {
            takeScreenshot();
        }

    }

    @When("^User clicks on Back Button$")
    public void user_clicks_on_Back_Button() throws Throwable {
        try {
            click(lpo.BackButton);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on Profile button$")
    public void user_clicks_on_Profile_button() throws Throwable {
        try {
            click(lpo.ProfileMenu);
        } finally {
            takeScreenshot();
        }

    }

    @When("^ user clicks on Profile icon$")
    public void user_clicks_on_Profile_icon() throws Throwable {
        try {
            click(ppo.AddNewProfile);

        } finally {
            takeScreenshot();
        }
    }

    @Then("^Edit Delete and Add New options are displayed$")
    public void edit_Delete_and_Add_New_options_are_displayed() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.DeleteProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.EditProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.AddNewProfile));
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see today date$")
    public void user_is_able_to_see_todaydate() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lipo.timeLine));
            getLogger("----User is able to see the time line");
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see search button$")
    public void user_is_able_to_see_search_button() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lipo.SearchButton));
            getLogger("----User is able to see the search button");
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on VOD page$")
    public void User_clicks_on_VOD_Page() {
        try {
            click(lpo.VOD);
            vpo = new VoDPageObjects(driver);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see vidoes under Recommended, Recently Uploaded and Trending Now Sections$")
    public void User_is_able_to_see__vidoes_under_Recommended_Recently_Uploaded_and_Trending_Now() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(vpo.Releasesin2019));
            Assert.assertTrue(VerifyElementDisplayed(vpo.RecentUploaded_Videos));
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see videos under Releases in 2019 , Top rated$")
    public void user_is_able_to_see_videos_under_Releases_in_2019_Top_rated() {
        try {
            lpo.waitTillPageLoads(3);
            vpo = new VoDPageObjects(driver);
            Assert.assertTrue(VerifyElementDisplayed(vpo.releasein2019));
            Assert.assertTrue(VerifyElementDisplayed(vpo.getTopRated));
            Assert.assertTrue(VerifyElementDisplayed(vpo.seeAll));
            getLogger("----User is able to see Text, Top Rated and Releases in 2019");
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (AssertionError e) {
            updateCOECriteria(Criteria.Has_Page_Loaded_Properly_On_Time, 0.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on a video$")
    public void User_clicks_on_a_video() {
        try {
            click(vpo.VideoinVOD);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User click on back button on player screen$")
    public void user_click_on_back_button_on_player_screen() {
        try {
            updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 10.0);
            if (VerifyElementDisplayed(videoPlayer.videoBackBtn)) {
                click(videoPlayer.videoBackBtn);
                click(videoPlayer.videoBackBtn);
            } else {
                driver.navigate().back();
            }
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 0.0);
        } finally {

            takeScreenshot();

        }
    }

    @Then("^Back button is displayed on the player screen in live channel$")
    public void Back_button_is_displayed_on_the_player_screen_in_live_channel() {
        try {
            click(lipo.BackButton);
        } finally {
            takeScreenshot();
        }
    }

    @When("^user clicks on a video from NextGenApps screen$")
    public void user_clicks_on_a_video_from_NextGenApps_screen() {
        try {
            npo = new NextGenPageObjects(driver);
            click(npo.playvideoinNextgenApps);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on a video on Livechannel$")
    public void user_clicks_on_a_video_on_Livechannel() {
        try {
            click(lipo.Channelslist);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on resume/play Button on the selected video$")
    public void user_clicks_on_resume_playButton_Button_on_the_selected_video() {
        try {
            click(vpo.resumeBtn);
            staticWait(5);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on a video on vod$")
    public void user_clicks_on_a_video_on_vod() {
        try {
            click(vpo.VideoinVOD);
            lpo.waitTillPageLoads(2);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^Check whether the Play, Pause etc. are visible$")
    public void gCheck_whether_the_Play_Pause_etc_are_visible() {
        try {
            videoPlayer = new VideoPageObjects(driver);
            Assert.assertTrue(VerifyElementDisplayed(videoPlayer.videoBackBtn));
            Assert.assertTrue(VerifyElementDisplayed(videoPlayer.wkarIcon));
            Assert.assertTrue(VerifyElementDisplayed(videoPlayer.playpauseBtn));
            getLogger("----User is able to see the wkar logo, Back Button and Play and Pause Button");
        } catch (AssertionError e) {
            updateCOECriteria(Criteria.Has_Page_Loaded_Properly_On_Time, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks system back button$")
    public void User_clicks_system_back_button() {
        try {
            driver.navigate().back();
          //  lpo.waitTillPageLoads(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }


    @When("^User is able to see the video in \"([^\"]*)\" page$")
    public void User_is_able_to_see_the_video(String pageName) {
        try {
            videoPlayer = new VideoPageObjects(driver);
            boolean status = VerifyElementDisplayed(videoPlayer.videoStream);
            if (VerifyElementDisplayed(videoPlayer.videoStream)) {
                getLogger("Video is not playing in the " + pageName + " Page");
                if (pageName.equals((VOD)))
                    updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 0.0);
                else if (pageName.equals(LiveChannel))
                    updateCOECriteria(Criteria.Is_Video_playing_from_Live_Screen_in_App, 0.0);
                else if (pageName.equals(HomePage))
                    updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 0.0);
                Assert.assertTrue(false);
            } else {
                getLogger("Video is playing in the " + pageName + " Page");
                if (pageName.equals((VOD)))
                    updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 10.0);
                else if (pageName.equals(LiveChannel))
                    updateCOECriteria(Criteria.Is_Video_playing_from_Live_Screen_in_App, 10.0);
                else if (pageName.equals(HomePage))
                    updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 10.0);
                Assert.assertTrue(true);
            }
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on HomePage$")
    public void User_clicks_on_HomePage() {
        try {
            click(lpo.HomeMenu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^User triggers BA delivery API Call$")
    public void User_triggers_an_API_call() throws IOException {
        String url = getReadConfig().getBaseURI() + getReadConfig().getBADeliveryEndpoint();
        getLogger("----URI USER >>> " + url);
        if (accessToken == null)
            throw new IllegalArgumentException("----Please login before making API call");
        String authorizationValue = "Bearer " + accessToken;
        Headers headersList = new Headers(new Header("Authorization", authorizationValue), new Header("Content-Type", ContentType.JSON.toString()));
        responseObj = APIController.makePostRequest(url, headersList, RTC.getNewBADeliveryCall(getReadConfig().getBAPackageName(), getReadConfig().getBAID()));
        responseObj.prettyPeek();
        if(responseObj.getStatusCode() == 200){
            JsonPath jsonPath = new JsonPath(responseObj.asString());
            engagementId = jsonPath.getString("id");
            getLogger(" The Engagement ID for the BA Delivery API : "+engagementId);
        }
        else
            Assert.fail(" Something went wring with the API. Status Code :"+responseObj.getStatusCode());
    }

    @Then("^User verifies if the BA Package is recieved by the device$")
    public void API_call_succeeds() {
        try {
            Assert.assertEquals(200, responseObj.getStatusCode());
            staticWait(45);
            saveDeviceLogs("BATEST");
            if(verifyBAInLogs(getReadConfig().getBAPackageName()))
                Assert.assertTrue(true);
            else
                Assert.fail("BA not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User verifies if the files got uploaded to pitcher$")
    public void user_triggers_pitcher_api_call(){
        if(responseObj.statusCode() == 200 && engagementId != null){
            boolean flag =false;
            String url = getReadConfig().getBaseURI() + getReadConfig().getPitcherURL();
            if( accessToken == null )
                accessToken = getAccessToken(scenarioUsername,scenarioPassword);
            Headers headers = new Headers(new Header("Authorization","Bearer " + accessToken),new Header("Content-Type", ContentType.JSON.toString()));
            Map<String, String> payloadMap = new HashMap<>();
            payloadMap.put("engagementID",engagementId);
            int noOfTrigger = 10;
                do {
                    this.responseObj = APIController.makeGetRequest(url, headers, payloadMap);
                    parser = new JsonPath(responseObj.asString());
                    responseObj.prettyPeek();
                    System.out.println("JSON PATH PARSER "+parser.getBoolean("[0].isDelivered"));

                    if (responseObj.getStatusCode() == 200) {
                        JSONArray jsonarray = new JSONArray(responseObj.asString());
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            flag = jsonobject.getBoolean("isDelivered");
                            System.out.println(" Status of isDelievered " + flag);
                        }

                        if (flag) {
                            getLogger(" The File got successfully uploaded to the pitcher ");
                            Assert.assertTrue(true);
                            break;
                        }
                        staticWait(5);
                        noOfTrigger--;
                    }
                }
                while (noOfTrigger > 0);
                if (!flag) {
                    logger.info("File did not get uploaded to pitcher");
                    Assert.fail();
                }
            }
            else
                Assert.fail("Something went wrong with API. Status Code :"+responseObj.statusCode());
    }


    @Then("^API call fails$")
    public void API_call_fails() {
        Assert.assertEquals(401, responseObj.getStatusCode());
    }

    @Then("^User clicks on VOD back button$")
    public void User_clicks_on_VOD_back_button() {
        try {
            click(videoPlayer.videoBackBtn);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to search bar, favorites, go button on tower screen$")
    public void User_is_able_to_search_bar_favorites_go_button_on_tower_screen() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(hpo.locationSearch));
            Assert.assertTrue(VerifyElementDisplayed(hpo.favourite));
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on a video on home page which is not playing$")
    public void user_clicks_on_a_video_on_home_page_which_is_not_playing() {
        try {
            click(hpo.playInHomepage); // make use of playInHomepage for final purpose
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on ProfileSection to edit profile details$")
    public void user_clicks_on_ProfileSection_to_edit_profile_details() {
        try {
            click(ppo.EditProfileName);
            Assert.assertTrue(VerifyElementDisplayed(ppo.FirstNameNewProfile));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            lpo.waitTillPageLoads(5);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on Back Button on profile edit page$")
    public void user_clicks_on_Back_Button_on_profile_edit_page() {
        try {
            click(ppo.BackButtonFromProfile);
            Assert.assertTrue(VerifyElementDisplayed(ppo.LoggedinProfileUserName));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User updates \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" etc.$")
    public void user_updates_firstname_lastname_age_address_etc(String firstname, String lastname, String age, String address) throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.FirstNameNewProfile));
            clear(ppo.FirstNameNewProfile);
            enterValue(ppo.FirstNameNewProfile, firstname);
            Assert.assertTrue(VerifyElementDisplayed(ppo.LastNameNewProfile));
            clear(ppo.LastNameNewProfile);
            enterValue(ppo.LastNameNewProfile, lastname);
            Assert.assertTrue(VerifyElementDisplayed(ppo.AgeNewProfile));
            clear(ppo.AgeNewProfile);
            enterValue(ppo.AgeNewProfile, age);
            Assert.assertTrue(VerifyElementDisplayed(ppo.AddressNewProfile));
            clear(ppo.AddressNewProfile);
            enterValue(ppo.AddressNewProfile, address);
            Assert.assertTrue(VerifyElementDisplayed(ppo.SaveButtonInProfile));
            click(ppo.SaveButtonInProfile);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }

    }

    @When("^User clicks on Signup on the Login Page$")
    public void User_clicks_on_Signup_on_the_Login_Page() {
        try {
            click(lpo.signUp);
            spo = new SignupPageObjects(driver);
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User enters \"([^\"]*)\" in email field of Signup page$")
    public void User_enters_email_in_email_field_of_Signup_page(String email) {
        try {
            if (email.equalsIgnoreCase("AUTO")) {
                email = defaultUserName;
            }
            if (email.trim().equalsIgnoreCase("RANDOM")) {
                email = Utilities.getRandomValues("email");
            }
            if (email.trim().equalsIgnoreCase("FROM DB")) {
                if (scenarioEmail == null) {
                    Map<String, Object> userRecord = dbc.getUserDetails(0, null);
                    email = userRecord.get("emailId").toString();
                    scenarioPassword = userRecord.get("password").toString();
                } else {
                    email = scenarioEmail;
                }
            }
            enterValue(spo.emailInSignup, email);
            scenarioEmail = email;
            getLogger("Scenario Email Used For SignUp "+scenarioEmail);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on next button in Signup page$")
    public void User_clicks_on_next_button_in_Signup_page() {
        try {
            click(spo.nextButtonInSignUp);
          //  updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User enters the \"([^\"]*)\" on screen two of Signup page$")
    public void User_enters_the_otp_on_screen_two_of_Signup_page(String otp) {
        try {
            String[] otparray = otp.split("");
            enterValue(spo.otpOneInSignUp, otparray[0]);
            enterValue(spo.otpTwoInSignUp, otparray[1]);
            enterValue(spo.otpThreeInSignUp, otparray[2]);
            enterValue(spo.otpFourInSignUp, otparray[3]);
            enterValue(spo.otpFiveInSignUp, otparray[4]);
            enterValue(spo.otpSixInSignUp, otparray[5]);
            click(spo.nextButtonInSignUp);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^Verify whether user signed up successfully$")
    public void Verify_whether_user_signed_up_successfully() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginUserName));
           // dbc = new DBController();
            accessToken = getAccessToken(scenarioUsername, scenarioPassword);
            if(accessToken!= null || !accessToken.isEmpty()) {
                getLogger("----Access token has been generated for Username "+scenarioUsername+" with Password "+scenarioPassword);
                scenarioUserId = getUserId(scenarioUsername, scenarioPassword);
            }
            scenarioDbUserId = dbc.insertNewCredentials(scenarioUsername, scenarioEmail, scenarioPassword, scenarioUserId);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User is able to see the payment options like creditcard, phonepay, payu etc., in payment page$")
    public void User_is_able_to_see_the_payment_page() {
        try {
            lpo.waitTillPageLoads(1);
            Assert.assertTrue(VerifyElementDisplayed(spo.creditCardInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.phonePayInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.googlePayInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.payUInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.paypalInSignUp));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,10.0);
        }catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on \"([^\"]*)\" in the payment page$")
    public void user_clicks_on_payment_option_in_the_payment_page(String option) {
        try {
            if (option.equals("CreditCard"))
                click(spo.creditCardInSignUp);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User enters \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void user_enters_card_details_in_the_field(String enteredValue, String field) {
        try {
            if (field.equals("Card Number")) {
                if (enteredValue.equalsIgnoreCase("RANDOM")) {
                    if (scenarioCardNumber != null) {
                        enteredValue = scenarioCardNumber;
                    } else {
                        enteredValue = Utilities.getRandomValues("cardNo");
                    }
                }
                enterValue(spo.ccNumberInSignUp, enteredValue);
                scenarioCardNumber = enteredValue;
            }
            else if (field.equals("Expiry")) {
                if (enteredValue.equalsIgnoreCase("RANDOM")) {
                    if (scenarioExpiryDate != null) {
                        enteredValue = scenarioExpiryDate;
                    } else {
                        enteredValue = Utilities.getRandomValues("expiry");
                    }
                }
                enterValue(spo.ccexpDateInSignUp, enteredValue);
                scenarioExpiryDate = enteredValue;
            }
            else if (field.equals("Card Holders Name")) {
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
                enterValue(spo.ccHolderNameInSignUp, enteredValue);
            }
            else if (field.equals("CVV")) {
                if (enteredValue.equalsIgnoreCase("RANDOM")) {
                    if (scenarioCvv != null) {
                        enteredValue = scenarioCvv;
                    } else {
                        enteredValue = Utilities.getRandomValues("cvv");
                    }
                }
                enterValue(spo.cvvInSignUp, enteredValue);
                scenarioCvv = enteredValue;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on pay now button$")
    public void User_clicks_on_pay_now_button() {
        try {
            click(spo.paynowInSignUp);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }
    }

    private String usernameAutoGenerated;

    @Then("^User enters \"([^\"]*)\" as \"([^\"]*)\" on screen three of Signup Page$")
    public void User_enters_input_on_screen_three_Signup_page(String inputData, String option) {
        try {
            switch (option) {
                case "Username":
                    Assert.assertTrue(VerifyElementDisplayed(spo.usernameInSignUp));
                    getLogger("----Scenario User Name in the Sign up at the beginning "+scenarioUsername+ ". Required to generate it.");
                    if (inputData.equalsIgnoreCase("RANDOM")) {
                        if (scenarioUsername != null) {
                            inputData = scenarioUsername;
                        } else {
                            inputData = Utilities.getRandomValues("username");
                        }
                    }
                    if (inputData.equalsIgnoreCase("AUTO")) {
                        if(scenarioUsername == null)
                            inputData = defaultUserName.substring(0, defaultUserName.lastIndexOf("@"));
                        else
                            inputData=scenarioUsername;
                    }
                    enterValue(spo.usernameInSignUp, inputData);
                    scenarioUsername = inputData;
                    getLogger("----Scenario User Name in the Sign up Page After Entering details: "+scenarioUsername);
                    break;
                case "Password":
                    Assert.assertTrue(VerifyElementDisplayed(spo.passwordInSignUp));
                    if (inputData.equalsIgnoreCase("RANDOM")) {
                        if (scenarioPassword != null) {
                            inputData = scenarioPassword;
                        } else {
                            inputData = Utilities.getRandomValues("password");
                        }
                    }
                    enterValue(spo.passwordInSignUp, inputData);
                    scenarioPassword=inputData;
                    getLogger("----ScenarioPassword Entered in the SignupPage "+scenarioPassword);
                    break;
                case "Confirm Password":
                    Assert.assertTrue(VerifyElementDisplayed(spo.confirmPasswordInSignUp));
                    if (inputData.equalsIgnoreCase("RANDOM")) {
                        if (scenarioPassword != null) {
                            inputData = scenarioPassword;
                        } else {
                            inputData = Utilities.getRandomValues("password");
                        }
                    }
                    enterValue(spo.confirmPasswordInSignUp, inputData);
                    scenarioPassword = inputData;
                    getLogger("---scenarioUpdatePassword during SignUp " +scenarioPassword);
                    break;
                default:
                    throw new IllegalArgumentException("Unable to identify " + option + " the field");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see the Progress bar, Next/Back buttons etc.$")
    public void User_is_able_to_Email_field_Progress_bar_Next_Back_buttons() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(spo.backButtonInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.tenantLogoInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.screenIndicatorInSignUp));
            Assert.assertTrue(VerifyElementDisplayed(spo.nextButtonInSignUp));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on the Forgot Password Link on the Login Page$")
    public void User_clicks_on_the_Forgot_Password_Link_on_the_Login_Page() {
        try {
            click(lpo.forgotPassword);
            fpo = new ForgotPasswordObjects(driver);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User is successfully redirected to \"([^\"]*)\" of Reset Password Page$")
    public void User_is_successfully_redirected_to_of_Reset_Password_Page(String screen) {
        try {
            lpo.waitTillPageLoads(1);
            fpo = new ForgotPasswordObjects(driver);
            if (screen.equalsIgnoreCase("Screen1")) {
                Assert.assertTrue(VerifyElementDisplayed(fpo.forgotPwdEmail));
                updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            } else if (screen.equalsIgnoreCase("Screen2")) {
                Assert.assertTrue(VerifyElementDisplayed(fpo.otpOneInSignUp));
                updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            }
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User enters \"([^\"]*)\" in the Reset Password Page$")
    public void User_enters_in_the_forgot_password_page(String resetEmail) {
        try {
            if (resetEmail.equalsIgnoreCase("FROM DB") ||resetEmail.equalsIgnoreCase("RANDOM") ) {
                Map<String, Object> userDetails = dbc.getUserDetails(0, "");
                scenarioDbUserId = Integer.parseInt(userDetails.get("dbUserId").toString());
                scenarioUsername = userDetails.get("username").toString();
                scenarioEmail = userDetails.get("emailId").toString();
                scenarioPassword = userDetails.get("password").toString();
                scenarioUserId = userDetails.get("userId").toString();
                resetEmail = scenarioEmail;
            }
            if (resetEmail.equalsIgnoreCase("AUTO")) {
                resetEmail=defaultUserName;
            }
            enterValue(fpo.forgotPwdEmail, resetEmail);
            scenarioEmail = resetEmail;
            getLogger("----Entered ScenarioEmail is "+resetEmail);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("User clicks on \"([^\"]*)\" button in the Reset Password Page")
    public void User_clicks_on_button_in_the_Reset_Password_Page(String button_type) {
        try {
            switch (button_type) {
                case "Submit":
                    click(fpo.submitButtonOnForgotPwd);
                    break;
                case "Verify":
                    click(fpo.verifyButtonOnForgotPwd);
                    break;
                default:
                    getLogger("----Please Click The Appropriate Button Available");
                    break;
            }
        } finally {
            takeScreenshot();
        }
    }

    @When("^User enters \"([^\"]*)\" as password in the Reset Password Page$")
    public void User_Enters_newPwd_as_Password_in_the_Reset_Password_Page(String enteredValue) {
        try {
            if (enteredValue.equalsIgnoreCase("RANDOM")) {
                enteredValue = Utilities.getRandomValues("password");
            }
            enterValue(fpo.passwordFieldInForgotPwd, enteredValue);
            scenarioUpdatedPassword = enteredValue;
            getLogger("----ScenarioUpdatedPassword Entered in Reset Password Page is "+scenarioUpdatedPassword);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is successfully redirected to Login Page$")
    public void User_is_successfully_redirected_to_Login_Page() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginUserName));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            if (scenarioDbUserId == -999) {
                Map<String, Object> userDetails = dbc.getUserDetails(0, scenarioUsername);
                scenarioDbUserId = Integer.parseInt(userDetails.get("dbUserId").toString());
                scenarioUserId = userDetails.get("userId").toString();
            }
            getLogger("----Updated Password is  "+scenarioUpdatedPassword);
            getLogger("----DB User ID is for which the values are getting updated "+scenarioDbUserId);
            dbc.updateUserPassword(scenarioDbUserId, scenarioUsername, scenarioUpdatedPassword);
//            scenarioPassword=scenarioUpdatedPassword;
//            scenarioUpdatedPassword = null;
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @When("^User enters \"([^\"]*)\" in the otp field of \"([^\"]*)\" Page$")
    public void User_enters_otp(String otp, String page) {
        try {
            lpo.waitTillPageLoads(1);
            String[] otparray = otp.split("");
            switch (page) {
                case "Signup":
                    enterValue(spo.otpOneInSignUp, otparray[0]);
                    enterValue(spo.otpTwoInSignUp, otparray[1]);
                    enterValue(spo.otpThreeInSignUp, otparray[2]);
                    enterValue(spo.otpFourInSignUp, otparray[3]);
                    enterValue(spo.otpFiveInSignUp, otparray[4]);
                    enterValue(spo.otpSixInSignUp, otparray[5]);
                    break;
                case "Reset Password":
                    enterValue(fpo.otpOneInSignUp, otparray[0]);
                    enterValue(fpo.otpTwoInSignUp, otparray[1]);
                    enterValue(fpo.otpThreeInSignUp, otparray[2]);
                    enterValue(fpo.otpFourInSignUp, otparray[3]);
                    enterValue(fpo.otpFiveInSignUp, otparray[4]);
                    enterValue(fpo.otpSixInSignUp, otparray[5]);
                    break;
                default:
                    getLogger("----Please enter valid otp / page");
                    break;
            }
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,10.0);
        } catch (Throwable throwable) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,0.0);
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^User is unable to see the video in \"([^\"]*)\" page$")
    public void User_is_unable_to_see_the_video(String pageName) {
        try {
            videoPlayer = new VideoPageObjects(driver);
            boolean status = VerifyElementDisplayed(videoPlayer.videoStream);
            if (VerifyElementDisplayed(videoPlayer.videoStream)) {
                getLogger("Video is not playing in the " + pageName + " Page");
                if (pageName.equals((VOD)))
                    updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 0.0);
                else if (pageName.equals(LiveChannel))
                    updateCOECriteria(Criteria.Is_Video_playing_from_Live_Screen_in_App, 0.0);
                else if (pageName.equals(HomePage))
                    updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 0.0);
                Assert.assertTrue(true);
            } else {
                getLogger("Video is playing in the " + pageName + " Page");
                if (pageName.equals((VOD)))
                    updateCOECriteria(Criteria.Is_Video_playing_from_VOD_Screen_in_App, 10.0);
                else if (pageName.equals(LiveChannel))
                    updateCOECriteria(Criteria.Is_Video_playing_from_Live_Screen_in_App, 10.0);
                else if (pageName.equals(HomePage))
                    updateCOECriteria(Criteria.Is_Video_playing_from_HomeScreen_in_App, 10.0);
                Assert.assertTrue(false);
            }
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User receive a toast message")
    public void userReceiveAToastMessage() throws Throwable {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.ToastMsg));
        } catch (AssertionError e) {
            throw new AssertionError(e.toString());
        } finally {
            takeScreenshot();
        }
    }

    @When("^User is able to see his \"([^\"]*)\" in Profile Page and clicks on it$")
    public void User_is_able_to_see_his_Name_in_Profile_Page(String username) {
        try {
            if(scenarioUsername != null)
                username=scenarioUsername;
            Assert.assertTrue(VerifyElementDisplayed(ppo.getElementThroughText(username)));
            click(ppo.getElementThroughText(username));
            lpo.waitTillPageLoads(3);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see Add new and clicks on it$")
    public void User_is_able_to_see_Add_new_and_clicks_on_it() {
        try {
            scrollParticularWebElement(ppo.ListOfSubProfiles,ppo.AddNewProfile);
            Assert.assertTrue(VerifyElementDisplayed(ppo.AddNewProfile));
            click(ppo.AddNewProfile);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User is on create New Avatar page$")
    public void User_is_on_create_New_Avatar_page() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.UserNameNewPofile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.FirstNameNewProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.getGenderNewProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.SchooleNewProfile));
            Assert.assertTrue(VerifyElementDisplayed(ppo.GradeeNewProfile));
        } finally {
            takeScreenshot();
        }
    }

    @And("^User enters \"([^\"]*)\" as \"([^\"]*)\" in create new avatar$")
    public void User_enters_details_as_details_in_create_new_avatar(String details, String location) {
        try {
            if(details.equalsIgnoreCase("RANDOM"))
                details = Utilities.getRandomValues("username");
            switch (location.toUpperCase()) {
                case "USERNAME":
                    enterValue(ppo.UserNameNewPofile, details);
                    break;
                case "FIRSTNAME":
                    enterValue(ppo.FirstNameNewProfile, details);
                    break;
                case "LASTNAME":
                    enterValue(ppo.LastNameNewProfile,details);
                    break;
                case "GENDER":
                    click(ppo.GenderDropDown);
                    switch(details.toUpperCase()){
                        case "MALE":
                            click(ppo.MaleInNewProfile);
                            break;
                        case "FEMALE":
                            click(ppo.FemaleInNewProfile);
                            break;
                        case "OTHERS":
                            click(ppo.OthersInNewProfile);
                            break;
                        default:
                            logger.info("Please select the gender from the list");
                            break;
                    }
                    break;
                case "AGE":
                    enterValue(ppo.AgeNewProfile,details);
                    break;
                case "SCHOOL":
                    enterValue(ppo.SchooleNewProfile,details);
                    break;
                case "GRADE":
                    enterValue(ppo.GradeeNewProfile,details);
                    break;
                case "ADDRESS":
                    enterValue(ppo.AddressNewProfile,details);
                    break;
                case "TIMEZONE":
                    scrollToElement(ppo.TimeZoneInNewProfile,true);
                    enterValue(ppo.TimeZoneInNewProfile,details);
                    break;
                default:
                    getLogger("----Please Select Valid Locator or Add Code For Selected Locator");
                    Assert.fail();
                    break;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on Save button to save his New Profile Details$")
    public void User_clicks_on_Save_button_to_save_his_New_Profile_Details() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.SaveInNewProfile));
            click(ppo.SaveInNewProfile);
            lpo.waitTillPageLoads(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @When("^User clicks on his name in Profile Page$")
    public void User_clicks_on_his_name_in_Profile_Page() {
        try {
            click(ppo.LoggedinProfileUserName);
            lpo.waitTillPageLoads(5);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is \"([^\"]*)\" to see his \"([^\"]*)\" Profile Created$")
    public void User_is_able_to_see_his_profile_created(String username, String abilityToSee) {
        try {
            boolean ableToSee = VerifyElementDisplayed(ppo.getElementThroughText(username));
            if (abilityToSee.equalsIgnoreCase("able"))
                Assert.assertTrue(ableToSee);
            else if (abilityToSee.equalsIgnoreCase("unable")) {
                if (ableToSee)
                    Assert.fail();
                else
                    Assert.assertTrue(true);
            }
        } finally {
            takeScreenshot();
        }
    }

//    @When("^User clicks on delete icon in user Profiles$")
//    public void User_clicks_on_delete_icon_in_user_Profiles() {
//        try {
//            Assert.assertTrue(VerifyElementDisplayed(ppo.DeleteProfile));
//            click(ppo.DeleteProfile);
//            lpo.waitTillPageLoads(5);
//        } finally {
//            takeScreenshot();
//        }
//    }

    @When("^User wants to identify the WKAR and MobiusTv logo$")
    public void userWantsToIdentifyTheWKARAndMobiusTvLogo() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.WkarLogo));
            Assert.assertTrue(VerifyElementDisplayed(lpo.mobiusLogo));
        } finally {
            takeScreenshot();
        }
    }

    @And("^User able to click on Facebook button$")
    public void userAbleToClickOnFacebookButton() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.facebook));
            click(lpo.facebook);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User able to click on Google button$")
    public void userAbleToClickOnGoogleButton() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.google));
            click(lpo.google);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^Clicks on back button in signup page$")
    public void clicksOnBackButtonInSignupPage() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(spo.backButtonInSignUp));
            click(spo.backButtonInSignUp);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User is able to see WalkThrough feature of the App$")
    public void User_is_able_to_see_WalkThrough_feature_of_the_App() {
        try {
            takeScreenshot();
            lpo.waitTillPageLoads(5);
            Assert.assertTrue(VerifyElementDisplayed(hpo.nextInWalkThrough));
            Assert.assertTrue(VerifyElementDisplayed(hpo.skipInWalkThrough));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^Clicks on login button in forgot password page$")
    public void clicksOnLoginButtonInForgotPasswordPage() {
        try {
            // Assert.assertTrue(VerifyElementDisplayed(fpo.loginPageLinkOnForgotPwd));
            click(fpo.loginPageLinkOnForgotPwd);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^user checks the user interface in \"([^\"]*)\"$")
    public void userChecksTheUserInterfaceIn(String signup) throws Throwable {
        try {
            switch (signup) {
                case "logos":
                    Assert.assertTrue(VerifyElementDisplayed(spo.WkarLogo));
                    Assert.assertTrue(VerifyElementDisplayed(spo.mobiusLogo));
                    break;
                case "emailTextfield":
                    Assert.assertTrue(VerifyElementDisplayed(spo.emailInSignup));
                    break;
                case "backButton":
                    Assert.assertTrue(VerifyElementDisplayed(spo.backButtonInSignUp));
                    click(spo.backButtonInSignUp);
                    break;
                case "next":
                    Assert.assertTrue(VerifyElementDisplayed(spo.nextButtonInSignUp));
                    click(spo.nextButtonInSignUp);
                    break;
                case "login":
                    Assert.assertTrue(VerifyElementDisplayed(spo.loginLinkInSignup));
                    click(spo.loginLinkInSignup);
                    break;
            }
        } finally {
            takeScreenshot();
        }
    }

    @Then("User clicks on \"([^\"]*)\" button in the WalkThrough")
    public void user_clicks_on_button_in_the_WalkThrough(String buttonType) {
        try {
            switch (buttonType.toLowerCase()) {
                case "skip":
                    click(hpo.skipInWalkThrough);
                    break;
                case "next":
                    click(hpo.nextInWalkThrough);
                    break;
                default:
                    throw new AssertionError("----Please Click on either SKIP or Next");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see all the Logos, Textboxes,Buttons etc\\.\\.$")
    public void userIsAbleToSeeAllTheLogosTextboxesButtonsEtc() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(lpo.WkarLogo));
            Assert.assertTrue(VerifyElementDisplayed(lpo.mobiusLogo));
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginUserName));
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginPassword));
            Assert.assertTrue(VerifyElementDisplayed(lpo.LoginButton));
            Assert.assertTrue(VerifyElementDisplayed(lpo.signUp));
            Assert.assertTrue(VerifyElementDisplayed(lpo.forgotPassword));
            Assert.assertTrue(VerifyElementDisplayed(lpo.facebook));
            Assert.assertTrue(VerifyElementDisplayed(lpo.google));
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on Write A Review$")
    public void User_clicks_on_Write_A_Review() {
        try {
            click(vpo.writeReview);
            lpo.waitTillPageLoads(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User taps on star rating$")
    public void user_Taps_on_star_ratings() {
        try {
            click(vpo.rating);
            lpo.waitTillPageLoads(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User writes his review as \"([^\"]*)\" on User Review Page$")
    public void User_enters_his_review(String review) {
        try {
            Assert.assertTrue(VerifyElementDisplayed(vpo.writeDescription));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            enterValue(vpo.writeDescription, review + getTime("ddMM"));// ddMMHHmm
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see entered \"([^\"]*)\" Review$")
    public void User_is_able_to_see_entered_Review(String userReview) {
        try {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
            scrollToElement(vpo.writtenReview(userReview + getTime("ddMM")), false);
        } catch (Exception e) {
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 0.0);
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on submit button on User Review Page$")
    public void User_clicks_on_submit_button() {
        try {
            click(vpo.submitReview);
            lpo.waitTillPageLoads(2);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User clicks on settings$")
    public void userClicksOnSettings() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.Settings));
            click(ppo.Settings);
            lpo.waitTillPageLoads(2);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is in settings page$")
    public void userIsInSettingsPage() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.SettingsTitle));
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User able to identify the \"([^\"]*)\" in settings page$")
    public void userAbleToIdentifyTheInSettingsPage(String selection) throws Throwable {
        try {
            switch (selection) {
                case "Notifications":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    break;
                case "StreamingQuality":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    break;
                case "Quality":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    click(ppo.getSettingsPage(selection));
                    break;
                case "Language":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    break;
                case "LanguageList":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    break;
                case "Remainder":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.getSettingsPage(selection)));
                    break;
            }
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on save button$")
    public void userClicksOnSaveButton() {
        try {
            Assert.assertTrue(VerifyElementDisplayed(ppo.SettingsSave));
            click(ppo.SettingsSave);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User able to enable and disable the \"([^\"]*)\" toggle button$")
    public void userAbleToEnableAndDisableTheToggleButton(String toggles) throws Throwable {
        try {
            switch (toggles) {
                case "notifications":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.NotificationsToggle));
                    click(ppo.NotificationsToggle);
                case "Email":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.EmailToggle));
                    click(ppo.EmailToggle);
                    break;
                case "SMS":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.SmsToggle));
                    click(ppo.SmsToggle);
                    break;
            }
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User clicks on \"([^\"]*)\" streaming quality$")
    public void userClicksOnStreamingQuality(String quality) throws Throwable {
        try {
            switch (quality) {
                case "Auto":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.QualityAuto));
                    click(ppo.QualityAuto);
                    break;
                case "Medium":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.QualityMedium));
                    click(ppo.QualityMedium);
                    break;
                case "High":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.QualityHigh));
                    click(ppo.QualityHigh);
                    break;
                case "Low":
                    Assert.assertTrue(VerifyElementDisplayed(ppo.QualityLow));
                    click(ppo.QualityLow);
                    break;
            }
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User opens \"([^\"]*)\" video in the homepage$")
    public void user_clicks_on(String nameOfTheVideo) {
        try {
            lpo.waitTillPageLoads(3);
            scrollToElement(hpo.getVideoLocation(nameOfTheVideo), true);
            click(hpo.getVideoLocation(nameOfTheVideo));
            staticWait(5);
            videoPlayer = new VideoPageObjects(driver);
            if (!VerifyElementDisplayed(videoPlayer.videoStream))
                getLogger(nameOfTheVideo + " Video is playing in the video player");
            else {
                getLogger(nameOfTheVideo + " Video is not playing in the video player");
                noOfVideosPlaying++;
                getLogger("Total No of videos that are not playing in the HomePage as of now are "+noOfVideosPlaying);
            }
        } finally {
            takeScreenshot();
        }
    }

    @When("^User selects \"([^\"]*)\" and gets redirected to respective Screen$")
    public void User_selects_ba_and_gets_redirected_to_respective_ba_screen(String typeOfBASelected){
        try {
            click(npo.getBAListItems(typeOfBASelected));
            lpo.waitTillPageLoads(2);
            switch (typeOfBASelected.toUpperCase()) {
                case "MB":
                    Assert.assertTrue(VerifyElementDisplayed(npo.MessageBoard));
                    break;
                case "EMOTICONS":
                    Assert.assertTrue(VerifyElementDisplayed(npo.Emoticons));
                    break;
                case "Q&A":
                    Assert.assertTrue(VerifyElementDisplayed(npo.QandA));
                    break;
                case "RESPONDER":
                    Assert.assertTrue(VerifyElementDisplayed(npo.Responder));
                    break;
                default:
                    throw new AssertionError("Enter Valid BA");
            }
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App, 10.0);
        } catch (Exception e){
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,0.0);
        } finally {
            takeScreenshot();
        }
    }

    @Then("^User is able to see the BA List Page$")
    public void User_is_able_to_see_the_list_of_BA_List_Page(){
        try{
            Assert.assertTrue(VerifyElementDisplayed(npo.PageTitle));
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,10.0);
        } catch (Exception e){
            updateCOECriteria(Criteria.Is_Navigation_happenning_properly_in_App,0.0);
        } finally {
            takeScreenshot();
        }
    }

    @And("^User clicks on more arrow in Trending Apps$")
    public void User_clicks_on_more_arrow_in_Trending_Apps(){
        try{
            click(npo.More);
            lpo.waitTillPageLoads(1);
        } finally {
            takeScreenshot();
        }
    }
    @And("^User launches the BroadCasting APP$")
    public void User_launches_the_BroadCasting_APP(){
        try{
            click(npo.launchBA);
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User is navigated back by clicking back button$")
    public void User_is_navigated_back_by_clicking_back_button(){
        try{
            click(npo.Back);
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User taps on \"([^\"]*)\" in VOD Page$")
    public void User_taps_on_required_section(String enteredValue){
        try {
            switch (enteredValue.toUpperCase()){
                case "EPISODES":
                    scrollToElement(vpo.episodes, true);
                    click(vpo.episodes);
                    break;
                case "ABOUT":
                    scrollToElement(vpo.AboutInVOD, false);
                    click(vpo.AboutInVOD);
                    break;
                case "TEXTOFTHESEASONS":
                    scrollToElement(vpo.TextBoxSeason, true);
                    break;
                case "NGS":
                    scrollToElement(vpo.ButtonNGS, true);
                    click(vpo.ButtonNGS);
                    break;
                default:
                    throw new AssertionError("In valid section selected in vod page.");
            }
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User is able to see the seasons DropDown and click on it$")
    public void User_is_able_to_see_the_seasons_DropDown_and_click_on_it(){
        try{
            scrollToElement(vpo.dropDownOnVODSeason,true);
            click(vpo.dropDownOnVODSeason);
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User selects Season name as \"([^\"]*)\"$")
    public void User_selects_season_name(String text){
        try{
            scrollToElement(vpo.seasonsDropDown(text),true);
            click(vpo.seasonsDropDown(text));
        } finally {
            takeScreenshot();
        }
    }
    @Then("User is able to see \"([^\"]*)\" video thumbnail")
    public void User_selects_video_name(String nameOfTheVideo){
        try{
            scrollToElement(vpo.getNameOfTheVideo(nameOfTheVideo),true);
        } finally {
            takeScreenshot();
        }
    }
    @And("^User is able to swipe the \"([^\"]*)\" section$")
    public void User_is_able_to_swipe(String corouselToSwipe){
        try{
           List<String> textOfThumbnails = new ArrayList<>();
           if(corouselToSwipe.equalsIgnoreCase("TOPRATED")) {
               generalSwipeOfParticularElement(vpo.corouselViews(corouselToSwipe));
               Assert.assertTrue(true);
           } else if(corouselToSwipe.equalsIgnoreCase("CONTINUETOWATCH")) {
                generalSwipeOfParticularElement(vpo.corouselViews(corouselToSwipe));
                Assert.assertTrue(true);
            }
           else {
               textOfThumbnails.add(vpo.ListOfTitlesOnThumbnails(corouselToSwipe).getText());
               if (!checkIfStringIsEmpty(textOfThumbnails.get(0)) && textOfThumbnails.get(0) != null) {
                   getLogger("The First Video In the " + corouselToSwipe + " is " + textOfThumbnails.get(0));
                   generalSwipeOfParticularElement(vpo.corouselViews(corouselToSwipe));
               }
               textOfThumbnails.add(vpo.ListOfTitlesOnThumbnails(corouselToSwipe).getText());
               getLogger("The First Video In the " + corouselToSwipe + " after swipe is "
                       + textOfThumbnails.get(1));
               if (!textOfThumbnails.get(0).equals(textOfThumbnails.get(1)))
                   Assert.assertTrue(true);
               else
                   Assert.fail();
           }
        } finally {
            takeScreenshot();
        }
    }
    @When("User scrolls \"([^\"]*)\" the page for \"([^\"]*)\" times")
    public void User_scrolls_the_page(String typeOfScroll,int noOfScrolls){
        try{
            boolean scrollType =typeOfScroll.equalsIgnoreCase("Down")? true : false;
            generalScroll(noOfScrolls,scrollType);
        } catch (Exception e){
            getLogger("Unable to perform scroll operation");
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }
    @And("^User gives rating of \"([^\"]*)\" to the episode$")
    public void user_gives_ratings_through_swipe(int swipePoints){
        try{
            switch (swipePoints){
                case 5:
                    swipePoints=100;
                    break;
                case 4:
                    swipePoints=90;
                    break;
                case 3:
                    swipePoints=70;
                    break;
                case 2:
                    swipePoints=50;
                    break;
                case 1:
                    swipePoints=30;
                    break;
                default:
                    throw new Exception("Please Enter Valid Points");
            }
            generalSwipeOfWebElementUptoRequiredPercentage(vpo.rating,swipePoints);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            takeScreenshot();
        }
    }
    @And("^User is Not on create New Avatar page$")
    public void user_is_not_on_create_new_avatar_page(){
        try{
            Assert.assertFalse(VerifyElementDisplayed(ppo.UserNameNewPofile));
        } catch (AssertionError e){
            getLogger("User has been redirected to create new avatar page");
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }
    @And("^User tries to delete all the created sub-profiles$")
    public void User_tries_to_delete_all_the_created_sub_profiles(){
        try{
            int totalNoOfDeleteIcons = ppo.getListOfDeleteIcons().size();
            for(int i=1 ; i <= totalNoOfDeleteIcons ; i++){
                takeScreenshot();
                click(ppo.DeleteProfile);
                lpo.waitTillPageLoads(10);
                click(ppo.LoggedinProfileUserName);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User is unable to see any created sub profiles$")
    public void user_is_unable_to_see_the_delete_icons(){
        try{
            Assert.assertFalse(VerifyElementDisplayed(ppo.DeleteProfile));
        } catch(AssertionError e){
            getLogger("User is able to see the delete icon");
        } finally {
            takeScreenshot();
        }
    }
    @When("^User marks the video as Bookmark$")
    public void user_marks_the_video_as_Bookmark(){
        try{
            click(vpo.bookmark);
            lpo.waitTillPageLoads(10);
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User clicks on \"([^\"]*)\" in profile page$")
    public void listOfItems(String clickOnItem){
        try {
            switch (clickOnItem.toUpperCase()) {
                case "MYLIST":
                    click(ppo.MyListBox);
                    break;
                default:
                    throw new Exception("Please click on valid element in profile page");
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            takeScreenshot();
        }

    }
    @Then("^User clicks on a video in \"([^\"]*)\" in VOD Page$")
    public void User_clicks_on_a_video_in_VOD_Page(String videoSection){
        try {
            List<AndroidElement> textOfThumbnails = new ArrayList<>();
            switch(videoSection.toUpperCase()) {
                case "TOPRATED":
                case "CONTINUETOWATCH":
                    break;
                case "RELEASESIN2019":
                    textOfThumbnails.add(vpo.ListOfTitlesOnThumbnails(videoSection));
                    if (!checkIfStringIsEmpty(textOfThumbnails.get(0).getText()) && textOfThumbnails.get(0).getText() != null) {
                        bookmarkVideo = textOfThumbnails.get(0).getText();
                        getLogger("The First Video In the " + videoSection + " is " + bookmarkVideo);
                        click(textOfThumbnails.get(0));
                    } else {
                        Assert.fail("Something went wrong at the click operation");
                    }
                    break;
                default:
                    throw new Exception("Please select appropriate carousel...!");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            takeScreenshot();
        }
    }
    @Then("^User is able to see the video added in the bookmark$")
    public void User_is_able_to_see_the_video_added_in_the_bookmark(){
        try{
            Assert.assertTrue(VerifyElementDisplayed(ppo.getElementThroughText(bookmarkVideo)));
        } finally {
            takeScreenshot();
        }
    }
    @Then("^User selects his profile after tower screen$")
    public void selectProfileSectionAfterTowerScreen(){
        try{
            staticWait(3);
            click(hpo.getSelectProfileInHomeScreen(scenarioUsername));
        } finally {
            takeScreenshot();
        }
    }

}

