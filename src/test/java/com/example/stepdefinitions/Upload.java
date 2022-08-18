package com.example.stepdefinitions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Upload {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on upload page")
    public void navigateToUploadPage() {
        final String uploadURL = "https://demoqa.com/upload-download";
        driver.get(uploadURL);
    }

    @When("he selects a file")
    public void selectFile() {
        driver
                .findElement(By.id("uploadFile"))
                .sendKeys("/home/razvanpustea/Desktop/git-cheatsheet.pdf");
    }

    @Then("the path of the file should appear on the page")
    public void verifyResult() {
        Assert.assertTrue(driver.findElement(By.id("uploadedFilePath")).isDisplayed());
        driver.quit();
    }
}
