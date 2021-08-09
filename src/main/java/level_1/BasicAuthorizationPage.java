package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;


public class BasicAuthorizationPage {

    public IElementFactory elementFactory = AqualityServices.getElementFactory();
    public String USERNAME = "admin";
    public String PASSWORD = "admin";
    public String URL = "https://" + USERNAME + ":" + PASSWORD + "@" + "the-internet.herokuapp.com/basic_auth";
    ;
    public IElement label = elementFactory.getLabel(By.xpath("//*[@id=\"content\"]/div/p"), "Test Label");
    public String expectedText = "Congratulations! You must have the proper credentials.";

    public String getLableText(IElement label) {
        return label.getText();
    }

}
