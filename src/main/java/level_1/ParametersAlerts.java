package level_1;

import io.cucumber.java.ParameterType;


import static level_1.AlertsPage.*;

public class ParametersAlerts {
    @ParameterType(value = "Click for JS Alert|Click for JS Confirm|Click for JS Prompt")
    public AlertButton JSButton(String tabName) {
        return AlertButton.parse(tabName);
    }
}
