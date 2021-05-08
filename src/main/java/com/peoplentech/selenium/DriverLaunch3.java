package com.peoplentech.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverLaunch3 {

    private static WebDriver driver;

    public static void setupBrowser(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.get(url);
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeBrowser() {
        waitFor(5);
        driver.quit();
    }

    public static void clickOnId(String id) {
        driver.findElement(By.id(id)).click();
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public void clickOnLinkText(String lnkTxt) {
        driver.findElement(By.linkText(lnkTxt)).click();
    }

    public void clickOnXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void typeOnXpath(String xpath, String data) {
        driver.findElement(By.xpath(xpath)).sendKeys(data);
    }

    @Test
    public void validateUserCanClickOnRegisterButton() {
        setupBrowser("chrome", "https://www.ebay.com");
        clickOnLinkText("register");
        navigateBack();
        closeBrowser();
    }

    @Test
    public void validateUserCanClickOnSignInButton() {
        setupBrowser("chrome", "https://www.ebay.com");
        clickOnLinkText("Sign in");
        closeBrowser();
    }


    @Test
    public void validateUserCanSearchForItemsInAmazon() {
        setupBrowser("firefox", "https://www.amazon.com");
        waitFor(2);
        typeOnXpath("//input[@id='twotabsearchtextbox']", "Java Books");
        closeBrowser();
    }

    @Test
    public void validateUserCanSearchForItemsInEbay() {
        setupBrowser("firefox", "https://www.ebay.com");
        waitFor(2);
        typeOnXpath("//input[@id='gh-ac']", "Java Books");
        clickOnId("gh-btn");
        closeBrowser();
    }

}
