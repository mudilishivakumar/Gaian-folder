package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

public class ProfilePageObjects extends BaseLibrary {

	public ProfilePageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public By DevicesinProfile				= By.xpath("//android.widget.TextView[@text='Devices']");
	public By LoggedinProfileUserName		= By.id("ivProfileName");
	public By VersioninProfile				= By.id("tvVersionName");
	public By SettingsinProfile				= By.id("cvSettings");
	public By DownloadsInProfile 			= By.xpath("//android.widget.TextView[@text='Downloads']");
	public By AboutInProfile				= By.xpath("//android.widget.TextView[@text='About']");
	public By EditProfileName				= By.id("cvProfile");
	public By FirstNameNewProfile 			= By.id("etFirstName");
	public By LastNameNewProfile 			= By.id("etLastName");
	public By MobileNumber 					= By.id("etMobile");
	public By AgeNewProfile 				= By.id("etAge");
	public By AddressNewProfile 			= By.id("etAddress");
	public By BackButtonFromProfile			= By.id("ivBack");
	public By SaveButtonInProfile			= By.id("btnSave");
	public By NewUserNameInProfile			= By.xpath("//android.widget.TextView[@text='ksaketh']");
	public By AddNewProfile 				= By.id("ivAdd");
	public By UserNameNewPofile 			= By.id("etUserName");
	public By Logout 						= By.id("ivLogout");
	public By getGenderNewProfile			= By.id("spGender");
	public By MaleInNewProfile				= By.xpath("//android.widget.TextView[@text='Male']");
	public By FemaleInNewProfile			= By.xpath("//android.widget.TextView[@text='Female']");
	public By OthersInNewProfile			= By.xpath("//android.widget.TextView[@text='Others']");
	public By SaveInNewProfile 				= By.id("btnSave");
	public By TimeZoneInNewProfile			= By.id("etTimeZone");
	public By DeleteProfile 				= By.id("ivDelete");
	public By EditProfile 					= By.id("ivEdit");
	public By SchooleNewProfile 			= By.id("etSchool");
	public By GradeeNewProfile 				= By.id("etGrades");
	public By ListOfSubProfiles				= By.xpath("//android.widget.ListView");
	public By Settings        				= By.id("cvSettings");
	public By SettingsTitle   				= By.id("tvTitle");
	public By NotificationsToggle 			= By.id("sNotification");
	public By EmailToggle     				= By.id("swEmail");
	public By SmsToggle       				= By.id("swSms");
	public By SettingsSave    				= By.id("btnSave");
	public By QualityHigh     				= By.xpath("//android.widget.TextView[1]");
	public By QualityLow      				= By.xpath("//android.widget.TextView[3]");
	public By QualityMedium    				= By.xpath("//android.widget.TextView[2]");
	public By QualityAuto    				= By.xpath("//android.widget.TextView[4]");
	public By SettingsPage 					= By.xpath("//android.widget.TextView");
	public By GenderDropDown				= By.id("spGender");
	public By MyListBox						= By.id("cvMyList");
	public By getElementThroughText(String profileUser){
		return By.xpath("//android.widget.TextView[@text='"+profileUser+"']");
	}

	public AndroidElement getSettingsPage(String SettingElements) {
		List<AndroidElement> settingsList = driver.findElements(SettingsPage);
		switch (SettingElements) {
			case "Settings":
				return settingsList.get(0);
			case "Notifications":
				return settingsList.get(1);
			case "StreamingQuality":
				return settingsList.get(2);
			case "Quality":
				return settingsList.get(3);
			case "Language":
				return settingsList.get(4);
			case "LanguageList":
				return settingsList.get(5);
			case "Remainder":
				return settingsList.get(6);
			case "Email":
				return settingsList.get(7);
			case "SMS":
				return settingsList.get(8);
			default:
				throw new IllegalArgumentException("Channel of type " + SettingElements + " not present, please check");
		}
	}

	public List<AndroidElement> getListOfDeleteIcons(){
		List<AndroidElement> deleteIconsList = driver.findElements(DeleteProfile);
		return deleteIconsList;
	}
}




