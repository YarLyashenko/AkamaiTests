package com.dez.akamaitest.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    
    @Step("Get page source")
    public String getPageSource() {
        return driver.getPageSource();
    }
}
