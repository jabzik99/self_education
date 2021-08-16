package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.BasicAuthorizationPage;
import org.testng.Assert;

import static java.lang.String.format;


public class BasicAuthSteps {
    Browser browser = AqualityServices.getBrowser();
    BasicAuthorizationPage page = new BasicAuthorizationPage();

    @When("I navigate to the Basic Authorization page")
    public void openSite() {
        browser.goTo(page.getURL());
        browser.waitForPageToLoad();
    }

    @When("I navigate to the site by modifying URL address")
    public void openMainPageByModifyingURL() {
        browser.goTo(page.getmodificationURL());
        browser.waitForPageToLoad();
    }

    @Then("Basic Authorization page is opened")
    public void mainPageIsOpened() {
        AqualityServices.getBrowser().waitForPageToLoad();
    }


    @Then("{string} message is displayed on the page")
    public void checkTextOnThePage1(String value) {
        boolean actualString = page.getLableText(page.label).contains(value);
        Assert.assertTrue(actualString, format("Page must contains %s label", value));
    }

}
