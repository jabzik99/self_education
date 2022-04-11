package cucumber.steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.HoversPage;
import org.testng.Assert;
import utilities.PropertiesUtil;

import javax.inject.Inject;
import java.util.Locale;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class HoversPageSteps {
    private final Browser browser;
    private final HoversPage page;

    @Inject
    HoversPageSteps() {
        browser = AqualityServices.getBrowser();
        page = new HoversPage();
    }

    @When("I navigate to Hovers page")
    public void navigateToPage() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.hovers"));
        browser.waitForPageToLoad();
    }

    @Then("Hovers page is opened")
    public void PageIsOpened() {
        Assert.assertTrue(page.state().waitForDisplayed(), "Hovers page isn't opened");
    }

    @When("I hover the pointer for '{users}' user")
    public void hoverImg(HoversPage.Users user) {
        getElementFactory().getLabel(user.getLocatorForHoverImg(), user.name()).getMouseActions().moveMouseToElement();
    }

    @Then("User name for '{users}' user is displayed")
    public void checkUserName(HoversPage.Users user) {
        Assert.assertTrue(user.getLocatorForUserName().toString().contains(user.toString().toLowerCase(Locale.ROOT)), String.format("User name must contain %s", user.toString().toLowerCase(Locale.ROOT)));
    }

    @Then("Profile link for '{users}' user is displayed")
    public void checkProfileLink(HoversPage.Users user) {
        Assert.assertTrue(getElementFactory().getLink(user.getLocatorForProfileLink(), "User link ").state().isExist(), "Link is not displayed");
    }

    @When("I click profile link for '{users}'")
    public void clickProfileLink(HoversPage.Users user) {
        getElementFactory().getLabel(user.getLocatorForProfileLink(), user.getDisplayedName()).click();
    }

    @Then("Page for '{users}' is opened")
    public void checkPageOpening(HoversPage.Users user) {
        Integer userOrdinal = user.ordinal() + 1;
        Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains(userOrdinal.toString()), String.format("Page for %d user is not opened", userOrdinal));
    }

    @When("I navigate to previous page")
    public void browserGoBack() {
        AqualityServices.getBrowser().goBack();
    }

    @Then("Page with users is displayed")
    public void checkPagewithUsers() {
        Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains(PropertiesUtil.getEnvironment("environment.hovers")), "Page is not opened");
    }
}
