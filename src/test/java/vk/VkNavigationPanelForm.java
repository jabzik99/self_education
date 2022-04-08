package vk;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkNavigationPanelForm extends Form {

    private final ILink myPageLnk = getElementFactory().getLink(By.xpath("//a/span[contains(text(), 'Моя страница') or contains(text(), 'My Page')]"), "My Page");

    public VkNavigationPanelForm() {
        super(By.xpath("//*[@id='side_bar']"), "Vk Navigation panel form");
    }

    public void openMyPageLink() {
        myPageLnk.state().waitForDisplayed();
        myPageLnk.clickAndWait();
    }
}
