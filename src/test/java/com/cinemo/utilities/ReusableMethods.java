package com.cinemo.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.sleep;



public class ReusableMethods {
static Driver driver;
   public static void randomUUID() {
       String uniqueID = UUID.randomUUID().toString();
       System.out.println("uniqueID = " + uniqueID);

   }


        public static int generateUniqueId() {
            UUID idOne = UUID.randomUUID();
            String str=""+idOne;
            int uid=str.hashCode();
            String filterStr=""+uid;
            str=filterStr.replaceAll("-", "");
            System.out.println("str = " + str);
            return Integer.parseInt(str);

        }
        // XXX: replace with java.util.UUID

        public static void main(String[] args) {
            for (int i = 0; i < 12; i++) {
                System.out.println(generateUniqueId());
                //generateUniqueId();
            }
        }



    public static WebElement clickFunction(WebElement clickElement) {

        waitForClickablility(clickElement,3);

        //wait.until(ExpectedConditions.elementToBeClickable(clickElement));

        clickElement.click();
        return clickElement;
    }
public static WebElement waitForClickablility(WebElement element, int i) {


    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.elementToBeClickable(element));
}


    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        //Driver.getDriver().switchTo().window(origin);
    }


    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }


    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        System.out.println("elemTexts.size() = " + elemTexts.size());
        System.out.println("elemTexts = " + elemTexts);
        return elemTexts;

    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void waitFor(int sec) {
        try {
            sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //===============Explicit Wait==============//
//    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.of(timeToWaitInSec));
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }


//
//    public static WebElement waitForVisibility(By locator, int timeout) {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public static WebElement waitForClickablility(WebElement element, int timeout) {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
//        return wait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    public static WebElement waitForClickablility(By locator, int timeout) {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
//        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public static void clickWithTimeOut(WebElement element, int timeout) {
//        for (int i = 0; i < timeout; i++) {
//            try {
//                element.click();
//                return;
//            } catch (WebDriverException e) {
//                waitFor(1);
//            }
//        }
//    }

//    public static void waitForPageToLoad(long timeOutInSeconds) {
//        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//            }
//        };
//        try {
//            System.out.println("Waiting for page to load...");
//            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
//            wait.until(expectation);
//        } catch (Throwable error) {
//            System.out.println(
//                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
//        }
//    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return element;
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    /**
     * Selects a random value from a dropdown list and returns the selected Web Element
     *
     * @param select
     * @return
     */
    public static WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    public static void closeCerez() {
        WebElement cerez = Driver.getDriver().findElement(By.cssSelector(".cookie-notification.row"));
        if (cerez.isEnabled()) {
            Driver.getDriver().findElement(By.cssSelector(".js-close-notification")).click();
        }

    }

    public static void closeCerez1() {
        WebElement cerez = Driver.getDriver().findElement(By.className("cookiespool-cookies"));
        if (cerez.isEnabled()) {
            Driver.getDriver().findElement(By.xpath("//button[@id='banner-accept-button']")).click();
        }

    }


    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);



        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick)
    {
        Actions action = new Actions(driver);
        action.moveToElement(elementToHover).click(elementToClick).build().perform();
    }

    public static String generatePhoneNumber1() {
        Random rand = new Random();
        int num1, num2, num3;
        num1 = rand.nextInt(900) + 100;
        num2 = rand.nextInt(643) + 100;
        num3 = rand.nextInt(9000) + 1000;

        String phoneNumber = ("80" + (Integer.toString(num2)) + (Integer.toString(num3)));
        System.out.println(phoneNumber);
        //806041653
        return phoneNumber;
    }
    public static String generateOtpCode() {
        Random rand = new Random();
        int num1, num2, num3;
        num1 = rand.nextInt(900) + 100;
        num2 = rand.nextInt(643) + 100;
        //num3 = rand.nextInt(900) + 100;
        String otpCode = ((Integer.toString(num1) + Integer.toString(num2)));
        System.out.println(otpCode);
        //806041653
        return otpCode;
    }
    public static boolean retryingFindClick() {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                Driver.getDriver().findElement(By.xpath("//input[@id='js-site-search-input']")).click();
                result = true;
                break;
            } catch (Exception e) {
            }
            attempts++;
        }
        return result;
    }

    public static void hover() {
    }

    public boolean verifyelementNotDisplayed(WebElement element){
        try {
            return (!element.isDisplayed());

        }catch (Exception e){
            return false;
        }
    }

    public static void scrollDown(WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
    }

    public static void scrollUp(WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-350)", "");
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public static List<String> getTextOfList(List<WebElement> list) {
        List<String> newList = new ArrayList<>();
        for (WebElement each : list) {
            newList.add(each.getText());
        }
        System.out.println("newList = " + newList);
        return newList;
    }

    public static class IdGenerator {
        public static int generateUniqueId() {
            UUID idOne = UUID.randomUUID();
            String str=""+idOne;
            int uid=str.hashCode();
            String filterStr=""+uid;
            str=filterStr.replaceAll("-", "");
            return Integer.parseInt(str);
        }

        // XXX: replace with java.util.UUID

        public static void main(String[] args) {
            for (int i = 0; i < 12; i++) {
                System.out.println(generateUniqueId());
                //generateUniqueId();
            }
        }

    }

    public static String tcKimlikOlustur() {
            // Generate the first nine digits randomly
            Random rand = new Random();
            StringBuilder tcKimlik = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                int digit = rand.nextInt(10); // Generate a random digit between 0 and 9
                tcKimlik.append(digit);
            }

            // Calculate the 10th digit (control digit) using the first 9 digits
            int total = 0;
            for (int i = 0; i < 9; i++) {
                total += Character.getNumericValue(tcKimlik.charAt(i)) * (i % 2 == 0 ? 7 : 9);
            }
            int controlDigit = total % 10;
            tcKimlik.append(controlDigit);

            return tcKimlik.toString();

    }

    private static String[] toStringArray(Integer integer) {
        String string = integer.toString();
        char[] chars = string.toCharArray();
        String[] strings = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            strings[i] = chars[i] + "";
        }

        return strings;
    }

    private static int calculateDigit11(int pool1Sum, int pool2Sum, int digit10) {
        return (pool1Sum + pool2Sum + digit10) % 10;
    }

    private static int calculateDigit10(Integer pool1Sum, Integer pool2Sum) {
        int i = pool1Sum * 7;
        int digit10 = (i - pool2Sum) % 10;
        return digit10;
    }

    private static Integer arraySum(String[] arr) {
        int sum = 0;
        for (String num : arr) {
            // Convert each string element to an integer and add it to the sum
            sum += Integer.parseInt(num);
        }
        return sum;
    }
    public static String generateEmail() {
        StringBuilder eMail = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            eMail.append((char) ((Math.random() * 25) + 97));
        }
        eMail.append("@gmail.com");
        return eMail.toString();
    }
    public static void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }




    public static void setImplicitWait(int seconds) {
        Driver.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    public static void resetImplicitWait() {
        Driver.getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

    }





//    public static void closePopupWithWait(int second) throws InterruptedException {
//        sleep(second);
//        try {
//            ReusableMethods.setImplicitWait(1);
//            new BasePage().discountCloseBtn().click();
//            System.out.println("Popup kapatıldı");
//        } catch (Exception ignored) {
//            System.out.println("Popup yok.");
//        }
//        ReusableMethods.resetImplicitWait();
//    }

    public static boolean verifyElementIsExist(By locator) {
        setImplicitWait(3);
        boolean flag = false;
        try {
            flag = Driver.getDriver().findElements(locator).size() > 0;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        resetImplicitWait();
        return flag;
    }



    }



