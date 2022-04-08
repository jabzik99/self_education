package utilities;

import aquality.selenium.browser.AqualityServices;
import lombok.experimental.UtilityClass;

import java.io.*;

@UtilityClass
public class FileProcessor {
    public String getFileContent(String filePath) {
        AqualityServices.getLogger().debug(String.format("Getting content of file: %s", filePath));
        String content = "";
        try (InputStream inStream = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inStream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(String.format("%s%n", line));
            }
            content = sb.toString();
        } catch (FileNotFoundException e) {
            AqualityServices.getLogger().error(String.format("Following file isn't found: %s%nException message: %s%n", filePath, e.getMessage()));
        } catch (IOException e) {
            AqualityServices.getLogger().error(String.format("IO exception during reading content of following file: %s%nException message: %s%n", filePath, e.getMessage()));
        }
        return content;
    }
}
