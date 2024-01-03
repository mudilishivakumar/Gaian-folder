package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseLibrary.BaseLibrary;

public class LoginObjects extends BaseLibrary {
	public LoginObjects(AndroidDriver<AndroidElement> driver) {
		BaseLibrary.driver = driver;
	}

	public By LoginUserName = By.id("etEmail");
	public By LoginPassword = By.id("etPasswordLogin");
	public By LoginButton = By.id("tvLogin");
	public By ForgotPassword = By.id("tvForgotPwd");
	public By FacebookButton = By.id("llFacebook");
	public By GoogleButton = By.id("llGoogle");
	public By SignupHere = By.id("tvSignUp");

}