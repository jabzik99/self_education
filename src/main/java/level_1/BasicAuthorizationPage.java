package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;


public class BasicAuthorizationPage{

    public IElementFactory elementFactory = AqualityServices.getElementFactory();
    private String USERNAME = "admin";
    private String PASSWORD = "admin";
    private String modURL = "https://" + USERNAME + ":" + PASSWORD + "@" + "the-internet.herokuapp.com/basic_auth";
    private String URL = "http://the-internet.herokuapp.com/basic_auth";

    public IElement label = elementFactory.getLabel(By.xpath("//*[@id=\"content\"]/div/p"), "Test Label");

    public String getLableText(IElement label) {
        return label.getText();
    }
    public String getURL() {
        return URL;
    }
    public String getmodificationURL() {
        return modURL;
    }

}
