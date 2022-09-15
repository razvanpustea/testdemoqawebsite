package com.example.stepdefinitions.interactions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

public class Sortable {

    public static final String SORTABLE_URL = "https://demoqa.com/sortable";
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private List<WebElement> list;
    private List<WebElement> grid;
    private final HashMap<String, Integer> numbers = new HashMap<>();

    @Given("user is on sortable page")
    public void navigateToSortablePage() {
        driver.get(SORTABLE_URL);
        initializeLists();
        populateMap(numbers);
    }

    @When("he drags a block over another one, for example '{}' over '{}' - list")
    public void dragBlockFromList(String firstBlock, String secondBlock) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(list.get(numbers.get(firstBlock) - 1), list.get(numbers.get(secondBlock) - 1)).perform();
    }

    @Then("they should be swapped")
    public void verifySwap() {
        SeleniumInitializer.sleep();
        driver.quit();
    }

    @And("clicks on 'Grid' tab")
    public void clickOnGridTab() {
        driver.findElement(By.id("demo-tab-grid")).click();
    }

    @When("he drags a block over another one, for example '{}' over '{}' - grid")
    public void dragBlockFromGrid(String firstBlock, String secondBlock) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(grid.get(numbers.get(firstBlock) - 1), grid.get(numbers.get(secondBlock) - 1)).perform();
    }

    private void initializeLists() {
        List<WebElement> temp = driver.findElements(By.xpath("//div[@class='list-group-item list-group-item-action']"));
        list = temp.subList(0, 6);
        grid = temp.subList(6, temp.size());
    }

    private void populateMap(HashMap<String, Integer> numbers) {
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
    }
}
