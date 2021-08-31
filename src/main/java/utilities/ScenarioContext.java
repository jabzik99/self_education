package utilities;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.guice.ScenarioScoped;

@ScenarioScoped
public class ScenarioContext {

    private final Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public <T> void add(String key, T value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) context.get(key);
    }

    public boolean isPresent(String key) {
        return context.containsKey(key);
    }
}