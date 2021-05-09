package com.peoplentech.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EbayTest extends TestBase {
    private static final Logger logger = Logger.getLogger(EbayTest.class);

    @Test
    public void validateUserCanSearchForItemsInEbay() {
        setupBrowser("chrome", "https://www.ebay.com");
        logger.info("browser opened and ebay.com launched ");

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.ebay.com/";
        Assert.assertEquals(actualUrl, expectedUrl, "url didn't match");
        logger.info("ebay homepage url has validated");

        WebElement ebayLogo = driver.findElement(By.id("gh-l-h1"));
        //boolean result = ebayLogo.isDisplayed();
        //Assert.assertEquals(result,true,"result is not true");
        Assert.assertTrue(ebayLogo.isDisplayed(), "result is not true");
        logger.info("ebay logo has been displayed");


        waitFor(5);
        typeOnXpath("//input[@id='gh-ac']", "Java Books");
        logger.info("typed java books in the search bar");

        clickOnId("gh-btn");
        logger.info("search button has been clicked");

        WebElement result = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']"));

        String log = result.getText();
        Assert.assertTrue(log.contains("Java Books"), "log didn't contain Java Books");
        logger.info(log + " : has been displayed");

        closeBrowser();
    }


    @Test
    public void validateUserBeingAbleToChooseOptionFromSearchDropDown() {
        setupBrowser("chrome", "https://www.ebay.com");

        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        Select select = new Select(categoryDropdown);
        select.selectByVisibleText("Art");

        closeBrowser();
    }

    @Test
    public void validateUserBeingAbleToChooseOptionFromMouseHover() {
        setupBrowser("chrome", "https://www.ebay.com");

        WebElement motors = driver.findElement(By.linkText("Motors"));
        Actions actions = new Actions(driver);
        actions.moveToElement(motors).build().perform();

        driver.findElement(By.linkText("Classics")).click();

        closeBrowser();
    }

}
