package steps_definition;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.HoversPage;
import org.testng.Assert;

import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static level_1.HoversPage.Users.*;

public class HoversPageSteps {
    Browser browser = AqualityServices.getBrowser();
    HoversPage page = new HoversPage();

    @When("I navigate to Hovers page")
    public void navigateToPage() {
        browser.goTo(page.getURL());
        browser.waitForPageToLoad();
    }

    @Then("Hovers page is opened")
    public void PageIsOpened() {
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    @When("I hover the pointer for {string} user")
    public void hoverImg(String value) {
        if (value.equals(page.getUserName(USER1))) {
            getElementFactory().getLabel(USER1.getLocatorForHoverImg(), value).getMouseActions().moveMouseToElement();
        } else if (value.equals(page.getUserName(USER2))) {
            getElementFactory().getLabel(HoversPage.Users.USER2.getLocatorForHoverImg(), value).getMouseActions().moveMouseToElement();
        } else {
            getElementFactory().getLabel(USER3.getLocatorForHoverImg(), value).getMouseActions().moveMouseToElement();
        }
    }

    @Then("User name for {string} user is displayed")
    public void checkUserName(String value) {
        if (value.equals(page.getUserName(USER1))) {
            Assert.assertTrue(USER1.getLocatorForUserName().toString().contains(value), String.format("User name must contain %s", value));
        } else if (value.equals(page.getUserName(USER2))) {
            Assert.assertTrue(HoversPage.Users.USER2.getLocatorForUserName().toString().contains(value), String.format("User name must contain %s", value));
        } else {
            Assert.assertTrue(USER3.getLocatorForUserName().toString().contains(value), String.format("User name must contain %s", value));
        }
    }

    @When("Profile link for {string} user is displayed")
    public void checkProfileLink(String value){
        if (value.equals(page.getUserName(USER1))) {
          Assert.assertTrue(getElementFactory().getLink(USER1.getLocatorForUserName(),"User link 1").state().isDisplayed(), "Link is not displayed");
        } else if (value.equals(page.getUserName(USER2))) {
            Assert.assertTrue(getElementFactory().getLink(USER2.getLocatorForUserName(),"User link 1").state().isDisplayed(), "Link is not displayed");
        } else {
            Assert.assertTrue(getElementFactory().getLink(USER3.getLocatorForUserName(),"User link 1").state().isDisplayed(), "Link is not displayed");
        }
    }

    @When("I click profile link for {string}")
    public void clickProfileLink(String value){
        if (value.equals(page.getUserName(USER1))) {
            getElementFactory().getLabel(USER1.getLocatorForProfileLink(), value).click();
            browser.waitForPageToLoad();
        } else if (value.equals(page.getUserName(USER2))) {
            getElementFactory().getLabel(HoversPage.Users.USER2.getLocatorForProfileLink(), value).click();
            browser.waitForPageToLoad();
        } else {
            getElementFactory().getLabel(USER3.getLocatorForProfileLink(), value).click();
            browser.waitForPageToLoad();
        }
    }

    @Then("Page for {string} is opened")
    public void checkPageOpening(String value){
        if (value.equals(page.getUserName(USER1))) {
            Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains("/users/1"));
        } else if (value.equals(page.getUserName(USER2))) {
            Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains("/users/2"));
        } else {
            Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains("/users/3"));
        }
    }

    @When("I navigate to previous page")
    public void browserGoBack(){
        AqualityServices.getBrowser().goBack();
        browser.waitForPageToLoad();
    }

    @Then("Page with users is displayed")
    public void checkPagewithUsers(){
        Assert.assertTrue(AqualityServices.getBrowser().getCurrentUrl().contains(page.getURL()));
    }
}
