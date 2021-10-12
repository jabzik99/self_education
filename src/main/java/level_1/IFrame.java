package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class IFrame extends Form {


    private final IElement btnBold = AqualityServices.getElementFactory().getButton(By.xpath("//button[@title='Bold']//span"), "Bold button", ElementState.EXISTS_IN_ANY_STATE);
    private final IElement txtFramefield = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p"), "Frame input field");
    private final IElement txtBoldText = AqualityServices.getElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/child::p/child::strong"), "Bold text");

    public IFrame() {
        super(By.xpath("//h3[contains(text(),'An iFrame containing the TinyMCE WYSIWYG Editor')]"), "IFrame");
    }

    public void clickOnBtnBold() {
        btnBold.clickAndWait();
    }

    public boolean istBoldTextIsDisplayed() {
        return txtBoldText.state().isDisplayed();
    }

    public String getTxtFramefieldText() {
        return txtFramefield.getText();
    }

    public void typeValueToTxtFrameField(String value){
        txtFramefield.sendKeys(value);
    }

    public void sendKeysToTxtFrameField(Keys keys){
        txtFramefield.sendKeys(keys);
    }
}
