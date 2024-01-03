package pageObjects;

import com.cucumber.listener.Reporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import baseLibrary.BaseLibrary;

import java.util.List;

public class LoginPageObjects extends BasePageObjects {

	public LoginPageObjects(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public By LoginUserName = By.id("etEmail");
	public By LoginPassword = By.id("etPassword");
	public By LoginButton = By.id("btnLogin");
	public By Signup = By.id("btnSignup");
	public By ForgotPassword = By.id("btnForgotPass");
	public By LoginGoogle = By.id("llGoogle");
	public By LoginTxt = By.xpath("//android.widget.TextView[@text='Login to your account']");
	public By LoginWith = By.xpath("//android.widget.TextView[@text='or Login with']");
	public By QrCodeText = By.id("//android.widget.ImageView[@resource-id='com.gaian.ngtv:id/qrImage']/preceding-sibling::android.widget.TextView");
	public By NoiseView = By.id("tvNoiseView");
	public By QrCodeImage = By.id("qrImage");
	public By LoginToastMsg = By.id("llGoogle");
	public By ImgInput = By.xpath("//android.widget.ImageView");

	public AndroidElement getImg(String imageSelector) {
		List<AndroidElement> imgList = driver.findElements(ImgInput);
		switch (imageSelector){
			case "WKARng":
				return imgList.get(0);
			case "MobiusTv":
				return imgList.get(1);
			case "Google+":
				return imgList.get(2);
			case "QrCode":
				return imgList.get(3);
			default:
				throw new IllegalArgumentException("Image of type " +imageSelector+" not present, please check");

		}

	}
}
