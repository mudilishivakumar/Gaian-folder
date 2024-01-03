package baseLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import controller.DBController;
import enums.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.appium.java_client.touch.offset.PointOption.point;
import static net.andreinc.mockneat.types.enums.CVVType.CVV3;
import static net.andreinc.mockneat.unit.financial.CVVS.cvvs;
import static net.andreinc.mockneat.unit.text.Strings.strings;
import static net.andreinc.mockneat.unit.types.Ints.ints;
import static net.andreinc.mockneat.unit.user.Names.names;


public class Utilities extends BaseLibrary {
    protected static DBController dbc = null;
    protected static APIController apiController;
    static Map<Criteria, Boolean> coeUpdateMap = new HashMap<Criteria, Boolean>();
    private static final Logger logger = LoggerFactory.getLogger(Utilities.class);
    private Response responseObj = null;
    private ReadConfig readConfig;

    public Utilities() {
        readConfig = new ReadConfig();
    }

    public void updateCOECriteria(Criteria criteria, Double score) {
        if (coeUpdateMap.containsKey(criteria)) {
            return;
        } else {
            coeUpdateMap.put(criteria, true);
            dbc.updateCriteria("NGAPP", System.getProperty("APP_VERSION"), score, criteria);
        }
    }

    public void click(By ele) {
        driver.findElement(ele).click();
        logger.info("----clicking");
    }

    public void click(AndroidElement ele) {
        ele.click();
    }

