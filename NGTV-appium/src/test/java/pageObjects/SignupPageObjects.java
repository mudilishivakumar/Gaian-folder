package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;

public class SignupPageObjects extends BaseLibrary {

	public SignupPageObjects(AndroidDriver<AndroidElement> driver) {
		BaseLibrary.driver = driver;
	}
	// Enter Email Page
	public By PageIndicator 				= By.id("stepped_indicator");
	public By EmailInput                    = By.id("etEmailSignup");
	public By BackBtn                       = By.id("btnBack");
	public By NextBtn                       = By.id("btnNext");
	public By LoginBtn                      = By.id("btnLogin");
    public By SignupToastMsg				=By.id("snackbar_text");


	// Verify OTP page
	public By OtpInput                      = By.id("ll_otp");
	public By ResendOTPBtn                  = By.id("tv_Resend");
	public By OTPBackBtn                    = By.id("backBT");
	public By OTPNextBtn                    = By.id("tvSignUp");

	// User Details Page
	public By UsernameInput                 = By.id("etSUserName");
	public By PasswordInput                 = By.id("etSPassword");
	public By ConfirmPasswordInput          = By.id("etConfrmPassword");
	public By DetailsBackButton             = By.id("backBT");
	public By DetailsSignUpButton           = By.id("tvSignUp");

	// Payment Details Page
	public By CreditCardBtn                 = By.id("btnCredit");
	public By PayPalBtn                     = By.id("btnPayPal");
	public By PayUBtn                       = By.id("btnPayu");
	public By GooglePayBtn                  = By.id("btnGooglePay");
	public By PhonePeBtn                    = By.id("btnPhonepe");
	public By CardNumberInput               = By.id("etCardNumber");
	public By CardHolderNameInput           = By.id("etcardHolderName");
	public By CardExpiryDateInput           = By.id("etDate");
	public By CardCVVInput                  = By.id("etCvv");
	public By PayNowBtn                     = By.id("btnPay");
	public By ImgInput						 = By.xpath("//android.widget.ImageView");

	public void enterOTP(String otpValue){
		List<MobileElement> otpInputs = driver.findElement(OtpInput).findElements(By.xpath("//android.widget.EditText"));
		if(otpInputs.size() != otpValue.length()){
			throw new InputMismatchException("Expected OTP of length " + otpInputs.size() + ", got OTP of length " + otpValue.length());
		}
		for (int i = 0; i < otpValue.length(); i++) {
			otpInputs.get(i).sendKeys(String.valueOf(otpValue.toCharArray()[i]));
		}

	}
	public AndroidElement getImg(String imageSelector) {
		List<AndroidElement> imgList = driver.findElements(ImgInput);
		switch (imageSelector) {
			case "WKARng":
				return imgList.get(0);
			case "MobiusTv":
				return imgList.get(1);
			case "Google+":
				return imgList.get(2);
			case "QrCode":
				return imgList.get(3);
			default:
				throw new IllegalArgumentException("Image of type " + imageSelector + " not present, please check");

		}
	}
}
