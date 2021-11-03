package vk;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class VkLoginPage extends Form {

    private final IButton signInBtn = getElementFactory().getButton(By.xpath("//button[contains(@class, 'signInButton')]"), "Sign in button");
    private final IButton continueBtn = getElementFactory().getButton(By.xpath("//button[.='Continue']"), "Continue button");
    private final ITextBox phoneOrEmailTxt = getElementFactory().getTextBox(By.xpath("//input[@name='login']"), "Email or phone");
    private final ITextBox passwordTxt = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "Password field");

    public VkLoginPage() {
        super(By.xpath("//*[@id='page_body']/*[@id='wrap3']"), "Vk Login page");
    }

    public void loginAsDefaultUser(String user, String password) {
        signInBtn.click();
        phoneOrEmailTxt.state().waitForDisplayed();
        phoneOrEmailTxt.clearAndType(user);
        continueBtn.click();
        passwordTxt.clearAndType(password);
        continueBtn.click();
        passwordTxt.sendKeys(Keys.ENTER);
    }
}
