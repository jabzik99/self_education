package utilities;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class JsonObjectMapper {
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public <T> T mapToObject(String jsonData,
                             TypeReference<T> typeReference) {
        try {
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);
            return OBJECT_MAPPER.readValue(jsonData, typeReference);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            String errorMsg = String.format("Can't parse following data to object of class '%s'%nException message: %s%nData:%s%n",
                    typeReference.getClass().getName(), e.getMessage(), jsonData);
            AqualityServices.getLogger().error(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    @SneakyThrows
    public void changeBrowser(String browser) {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/settings.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        ((ObjectNode) rootNode).put("browserName", browser);
        objectMapper.writeValue(new File("src/test/resources/settings.json"), rootNode);
    }
}
