package com.example.stepdefinitions.elements;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class RadioButton {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on radio button page")
    public void navigateToRadioButtonPage() {
        final String url = "https://demoqa.com/radio-button";
        driver.get(url);
    }

    @When("he clicks on a radio button")
    public void clickOnRadioButton() {
        List<WebElement> radioButtons = driver.findElements(By.xpath("//label[@class='custom-control-label']"));
        int randomIndex = (int)(Math.random() * 2);
        radioButtons.get(randomIndex).click();
    }

    @Then("a text should appear with the value he selected")
    public void verifyResult() {
        Assert.assertTrue(driver.getPageSource().contains("You have selected"));
        SeleniumInitializer.sleep();
        driver.quit();
    }
}
