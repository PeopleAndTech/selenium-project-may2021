package com.peoplentech.selenium.ebay;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EbayTest extends TestBase {
    private static final Logger logger = Logger.getLogger(EbayTest.class);

    @Test(priority = 1)
    public void createAUser() {
        System.out.println("user created ");
    }

    @Test(priority = 0)
    public void validateUserCanSearchForItemsInEbay() {
        logger.info("browser opened and ebay.com launched ");

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.ebay.com/";
        Assert.assertEquals(actualUrl, expectedUrl, "url didn't match");
        logger.info("ebay homepage url has validated");

        WebElement ebayLogo = driver.findElement(By.id("gh-l-h1"));
        //boolean result = ebayLogo.isDisplayed();
        //Assert.assertEquals(result,true,"result is not true");
        //Assert.assertTrue(result, "result is not true");
        Assert.assertTrue(ebayLogo.isDisplayed(), "result is not true");

        logger.info("ebay logo has been displayed");

        waitFor(5);
        typeOnXpath("//input[@id='gh-ac']", "Java Books");
        logger.info("typed java books in the search bar");

        clickOnId("gh-btn");
        logger.info("search button has been clicked");

        WebElement result = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']"));
        String log = result.getText();
        //boolean resultFromLog = log.contains("Java Books");
        //Assert.assertTrue(resultFromLog, "log didn't contain Java Books");
        Assert.assertTrue(log.contains("Java Books"), "log didn't contain Java Books");
        logger.info(log + " : has been displayed");
    }


    @Test(enabled = false)
    public void validateUserBeingAbleToChooseOptionFromSearchDropDown() {
        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        Select select = new Select(categoryDropdown);
        select.selectByVisibleText("Art");
    }

    @Test(enabled = false)
    public void validateUserBeingAbleToHandleDropDownOptions() {
        List<WebElement> dropdownList = driver.findElements(By.xpath("//select[@id='gh-cat']/option"));

        System.out.println(dropdownList.size());

        for (int i = 0; i < dropdownList.size(); i++) {
            System.out.println(dropdownList.get(i).getText());
            // System.out.println(dropdownList.get(i).getAttribute("value"));
        }
    }


    @Test(enabled = false)
    public void validateUserBeingAbleToChooseOptionFromMouseHover() {
        WebElement motors = driver.findElement(By.linkText("Motors"));
        Actions actions = new Actions(driver);
        actions.moveToElement(motors).build().perform();
        driver.findElement(By.linkText("Classics")).click();
    }

    @Test(enabled = false)
    public void userShouldBeAbleToScrollDown() {
        waitFor(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        waitFor(2);
    }

    @Test(enabled = false)
    public void userShouldBeAbleToScrollDownToElement() {
        WebElement element = driver.findElement(By.linkText("Registration"));
        waitFor(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitFor(2);
    }

    @Test(enabled = false)
    public void validateUserBeingAbleToLogin() {
        System.out.println("login the user ");
    }

}
