package com.example.stepdefinitions.widgets;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AutoComplete {

    private final WebDriver driver = SeleniumInitializer.getDriver();
    private String[] inputColorsFormatted;
    private String[] colorsFormatted;
    private String letters;

    @Given("user is on autocomplete page")
    public void navigateToAutocompletePage() {
        final String autoCompleteURL = "https://demoqa.com/auto-complete";
        driver.get(autoCompleteURL);
    }

    @When("^he types ([a-zA-Z]+) in multiple color names field$")
    public void typeLettersInMultipleColorsField(String letters) {
        this.letters = letters;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3L));
        WebElement multipleColorsInput = driver.findElement(By.id("autoCompleteMultipleInput"));
        multipleColorsInput.clear();
        multipleColorsInput.sendKeys(letters);
        SeleniumInitializer.sleep();
        wait.until(ExpectedConditions.attributeContains(multipleColorsInput, "value", letters));
    }

    @Then("colors containing those letters should appear")
    public void verifyColors() {
        String unformattedColors = driver.findElement(By.cssSelector(".auto-complete__menu.css-26l3qy-menu")).getText();
        colorsFormatted = unformattedColors.split("\n");
    }

    @And("^([a-zA-Z\\s,]+) from the list can be selected$")
    public void selectColor(String inputColors) {
        inputColorsFormatted = inputColors.split("[,\\s]+");

        for (String inputColorFormatted : inputColorsFormatted) {
            for (int i = 0; i < colorsFormatted.length; i++) {
                String colorFormatted = colorsFormatted[i];
                if (inputColorFormatted.equalsIgnoreCase(colorFormatted)) {
                    WebElement autoCompleteSuggestion = driver.findElement(By.xpath("//*[contains(text(),'" + colorFormatted + "')]"));
                    autoCompleteSuggestion.click();
                    SeleniumInitializer.sleep();
                    typeLettersInMultipleColorsField(letters);
                    break;
                } else if (i == colorsFormatted.length - 1)
                    System.out.println(inputColorFormatted + " color doesn't exist on our website OR you didn't type the correct letters!");
            }
        }

        verifyResult();
    }

    private void verifyResult() {
        byte count = 0;
        List<WebElement> results = driver.findElements(By.cssSelector(".css-12jo7m5.auto-complete__multi-value__label"));
        for (String inputColor : inputColorsFormatted) {
            for (WebElement result : results)
                if (inputColor.equalsIgnoreCase(result.getText())) {
                    count++;
                    break;
                }
        }
        Assert.assertEquals(count, results.size());
        driver.quit();
    }

    @When("^he types letters, for example '([a-zA-Z]+)', in single color name field$")
    public void typeLettersInSingleColorField(String letters) {
        getInputFieldForSingleColor().sendKeys(letters);
    }

    @And("^user can select a color, for example '([a-zA-Z]+)', from the list$")
    public void selectColorFromList(String color) {
        color = Character.toUpperCase(color.charAt(0)) + color.substring(1).toLowerCase();
        WebElement fieldForSingleColor = getInputFieldForSingleColor();
        for (int i = 0; i < colorsFormatted.length; i++) {
            if (i != colorsFormatted.length - 1 && !color.equals(colorsFormatted[i])) {
                SeleniumInitializer.sleep();
                fieldForSingleColor.sendKeys(Keys.ARROW_DOWN);
            }
            else if (color.equals(colorsFormatted[i])) {
                SeleniumInitializer.sleep();
                fieldForSingleColor.sendKeys(Keys.ENTER);
                break;
            }
            else if (i == colorsFormatted.length - 1)
                Assert.fail(color + " color was not found. Try another one!");
        }
    }

    private WebElement getInputFieldForSingleColor() {
        return driver.findElement(By.id("autoCompleteSingleInput"));
    }
}
