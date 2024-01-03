package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.InputMismatchException;
import java.util.List;

public class ForgotPasswordObjects extends BaseLibrary {
    public ForgotPasswordObjects(AndroidDriver<AndroidElement> driver) {
        BaseLibrary.driver = driver;
    }

    public By emailID           = By.id("etEmail");
    public By submitBtn         = By.id("tvResetPwd");
    public By loginBtn          = By.id("btnLogin");
    public By OtpInput          = By.id("ll_otp");
    public By NewPassword       = By.id("etPassword");
    public By ResendOtpButton   = By.id("btn_Resend");
    public By VerifyOtpButton   = By.id("btnVerify");
    public By ForgotToastMsg    =By.id("snackbar_text");

    public void enterOTP(String otpValue){
        List<MobileElement> otpInputs = driver.findElement(OtpInput).findElements(By.xpath("//android.widget.EditText"));
        if(otpInputs.size() != otpValue.length()){
            throw new InputMismatchException("Expected OTP of length " + otpInputs.size() + ", got OTP of length " + otpValue.length());
        }
        for (int i = 0; i < otpValue.length(); i++) {
            otpInputs.get(i).sendKeys(String.valueOf(otpValue.toCharArray()[i]));
        }
    }

}
