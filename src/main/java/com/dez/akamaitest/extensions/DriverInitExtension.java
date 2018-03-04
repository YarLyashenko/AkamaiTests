package com.dez.akamaitest.extensions;

import com.dez.akamaitest.utils.DriverFactory;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.dez.akamaitest.utils.DriverManager.getDriver;
import static com.dez.akamaitest.utils.DriverManager.setDriver;

public class DriverInitExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (getDriver() != null) {
            getDriver().close();
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        WebDriver driver = DriverFactory.createInstance(System.getProperty("browserName", "chrome"));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setDriver(driver);
    }
}
