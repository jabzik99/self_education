package utilities;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;

public interface ICustomParameters {

    String name();

    default String getDisplayedName() {
        return WordUtils.capitalizeFully(name().replace("_", " "));
    }

    static <T extends ICustomParameters> T parse(String value, T[] values) {
        return Arrays.stream(values).filter(item -> item.getDisplayedName().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
