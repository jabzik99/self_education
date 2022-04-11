package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static final Properties properties = new Properties();
    private final static String ENV_PROPERTIES_PATH = "src/test/resources/environment.properties";
    private final static String BASIC_AUTH_TEST_DATA_PATH = "src/test/resources/basicauthtestdata.properties";
    private final static String VK_TEST_DATA_PATH = "src/test/resources/vktestdata.properties";
    private final static String BROWSER_DATA_PATH = "src/test/resources/browser.properties";
    public static PropertiesUtil environment = new PropertiesUtil();

    public void load(InputStream io) {
        try {
            properties.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEnvironment(String key) {

        File file = new File(ENV_PROPERTIES_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        File file = new File(BROWSER_DATA_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty("bw");
    }

    public static String getBasicAuthTestData(String key) {
        File file = new File(BASIC_AUTH_TEST_DATA_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static String getVkTestData(String key) {
        File file = new File(VK_TEST_DATA_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
