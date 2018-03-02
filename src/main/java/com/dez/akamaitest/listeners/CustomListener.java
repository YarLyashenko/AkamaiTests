package com.dez.akamaitest.listeners;

import com.dez.akamaitest.utils.DriverFactory;
import com.dez.akamaitest.utils.DriverManager;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.dez.akamaitest.utils.DriverManager.getDriver;

public class CustomListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = DriverFactory.createInstance(System.getProperty("browserName", "chrome"));
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            DriverManager.setDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = getDriver();
            if (!testResult.isSuccess()) {
                try {
                    screenshot();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachHTML();
            }
            if (driver != null) {
                driver.close();
            }
        }
    }

    @Attachment(type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.FILE);
        return Files.toByteArray(screenshot);
    }

    @Attachment(type = "text/html")
    private String attachHTML() {
        return getDriver().getPageSource();
    }
}
