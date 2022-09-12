package com.example.stepdefinitions.widgets;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Slider {

    public static final int INITIAL_VALUE = 25;
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private int inputValue;

    @Given("user is on slider page")
    public void navigateToSliderPage() {
        String sliderURL = "https://demoqa.com/slider";
        driver.get(sliderURL);
    }

    @When("he enters a value, for example {}")
    public void initializeField(String number) {
        validateNumber(number);
        int n = Integer.parseInt(number);
        validateNumber(n);
        inputValue = n;
    }

    @Then("the slider should move left or right until that value is reached")
    public void moveSlider() {
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        WebElement sliderValue = driver.findElement(By.id("sliderValue"));
        if (inputValue > INITIAL_VALUE)
            while (!sliderValue.getAttribute("value").equals(inputValue + ""))
                slider.sendKeys(Keys.ARROW_RIGHT);
        else if (inputValue < INITIAL_VALUE)
            while (!sliderValue.getAttribute("value").equals(inputValue + ""))
                slider.sendKeys(Keys.ARROW_LEFT);
        SeleniumInitializer.sleep();
        driver.quit();
    }

    private void validateNumber(String input) {
        if (!input.matches("-?\\d+"))
            throw new IllegalArgumentException(input + " contains illegal characters!");
    }

    private void validateNumber(int n) {
        if (n < 0 || n > 100)
            throw new IllegalArgumentException("The number must be between 0 and 100, inclusively.");
    }
}
