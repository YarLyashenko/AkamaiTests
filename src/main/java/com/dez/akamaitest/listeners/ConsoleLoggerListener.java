package com.dez.akamaitest.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ConsoleLoggerListener implements ITestListener {
    private final Logger LOGGER = LoggerFactory.getLogger("TEST");

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("START TEST " + result.getName() + ":");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info(result.getName() + ": SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error(result.getName() + ": FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info(result.getName() + ": SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOGGER.info(result.getName() + ": FAILED WITHIN SUCCESS PERCENTAGE");
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("===============================================");
        LOGGER.info("SUITE " + context.getName() + ":");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n");
        LOGGER.info("===============================================");
        LOGGER.info("SUITE " + context.getName() + " FINISHED");
        LOGGER.info("===============================================");
        LOGGER.info("SUMMARY:");
        LOGGER.info("Started at: " + context.getStartDate().toString());
        LOGGER.info("Finished at: " + context.getEndDate().toString());
        LOGGER.info("Total tests run: " + context.getAllTestMethods().length);
        LOGGER.info("Tests failed: " + context.getFailedTests().size());
        LOGGER.info("Tests skipped: " + context.getSkippedTests().size() +"\n");
    }
}

