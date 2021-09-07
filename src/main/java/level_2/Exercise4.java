package level_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static utilities.APIUtils.buildFormDataFromMap;

public class Exercise4 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String postCreateNewPost = "https://jsonplaceholder.typicode.com/posts/";

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        String testTitle = RandomStringUtils.randomAlphabetic(8);
        String testBody = RandomStringUtils.randomAlphabetic(8);

        Map<Object, Object> data = new HashMap<>();
        data.put("userId", 1);
        data.put("title", testTitle);
        data.put("body", testBody);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create(postCreateNewPost))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(response.body(), Post.class);
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(post.getUserId().intValue(),1);
        Assert.assertEquals(post.getTitle(),testTitle);
        Assert.assertEquals(post.getBody(),testBody);
        Assert.assertNotNull(post.getId());
    }
}
