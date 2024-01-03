package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import baseLibrary.BaseLibrary;

public class ProfilePageObjects extends BasePageObjects {

	public ProfilePageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public By settingsTxt			=	By.xpath("//android.widget.TextView[@text='Settings']");
	public By locationTxt			=	By.xpath("//android.widget.TextView[@text='Location']");
	public By profileTxt			=	By.xpath("//android.widget.TextView[@text='Profile']");
	public By listTxt				=	By.xpath("//android.widget.TextView[@text='My List']");
	public By downloadTxt			=	By.xpath("//android.widget.TextView[@text='Download']");
	public By pairedDevicesTxt		=	By.xpath("//android.widget.TextView[@text='Paired Mobile Devices']");
	public By languageTxt			=	By.xpath("//android.widget.TextView[@text='Language']");
	public By notificationTxt		=	By.id("tvNotifications");
	public By emailId				=	By.id("tvEmail");
	public By signOutFrame          =   By.id("cvLogout");
	public By signOut				=	By.id("tvSignOut");
	public By verifyLogoutDialog	=	By.id("llDialog");
	public By verifyLogoutMessage	=	By.id("tvMessage");
	public By cancelBtn				=	By.id("btnCancel");
	public By continueBtn			=	By.id("btnContinue");
	public By apolloLogo			=	By.id("//android.widget.ImageView");
	public By profileDetailsButton  =   By.id("cvProfile");
	public By ToastMsg				=	By.id("snackbar_text");
	public By pairedMB				=By.id("tvPairedDevice");
}




