package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Alert {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on alerts page")
    public void navigateToAlertsPage() {
        final String alertsURL = "https://demoqa.com/alerts";
        driver.get(alertsURL);
    }

    @When("he clicks on the button next to 'Click Button to see alert'")
    public void clickOnAlertButton() {
        driver
                .findElement(By.id("alertButton"))
                .click();
    }

    @Then("an alert should appear on the screen and user can exit it by clicking 'OK'")
    public void verifyResult() {
        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        Assert.assertNotEquals(alert.getText(), "");
        alert.accept();
        SeleniumInitializer.sleep();
        driver.quit();
    }

    @When("he clicks on a button next to 'On button click, alert will appear after 5 seconds'")
    public void clickOnDelayedAlertButton() {
        driver
                .findElement(By.id("timerAlertButton"))
                .click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
