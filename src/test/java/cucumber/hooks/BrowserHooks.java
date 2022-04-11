package cucumber.hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.JsonObjectMapper;
import utilities.PropertiesUtil;

public class BrowserHooks {

    @Before(order = 0)
    public void setBrowser() {
        String temp = PropertiesUtil.getBrowser();
        JsonObjectMapper.changeBrowser(temp);
    }

    @After(order = 0)
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
