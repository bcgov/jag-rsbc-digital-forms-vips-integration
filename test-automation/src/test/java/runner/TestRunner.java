package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
        features = "src/test/java/features",
        glue = "stepdefs",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"},
        tags = "not @setup"
        )

public class TestRunner extends AbstractTestNGCucumberTests
{
}
