package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static final Properties properties = new Properties();
    private final static String ENV_PROPERTIES_PATH = "src/main/resources/environment.properties";
    private final static String BASIC_AUTH_TEST_DATA_PATH = "src/main/resources/basicauthtestdata.properties";

    public void load(InputStream io) {
        try {
            properties.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEnvironment(String key) {
        PropertiesUtil environment = new PropertiesUtil();
        File file = new File(ENV_PROPERTIES_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static String getBasicAuthTestData(String key) {
        PropertiesUtil environment = new PropertiesUtil();
        File file = new File(BASIC_AUTH_TEST_DATA_PATH);
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
