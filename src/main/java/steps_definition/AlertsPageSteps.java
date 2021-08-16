package steps_definition;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.AlertsPage;
import org.openqa.selenium.Alert;
import org.testng.Assert;

import static aquality.selenium.browser.AqualityServices.getElementFactory;


public class AlertsPageSteps {
    Browser browser = AqualityServices.getBrowser();
    AlertsPage alertsPage = new AlertsPage();
    Alert alert = new Alert() {
        @Override
        public void dismiss() {

        }

        @Override
        public void accept() {

        }

        @Override
        public String getText() {
            return alert.getText();
        }

        @Override
        public void sendKeys(String keysToSend) {

        }
    };
    @When("I navigate to the main page")
    public void openSite() {
        browser.goTo(alertsPage.getURL());
        browser.waitForPageToLoad();
    }
    @Then("Alerts page is opened")
    public void mainPageIsOpened(){
       AqualityServices.getBrowser().waitForPageToLoad();
    }

    @When("I click 'Click for JS Alert' alert button")
    public void clickButton(String value){
        getElementFactory().getButton(AlertsPage.AlertButton.ALLERT_JS_BUTTON.getLocator(),value);
    }

    @When("I click 'OK' button")
    public void acceptAlert(){
        browser.handleAlert(AlertActions.ACCEPT);
    }

    @Then("Alert with 'I am a JS Alert' text is displayed")
    public void checkAlertText(String value){
        Assert.assertTrue(alert.getText().contains(value),String.format("Alert must contains %s value", value));
    }
}
