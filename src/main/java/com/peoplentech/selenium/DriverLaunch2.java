package com.peoplentech.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverLaunch2 {

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

    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void validateUserCanClickOnRegisterButton() throws InterruptedException {
        setupBrowser("chrome", "https://www.ebay.com");
        driver.findElement(By.linkText("register")).click();
        closeBrowser();
    }

    @Test
    public void validateUserCanClickOnSignInButton() throws InterruptedException {
        setupBrowser("chrome", "https://www.ebay.com");
        driver.findElement(By.linkText("Sign in")).click();
        closeBrowser();
    }


    @Test
    public void validateUserCanSearchForItems() throws InterruptedException {
        setupBrowser("firefox", "https://www.amazon.com");

        closeBrowser();
    }


}
