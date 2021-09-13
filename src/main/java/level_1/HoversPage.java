package level_1;

import aquality.selenium.forms.Form;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utilities.ICustomParameters;

import static java.lang.String.format;

public class HoversPage extends Form {

    protected HoversPage(By locator, String name) {
        super(locator, name);
    }

    public HoversPage(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public String getUserName(Users user) {
        return user.userName;
    }

    @AllArgsConstructor
    public enum Users implements ICustomParameters {
        USER1("user1"),
        USER2("user2"),
        USER3("user3");

        public final String userName;

        private static final String XPATH_TEMPLATE = "//h5[contains(text(),'name: %s')]";
        private static final String XPATH_TEMPLATE_HOVER_IMG = "//h5[contains(text(),'name: %s')]/parent::div//preceding-sibling::img";
        private static final String XPATH_TEMPLATE_PROFILE_LINK = "//h5[text()='name: %s']/following-sibling::a";

        public By getLocatorForUserName() {
            return By.xpath(format(XPATH_TEMPLATE, userName));
        }


        public By getLocatorForHoverImg() {
            return By.xpath(format(XPATH_TEMPLATE_HOVER_IMG, userName));
        }

        public By getLocatorForProfileLink() {
            return By.xpath(format(XPATH_TEMPLATE_PROFILE_LINK, userName));
        }

        public static Users parse(String input) {
            return ICustomParameters.parse(input, values());
        }

    }

}
