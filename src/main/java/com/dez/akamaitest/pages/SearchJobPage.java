package com.dez.akamaitest.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchJobPage extends BasePage {

    @FindBy(id = "keyword")
    private WebElement searchJobKeywordInput;

    @FindBy(id = "jSearchSubmit")
    private WebElement searchButton;

    @FindBy(id = "location_facet")
    private WebElement locationSelect;

    @FindBy(id = "jLocInputHldr")
    private WebElement locationInput;

    @FindBy(className = "total_results")
    private WebElement totalResults;

    @FindBy(css = "h1.title")
    private WebElement jobSearchTitle;

    private String jobTitle = "";

    public SearchJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Set job title to {0}")
    public SearchJobPage specifyJobTitle(String jobTitle) {
        searchJobKeywordInput.sendKeys(jobTitle);
        this.jobTitle = jobTitle;
        return this;
    }

    @Step("Set location to {0}")
    public SearchJobPage specifyLocation(String location) {
        locationInput.click();
        locationInput.findElement(By.tagName("input")).sendKeys(location);
        locationInput.findElement(By.xpath("//em[text()='Krakow, Poland']")).click();
        return this;
    }

    @Step("Click on Search button")
    public SearchJobPage clickOnSearch() {
        searchButton.click();
        return this;
    }

    @Step("Get job search results count")
    public int getJobSearchResultsCount() {
        waitUntilSearchIsInProgress();
        String result = totalResults.getText();
        return result.equals("") ? 0 : Integer.parseInt(result);
    }

    private void waitUntilSearchIsInProgress() {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div[data-keyword-string='" + jobTitle + "'][style='display: block;']")));
    }
}