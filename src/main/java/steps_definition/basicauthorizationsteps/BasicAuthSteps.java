package steps_definition.basicauthorizationsteps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import level_1.BasicAuthorizationPage;


public class BasicAuthSteps {

    Browser browser = AqualityServices.getBrowser();

    @When("I navigate to the site by modifying URL address")
    public void openSite() {
        BasicAuthorizationPage basicAuthorizationPage = new BasicAuthorizationPage();
        browser.goTo(basicAuthorizationPage.URL);
        browser.waitForPageToLoad();
    }

    @Then("Next message is displayed on the page")
    public void checkTextOnThePage1() {
        BasicAuthorizationPage basicAuthorizationPage = new BasicAuthorizationPage();
        String actualString = basicAuthorizationPage.getLableText(basicAuthorizationPage.label);
        if (actualString.equals(basicAuthorizationPage.expectedText)) {
            System.out.printf("Actual label is equal to expected \nExpected: %s \nActual: %s ", basicAuthorizationPage.expectedText, actualString);
        } else {
            System.out.println("Labels are not equals !");
        }

    }

}
