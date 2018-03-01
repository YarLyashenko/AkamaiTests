package com.dez.akamaitest.test;

import com.dez.akamaitest.listeners.ConsoleLoggerListener;
import com.dez.akamaitest.listeners.ScreenShotOnFailListener;
import com.dez.akamaitest.pages.SearchJobPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Listeners({ScreenShotOnFailListener.class, ConsoleLoggerListener.class})
public abstract class BaseTest {

    private static final String SEARCH_JOB_URL = "http://akamaijobs.referrals.selectminds.com/";
    private final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(ChromeDriver::new);

    @BeforeClass
    @Step("Configure WebDriver before class")
    public void classSetUp() {
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    @Step("Close driver.")
    public void classTearDown() {
        getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @Step("Open 'Search Job' page")
    SearchJobPage openSearchJobPage() {
        getDriver().navigate().to(SEARCH_JOB_URL);
        assertThat(getDriver().getTitle()).containsIgnoringCase("Akamai Careers - Jobs");
        return PageFactory.initElements(getDriver(), SearchJobPage.class);
    }
}