package utilities;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

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
}
