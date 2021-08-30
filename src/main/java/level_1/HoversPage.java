package level_1;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class HoversPage {
    private String URL = "http://the-internet.herokuapp.com/hovers";

    public String getUserName (Users user){
        return user.userName;
    }

    @AllArgsConstructor
    public enum Users {
        USER1("user1"),
        USER2("user2"),
        USER3("user3");
        private final String userName;
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

    }
    public String getURL() {
        return URL;
    }
}
