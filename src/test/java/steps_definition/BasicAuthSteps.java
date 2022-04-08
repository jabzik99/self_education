package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.BasicAuthorizationPage;
import org.testng.Assert;
import utilities.ModifyingUrlUtil;
import utilities.PropertiesUtil;

import javax.inject.Inject;

import static java.lang.String.format;


public class BasicAuthSteps {
    private final Browser browser;
    private final BasicAuthorizationPage page;

    @Inject
    BasicAuthSteps() {
        browser = AqualityServices.getBrowser();
        page = new BasicAuthorizationPage();
    }

    @When("I navigate to the Basic Authorization page")
    public void openSite() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.basic_auth"));
    }

    @When("I navigate to the site by modifying URL address")
    public void openMainPageByModifyingURL() {
        browser.goTo(ModifyingUrlUtil.getBasicAuthModifyingURL());
    }

    @Then("Basic Authorization page is opened")
    public void mainPageIsOpened() {
        Assert.assertTrue(page.state().waitForDisplayed(), "Basic Authorization page isn't opened");
    }


    @Then("{string} message is displayed on the page")
    public void checkTextOnThePage1(String value) {
        boolean actualString = page.getLableText().contains(value);
        Assert.assertTrue(actualString, format("Page must contains %s label", value));
    }

}
