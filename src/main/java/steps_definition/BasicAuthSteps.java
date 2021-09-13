package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.BasicAuthorizationPage;
import org.testng.Assert;
import utilities.ModifyingUrlUtil;
import utilities.PropertiesUtil;

import static java.lang.String.format;


public class BasicAuthSteps {
    private Browser browser = AqualityServices.getBrowser();
    private BasicAuthorizationPage page = new BasicAuthorizationPage("Basic Auth");

    @When("I navigate to the Basic Authorization page")
    public void openSite() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.basic_auth"));
        browser.waitForPageToLoad();
    }

    @When("I navigate to the site by modifying URL address")
    public void openMainPageByModifyingURL() {
        browser.goTo(ModifyingUrlUtil.getBasicAuthModifyingURL());
        browser.waitForPageToLoad();
    }

    @Then("Basic Authorization page is opened")
    public void mainPageIsOpened() {
        AqualityServices.getBrowser().waitForPageToLoad();
    }


    @Then("{string} message is displayed on the page")
    public void checkTextOnThePage1(String value) {
        boolean actualString = page.getLableText().contains(value);
        Assert.assertTrue(actualString, format("Page must contains %s label", value));
    }

}
