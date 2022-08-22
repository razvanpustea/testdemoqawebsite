package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PromptBox {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on prompt box page")
    public void navigateToPromptBoxPage() {
        final String promptBoxURL = "https://demoqa.com/alerts";
        driver.get(promptBoxURL);
    }

    @When("he clicks on button next to 'prompt box'")
    public void clickOnPromptBoxButton() {
        driver
                .findElement(By.id("promtButton"))
                .click();
    }

    @And("^enters a ([A-Z]{1}[a-z]{2,}) inside the alert popup$")
    public void enterNameInsideAlert(String name) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    @Then("^the ([A-Z]{1}[a-z]{2,}) should be displayed on the page$")
    public void verifyResult(String name) {
        String displayedValue = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(displayedValue.contains(name));
        driver.quit();
    }
}
