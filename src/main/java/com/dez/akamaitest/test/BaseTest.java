package com.dez.akamaitest.test;

import com.dez.akamaitest.listeners.ConsoleLoggerListener;
import com.dez.akamaitest.listeners.CustomListener;
import com.dez.akamaitest.pages.SearchJobPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import static com.dez.akamaitest.utils.DriverManager.getDriver;
import static org.assertj.core.api.Java6Assertions.assertThat;

@Listeners({CustomListener.class, ConsoleLoggerListener.class})
abstract class BaseTest {

    private static final String SEARCH_JOB_URL = "http://akamaijobs.referrals.selectminds.com/";

    @Step("Open 'Search Job' page")
    SearchJobPage openSearchJobPage() {
        getDriver().navigate().to(SEARCH_JOB_URL);
        assertThat(getDriver().getTitle()).containsIgnoringCase("Akamai Careers - Jobs");
        return PageFactory.initElements(getDriver(), SearchJobPage.class);
    }
}
