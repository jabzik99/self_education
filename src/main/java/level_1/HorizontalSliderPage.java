package level_1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.ThreadLocalRandom;


public class HorizontalSliderPage {
    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    public IButton rngSlider = elementFactory.getButton(By.xpath("//*[@type='range']"), "Slider");
    public IElement rngLabel = elementFactory.getLabel(By.xpath("//*[@id='range']"), "Range value");
    private String URL = "http://the-internet.herokuapp.com/horizontal_slider";

    public void setRandomRangeValue() {
        int rand = ThreadLocalRandom.current().nextInt(1, 9);
        for (int i = 0; i < rand; i++) {
            rngSlider.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public boolean checkRangeValue() {
        return !rngLabel.getText().equals("0") && !rngLabel.getText().equals("5");
    }
    public String getURL() {
        return URL;
    }
}
