package com.example.stepdefinitions;

import com.example.helpers.SeleniumInitializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebTable {

    private static final byte numberOfColumns = 6;
    private final WebDriver driver = SeleniumInitializer.getDriver();
    private final List<String> propertiesOfEmployee = new ArrayList<>(numberOfColumns);
    private final List<String> columnValues = new ArrayList<>(5);

    @Given("user in on webtables page")
    public void navigateToWebtablesPage() {
        final String webTablesUrl = "https://demoqa.com/webtables";
        driver.get(webTablesUrl);
    }

    @When("he clicks on Add button")
    public void clickOnAddButton() {
        driver
                .findElement(By.xpath("//button[@id='addNewRecordButton']"))
                .click();
    }

    @And("inserts data - {} {} {} {} {} {} - into the fields")
    public void insertDataIntoFields(String firstName, String lastName, String age, String email, String salary, String department) {
        insertDataIntoFirstNameAndLastNameFields(firstName, lastName);
        insertDataIntoAgeAndEmailFields(age, email);
        insertDataIntoSalaryAndDepartmentFields(salary, department);
        propertiesOfEmployee.addAll(Arrays.asList(firstName, lastName, age, email, salary, department));
    }

    @And("presses the Submit button from webtables page")
    public void pressSubmitButton() {
        SeleniumInitializer.sleep();
        driver
                .findElement(By.xpath("//button[@id='submit']"))
                .click();
    }

    @Then("a new row should be added to the table")
    public void verifyIfANewRowWasAdded() {
        List<WebElement> cells = driver.findElements(By.xpath("//div[@class='rt-td']"));

        Assert.assertEquals(getCount(cells), numberOfColumns);
    }

    @When("^he enters a column number: ([1-6]{1})$")
    public void getColumnValues(String number) {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        int castedNumber = Integer.parseInt(number) - 1;

        for (WebElement row : rows) {
            String rowValues = row.getText();
            if (rowValues.isBlank())
                break;

            String columnValue = rowValues.split("\n")[castedNumber];
            columnValues.add(columnValue);
        }
    }

    private void insertDataIntoFirstNameAndLastNameFields(String firstName, String lastName) {
        driver
                .findElement(By.xpath("//input[@placeholder='First Name']"))
                .sendKeys(firstName);
        driver
                .findElement(By.xpath("//input[@placeholder='Last Name']"))
                .sendKeys(lastName);
    }

    private void insertDataIntoAgeAndEmailFields(String age, String email) {
        driver
                .findElement(By.xpath("//input[@id='age']"))
                .sendKeys(age);
        driver
                .findElement(By.xpath("//input[@id='userEmail']"))
                .sendKeys(email);
    }

    private void insertDataIntoSalaryAndDepartmentFields(String salary, String department) {
        driver
                .findElement(By.xpath("//input[@placeholder='Salary']"))
                .sendKeys(salary);
        driver
                .findElement(By.xpath("//input[@placeholder='Department']"))
                .sendKeys(department);
    }

    private short getCount(List<WebElement> cells) {
        short count = 0;
        for (WebElement cell : cells) {
            String tagValue = cell.getText();
            if (tagValue.equals(propertiesOfEmployee.get(count))) ++count;
            if (count == numberOfColumns) break;
        }
        return count;
    }

    @Then("a new column should be added into the Excel file")
    public void insertIntoExcelFile() {
        final String pathToExcel = "/home/razvanpustea/IdeaProjects/testdemoqawebsite/src/test/excel/output.xlsx";
        try (
                FileInputStream fs = new FileInputStream(pathToExcel);
                Workbook wb = new XSSFWorkbook(fs);
                FileOutputStream fos = new FileOutputStream(pathToExcel)
        ) {
            Sheet sheetName = wb.getSheetAt(0);
            sheetName.autoSizeColumn(0, true);
            for (int i = 0; i < columnValues.size(); i++) {
                Row row = sheetName.createRow(i);
                row.createCell(0).setCellValue(columnValues.get(i));
            }
            wb.write(fos);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}