package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class ForgotPasswordObjects extends BaseLibrary {
    public ForgotPasswordObjects(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public By forgotPwdEmail = By.id("etEmail");
    public By submitButtonOnForgotPwd = By.id("tvResetPwd");
    public By ForgotPasswordToastMsg = By.id("snackbar_text");
    public By loginPageLinkOnForgotPwd = By.id("tvSignup");
    public By otpOneInSignUp = By.id("et_otp_one");
    public By otpTwoInSignUp = By.id("et_otp_two");
    public By otpThreeInSignUp = By.id("et_otp_three");
    public By otpFourInSignUp = By.id("et_otp_four");
    public By otpFiveInSignUp = By.id("et_otp_five");
    public By otpSixInSignUp = By.id("et_otp_six");
    public By passwordFieldInForgotPwd = By.id("etPassword");
    public By verifyButtonOnForgotPwd = By.id("tvVerify");

}
