import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Post;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.APIUtils;
import utilities.PropertiesUtil;

import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class APITest {

    private final static String ALL_POSTS = PropertiesUtil.getEnvironment("environment.posts");
    private final static String POST_99 = PropertiesUtil.getEnvironment("environment.post99");
    private final static String POST_150 = PropertiesUtil.getEnvironment("environment.post150");
    private final static String ALL_USERS = PropertiesUtil.getEnvironment("environment.users");
    private final static String USER_5 = PropertiesUtil.getEnvironment("environment.user5");

    @Test
    public void apiTest() throws IOException, InterruptedException {
        //step 1
        HttpResponse<String> response_1 = APIUtils.getResponseFromGetRequest(ALL_POSTS);
        Assert.assertEquals(String.valueOf(response_1.statusCode()), "200", "Http status code is not 200");
        ObjectMapper mapper_1 = new ObjectMapper();

        List<Post> post_1 = mapper_1.readValue(response_1.body(), new TypeReference<List<Post>>() {
        });
        List<Post> sortedById = post_1
                .stream()
                .sorted((ob1, ob2) -> ob1.getId().compareTo(ob2.getId()))
                .collect(Collectors.toList());
        Assert.assertTrue(APIUtils.isPostsSortedByID(post_1, sortedById), "Posts are not sorted by ID");

        //step 2
        HttpResponse<String> response_2 = APIUtils.getResponseFromGetRequest(POST_99);
        ObjectMapper mapper_2 = new ObjectMapper();
        Post post_2 = mapper_2.readValue(response_2.body(), Post.class);
        Assert.assertEquals(String.valueOf(response_2.statusCode()), "200", "Http status code is not 200");
        Assert.assertEquals(post_2.getUserId().intValue(), 10, "User ID is not equal to 10");
        Assert.assertEquals(post_2.getId().intValue(), 99, "ID is not equal to 99");
        Assert.assertFalse(post_2.getBody().isEmpty() && post_2.getTitle().isEmpty(), "The Title and body are empty");

        //step 3
        HttpResponse<String> response_3 = APIUtils.getResponseFromGetRequest(POST_150);
        Assert.assertEquals(response_3.statusCode(), 404, "Http status code is not 404");
        Assert.assertEquals(response_3.body(), "{}", "Body of response is not empty");

        //step 4
        String testTitle = RandomStringUtils.randomAlphabetic(8);
        String testBody = RandomStringUtils.randomAlphabetic(8);
        Map<Object, Object> data = new HashMap<>();
        data.put("userId", 1);
        data.put("title", testTitle);
        data.put("body", testBody);
        HttpResponse<String> response_4 = APIUtils.getResponseFromPostRequest(ALL_POSTS, data);
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(response_4.body(), Post.class);
        Assert.assertEquals(response_4.statusCode(), 201, "Http status code is not 201");
        Assert.assertEquals(post.getUserId().intValue(), data.get("userId"), "UserID's don't match");
        Assert.assertEquals(post.getTitle(), testTitle, "Title's don't match");
        Assert.assertEquals(post.getBody(), testBody, "Bodies don't match");
        Assert.assertNotNull(post.getId(),"ID is not present in response");

        //step 5
        HttpResponse<String> response_5 = APIUtils.getResponseFromGetRequest(ALL_USERS);
        Assert.assertEquals(response_5.statusCode(), 200, "Http status code is not 200");
        Assert.assertTrue(APIUtils.isJSONValid(response_5.body()), "Http response not in Json format");

        JSONObject user_5_object = APIUtils.getJsonObjectForGivenKey(response_5.body(), 4);
        ObjectMapper mapper_5 = new ObjectMapper();
        mapper_5.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        User user_5 = mapper_5.readValue(user_5_object.toString(), User.class);
        FileReader reader = new FileReader("src/main/resources/data/TestUser5.json");
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);

        Assert.assertEquals(jsonObject.get("name").getAsString(), user_5.getName(), "Name from json file are not equal to name from http response");
        Assert.assertEquals(jsonObject.get("username").getAsString(), user_5.getUsername(), "User name from json file are not equal to user name from http response");
        Assert.assertEquals(jsonObject.get("email").getAsString(), user_5.getEmail(), "Email from json file are not equal to email from http response");
        Assert.assertEquals(jsonObject.get("phone").getAsString(), user_5.getPhone(), "Phone from json file are not equal to phone from http response");
        Assert.assertEquals(jsonObject.get("website").getAsString(), user_5.getWebsite(), "Website from json file are not equal to website from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("address").get("street").getAsString(), user_5.getAddress().get(0).getStreet(),
                "Street from json file are not equal to street from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("address").get("suite").getAsString(), user_5.getAddress().get(0).getSuite(),
                "Suite from json file are not equal to suite from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("address").get("city").getAsString(), user_5.getAddress().get(0).getCity(),
                "City from json file are not equal to city from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("address").get("zipcode").getAsString(), user_5.getAddress().get(0).getZipcode(),
                "Zipcode from json file are not equal to zipcode from http response");

        Assert.assertEquals(jsonObject.getAsJsonObject("address").getAsJsonObject("geo").get("lat").getAsString(), user_5.getAddress().get(0).getGeo().get(0).getLat(),
                "Lat from json file are not equal to lat from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("address").getAsJsonObject("geo").get("lng").getAsString(), user_5.getAddress().get(0).getGeo().get(0).getLng(),
                "Lng from json file are not equal to lng from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("company").get("name").getAsString(), user_5.getCompany().get(0).getName(),
                "Company name from json file are not equal to company name from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("company").get("catchPhrase").getAsString(), user_5.getCompany().get(0).getCatchPhrase(),
                "Catch phrase from json file are not equal to catch phrase from http response");
        Assert.assertEquals(jsonObject.getAsJsonObject("company").get("bs").getAsString(), user_5.getCompany().get(0).getBs(),
                "Bs from json file are not equal to bs from http response");

        //step 6
        HttpResponse<String> response_6 = APIUtils.getResponseFromGetRequest(USER_5);
        Assert.assertEquals(response_6.statusCode(), 200, "Http status code is not 200");
        ObjectMapper mapper_6 = new ObjectMapper();
        mapper_6.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        User user_step_6 = mapper_6.readValue(response_6.body(), User.class);
        Assert.assertEquals(user_step_6.getId(), user_5.getId(), "ID from step 5 is not equal to ID from http response");
        Assert.assertEquals(user_step_6.getName(), user_5.getName(), "Name from step 5 is not equal to Name from http response");
        Assert.assertEquals(user_step_6.getUsername(), user_5.getUsername(), "Username from step 5 is not equal to username from http response");
        Assert.assertEquals(user_step_6.getEmail(), user_5.getEmail(), "Email from step 5 is not equal to email from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getStreet(), user_5.getAddress().get(0).getStreet(), "Street from step 5 is not equal to street from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getSuite(), user_5.getAddress().get(0).getSuite(), "Suite from step 5 is not equal to suite from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getCity(), user_5.getAddress().get(0).getCity(), "City from step 5 is not equal to city from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getZipcode(), user_5.getAddress().get(0).getZipcode(), "Zipcode from step 5 is not equal to zipcode from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getGeo().get(0).getLat(), user_5.getAddress().get(0).getGeo().get(0).getLat(), "Lat from step 5 is not equal to lat from http response");
        Assert.assertEquals(user_step_6.getAddress().get(0).getGeo().get(0).getLng(), user_5.getAddress().get(0).getGeo().get(0).getLng(), "Lng from step 5 is not equal to lng from http response");
        Assert.assertEquals(user_step_6.getPhone(), user_5.getPhone(), "Phone from step 5 is not equal to phone from http response");
        Assert.assertEquals(user_step_6.getWebsite(), user_5.getWebsite(), "Website from step 5 is not equal to website from http response");
        Assert.assertEquals(user_step_6.getCompany().get(0).getName(), user_5.getCompany().get(0).getName(), "Company name from step 5 is not equal to company name from http response");
        Assert.assertEquals(user_step_6.getCompany().get(0).getCatchPhrase(), user_5.getCompany().get(0).getCatchPhrase(), "Catch Phrase from step 5 is not equal to catch phrase from http response");
        Assert.assertEquals(user_step_6.getCompany().get(0).getBs(), user_5.getCompany().get(0).getBs(), "Bs from step 5 is not equal to bs from http response");
    }
}
