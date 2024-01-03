package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class EditProfileDetailsObjects extends BaseLibrary {

	public EditProfileDetailsObjects(AndroidDriver<AndroidElement> driver) {
		BaseLibrary.driver = driver;
	}

	public By userNameTxt			=	By.xpath("//android.widget.TextView[@text='User Name']");
	public By FirstNameTxt			=	By.xpath("//android.widget.TextView[@text='First Name']");
	public By LastNameTxt			=	By.xpath("//android.widget.TextView[@text='Last Name']");
	public By EmailTxt				=	By.xpath("//android.widget.TextView[@text='Email']");
	public By AgeTxt			=	By.xpath("//android.widget.TextView[@text='Age']");
	public By usernameInput 		=	By.id("etUserName");
	public By firstNameInput		=	By.id("etFirstName");
	public By lastNameInput         =   By.id("etLastName");
	public By emailInput			=	By.id("etEmail");
	public By ageInput          	=	By.id("etAge");

	public By saveBtn				=	By.id("btnSave");

}




