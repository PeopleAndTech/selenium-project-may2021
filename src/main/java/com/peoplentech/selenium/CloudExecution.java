package com.peoplentech.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static com.peoplentech.selenium.TestBaseOld.waitFor;

public class CloudExecution {


    @Test
    public void validateUserCanExecuteTestsInBrowserstack() throws MalformedURLException {
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

        WebDriver driver = new RemoteWebDriver(url, caps);//
        driver.get("https://www.google.com/gmail/about/#");

        driver.findElement(By.linkText("Create an account")).click();

        Set<String> windowsAfterOpeningNewTab = driver.getWindowHandles();
        String currentWindowsHash = driver.getWindowHandle();
        for (String window : windowsAfterOpeningNewTab) {
            if (!window.equalsIgnoreCase(currentWindowsHash)) {
                driver.switchTo().window(window);
            }
        }

        driver.findElement(By.xpath("//input[@aria-label='First name']")).sendKeys("FName");
        driver.findElement(By.xpath("//input[@aria-label='Last name']")).sendKeys("LName");
        driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb'])[1]")).click();

        waitFor(2);
        WebElement element = driver.findElement(By.xpath("//div[@class='o6cuMc']"));
        String actual = element.getText();
        Assert.assertEquals(actual, "Choose a email address", "error message didn't match");

        driver.quit();
    }
}
