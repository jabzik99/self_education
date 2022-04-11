package cucumber.transformations;

import io.cucumber.java.ParameterType;

import static level_1.HoversPage.Users;

public class ParameterTypes {

    @ParameterType(value = "user1|user2|user3")
    public Users users(String user) {
        return Users.parse(user);
    }
}
