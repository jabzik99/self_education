package cucumber.steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.HorizontalSliderPage;
import org.testng.Assert;
import utilities.PropertiesUtil;

import javax.inject.Inject;

public class HorizontalSliderSteps {
    private final Browser browser;
    private final HorizontalSliderPage page;

    @Inject
    HorizontalSliderSteps() {
        browser = AqualityServices.getBrowser();
        page = new HorizontalSliderPage();
    }

    @When("I navigate to Horizontal Slider page")
    public void navigateToPage() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.horizontal_slider"));
    }

    @Then("Horizontal Slider page is opened")
    public void PageIsOpened() {
        Assert.assertTrue(page.state().isDisplayed(), "Horizontal Slider page isn't opened");
    }

    @When("I set random value for slider except boundary values")
    public void setRandomValue() {
        page.setRandomRangeValue();
    }

    @Then("Valid values is displayed near by slider")
    public void checkRangeValues() {
        Assert.assertTrue(page.isRangeValuesOnBorder(), "Boundary values should not be selected");
    }
}
