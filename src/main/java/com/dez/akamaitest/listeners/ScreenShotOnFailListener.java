package com.dez.akamaitest.listeners;

import com.dez.akamaitest.test.BaseTest;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ScreenShotOnFailListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            screenshot(iTestResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachHTML(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(type = "image/png")
    private byte[] screenshot(ITestResult iTestResult) throws IOException {
        File screenshot = ((TakesScreenshot) ((BaseTest) iTestResult.getInstance()).getDriver())
                .getScreenshotAs(OutputType.FILE);
        return Files.toByteArray(screenshot);
    }

    @Attachment(type = "text/html")
    private String attachHTML(ITestResult iTestResult) {
        return ((BaseTest) iTestResult.getInstance()).getDriver()
                .getPageSource();
    }
}