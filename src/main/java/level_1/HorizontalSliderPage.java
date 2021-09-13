package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;


public class HorizontalSliderPage extends Form {
    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton rngSlider = elementFactory.getButton(By.xpath("//*[@type='range']"), "Slider");
    private IElement rngLabel = elementFactory.getLabel(By.xpath("//*[@id='range']"), "Range value");

    protected HorizontalSliderPage(By locator, String name) {
        super(locator, name);
    }

    public HorizontalSliderPage(String name) {
        this(By.xpath(format("//h3[contains(text(),'%s')]", name)), format("%s page", name));
    }

    public void setRandomRangeValue() {
        int rand = ThreadLocalRandom.current().nextInt(1, 9);
        for (int i = 0; i < rand; i++) {
            rngSlider.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public boolean isRangeValuesOnBorder() {
        return !rngLabel.getText().equals("0") && !rngLabel.getText().equals("5");
    }

}
