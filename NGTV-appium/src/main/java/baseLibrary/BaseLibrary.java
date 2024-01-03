package baseLibrary;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.appmanagement.ApplicationState;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.util.Calendar;
import java.util.List;

public class BaseLibrary {
	public static final String reportLocation = System.getProperty("user.dir") + "/target/cucumber-reports/";
	public static final String appLogLocation = System.getProperty("user.dir") + "/app-logs/";
	private static final Logger logger = LoggerFactory.getLogger(BaseLibrary.class);
	public static AndroidDriver<AndroidElement> driver;
	public static String deviceName;
	public static String fileID;
	static boolean Execution;
	public Actions act;
	public URL url;

	public static void setUp() throws MalformedURLException {
		if (System.getProperty("localRun").equalsIgnoreCase("true")) {
			logger.info("Running Automation Locally on device");
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("browsername", "");

			capabilities.setCapability("udid", System.getProperty("deviceID"));

			capabilities.setCapability("platformName", "Android");

			capabilities.setCapability("appPackage", "com.gaian.ngtv");

			capabilities.setCapability("appActivity", "view.ngtvactivities.SplashActivity"); //com.gaian.ngtv.view.activities.LaunchActivity

			capabilities.setCapability("newCommandTimeout", "90");
			capabilities.setCapability("platformVersion", "11");
			capabilities.setCapability("appiumVersion", "1.19.1");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", false); // false for always new installation
			capabilities.setCapability("dontStopAppOnReset", false);
			capabilities.setCapability("clearDeviceLogsOnStart", true);

			String appiumHubUrl = System.getProperty("appiumServerUrlforLocal");

			logger.info("Desired Capabilities >>\t {}", capabilities);
			driver = new AndroidDriver<>(new URL(appiumHubUrl), capabilities);
			SessionId session = driver.getSessionId();
			logger.info("New Session id: {}", session);
			ApplicationState state = driver.queryAppState("com.gaian.ngtv");
			logger.info("APPLICATION STATE >>  >> {}", state);
			if (state == ApplicationState.NOT_INSTALLED || state == ApplicationState.NOT_RUNNING) {
				throw new IllegalStateException("App could not be installed & launched.");
			}

			driver.startRecordingScreen(AndroidStartScreenRecordingOptions.startScreenRecordingOptions().enableBugReport());

			return;

		}
		fileID = System.getProperty("fileID");
		if (System.getProperty("platformName") == null || System.getProperty("platformName").equalsIgnoreCase("")) {
			System.setProperty("platformName", "Android");
		}
		if (System.getProperty("platformVersion") == null || System.getProperty("platformVersion").equalsIgnoreCase("")) {
			System.setProperty("platformVersion", "11");
		}
		if (fileID == null || fileID.equalsIgnoreCase("")) {
			logger.info("No Exisiting fileID provided. Using default File ID...");
			fileID = System.getProperty("defaultFileID");
			System.setProperty("fileID", fileID);
			logger.info("Using Default fileID >> {}", fileID);
		} else
			logger.info("Using fileID >> {}", fileID);

		if (System.getProperty("platformName").equalsIgnoreCase("Android")) {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("newCommandTimeout", "90");

			caps.setCapability("name", System.getProperty("testName").equalsIgnoreCase("") ? "NextGenApp Sanity Test"
					: System.getProperty("testName"));

			deviceName = System.getProperty("deviceName");
			logger.info("Device name is >> {}", deviceName);
			if (deviceName == null || deviceName.equalsIgnoreCase("")) {
				logger.info("No Existing deviceName provided. Using default deviceName");
				deviceName = System.getProperty("defaultDevice");
				System.setProperty("deviceName", deviceName);
				logger.info("Default Device is >> {}", deviceName);
			} else
				logger.info("Using Device >> {}", deviceName);

			caps.setCapability("deviceName", deviceName);
			caps.setCapability("platformName", System.getProperty("platformName"));
			caps.setCapability("platformVersion", System.getProperty("platformVersion"));
			caps.setCapability("tabletOnly", "false");
			caps.setCapability("phoneOnly", "false");
			caps.setCapability("noReset", false); // false for always new installation
			caps.setCapability("dontStopAppOnReset", true);

			caps.setCapability("app", "storage:" + System.getProperty("fileID"));

			caps.setCapability("appiumVersion", "1.20.1");
			caps.setCapability("autoGrantPermissions", true);

			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("browserName", "");
			caps.setCapability("clearDeviceLogsOnStart", true);

			String appiumHubUrl = "https://" + System.getProperty("sauceUsername") + ":"
					+ System.getProperty("sauceAccesskey") + "@" +
					System.getProperty("appiumServerUrlSauceLabs");

			logger.info("Desired Capabilities >>\t {}", caps);
			driver = new AndroidDriver<>(new URL(appiumHubUrl), caps);
			SessionId session = driver.getSessionId();
			logger.info("New Session id: {}", session);
			ApplicationState state = driver.queryAppState("com.gaian.ngtv");
			logger.info("APPLICATION STATE >>  >> {}", state);
			if (state == ApplicationState.NOT_INSTALLED || state == ApplicationState.NOT_RUNNING) {
				throw new IllegalStateException("App could not be installed & launched.");
			}

		} else if (System.getProperty("platformName").equalsIgnoreCase("IOS")) {
			// Code to Launch iOS Application
			MutableCapabilities capabilities = new MutableCapabilities();
			capabilities.setCapability("appiumVersion", "1.20.1");
			capabilities.setCapability("platformVersion", "14.4");
			capabilities.setCapability("deviceName", "iPhone 12 Pro Max");

			capabilities.setCapability("idleTimeout", "90");
			capabilities.setCapability("noReset", "false");
			capabilities.setCapability("language", "en");
			capabilities.setCapability("platformName", "iOS");

			//driver = new IOSDriver(new URL("https://us1.appium.testobject.com/wd/hub"), capabilities);
		}

	}

