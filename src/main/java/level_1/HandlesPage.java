package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Set;

public class HandlesPage {
    private String URL = "http://the-internet.herokuapp.com/windows";
    public IElement hndlLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[text()='Click Here']"), "Click here link");
    public IElement hndLable = AqualityServices.getElementFactory().getLabel(By.xpath("/html/body/div/h3"), "New window label", ElementState.EXISTS_IN_ANY_STATE);

    public String getURL() {
        return URL;
    }

    public static void main(String[] args) {
        //I navigate to main page
        WebDriver driver = AqualityServices.getBrowser().getDriver();
        HandlesPage page = new HandlesPage();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(page.getURL());
        browser.waitForPageToLoad();

        String firstWindow = driver.getWindowHandle();

        //Main page is opened
        browser.waitForPageToLoad();

        //I click for 'Click here' link and  switch to new window
        page.hndlLink.clickAndWait();
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
        Assert.assertTrue(page.hndLable.getText().contains("New Window"),"The page does not contain text");
        Assert.assertTrue(driver.getTitle().contains("New Window"),"The title does not contain text");

        //Switch to tab from 1 step
        driver.switchTo().window(firstWindow);

        //I click for 'Click here' link and  switch to new window
        page.hndlLink.clickAndWait();
        browser.waitForPageToLoad();
        Set<String> currentWindows1 = driver.getWindowHandles();
        String thirdWindow = null;
        for (String window:currentWindows1) {
            if(!window.equals(firstWindow)&&!window.equals(secondWindow)){
                thirdWindow = window;
                break;
            }

        }
        driver.switchTo().window(thirdWindow);


        //New page is opened. Check page for text and title
        browser.waitForPageToLoad();
        Assert.assertTrue(page.hndLable.getText().contains("New Window"),"The page does not contain text");
        Assert.assertTrue(driver.getTitle().contains("New Window"),"The title does not contain text");


        //Closing second, first, third tabs
        driver.switchTo().window(secondWindow);
        driver.close();

        driver.switchTo().window(firstWindow);
        driver.close();

        driver.switchTo().window(thirdWindow);
        driver.close();


    }


}