package steps_definition.alertspagesteps;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.AlertsPage;
import static aquality.selenium.browser.AqualityServices.getElementFactory;


public class AlertsPageSteps {
    Browser browser = AqualityServices.getBrowser();

    @When("I navigate to the main page")
    public void openSite() {
        AlertsPage alertsPage = new AlertsPage();
        browser.goTo(alertsPage.getURL());
        browser.waitForPageToLoad();
    }
    @Then("Main page is opened")
    public boolean mainPageIsOpened(){
        AlertsPage alertsPage = new AlertsPage();
        return  alertsPage.checkPageLoading();
    }
    @When("I click '{OtherButton}' alert button")
    public void clickbtn(AlertsPage.AlertButton alertButton){
        getElementFactory().getButton(AlertsPage.AlertButton.parse(alertButton), AlertsPage.AlertButton.parse(alertButton).toString());
    }
    @Then("Alert with next text is displayed")
    public void isAlertPresent(){
        browser.handleAlert(AlertActions.ACCEPT);
    }
}
