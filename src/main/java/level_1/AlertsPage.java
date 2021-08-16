package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static java.lang.String.format;

public class AlertsPage {

    private static final String URL = "http://the-internet.herokuapp.com/javascript_alerts";
    public IElementFactory elementFactory = getElementFactory();

    public String getURL() {
        return AlertsPage.URL;
    }

    public boolean checkPageLoading() {
        Browser browser = AqualityServices.getBrowser();
        String currentURL = browser.getCurrentUrl();
        if (currentURL.equals(URL)) {
            System.out.println("Main page is opened");
            return true;
        } else {
            System.out.println("Page is not opened");
            return false;
        }
    }

    public enum AlertButton {
        ALLERT_JS_BUTTON("Click for JS Alert"),
        CONFIRM_JS_BUTTON("Click for JS Confirm"),
        PROMT_JS_BUTTON("Click for JS Prompt");
        private final String btnName;
        private static final String XPATH_TEMPLATE = "//button[(text()='%s')]";

        AlertButton(String buttonName) {
            this.btnName = buttonName;
        }

        public By getLocator() {
            return By.xpath(format(XPATH_TEMPLATE, btnName));
        }

        public String getBtnName() {
            return btnName;
        }

        public static AlertButton parse(String input) {
            return AlertButton.parse(input);
        }
    }

}
