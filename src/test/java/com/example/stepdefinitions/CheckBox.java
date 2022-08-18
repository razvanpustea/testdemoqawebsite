package com.example.stepdefinitions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckBox {
    private final WebDriver driver = SeleniumInitializer.getDriver();

    @Given("user is on checkbox page")
    public void navigateToCheckboxPage() {
        final String url = "https://demoqa.com/checkbox";
        driver.get(url);
    }

    @When("he selects 1 or more checkboxes")
    public void selectCheckbox() {
        driver
                .findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/span/button"))
                .click();
        driver
                .findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[1]/span/button"))
                .click();
        driver
                .findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[1]/ol/li[1]/span/label/span[1]"))
                .click();
        driver
                .findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[1]/ol/li[2]/span/label/span[1]"))
                .click();
        driver
                .findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[2]/span/label/span[1]"))
                .click();
    }

    @Then("he should see what he selected")
    public void verifyResult() {
        String textValue = driver.findElement(By.xpath("//div[@id='result']//span")).getText();
        Assert.assertTrue(textValue.contains("You have selected"));
        SeleniumInitializer.sleep();
        driver.quit();
    }
}
