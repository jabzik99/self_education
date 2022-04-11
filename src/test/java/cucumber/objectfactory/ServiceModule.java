package cucumber.objectfactory;

import com.google.inject.AbstractModule;
import utilities.IScreenshotProvider;
import utilities.ScreenshotProvider;

final class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(IScreenshotProvider.class).toInstance(new ScreenshotProvider());
    }
}
