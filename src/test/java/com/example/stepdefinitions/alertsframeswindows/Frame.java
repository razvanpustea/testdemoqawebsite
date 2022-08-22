package com.example.stepdefinitions.alertsframeswindows;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Frame {

    private final WebDriver driver = SeleniumInitializer.getDriver();
    private String mainWindowName;

    @Given("user is on frames page")
    public void navigateToFramesPage() {
        final String framesURL = "https://demoqa.com/frames";
        driver.get(framesURL);
        mainWindowName = driver.getWindowHandle();
    }

    @Then("he should the 2 frames displayed")
    public void checkFrames() {
        String firstFrameText = getTextFromFrame("frame1");
        String secondFrameText = getTextFromFrame("frame2");
        Assert.assertTrue(firstFrameText.matches(".*") && secondFrameText.matches(".*"));
        driver.quit();
    }

    private String getTextFromFrame(String frameId) {
        WebDriver frame = driver.switchTo().frame(driver.findElement(By.id(frameId)));
        String textFromFrame = frame.findElement(By.tagName("h1")).getText();
        switchToMainWindow();
        return textFromFrame;
    }

    private void switchToMainWindow() {
        driver.switchTo().window(mainWindowName);
    }
}
