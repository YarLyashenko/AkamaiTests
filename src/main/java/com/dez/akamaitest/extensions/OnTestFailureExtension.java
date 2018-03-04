package com.dez.akamaitest.extensions;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.dez.akamaitest.utils.DriverManager.getDriver;

public class OnTestFailureExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        screenshot();
        attachHTML();
        throw throwable;
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
