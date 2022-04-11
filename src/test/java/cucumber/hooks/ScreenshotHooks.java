package cucumber.hooks;

import io.cucumber.java.After;
import utilities.IScreenshotProvider;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ScreenshotHooks {

    private final IScreenshotProvider screenshotProvider;

    @Inject
    public ScreenshotHooks(IScreenshotProvider screenshotProvider) {
        this.screenshotProvider = screenshotProvider;
    }

    @After(order = 1)
    public void takeScreenshot() throws IOException {
            byte [] data = screenshotProvider.takeScreenshot();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "jpg", new File("attach.jpg"));
    }
}
