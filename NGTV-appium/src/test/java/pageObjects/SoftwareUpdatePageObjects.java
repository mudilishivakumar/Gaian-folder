package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import baseLibrary.BaseLibrary;

public class SoftwareUpdatePageObjects extends BaseLibrary {

    public SoftwareUpdatePageObjects(AndroidDriver<AndroidElement> driver) {
        BaseLibrary.driver = driver;
    }

    public By apolloLogo            = By.id("ivApollo");
    public By tvInfo                = By.id("tvInfo");
    public By tvInfoText            = By.id("tvInfodesc");
    public By tvAppName             = By.id("tvAppName");
    public By tvVersion             = By.id("tvAppVersion");
    public By tvRelease             = By.id("tvAppReleaseDate");
    public By tvReleaseInfo         = By.id("tvAppReleaseInfo");
    public By tvAppSize             = By.id("tvAppSize");
    public By btnDownload           = By.id("btnDownload");
    public By gaianLink             = By.id("tvLink");
    public By learnMore             = By.id("tvLearn");
}