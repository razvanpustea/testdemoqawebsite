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

public class ConfirmBox {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on confirm box page")
    public void navigateToConfirmBoxPage() {
        final String confirmBoxURL = "https://demoqa.com/alerts";
        driver.get(confirmBoxURL);
    }

    @When("he clicks on the button next to to 'confirm box will appear'")
    public void clickOnConfirmBoxButton() {
        driver
                .findElement(By.id("confirmButton"))
                .click();
    }

    @Then("a confirm box should appear and user can select 'Cancel' or 'OK'")
    public void verifyCheckbox() {
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("confirm"));
        switch ((int) (Math.random() * 2)) {
            case 0:
                alert.accept();
            case 1:
                alert.dismiss();
        }
    }

    @And("when he selects 1 value, it should be displayed")
    public void verifySelectedValue() {
        String confirmationText = driver.findElement(By.id("confirmResult")).getText().toLowerCase();
        Assert.assertTrue(confirmationText.equals("you selected ok") || confirmationText.equals("you selected cancel"));
        SeleniumInitializer.sleep();
        driver.quit();
    }
}
