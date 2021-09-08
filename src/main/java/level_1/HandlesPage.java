package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class HandlesPage extends Form {
    private IElement hndlLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[text()='Click Here']"), "Click here link");
    private IElement hndLable = AqualityServices.getElementFactory().getLabel(By.xpath("//h3[contains(text(), 'New Window')]"), "New window label", ElementState.EXISTS_IN_ANY_STATE);

    protected HandlesPage(By locator, String name) {
        super(locator, name);
    }

    public HandlesPage(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public String getHndLableText() {
        return hndLable.getText();
    }

    public IElement getHndlLink() {
        return hndlLink;
    }
}