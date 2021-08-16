package utilities;

import aquality.selenium.forms.Form;
import org.openqa.selenium.NoSuchElementException;

public final class FormHelper {

    private FormHelper() {
        throw new InstantiationError("Should not instantiate static class");
    }

    public static <T extends Form> T waitForOpened(T form) {
        return waitForOpened(form, String.format("Form '%s' is not opened", form.getName()));
    }

    public static <T extends Form> T waitForOpened(T form, String message) {
        if (!form.state().waitForDisplayed()) {
            throw new NoSuchElementException(message);
        }
        return form;
    }
}

