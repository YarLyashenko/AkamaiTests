package com.dez.akamaitest;

import com.dez.akamaitest.listeners.CustomListener;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Listeners;

@CucumberOptions(plugin = "json:target/cucumber-report.json",
        features = "src/main/resources/features/")
@Listeners({CustomListener.class})
public class RunCukesTest extends AbstractTestNGCucumberTests {
}