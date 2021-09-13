package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.HorizontalSliderPage;
import org.testng.Assert;
import utilities.PropertiesUtil;

public class HorizontalSliderSteps {
    private Browser browser = AqualityServices.getBrowser();
    private HorizontalSliderPage page = new HorizontalSliderPage("Horizontal Slider");

    @When("I navigate to Horizontal Slider page")
    public void navigateToPage() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.horizontal_slider"));
        browser.waitForPageToLoad();
    }

    @Then("Horizontal Slider page is opened")
    public void PageIsOpened() {
        AqualityServices.getBrowser().waitForPageToLoad();
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
