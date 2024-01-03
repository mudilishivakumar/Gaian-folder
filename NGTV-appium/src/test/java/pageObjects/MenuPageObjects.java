package pageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import baseLibrary.BaseLibrary;

    public class MenuPageObjects extends BasePageObjects {
        public MenuPageObjects(AndroidDriver<AndroidElement> driver){
            super(driver);
        }

        public By profileImg    =   By.id("recycler_view");
        public By mobiusLogo    =   By.id("ivApollo");
        public By title         =   By.id("tvIdTitle");
        public By search        =   By.id("imgSearch");
        public By nextGenIcon   =   By.id("imgNg");
        public By liveTvIcon    =   By.id("imgLive");
        public By vodIcon       =   By.id("imgvod");
        public By accountIcon   =   By.id("imgProfile");

}
