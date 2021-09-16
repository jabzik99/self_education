package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.ThreadLocalRandom;


public class HorizontalSliderPage extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton rngSlider = elementFactory.getButton(By.xpath("//*[@type='range']"), "Slider");
    private final IElement rngLabel = elementFactory.getLabel(By.xpath("//*[@id='range']"), "Range value");

    public HorizontalSliderPage() {
        super(By.xpath("//h3[contains(text(),'Horizontal Slider')]"), "Horizontal Slider");
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
