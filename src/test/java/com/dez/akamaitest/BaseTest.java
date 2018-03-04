package com.dez.akamaitest;

import com.dez.akamaitest.extensions.DriverInitExtension;
import com.dez.akamaitest.extensions.OnTestFailureExtension;
import com.dez.akamaitest.pages.SearchJobPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.dez.akamaitest.utils.DriverManager.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DriverInitExtension.class)
@ExtendWith(OnTestFailureExtension.class)
abstract class BaseTest {

    private static final String SEARCH_JOB_URL = "http://akamaijobs.referrals.selectminds.com/";

    @Step("Open 'Search Job' page")
    SearchJobPage openSearchJobPage() {
        getDriver().navigate().to(SEARCH_JOB_URL);
        assertThat(getDriver().getTitle()).containsIgnoringCase("Akamai Careers - Jobs");
        return PageFactory.initElements(getDriver(), SearchJobPage.class);
    }
}
