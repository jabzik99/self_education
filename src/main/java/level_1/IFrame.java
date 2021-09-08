package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.PropertiesUtil;

import static java.lang.String.format;

public class IFrame extends Form {


    private IElement btnBold = AqualityServices.getElementFactory().getButton(By.xpath("//button[@aria-label='Bold']/child::span"), "Bold button");
    private IElement txtFramefield = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p"), "Frame input field");
    private IElement txtBoldText = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p/child::strong"), "Bold text");

    protected IFrame(By locator, String name) {
        super(locator, name);
    }

    public IFrame(String name){
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }


    public static void main(String[] args) {
        WebDriver driver = AqualityServices.getBrowser().getDriver();
        Browser browser = AqualityServices.getBrowser();
        IFrame page = new IFrame("An iFrame containing the TinyMCE WYSIWYG Editor");
        //Open main page
        browser.goTo(PropertiesUtil.getEnvironment("environment.iframe"));

        //Main page is opened
        browser.waitForPageToLoad();

        //Switch to frame. Highlight text and clear, fill the random value
        driver.switchTo().frame("mce_0_ifr");
        page.txtFramefield.sendKeys(Keys.CONTROL + "a");
        page.txtFramefield.sendKeys(Keys.DELETE);
        String randomValue = RandomStringUtils.randomAlphabetic(8);
        page.txtFramefield.sendKeys(randomValue);

        //Text id displayed
        Assert.assertTrue(page.txtFramefield.getText().contains(randomValue));

        //Highlight text and click 'B' button
        page.txtFramefield.sendKeys(Keys.CONTROL + "a");
        driver.switchTo().defaultContent();
        page.btnBold.clickAndWait();

        //Text is displayed
        driver.switchTo().frame("mce_0_ifr");
        Assert.assertTrue(page.txtBoldText.state().isDisplayed(), "Text must be highlighted as bold");
    }
}
