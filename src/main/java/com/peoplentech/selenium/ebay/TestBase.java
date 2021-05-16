package com.peoplentech.selenium.ebay;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;

    @BeforeMethod
    public static void setupBrowser() throws MalformedURLException {
        String browserName = "chrome";
        String url = "https://www.ebay.com";
        String os = "mac";
        String platform = "cloud";

        if (platform.equalsIgnoreCase("local")) {
            driver = setupLocalDriver(os, browserName);
        } else {
            driver = setupCloudDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    private static WebDriver setupCloudDriver() throws MalformedURLException {
        String userName = "meronasgedom_GSMUOb";
        String accessKey = "hoBrfWKNz6SeMTLuYKGb";

        String urlOfBrowserstack = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
        URL url = new URL(urlOfBrowserstack);//

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "Mojave");
        caps.setCapability("resolution", "1600x1200");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "89.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("name", "cloud execution test : 01");

        WebDriver driver = new RemoteWebDriver(url, caps);
        return driver;
    }

    private static WebDriver setupLocalDriver(String os, String browserName) {
        if (os.equalsIgnoreCase("mac")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
            }
        } else {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }

    @AfterMethod
    public static void closeBrowser() {
        waitFor(5);
        driver.quit();
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


}
