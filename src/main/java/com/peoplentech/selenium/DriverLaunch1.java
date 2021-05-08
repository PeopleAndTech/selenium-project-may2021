package com.peoplentech.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverLaunch1 {

    public static void main(String[] args) throws InterruptedException {

        // setup the env property
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        // created obj of chromedriver
        WebDriver driver = new ChromeDriver();

        // open the browser
        driver.get("https://www.google.com");

        // wait
        Thread.sleep(5000);

        // quit the browser
        driver.quit();


    }


}