    /*
     * Sending values to the element
     */
    public void enterValue(By ele, String Value) {
        try {
            driver.findElement(ele).sendKeys(Value);
            logger.info("----Entering " + Value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void VerifyActualAndExpectedNotEquals(String Actual, String Expected, String ValidMessage,
                                                 String InvalidMessage) throws Throwable {
        if (Actual.equalsIgnoreCase(Expected)) {
            throw new Exception(InvalidMessage);
        }
    }

    public boolean VerifyElementDisplayed(By ele) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            boolean statusOfVisbility = driver.findElement(ele).isDisplayed();
            if (!statusOfVisbility)
                wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
            logger.info("----Checking for the visibilty of the element");
            return driver.findElement(ele).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean VerifyElementDisplayed(AndroidElement ele) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            boolean statusOfVisbility = ele.isDisplayed();
            if (!statusOfVisbility)
                wait.until(ExpectedConditions.visibilityOf(ele));
            logger.info("----Checking for the visibilty of the element");
            return ele.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void switchToFrame(By frame) {
        logger.info("----Switching the frames");
        driver.switchTo().frame(driver.findElement(frame));
    }

    public void switchtoDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getText(By ele) {
        logger.info("----Getting the text of the element");
        return driver.findElement(ele).getText();
    }

    public static void staticWait(int inSeconds) {
        try {
            logger.info("----Waiting for  " + inSeconds + " SECONDS");
            Thread.sleep(inSeconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken(String username, String password) throws IllegalArgumentException {
        if (responseObj == null) {
            responseObj = getLoginResponse(username, password);
        }
        return responseObj.path("access_token").toString();
    }

    public void scrollToElement(By by, boolean scrollDown) {
        int startPoint;
        int endPoint;
        int maxNumberOfScrolls = 3;
        Dimension size = driver.manage().window().getSize();
        int anchor = (size.width / 2);
        if (scrollDown) {
            // Swipe up to scroll down
            startPoint = (size.height - 100);
            endPoint = 10;
        } else {
            // Swipe down to scroll up
            startPoint = 300;
            endPoint = (size.height - 10);
        }

        while (!VerifyElementDisplayed(by) && maxNumberOfScrolls-- > 0) {
            logger.info("----Scrolling {}...", (scrollDown ? "down" : "up"));
            new TouchAction<>(driver)
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        }
        if (!VerifyElementDisplayed(by))
            throw new NoSuchElementException("----Element was not found even after scrolling " + (scrollDown ? "down" : "up"));
    }

    public void scrollParticularWebElement(By webElementToBeScrolled, By webElementToBeSearched) {
        AndroidElement eleOne = driver.findElement(webElementToBeScrolled);
        int maxNumberOfScrolls = 3;
        Point point = eleOne.getLocation();
        getLogger("Element Location : " + point);
        Dimension dimension = eleOne.getSize();
        getLogger("Element Dimension : " + dimension);
        int anchor = point.getX() + (dimension.getWidth()) / 2;
        getLogger("Element Anchor : " + anchor);
        int startPoint = (int) (dimension.getHeight());
        getLogger("Scroll Startpoint : " + startPoint);
        int endPoint = (int) (dimension.getHeight() * 0.2);
        getLogger("Scroll EndPoint : " + endPoint);
        while (!VerifyElementDisplayed(webElementToBeSearched) && maxNumberOfScrolls-- > 0) {
            new TouchAction<>(driver)
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        }
    }

    public void generalScroll(int maxNumberOfScrolls, boolean scrollDown) {
        int startPoint;
        int endPoint;
        Dimension size = driver.manage().window().getSize();
        int anchor = (size.width / 2);
        if (scrollDown) {
            // Swipe up to scroll down
            startPoint = (size.height - 100);
            endPoint = 10;
        } else {
            // Swipe down to scroll up
            startPoint = 300;
            endPoint = (size.height - 10);
        }
        while (maxNumberOfScrolls-- > 0) {
            logger.info("----Scrolling {}...", (scrollDown ? "down" : "up"));
            new TouchAction<>(driver)
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        }
    }

    public void tap(By ele) {
        TouchActions action = new TouchActions(driver);
        action.singleTap(driver.findElement(ele));
        action.perform();
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



    public void clear(By ele) {
        logger.info("----clearing the element " + getText(ele));
        driver.findElement(ele).clear();
    }

    public String getTime(String pattern) {
        LocalDateTime myDateObj = LocalDateTime.now();
        String formattedDate = myDateObj.format(DateTimeFormatter.ofPattern(pattern));
        return formattedDate;
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
                generatedString = strings().size(2).type(StringType.LETTERS).get() + strings().size(1).type(StringType.NUMBERS).get() + strings().size(8).type(StringType.ALPHA_NUMERIC).get();
                break;
            case "password":
                // generatedString = strings().size(2).type(StringType.LETTERS).get() + ints().range(0, 9).get() + strings().size(6).type(StringType.ALPHA_NUMERIC).get() + strings().size(1).type(StringType.SPECIAL_CHARACTERS).get();
                generatedString = strings().size(2).type(StringType.LETTERS).get() + ints().range(0, 9).get() + strings().size(6).type(StringType.ALPHA_NUMERIC).get() + '@';
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
                generatedString = cvvs().types(CVV3, CVV3).get();
        }
        return generatedString;
    }

    public String getAppConsumerId(String username, String password) throws IllegalArgumentException {
        getLogger("Calling LOgin API Again");
        responseObj = getLoginResponse(username, password);
        return responseObj.path("appConsumer").toString();
    }

    public String getUserId(String username, String password) throws IllegalArgumentException {
        if (responseObj == null) {
            responseObj = getLoginResponse(username, password);
        }
        return responseObj.path("userId").toString();
    }

    public Response getLoginResponse(String username, String password) {
        logger.info("----Username Used is: " + username);
        String iamTokenUrl = readConfig.getBaseURI() + readConfig.getLoginEndpoint();
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
        logger.info(payloadMap.toString());
        responseObj.prettyPeek();
        return responseObj;
    }

    public void generalSwipeOfParticularElement(AndroidElement ele) {
        Point point = ele.getLocation();
        int anchor = point.getY() + 20;
        Dimension dimension = ele.getSize();
        int startPoint = (int) (dimension.getWidth() * 0.8);
        int endPoint = (int) (dimension.getWidth() * 0.1);
        int noOfScrolls = 2;
        while (noOfScrolls-- > 0 && ele.isDisplayed()) {
            getLogger("Swipe" + noOfScrolls);
            new TouchAction<>(driver)
                    .longPress(point(startPoint, anchor))
                    .moveTo(point(endPoint, anchor))
                    .release()
                    .perform();
        }
    }

    public void generalSwipeOfWebElementUptoRequiredPercentage(By by, float swipePercentage) {
        AndroidElement ele = driver.findElement(by);
        Point point = ele.getLocation();
        int anchor = point.getY() + 20;
        Dimension dimension = ele.getSize();
        swipePercentage /= 100;
        System.out.println(swipePercentage);
        int endPoint = (int) (dimension.getWidth() * swipePercentage);
        int startPoint = (int) (dimension.getWidth() * 0.25);
        new TouchAction<>(driver)
                .longPress(point(startPoint, anchor))
                .moveTo(point(endPoint, anchor))
                .release()
                .perform();
    }


    public boolean verifyBAInLogs(String searchStr) {
        boolean status = false;
        try {
            logger.info("---------------------------------- FILE FOR SEARCHING BA  " + getLogFileName());
            logger.info("---------------------------------- SEARCHING FOR PACKAGE  " + searchStr);
            Scanner scan = new Scanner(new File(getLogFileName()));
            while (scan.hasNext()) {
                String line = scan.nextLine().toLowerCase().toString();
                if (line.contains(searchStr)) {
                    getLogger("---------------------------------- FILE FOUND IN LOGS " + line);
                    status=true;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return status;
    }

    public ReadConfig getReadConfig() {
        return readConfig;
    }
}

