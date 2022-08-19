package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Tab {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on tab page")
    public void navigateToBrowserWindowsPage() {
        final String browserWindowsURL = "https://demoqa.com/browser-windows";
        driver.get(browserWindowsURL);
    }

    @When("he clicks on 'New Tab' button")
    public void clickOnNewTabButton() {
        driver
                .findElement(By.id("tabButton"))
                .click();
    }

    @Then("a new tab with a page should open")
    public void verifyResult() {
        Assert.assertNotEquals(driver.getCurrentUrl(), "", "the URL is incorrect");
        Assert.assertEquals(driver.getWindowHandles().size(), 2, "The number of tabs should be 2");
        driver.quit();
    }
}
