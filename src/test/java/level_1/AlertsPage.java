package level_1;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class AlertsPage extends Form {

    private final IElement lblResult = getElementFactory().getLabel(By.xpath("//p[@id='result']"), "Result label");

    public AlertsPage() {
        super(By.xpath("//h3[contains(text(),'JavaScript Alerts')]"), "Alerts Page");
    }

    public static String getButtonText(AlertButton button) {
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

    public String getLabelText() {
        return lblResult.getText();
    }
}