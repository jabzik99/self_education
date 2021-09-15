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
    public void iframeTest() {
        WebDriver driver = AqualityServices.getBrowser().getDriver();
        Browser browser = AqualityServices.getBrowser();
        IFrame page = new IFrame();
        //Open main page
        browser.goTo(PropertiesUtil.getEnvironment("environment.iframe"));

        //Main page is opened
        Assert.assertTrue(page.state().isDisplayed(), "IFrame page isn't opened");

        //Switch to frame. Highlight text and clear, fill the random value
        driver.switchTo().frame("mce_0_ifr");
        page.typeValueToTxtFrameField(Keys.CONTROL + "a");
        page.sendKeysToTxtFrameField(Keys.DELETE);
        String randomValue = RandomStringUtils.randomAlphabetic(8);
        page.typeValueToTxtFrameField(randomValue);

        //Text id displayed
        Assert.assertTrue(page.getTxtFramefieldText().contains(randomValue),"Text is not displayed in frame");

        //Highlight text and click 'B' button
        page.typeValueToTxtFrameField(Keys.CONTROL + "a");
        driver.switchTo().defaultContent();
        page.clickOnBtnBold();

        //Text is displayed
        driver.switchTo().frame("mce_0_ifr");
        Assert.assertTrue(page.istBoldTextIsDisplayed(), "Text must be highlighted as bold");
    }
}
