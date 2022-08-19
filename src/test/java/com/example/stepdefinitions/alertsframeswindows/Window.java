package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Window {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on window page")
    public void navigateToWindowPage() {
        final String windowPageURL = "https://demoqa.com/browser-windows";
        driver.get(windowPageURL);
    }

    @When("he clicks on 'New Window' button")
    public void clickOnNewWindowButton() {
        driver
                .findElement(By.id("windowButton"))
                .click();
    }

    @Then("a new window with a page should open")
    public void verifyResult1() {
        Assert.assertNotEquals(driver.getCurrentUrl(), "", "the URL is incorrect");
        Assert.assertEquals(driver.getWindowHandles().size(), 2, "The number of tabs should be 2");
        driver.quit();
    }
}
