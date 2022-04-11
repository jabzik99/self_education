package cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import cucumber.objectfactory.CustomObjectFactory;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/cucumber/features"},
        glue = {
                "cucumber.hooks",
                "cucumber.transformations",
                "cucumber.steps"
        },
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "json:target/cucumber-reports/cucumber.json"},
        tags = "@actions",
        strict = true,
        objectFactory = CustomObjectFactory.class
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}