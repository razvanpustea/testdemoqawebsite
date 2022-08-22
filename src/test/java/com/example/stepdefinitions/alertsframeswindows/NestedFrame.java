package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NestedFrame {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on nested frames page")
    public void navigateToNestedFramesPage() {
        final String nestedFramesURL = "https://demoqa.com/nestedframes";
        driver.get(nestedFramesURL);
    }

    @Then("he should see the text of the 2 frames")
    public void verifyResult() {
        driver.switchTo().frame("frame1");
        String textFromParentFrame = driver.findElement(By.xpath("*")).getText();
        System.out.println(textFromParentFrame);

        WebElement iframe = driver.findElement(By.xpath("//iframe[@srcdoc]"));
        driver.switchTo().frame(iframe);
        String textFromChildFrame = driver.findElement(By.tagName("p")).getText();
        System.out.println(textFromChildFrame);

        Assert.assertTrue(
                textFromParentFrame.toLowerCase().contains("parent frame")
                && textFromChildFrame.toLowerCase().contains("child iframe")
        );

        driver.quit();
    }
}
