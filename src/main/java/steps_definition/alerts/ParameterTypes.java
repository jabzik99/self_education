package steps_definition.alerts;

import io.cucumber.java.ParameterType;
import utilities.ScenarioContext;

import javax.inject.Inject;

import static level_1.HoversPage.Users;

public class ParameterTypes {

    private final ScenarioContext scenarioContext;

    @Inject
    public ParameterTypes(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ParameterType(value = "user1|user2|user3")
    public Users users(String user) {
        return Users.parse(user);
    }

}
