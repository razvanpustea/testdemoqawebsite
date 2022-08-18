package com.example.stepdefinitions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TextBox {
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private final String fullName = "Razvan Pustea";
    private final String email = "razvan" + "@yahoo.com";
    private final String currentAddress = "Republicii";
    private final String permanentAddress = "Republicii";

    @Given("user is on the textbox webpage")
    public void navigateToDemoQaWebpage() {
        final String demoQaWebpage = "https://demoqa.com/text-box";
        driver.get(demoQaWebpage);
    }

    @When("he inserts data into text boxes")
    public void insertData() {
        driver
                .findElement(By.id("userName"))
                .sendKeys(fullName);
        driver
                .findElement(By.id("userEmail"))
                .sendKeys(email);
        driver
                .findElement(By.id("currentAddress"))
                .sendKeys(currentAddress);
        driver
                .findElement(By.id("permanentAddress"))
                .sendKeys(permanentAddress);

        SeleniumInitializer.sleep();
    }

    @And("presses the Submit button")
    public void pressSubmitButton() {
        driver
                .findElement(By.id("submit"))
                .click();
    }

    @Then("he should see what he had entered previously")
    public void isCorrect() {
        List<WebElement> results = driver.findElements(By.xpath("//div[@id='output']//*//p"));

        Assert.assertTrue(verifyOutput(results));

        SeleniumInitializer.sleep();
        driver.quit();
    }

    private boolean verifyOutput(List<WebElement> results) {
        final String[] enteredData = new String[4];
        enteredData[0] = fullName;
        enteredData[1] = email;
        enteredData[2] = currentAddress;
        enteredData[3] = permanentAddress;

        for (int i = 0; i < results.size(); i++) {
            String textDisplayed = results.get(i).getText();
            if (!textDisplayed.contains(enteredData[i])) return false;
        }

        return true;
    }
}
