package com.cinemo.pages;


import com.gratis.utilities.Driver;
import com.gratis.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;


public class BasePage {
    public BasePage() {

        PageFactory.initElements(Driver.getDriver(), this);


    }


//    public WebElement discountCloseBtn() {
//        WebElement kapatBtn = null;
//        try {
//            JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
//            kapatBtn = (WebElement) jse.executeScript("return document.querySelector('#grt-shadow-host').shadowRoot.querySelector('div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)')");
//        } catch (NullPointerException ignored) {
//
//        }
//        return kapatBtn;
//    }
//
//    public void closeSeturPopup() {
//
//        if (ReusableMethods.verifyElementIsExist((By.cssSelector(".cx-icon.fas.fa-times")))) {
//            try {
//                System.out.println("Setur İndirim Popup Kapatıldı!");
//            } catch (NoSuchElementException e) {
//                System.out.println("Setur İndirim Popup Yok!");
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void clickElement(WebElement element){
//        //close popup
//        element.click();
//    }
//
//    public void sendKeys(WebElement element, String text){
//        // close popup
//        element.sendKeys(text);
//    }


    public void closePopup() {

        try {
            if (ReusableMethods.verifyElementIsExist(By.xpath("//span[normalize-space()='X']"))) {
                ReusableMethods.setImplicitWait(1);
                Driver.getDriver().findElement(By.xpath("//span[normalize-space()='X']")).click();
            }

        } catch (NoSuchElementException e) {
            System.out.println("catch a girildi");
            e.printStackTrace();
        }
        ReusableMethods.resetImplicitWait();
    }

    public void clickElement(WebElement element) {
        closePopup();
        element.click();
    }
}


//    public class PopupHandler {

//        public static void clickElementWithPopupHandling(WebDriver driver, WebElement element) {
//            // Click the element
//            element.click();
//
//            // Handle popup if it appears
//            String mainHandle = driver.getWindowHandle();
//            for (String handle : driver.getWindowHandles()) {
//                if (!handle.equals(mainHandle)) {
//                    driver.switchTo().window(handle);
//
//                    // Perform actions in the popup
//                    // For example, close the popup
//                    driver.close();
//
//                    // Switch back to the main window
//                    driver.switchTo().window(mainHandle);
//                    break;
//                }
//            }
//        }





