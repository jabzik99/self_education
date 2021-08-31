package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.HorizontalSliderPage;
import org.testng.Assert;

public class HorizontalSliderSteps {
    Browser browser = AqualityServices.getBrowser();
    HorizontalSliderPage page = new HorizontalSliderPage();

    @When("I navigate to Horizontal Slider page")
    public void navigateToPage() {
        browser.goTo(page.getURL());
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
        Assert.assertTrue(page.checkRangeValue(), "Boundary values should not be selected");
    }
}
