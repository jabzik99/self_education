package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static java.lang.String.format;


public class BasicAuthorizationPage extends Form {

    private IElementFactory elementFactory = AqualityServices.getElementFactory();

    private IElement label = elementFactory.getLabel(By.xpath("//*[@id='content']/div/p"), "Test Label");

    protected BasicAuthorizationPage(By locator, String name) {
        super(locator, name);
    }

    public BasicAuthorizationPage(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public String getLableText() {
        return label.getText();
    }


}
