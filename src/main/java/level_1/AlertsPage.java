package level_1;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static java.lang.String.format;

public class AlertsPage {

    private static final String URL = "http://the-internet.herokuapp.com/javascript_alerts";
    public IElement lblResult = getElementFactory().getLabel(By.xpath("//p[@id=\"result\"]"), "Result label");
    public String getURL() {
        return AlertsPage.URL;
    }

    public String getButtonText (AlertButton button){
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



        public ElementType getElementType() {
            return ElementType.BUTTON;
        }

     }


    }