package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class IFrame extends Form {


    private IElement btnBold = AqualityServices.getElementFactory().getButton(By.xpath("//button[@title='Bold']//span"), "Bold button", ElementState.EXISTS_IN_ANY_STATE);
    private IElement txtFramefield = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p"), "Frame input field");
    private IElement txtBoldText = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p/child::strong"), "Bold text");

    protected IFrame(By locator, String name) {
        super(locator, name);
    }

    public IFrame(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public IElement getBtnBold() {
        return btnBold;
    }

    public IElement getTxtBoldButton() {
        return txtBoldText;
    }

    public String getTxtFramefieldText() {
        return txtFramefield.getText();
    }

    public IElement getTxtFramefield() {
        return txtFramefield;
    }
}
