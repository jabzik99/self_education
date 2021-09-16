package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class BasicAuthorizationPage extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IElement label = elementFactory.getLabel(By.xpath("//*[@id='content']/div/p"), "Test Label");

    public BasicAuthorizationPage() {
        super(By.xpath("//h3[contains(text(),'Basic Auth')]"), "BasicAuthorization Page");
    }

    public String getLableText() {
        return label.getText();
    }

}
