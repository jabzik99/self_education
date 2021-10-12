package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HandlesPage extends Form {
    private final IElement hndlLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[text()='Click Here']"), "Click here link");
    private final IElement hndLable = AqualityServices.getElementFactory().getLabel(By.xpath("//h3[contains(text(),'New Window')]"), "New window label", ElementState.EXISTS_IN_ANY_STATE);

    public HandlesPage() {
        super(By.xpath("//h3[contains(text(),'Opening a new window')]"), "HandlesPage");
    }

    public String getHndLableText() {
        return hndLable.getText();
    }

    public void clickOnHndlLink() {
        hndlLink.clickAndWait();
    }
}