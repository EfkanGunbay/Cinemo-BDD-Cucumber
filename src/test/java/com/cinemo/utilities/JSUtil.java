package com.cinemo.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class JSUtil {
        public static String generateRandomWord() {
            Random random = new Random();
            String[] words = new String[0];
            int index = random.nextInt(words.length);
            return words[index];
        }

    public static void flash(WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            String bgcolor = element.getCssValue("backgroundColor");
            for (int i = 0; i < 500; i++) {
                changeColor("#000000", element, driver);// 1
                changeColor(bgcolor, element, driver);// 2
            }
        }
        public static void changeColor(String color, WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        public static void drawBorder(WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        }
        public static String getTitleByJS(WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            String title = js.executeScript("return document.title;").toString();
            return title;
        }
        public static void clickElementByJS(WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].click();", element);
        }
        public static void generateAlert(WebDriver driver, String message) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("alert('" + message + "')");
        }
        public static void refreshBrowserByJS(WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("history.go(0)");
        }
        public static String getPageInnerText(WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            String pageText = js.executeScript("return document.documentElement.innerText;").toString();
            return pageText;
        }
        public static void scrollPageDown(WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }
        public static void scrollIntoView(WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }

    }


