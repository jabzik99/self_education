package level_1;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class AlertsPage extends Form {

    private IElement lblResult = getElementFactory().getLabel(By.xpath("//p[@id='result']"), "Result label");

    protected AlertsPage(By locator, String name) {
        super(locator, name);
    }

    public AlertsPage(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public String getButtonText(AlertButton button) {
        return button.btnName;
    }

    @AllArgsConstructor
    public enum AlertButton {
        ALLERT_JS_BUTTON("Click for JS Alert"),
        CONFIRM_JS_BUTTON("Click for JS Confirm"),
        PROMT_JS_BUTTON("Click for JS Prompt");
        private final String btnName;
        private static final String XPATH_TEMPLATE = "//button[(text()='%s')]";

        public By getLocator() {
            return By.xpath(format(XPATH_TEMPLATE, btnName));
        }

    }

    public String getLabelText(){
        return lblResult.getText();
    }
}