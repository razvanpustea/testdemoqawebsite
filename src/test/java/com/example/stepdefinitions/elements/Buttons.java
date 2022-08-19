package com.example.stepdefinitions.elements;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class Buttons {

    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on buttons page")
    public void navigateToButtonsPage() {
        final String buttonsURL = "https://demoqa.com/buttons";
        driver.get(buttonsURL);
    }

    @When("he double clicks the button 'Double Click Me'")
    public void doubleClickButton() {
        Actions builder = new Actions(driver);
        Action doubleClick = builder
                .doubleClick(driver.findElement(By.id("doubleClickBtn")))
                .build();
        doubleClick.perform();
    }

    @Then("^he should see a text saying he had done a ([a-zA-Z]+) click$")
    public void verifyDoubleClick(String typeOfClick) {
        List<WebElement> paragraphs = driver.findElements(By.xpath("//p"));
        boolean textContained = false;

        for (WebElement paragraph : paragraphs) {
            if (paragraph.getText().contains(typeOfClick)) {
                textContained = true;
            }
        }
        Assert.assertTrue(textContained);
        driver.quit();
    }

    @When("he right clicks on the button 'Right Click Me'")
    public void rightClickOnButton() {
        Actions builder = new Actions(driver);
        Action rightClick = builder.contextClick(driver.findElement(By.id("rightClickBtn"))).build();
        rightClick.perform();
    }

    @When("he clicks on the button 'Click Me'")
    public void clickOnButton() {
        driver
                .findElement(By.xpath("//button[text() = 'Click Me']"))
                .click();
    }
}
