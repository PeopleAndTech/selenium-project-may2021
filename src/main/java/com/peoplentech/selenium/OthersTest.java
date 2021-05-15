package com.peoplentech.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class OthersTest extends TestBaseOld {

    @Test
    public void userShouldBeAbleToPerformDragNdrop() {
        setupBrowser("chrome", "http://demo.guru99.com/test/drag_drop.html");

        WebElement source = driver.findElement(By.xpath("(//li[@id='fourth'])[1]"));
        WebElement destination = driver.findElement(By.id("amt7"));

        waitFor(2);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, destination).build().perform();

        closeBrowser();
    }

    @Test
    public void userShouldBeAbleToPerformIframes() {
        setupBrowser("chrome", "https://demoqa.com/frames");

        // 3 ways - name, id, index
        driver.switchTo().frame("frame2");
        waitFor(5);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        driver.switchTo().defaultContent();
        waitFor(5);

        js.executeScript("window.scrollBy(0,1000)");

        closeBrowser();
    }


    @Test
    public void userShouldBeAbleToHandlePopup() {
        setupBrowser("chrome", " http://demo.guru99.com/test/delete_customer.php");
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        waitFor(4);

        //driver.switchTo().alert().dismiss();


        String dataFromAlert = driver.switchTo().alert().getText();
        System.out.println(dataFromAlert);

        driver.switchTo().alert().accept();
        waitFor(4);
        driver.switchTo().alert().accept();


        closeBrowser();
    }


    // http://demo.guru99.com/test/upload/


}
