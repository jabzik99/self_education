package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElementFactory;
import com.sun.jdi.Field;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static java.lang.String.format;

public class AlertsPage {

    private static final String URL = "http://the-internet.herokuapp.com/javascript_alerts";

    public String getURL() {
        return AlertsPage.URL;
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
//
//    @AllArgsConstructor
//    public enum AlertButton  {
//        BLOCKING_ALERT("red iv-alert icon"),
//        DELETE("fa-trash-alt");
//
//        private static final String ICON_LOCATOR = "//*[contains(@class,'%s')]";
//        private final String locatorPart;
//
//        @Override
//        public By getLocator() {
//            return By.xpath(format(ICON_LOCATOR, locatorPart));
//        }
//
//        @Override
//        public ElementType getElementType() {
//            return ElementType.BUTTON;
//        }
//
//        public static AlertButton parse(String input) {
//            return AlertButton.parse(input, values());
//        }
//    }

}
