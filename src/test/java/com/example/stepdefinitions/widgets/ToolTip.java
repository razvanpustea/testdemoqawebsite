package com.example.stepdefinitions.widgets;

import com.example.helpers.SeleniumInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToolTip {

    public static final String TOOLTIPS_URL = "https://demoqa.com/tool-tips";
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private final Actions action = new Actions(driver);

    public static void main(String[] args) {
        ToolTip toolTip = new ToolTip();
        toolTip.navigateToTooltipsPage();
        toolTip.hoverOverButton();
        toolTip.hoverOverTextInput();
        toolTip.hoverOverWord();
        toolTip.hoverOverSection();
    }

    public void navigateToTooltipsPage() {
        driver.get(TOOLTIPS_URL);
        SeleniumInitializer.sleep();
    }

    public void hoverOverButton() {
        WebElement hoverButton = driver.findElement(By.id("toolTipButton"));
        action.moveToElement(hoverButton).perform();
        displayTextFromToolTip("button");
    }

    public void displayTextFromToolTip(String location) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3L));
        SeleniumInitializer.sleep();
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
        System.out.println(location + ": " + tooltip.getText());
    }

    public void hoverOverTextInput() {
        WebElement textInput = driver.findElement(By.id("toolTipTextField"));
        action.moveToElement(textInput).perform();
        displayTextFromToolTip("text input");
    }

    public void hoverOverWord() {
        WebElement word = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/a[1]"));
        action.moveToElement(word).perform();
        displayTextFromToolTip("word");
    }

    public void hoverOverSection() {
        WebElement section = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/a[2]"));
        action.moveToElement(section).perform();
        displayTextFromToolTip("section");
    }
}
