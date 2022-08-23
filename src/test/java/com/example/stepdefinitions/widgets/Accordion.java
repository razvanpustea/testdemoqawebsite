package com.example.stepdefinitions.widgets;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Accordion {

    private final WebDriver driver = SeleniumInitializer.getDriver();
    private final List<Boolean> results = new ArrayList<>();

    @Given("user is on accordion page")
    public void navigateToAccordionPage() {
        final String accordionURL = "https://demoqa.com/accordian";
        driver.get(accordionURL);
    }

    @When("he clicks on an accordion")
    public void clickOnAccordion() {
        try {
            for (int i = 1; ; i++) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4L));
                WebElement accordion = driver.findElement(By.id("section" + i + "Heading"));
                if (i != 1) accordion.click();

                WebElement body = driver.findElement(By.xpath
                        ("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div[" + i + "]/div[2]/div"));

                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div[" + i + "]/div[2]/div/p"))));
                String accordionText = body.getText();

                SeleniumInitializer.sleep();

                if (accordionText.length() > 0)
                    results.add(true);
                else results.add(false);
            }
        } catch (NoSuchElementException exception) {
            System.out.println("NoSuchElementException occurred intentionally to test all the possibilities!");
        }
    }

    @Then("it should collapse and some text should be displayed")
    public void verifyResult() {
        for (Boolean result : results)
            if (!result) Assert.fail();
        Assert.assertTrue(true);
        driver.quit();
    }
}
