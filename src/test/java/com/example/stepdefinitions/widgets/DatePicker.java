package com.example.stepdefinitions.widgets;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DatePicker {

    public static final String[] months = {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
    };
    public static final String datePickerPageURL = "https://demoqa.com/date-picker";
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private int day;
    private int monthNumber;
    private int year;
    private int hour;
    private int minute;

    @Given("user is on date picker page")
    public void navigateToDatePickerPage() {
        driver.get(datePickerPageURL);
    }

    @When("^he selects a date, for example '(\\d+\\.\\d+\\.\\d+)'$")
    public void selectDate(String date) {
        String[] separatedDate = date.split("\\.");
        day = Integer.parseInt(separatedDate[0]);
        monthNumber = Integer.parseInt(separatedDate[1]);
        year = Integer.parseInt(separatedDate[2]);

        clickOnDateInput();
        String month = months[monthNumber - 1];
        selectMonthForDateOnly(month);
        selectYearForDateOnly(year);
        selectDay(month, day);
    }


    @Then("it should be selected")
    public void verifyDateSelected() {
        String date = driver.findElement(By.id("datePickerMonthYearInput")).getAttribute("value");
        Assert.assertTrue(date.contains(day + "") && date.contains(year + "") && date.contains(monthNumber + ""));
        driver.quit();
    }

    private void clickOnDateInput() {
        driver
                .findElement(By.id("datePickerMonthYearInput"))
                .click();
    }

    private void selectMonthForDateOnly(String month) {
        driver
                .findElement(By.xpath("//select[contains(@class, 'month')]/option[text() = '" + month + "']"))
                .click();
    }

    private void selectYearForDateOnly(int year) {
        driver
                .findElement(By.xpath("//select[contains(@class, 'year')]/option[@value='" + year + "']"))
                .click();
    }

    private void selectDay(String month, int day) {
        driver
                .findElement(By.xpath("//div[contains(@aria-label, '" + month + "') and text()='" + day + "']"))
                .click();
        SeleniumInitializer.sleep();
    }

    @When("^he selects a date and a time, for example '(\\d+/\\d+/\\d+)' and '(\\d{2}:\\d{2})'$")
    public void selectDateTime(String date, String time) {
        String[] separatedDate = date.split("/");
        day = Integer.parseInt(separatedDate[0]);
        monthNumber = Integer.parseInt(separatedDate[1]);
        year = Integer.parseInt(separatedDate[2]);

        String[] separatedTime = time.split(":");
        hour = Integer.parseInt(separatedTime[0]);
        if (hour < 0 || hour >= 24) throw new IllegalArgumentException("Pick an hour from the interval: [0, 23]");
        minute = Integer.parseInt(separatedTime[1]);
        if (minute % 15 != 0 || minute >= 60)
            throw new IllegalArgumentException("The minutes should be from this interval: [0, 15, 30, 45]");

        clickOnDateTimeInput();

        String month = months[monthNumber - 1];
        selectMonthForDateTime(month);
        selectYearForDateTime(year);
        selectDay(month, day);
        selectTime(hour, minute);
    }


    @Then("these should be selected")
    public void verifyResult() {
        String dateAndTime = driver.findElement(By.id("dateAndTimePickerInput")).getAttribute("value");
        Assert.assertTrue(
                dateAndTime.contains(months[monthNumber - 1])
                && dateAndTime.contains(day + "")
                && dateAndTime.contains(year + "")
                && dateAndTime.contains(hour + ":" + minute)
        );
        driver.quit();
    }

    private void clickOnDateTimeInput() {
        driver
                .findElement(By.id("dateAndTimePickerInput"))
                .click();
    }

    private void selectMonthForDateTime(String month) {
        driver.findElement(By.className("react-datepicker__month-read-view--down-arrow")).click();
        driver
                .findElement(By.xpath("//div[contains(@class,'react-datepicker__month-option') and text()='" + month + "']"))
                .click();
    }

    private void selectYearForDateTime(int year) {
        driver
                .findElement(By.xpath("//*[@class='react-datepicker__year-read-view']"))
                .click();
        WebElement previousYearsArrow = driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--years react-datepicker__navigation--years-previous']"));
        WebElement nextYearsArrow = driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--years react-datepicker__navigation--years-upcoming']"));

        final int yearUpperLimit = 2027;
        final int yearLowerLimit = 2017;

        if (year < yearLowerLimit) {
            for (int i = 0; yearLowerLimit - i != year; i++)
                previousYearsArrow.click();
            clickOnYear(year);
        } else if (year > yearUpperLimit) {
            for (int i = 0; yearUpperLimit + i != year; i++)
                nextYearsArrow.click();
            clickOnYear(year);
        } else clickOnYear(year);
    }

    private void clickOnYear(int year) {
        driver
                .findElement(By.xpath("//div[@class='react-datepicker__year-option' and text()='" + year + "']"))
                .click();
        SeleniumInitializer.sleep();
    }

    private void selectTime(int hour, int minute) {
        driver
                .findElement(By.xpath("//li[@class='react-datepicker__time-list-item ' and contains(text(),'" + hour + ":" + minute + "')]"))
                .click();
    }
}
