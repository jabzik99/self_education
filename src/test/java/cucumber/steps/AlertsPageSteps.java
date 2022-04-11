package cucumber.steps;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.AlertsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.PropertiesUtil;
import utilities.ScenarioContext;

import javax.inject.Inject;

import static aquality.selenium.browser.AqualityServices.getConditionalWait;
import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static level_1.AlertsPage.AlertButton.ALLERT_JS_BUTTON;
import static level_1.AlertsPage.AlertButton.CONFIRM_JS_BUTTON;


public class AlertsPageSteps {

    private final Browser browser;
    private final ScenarioContext scenarioContext;
    private final AlertsPage alertsPage;

    @Inject
    public AlertsPageSteps(ScenarioContext scenarioContext) {
        browser = AqualityServices.getBrowser();
        alertsPage = new AlertsPage();
        this.scenarioContext = scenarioContext;
    }

    @When("I navigate to the JavaScript Alerts page")
    public void openSite() {
        browser.goTo(PropertiesUtil.getEnvironment("environment.javascript_alerts"));
        browser.waitForPageToLoad();
    }

    @Then("Alerts page is opened")
    public void mainPageIsOpened() {
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    @When("I click {string} button")
    public void clickAlertButton(String value) {
        if (value.equals(AlertsPage.getButtonText(ALLERT_JS_BUTTON))) {
            getElementFactory().getButton(ALLERT_JS_BUTTON.getLocator(), value).click();
        } else if (value.equals(AlertsPage.getButtonText(CONFIRM_JS_BUTTON))) {
            getElementFactory().getButton(AlertsPage.AlertButton.CONFIRM_JS_BUTTON.getLocator(), value).click();
        } else {
            getElementFactory().getButton(AlertsPage.AlertButton.PROMT_JS_BUTTON.getLocator(), value).click();
        }
    }

    @When("I click {string} button with JS functions")
    public void clickAlertButtonWithJSFunctions(String value) {
        if (value.equals(AlertsPage.getButtonText(ALLERT_JS_BUTTON))) {
            getElementFactory().getButton(ALLERT_JS_BUTTON.getLocator(), value).getJsActions().click();
        } else if (value.equals(AlertsPage.getButtonText(CONFIRM_JS_BUTTON))) {
            getElementFactory().getButton(AlertsPage.AlertButton.CONFIRM_JS_BUTTON.getLocator(), value).getJsActions().click();
        } else {
            getElementFactory().getButton(AlertsPage.AlertButton.PROMT_JS_BUTTON.getLocator(), value).getJsActions().click();
        }
    }

    @When("I accept alert")
    public void acceptAlert() {
        browser.handleAlert(AlertActions.ACCEPT);
    }

    @When("I handle promt alert with random value and save it as {string}")
    public void acceptPromntAlertWithRandomValue(String key) {
        String randomValue = RandomStringUtils.randomAlphabetic(8);
        browser.handlePromptAlert(AlertActions.ACCEPT, randomValue);
        scenarioContext.add(key, randomValue);
    }

    @Then("Alert with {string} text is displayed")
    public void checkAlertText(String value) {
        Alert alert = getConditionalWait().waitFor(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(value), String.format("Alert must contains %s value", value));
    }

    @Then("{string} message is displayed in 'Result' section")
    public void checkTextInPage(String value) {
        Assert.assertTrue(
                value.contains(alertsPage.getLabelText()),
                String.format("Result  section must contains %s value", value)
        );
    }

    @Then("{string} message with {string} is displayed in 'Result' section")
    public void checkTextInPageWithRandomValue(String value, String storeValue) {
        String expectedString = value + " " + scenarioContext.get(storeValue);
        Assert.assertTrue(alertsPage.getLabelText().contains(expectedString), String.format("Result section must contains %s value", expectedString));
    }
}
