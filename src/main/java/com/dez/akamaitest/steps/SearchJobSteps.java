package com.dez.akamaitest.steps;

import com.dez.akamaitest.pages.SearchJobPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

import static com.dez.akamaitest.utils.DriverManager.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchJobSteps extends BaseSteps {
    private SearchJobPage searchJobPage;
    private WebDriver driver;

    @Before
    public void setup() {
        driver = getDriver();
    }

    @Given("^Customer is on '(.*)'$")
    public void openPage(String url) {
        driver.navigate().to(url);
        searchJobPage = PageFactory.initElements(driver, SearchJobPage.class);
    }

    @When("^Customer specifies job title : (.*)$")
    public void setJobTitle(String jobTitle) {
        if (!jobTitle.isEmpty()) searchJobPage.specifyJobTitle(jobTitle);
    }

    @And("^clicks on '(.*)'$")
    public void clicksOnButton(String buttonText) {
        switch (buttonText) {
            case "Search":
                searchJobPage.clickOnSearch();
                break;
            default:
                throw new NoSuchElementException("Please define button in page object class.");
        }
    }

    @Then("^Notification about no offers found is displayed$")
    public void notificationAboutNoOffersFoundIsDisplayed() {
        int jobSearchResultsCount = searchJobPage
                .getJobSearchResultsCount();
        assertThat(jobSearchResultsCount).isEqualTo(0)
                .overridingErrorMessage("Some of job offers much to the search criteria.");
        assertThat(searchJobPage.getPageSource()).contains("did not return any job results.")
                .overridingErrorMessage("There is no notification about no offers found.");
    }

    @And("^Location : (.*)$")
    public void setLocation(String location) {
        if (!location.isEmpty()) searchJobPage.specifyLocation(location);
    }

    @Then("^Any job offers are found$")
    public void anyJobOffersAreFound() {
        int jobSearchResultsCount = searchJobPage
                .getJobSearchResultsCount();
        assertThat(jobSearchResultsCount).isGreaterThan(0)
                .overridingErrorMessage("No job offers were found.");
    }

    @After
    public void attachAllIfScenarioFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            attachAll();
        }
    }
}
