import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import level_1.IFrame;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.PropertiesUtil;

public class IFrameTest {
    @Test
    public void iframeTest(){
        WebDriver driver = AqualityServices.getBrowser().getDriver();
        Browser browser = AqualityServices.getBrowser();
        IFrame page = new IFrame("An iFrame containing the TinyMCE WYSIWYG Editor");
        //Open main page
        browser.goTo(PropertiesUtil.getEnvironment("environment.iframe"));

        //Main page is opened
        browser.waitForPageToLoad();

        //Switch to frame. Highlight text and clear, fill the random value
        driver.switchTo().frame("mce_0_ifr");
        page.getTxtFramefield().sendKeys(Keys.CONTROL + "a");
        page.getTxtFramefield().sendKeys(Keys.DELETE);
        String randomValue = RandomStringUtils.randomAlphabetic(8);
        page.getTxtFramefield().sendKeys(randomValue);

        //Text id displayed
        Assert.assertTrue(page.getTxtFramefieldText().contains(randomValue));

        //Highlight text and click 'B' button
        page.getTxtFramefield().sendKeys(Keys.CONTROL + "a");
        driver.switchTo().defaultContent();
        page.getBtnBold().clickAndWait();

        //Text is displayed
        driver.switchTo().frame("mce_0_ifr");
        Assert.assertTrue(page.getTxtBoldButton().state().isDisplayed(), "Text must be highlighted as bold");
    }
}
