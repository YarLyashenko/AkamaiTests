package com.dez.akamaitest.steps;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.dez.akamaitest.utils.DriverManager.getDriver;

public abstract class BaseSteps {

    protected void attachAll() {
        try {
            screenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachHTML();
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
