package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ModalDialog {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on modal-dialogs page")
    public void navigateToModalDialogsPage() {
        final String modalDialogsURL = "https://demoqa.com/modal-dialogs";
        driver.get(modalDialogsURL);
    }

    @When("he clicks the 'Small modal' button")
    public void clickSmallModalButton() {
        driver
                .findElement(By.xpath("//button[contains(text(), 'Small modal')]"))
                .click();
    }

    @Then("he should see a modal with some text in it")
    public void verifyResult() {
        WebElement modalBody = driver.findElement(By.className("modal-body"));
        Assert.assertTrue(modalBody.isDisplayed());
        SeleniumInitializer.sleep();
        driver.findElement(By.xpath("//div[@class='modal-footer']/button")).click();
        driver.quit();
    }

    @When("he clicks the 'Large modal' button")
    public void clickLargeModalButton() {
        driver.findElement(By.id("showLargeModal")).click();
    }
}
