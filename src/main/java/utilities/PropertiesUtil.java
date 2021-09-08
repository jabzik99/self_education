package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static final Properties properties = new Properties();


    public void load(InputStream io) {
        try {
            properties.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEnvironment(String key) {
        PropertiesUtil environment = new PropertiesUtil();
        File file = new File("src/main/resources/environment.properties");
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static String getBasicAuthTestData(String key) {
        PropertiesUtil environment = new PropertiesUtil();
        File file = new File("src/main/resources/basicauthtestdata.properties");
        try (FileInputStream io = new FileInputStream(file)) {
            environment.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
