import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import level_1.HandlesPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.PropertiesUtil;

import java.util.Set;

public class HandlesTest {
    @Test
    public void handlesTest() {
        //I navigate to main page
        WebDriver driver = AqualityServices.getBrowser().getDriver();
        HandlesPage page = new HandlesPage("Opening a new window");
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(PropertiesUtil.getEnvironment("environment.windows"));
        browser.waitForPageToLoad();

        String firstWindow = driver.getWindowHandle();

        //Main page is opened
        browser.waitForPageToLoad();

        //I click for 'Click here' link and  switch to new window
        page.getHndlLink().clickAndWait();
        browser.waitForPageToLoad();
        Set<String> currentWindows = driver.getWindowHandles();
        String secondWindow = null;
        for (String window : currentWindows) {
            if (!window.equals(firstWindow)) {
                secondWindow = window;
                break;
            }
        }
        driver.switchTo().window(secondWindow);

        //New page is opened. Check page for text and title
        browser.waitForPageToLoad();
        Assert.assertTrue(page.getHndLableText().contains("New Window"), "The page does not contain text");
        Assert.assertTrue(driver.getTitle().contains("New Window"), "The title does not contain text");

        //Switch to tab from 1 step
        driver.switchTo().window(firstWindow);

        //I click for 'Click here' link and  switch to new window
        page.getHndlLink().clickAndWait();
        browser.waitForPageToLoad();
        Set<String> currentWindows1 = driver.getWindowHandles();
        String thirdWindow = null;
        for (String window : currentWindows1) {
            if (!window.equals(firstWindow) && !window.equals(secondWindow)) {
                thirdWindow = window;
                break;
            }

        }
        driver.switchTo().window(thirdWindow);


        //New page is opened. Check page for text and title
        browser.waitForPageToLoad();
        Assert.assertTrue(page.getHndLableText().contains("New Window"), "The page does not contain text");
        Assert.assertTrue(driver.getTitle().contains("New Window"), "The title does not contain text");


        //Closing second, first, third tabs
        driver.switchTo().window(secondWindow);
        driver.close();

        driver.switchTo().window(firstWindow);
        driver.close();

        driver.switchTo().window(thirdWindow);
        driver.close();

    }
}
