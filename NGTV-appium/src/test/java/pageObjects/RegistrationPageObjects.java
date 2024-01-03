package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPageObjects extends BaseLibrary {
    public RegistrationPageObjects(AndroidDriver<AndroidElement> driver) {
        BaseLibrary.driver = driver;
    }

    public By emailReg  = By.id("etEmailSignup");
    public By checkTxt  = By.id("tv_please_check");
    public By nextBtn   = By.id("btnNext");
    public By backBtn   = By.id("btnBack");
    public By loginBtn  = By.id("btnLogin");
    public By stepIndicator= By.id("stepped_indicator");
    public By otpOne        =   By.id("et_otp_one");
    public By otpTwo        =   By.id("et_otp_two");
    public By otpThree      =   By.id("et_otp_three");
    public By otpFour       =   By.id("et_otp_four");
    public By otpFive       =   By.id("et_otp_five");
    public By otpSix        =   By.id("et_otp_six");
    public By resendOtp     =   By.id("tv_Resend");
    public By signUp        =   By.id("tvSignUp");
    public By back          =   By.id("backBT");









}
