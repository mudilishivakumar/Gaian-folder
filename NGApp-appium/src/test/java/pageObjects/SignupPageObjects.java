package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class SignupPageObjects extends BaseLibrary {
    public SignupPageObjects(AndroidDriver<AndroidElement> driver) { this.driver = driver; }
    public By mobiusLogo  = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ImageView");
    public By WkarLogo    = By.id("ll_top");
    public By emailInSignup = By.id("etEmailSignup");
    public By SignupToastMsg = By.id("snackbar_text");
    public By loginLinkInSignup = By.id("tvLogin");
    public By nextButtonInSignUp = By.id("tvSignUp");
    public By backButtonInSignUp = By.id("backBT");
    public By tenantLogoInSignUp = By.id("tenant_logo");
    public By screenIndicatorInSignUp = By.id("stepper_indicator");
    public By otpOneInSignUp = By.id("et_otp_one");
    public By otpTwoInSignUp = By.id("et_otp_two");
    public By otpThreeInSignUp = By.id("et_otp_three");
    public By otpFourInSignUp = By.id("et_otp_four");
    public By otpFiveInSignUp = By.id("et_otp_five");
    public By otpSixInSignUp = By.id("et_otp_six");
    public By profileImageInSignUp = By.id("profile_image");
    public By usernameInSignUp = By.id("etSUserName");
    public By passwordInSignUp = By.id("etSPassword");
    public By confirmPasswordInSignUp = By.id("etConfrmPassword");
    public By appLogoSignUp = By.id("ivLogo");
    public By creditCardInSignUp = By.id("btnCredit");
    public By paypalInSignUp = By.id("btnPayPal");
    public By payUInSignUp = By.id("btnPayu");
    public By googlePayInSignUp = By.id("btnGooglePay");
    public By phonePayInSignUp = By.id("btnPhonepe");
    public By ccNumberInSignUp = By.id("etCardNumber");
    public By ccHolderNameInSignUp = By.id("etcardHolderName");
    public By ccexpDateInSignUp = By.id("etDate");
    public By cvvInSignUp = By.id("etCvv");
    public By paynowInSignUp = By.id("btnPay");










}
