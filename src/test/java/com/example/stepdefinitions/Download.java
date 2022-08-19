package com.example.stepdefinitions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;

public class Download {

    WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on download page")
    public void navigateToDownloadPage() {
        final String downloadURL = "https://demoqa.com/upload-download";
        driver.get(downloadURL);
    }

    @When("he clicks on 'Download' button")
    public void clickOnDownloadButton() {
        WebElement downloadButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/a"));
        downloadButton.click();
    }

    @Then("the file should be saved on his computer")
    public void verifyIfFileWasSaved() {
        final String pathToFile = "/home/razvanpustea/Downloads/";
        final String fileName = driver.findElement(By.id("downloadButton")).getAttribute("download");

        File file = new File(pathToFile + fileName);
        Assert.assertTrue(file.exists());
    }
}
