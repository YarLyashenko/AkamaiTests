package com.dez.akamaitest.test;

import com.dez.akamaitest.pages.SearchJobPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Search for a job feature")
public class SearchJobTest extends BaseTest{
    @Test
    @Story("Unlogged customer is able to search for a job")
    public void unloggedCustomerIsAbleToSearchForAJob() {
        //Given
        SearchJobPage searchJobPage = openSearchJobPage();
        //When
        searchJobPage
                .specifyJobTitle("Test")
                .specifyLocation("Krakow, Poland")
                .clickOnSearch();
        //Then
        int jobSearchResultsCount = searchJobPage
                .getJobSearchResultsCount();
        assertThat(jobSearchResultsCount).isGreaterThan(0)
                .overridingErrorMessage("No job offers were found.");

    }

    @Test
    @Story("Customer is notified when no offers match given criteria")
    public void customerIsNotifiedWhenNoOffersMatchGivenCriteria() {
        //Given
        SearchJobPage searchJobPage = openSearchJobPage();
        //When
        searchJobPage
                .specifyJobTitle("XXX")
                .clickOnSearch();
        //Then
        int jobSearchResultsCount = searchJobPage
                .getJobSearchResultsCount();
        assertThat(jobSearchResultsCount).isEqualTo(0)
                .overridingErrorMessage("Some of job offers much to the search criteria.");
        assertThat(searchJobPage.getPageSource()).contains("did not return any job results.")
                .overridingErrorMessage("There is no notification about no offers found.");

    }
}
