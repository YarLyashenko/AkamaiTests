package com.dez.akamaitest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    
    public String getPageSource() {
        return driver.getPageSource();
    }
}
