package utilities;

public class ModifyingUrlUtil {
    public static String getBasicAuthModifyingURL() {
        return "https://" + PropertiesUtil.getBasicAuthTestData("basicauthtestdata.username")
                + ":"
                + PropertiesUtil.getBasicAuthTestData("basicauthtestdata.password")
                + "@" + PropertiesUtil.getEnvironment("environment.basic_auth_withouthttp");
    }
}