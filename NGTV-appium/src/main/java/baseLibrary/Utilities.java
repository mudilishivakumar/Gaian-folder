package baseLibrary;

import controller.APIController;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.andreinc.mockneat.types.enums.NameType;
import net.andreinc.mockneat.types.enums.StringType;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.appium.java_client.touch.offset.PointOption.point;
import static net.andreinc.mockneat.types.enums.CVVType.CVV3;
import static net.andreinc.mockneat.types.enums.CVVType.CVV4;
import static net.andreinc.mockneat.types.enums.PassStrengthType.STRONG;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;
import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.unit.financial.CVVS.cvvs;
import static net.andreinc.mockneat.unit.text.Formatter.fmt;
import static net.andreinc.mockneat.unit.text.Strings.strings;
import static net.andreinc.mockneat.unit.text.Words.words;
import static net.andreinc.mockneat.unit.types.Ints.ints;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

public class Utilities extends BaseLibrary {

	private static final Logger logger = LoggerFactory.getLogger(Utilities.class);
	private String BASE_URI;
	private Properties testData;
	private Response responseObj = null;

	public Utilities() {
		testData = new Properties();
		String testDataPropertiesFile = System.getProperty("user.dir") + "/src/main/resources/testData.properties";
		try (FileInputStream fis = new FileInputStream(testDataPropertiesFile)) {
			testData.load(fis);
			if (testData.getProperty("ENV").equalsIgnoreCase("dev")) {
				BASE_URI = testData.getProperty("BASE_URI_DEV");
			} else {
				BASE_URI = testData.getProperty("BASE_URI_PROD");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String executionEnv = checkIfStringIsEmpty(System.getProperty("executionEnv")) ? testData.getProperty("ENV") : System.getProperty("executionEnv");
		logger.info("");
		if (executionEnv.equalsIgnoreCase("dev"))
			BASE_URI = testData.getProperty("BASE_URI_DEV");
		else if (executionEnv.equalsIgnoreCase("prod"))
			BASE_URI = testData.getProperty("BASE_URI_PROD");
		else
			throw new IllegalArgumentException("Unexpected environment value [" + executionEnv + "]");
	}

	/**
	 * Method waits for given time
	 *
	 * @param waitTimeInSeconds
	 * @author sakethkocherla
	 * Created on: Sep 9, 2021
	 */
	public static void staticWait(int waitTimeInSeconds) {
		try {
			Thread.sleep(waitTimeInSeconds * 1000L);
		} catch (InterruptedException e) {
			e.getStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public static boolean verifyElementDisplayed(By ele) {
		return verifyElementDisplayed(ele, 5);
	}

	public static boolean verifyElementDisplayed(By ele, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));

			return driver.findElement(ele).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verifyElementDisplayed(AndroidElement ele) {
		return verifyElementDisplayed(ele, 5);
	}

	public static boolean verifyElementDisplayed(AndroidElement ele, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(ele));

			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static String toCamelCase(String text, String delimiter) {
		if (text == null || text.isEmpty())
			return text;
		final StringBuilder ret = new StringBuilder(text.length());
		for (final String word : text.split(delimiter)) {
			if (!word.isEmpty()) {
				ret.append(Character.toUpperCase(word.charAt(0)));
				ret.append(word.substring(1).toLowerCase());
			}
		}
		return ret.toString();
	}

	public static boolean checkIfStringIsEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}

	public static String getRandomValues(String valueType) {
		String generatedString = "";
		switch (valueType) {
			case "email":
				generatedString = strings().size(2).type(StringType.LETTERS).get() + strings().size(8).type(StringType.ALPHA_NUMERIC).get() + "@gatestautomation.com";
				break;
			case "username":
				generatedString = strings().size(2).type(StringType.LETTERS).get() + strings().size(8).type(StringType.ALPHA_NUMERIC).get();
				break;
			case "password":
				String[] specialCharacterList = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")"};
				String randomPassword = passwords().type(STRONG).get();
				String substring = randomPassword.substring(0, Math.min(randomPassword.length() - 1, 11));
				generatedString =  strings().size(1).type(StringType.LETTERS).get().toUpperCase() + strings().size(1).type(StringType.NUMBERS).get() + substring + strings().size(1).type(StringType.LETTERS).get().toLowerCase() + specialCharacterList[(int) (Math.random()*10)];
				break;
			case "cardNo":
				generatedString = strings().size(16).type(StringType.NUMBERS).get();
				break;
			case "expiry":
				int month = ints().range(1, 12).get();
				generatedString = (month < 10 ? "0" + month : month) + "/" + ints().range(25, 35).get();
				break;
			case "firstName":
				generatedString = names().type(NameType.FIRST_NAME).get();
				break;
			case "lastName":
				generatedString = names().type(NameType.LAST_NAME).get();
				break;
			case "age":
				generatedString = String.valueOf(ints().range(18, 99).get());
				break;
			case "cvv":
				generatedString = cvvs().types(CVV3, CVV4).get();
				break;
			case "text":
				List<String> list = fmt("#{adj} #{noun}")
						.param("adj", words().adjectives().format(CAPITALIZED))
						.param("noun", words().nouns().format(LOWER_CASE))
						.list(10).get();
				generatedString = String.join(" ",list);
		}
		return generatedString;
	}

	public String getBaseURI() {
		return BASE_URI;
	}

	public Properties getTestData() {
		return testData;
	}

	/*
	 * Click Element - Any locator can be mentioned here as input
	 */
	public void click(By ele) {
		driver.findElement(ele).click();
	}

	public void click(AndroidElement ele) {
		ele.click();
	}

	public void scrollToElement(By by, boolean scrollDown) {
		int startPoint;
		int endPoint;
		int maxNumberOfScrolls = 3;
		Dimension size = driver.manage().window().getSize();
		int anchor = (size.width / 2);
		if (scrollDown) {
			// Swipe up to scroll down
			startPoint = (size.height - 10);
			endPoint = 10;
		} else {
			// Swipe down to scroll up
			startPoint = 500;
			endPoint = (size.height - 10);
		}

		while (!verifyElementDisplayed(by) && maxNumberOfScrolls-- > 0) {
			logger.info("---------------- Scrolling {}...", (scrollDown ? "down" : "up"));
			new TouchAction<>(driver)
					.longPress(point(anchor, startPoint))
					.moveTo(point(anchor, endPoint))
					.release()
					.perform();
		}

		if (!verifyElementDisplayed(by))
			throw new NoSuchElementException("Element was not found even after scrolling " + (scrollDown ? "down" : "up"));
	}

	/*
	 * Sending values to the element
	 */
	public void enterValue(By ele, String value) {
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(value);
	}

	public String getFieldValue(By ele) {
		return driver.findElement(ele).getText();
	}

	public void verifyActualAndExpectedNotEquals(String actual, String expected, String invalidMessage) throws IllegalArgumentException {
		if (actual.equalsIgnoreCase(expected)) {
			throw new IllegalArgumentException(invalidMessage);
		}

	}

	public void switchToFrame(By frame) {
		driver.switchTo().frame(driver.findElement(frame));
	}

	public void switchtoDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Methods waits for visibility of element until timeout
	 *
	 * @param ele
	 * @param timeOutInSeconds
	 * @author Suraj Krishnamoorthy
	 * Created on: Sep 9,2021
	 */
	public void waitForElement(By ele, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ele)));
	}

	public void waitForElement(By ele) {
		int timeoutInSeconds = 10;
		waitForElement(ele, timeoutInSeconds);
	}

	public Response getLoginResponse(String username, String password) {
		String iamTokenUrl = BASE_URI + testData.getProperty("IAM_URL");

		Headers headerList = new Headers(new Header("Content-Type", "application/x-www-form-urlencoded"));
		if ((username == null || username.isEmpty()) || (password == null || password.isEmpty())) {
			throw new IllegalArgumentException("Either username or password is missing, please send both");
		}
		Map<String, String> payloadMap = new HashMap<>();
		payloadMap.put("username", username);
		payloadMap.put("password", password);
		payloadMap.put("grant_type", "password");
		payloadMap.put("provider", "other");
		payloadMap.put("clientId", "gaian");
		payloadMap.put("checkB2B", "false");
		payloadMap.put("applicationId", "606fe4aa2cf0760001832be0");
		responseObj = APIController.makePostRequest(iamTokenUrl, headerList, payloadMap);
		responseObj.prettyPeek();
		return responseObj;
	}

	public String getAccessToken(String username, String password) throws IllegalArgumentException {
		if (responseObj == null) {
			responseObj = getLoginResponse(username, password);
		}
		return responseObj.path("access_token").toString();
	}

	public String getAppConsumerId(String username, String password) throws IllegalArgumentException {
		if (responseObj == null) {
			responseObj = getLoginResponse(username, password);
		}
		return responseObj.path("appConsumer").toString();
	}

	public String getUserId(String username, String password) throws IllegalArgumentException {
		if (responseObj == null) {
			responseObj = getLoginResponse(username, password);
		}
		return responseObj.path("userId").toString();
	}

}
