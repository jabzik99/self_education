package level_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Exercise2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String getPost99 = "https://jsonplaceholder.typicode.com/posts/99";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(getPost99))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(response.body(), Post.class);
        Assert.assertEquals(String.valueOf(response.statusCode()), "200");
        Assert.assertEquals(post.getUserId().intValue(),10);
        Assert.assertEquals(post.getId().intValue(),99);
        Assert.assertFalse(post.getBody().isEmpty() && post.getTitle().isEmpty());
    }
}
