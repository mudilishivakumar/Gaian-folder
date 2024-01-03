package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.jsoup.Connection;
import org.openqa.selenium.By;

public class VideoPageObjects extends BaseLibrary {
    public VideoPageObjects(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }
    public By videoBackBtn					= By.id("ivBack");
    public By playpauseBtn					= By.id("ivToggle");
    public By forward 						= By.id("btnForward");
    public By backward						= By.id("btnRewind");
    public By wkarIcon						= By.xpath("//android.widget.Image");
    public By videoStream                   = By.id("com.gaian.ngapp:id/exo_shutter");
    public By tapForOptions                  = By.id("oo_surface_view");
    public By playerCard                    = By.id("cvPlayerCard");
    public By actionBarRoot                 = By.id(("action_bar_root"));
}