	/*
	 * This method will close the driver instance for a test class after all test
	 * methods execution
	 */

	public static void tearDown(Scenario scenario) {
		if (System.getProperty("localRun").equalsIgnoreCase("true")) {
			String base64String = driver.stopRecordingScreen();
			byte[] data = Base64.decodeBase64(base64String);
			String destinationPath = System.getProperty("user.dir") + "/testcase-recordings/" + Utilities.toCamelCase(scenario.getName(), " ") + ".mp4";
			File destinationFile = new File(destinationPath);
			if (destinationFile.exists() && !destinationFile.delete()) {
				logger.error("Previous screen recording at {} could not be deleted, please check", destinationPath);
			} else {
				try {
					FileUtils.writeByteArrayToFile(destinationFile, data);
					logger.info("-------------------------------------- Wrote video to {} --------------------------------------", destinationPath);
				} catch (IOException e) {
					logger.error("Screen recording could not be saved due to exception {}", e.getLocalizedMessage());
				}
			}
		}

		driver.quit();
	}

	public static void takeScreenshot() {


		Calendar calendar = Calendar.getInstance();
		String screenshotName = String.valueOf(calendar.getTimeInMillis());
		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			// folder
			File destinationPath = new File(
					System.getProperty("user.dir") + "/target/cucumber-reports/" + screenshotName + ".png");

			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

			// This attaches the specified screenshot to the test
			// Reporter.addScreenCaptureFromPath(destinationPath.toString());

			// removing full path as screenshots are in same location as report html
			Reporter.addScreenCaptureFromPath(screenshotName + ".png");
		} catch (IOException e) {
		}
	}

	public void saveDeviceLogs(String logFileName) throws IOException {
		logger.info("-------------------------------------- Inside saveDeviceLogs --------------------------------------");
		if (logFileName == null || logFileName.isEmpty()) {
			logFileName = "appLog";
		}

		List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();

		String filePath = appLogLocation + "device-logs/" + logFileName + ".log";
		String filePathToNgTVLog = appLogLocation + "ngtv-logs/" + logFileName + "_ngTV_filtered.log";

		File file = new File(filePath);
		File fileToNgtvLog = new File(filePathToNgTVLog);

		if (file.exists() && !file.delete()) {
			throw new FileSystemException("Could not delete file " + filePath);
		}
		if (fileToNgtvLog.exists() && !fileToNgtvLog.delete()) {
			throw new FileSystemException("Could not delete file " + filePathToNgTVLog);
		}

		FileWriter fileWriter = new FileWriter(filePath);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		FileWriter fileWriterToNgTVLog = new FileWriter(filePathToNgTVLog);
		PrintWriter printWriterToNgTVLog = new PrintWriter(fileWriterToNgTVLog);
		logEntries.forEach(logVal -> {
			printWriter.println(logVal);
			if (logVal.toString().contains("com.gaian")) {
				printWriterToNgTVLog.println(logVal);
			}
		});
		printWriter.close();
		printWriterToNgTVLog.close();
		logger.info("-------------------------------------- Wrote logs to {} --------------------------------------", filePath);
		logger.info("-------------------------------------- Wrote logs to {} --------------------------------------", filePathToNgTVLog);
	}

	public void getLogger(String logMessage){
		Reporter.addStepLog(logMessage);
		logger.info(logMessage);
	}
}
