package pageObjects;

import baseLibrary.BaseLibrary;
import baseLibrary.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import java.util.List;

public class BasePageObjects extends BaseLibrary {

	private By PageLoader = By.id("llLoader");
	private By AtscLoader = By.id("img_atsc");
	public BasePageObjects(AndroidDriver<AndroidElement> driver) {
		BaseLibrary.driver = driver;
	}

	public void waitTillPageLoads(int timeoutInSeconds) {

		if (timeoutInSeconds <= 0) {
			timeoutInSeconds = 10;
		}

		int timeout = timeoutInSeconds;
		List<AndroidElement> pageLoader = driver.findElements(PageLoader);
		
		if (pageLoader.size() > 0) {
			while (pageLoader.size() > 0 && timeout-- > 0) {
				System.out.println("pageLoader still being displayed");
				Utilities.staticWait(1);
				pageLoader = driver.findElements(PageLoader);
			}
		}

		timeout = timeoutInSeconds;
		List<AndroidElement> atscLoader = driver.findElements(AtscLoader);
		if (atscLoader.size() > 0) {
			while (atscLoader.size() > 0 && timeout-- > 0) {
				System.out.println("ATSC Loader still being displayed");
				Utilities.staticWait(1);
				atscLoader = driver.findElements(PageLoader);
			}
		}
		System.out.println("Loader disappeared");
	}

}