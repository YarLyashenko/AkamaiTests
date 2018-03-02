package com.dez.akamaitest.listeners;

import com.dez.akamaitest.utils.DriverFactory;
import com.dez.akamaitest.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

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
            if (driver != null) {
                driver.close();
            }
        }
    }
}
