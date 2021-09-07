package level_2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import org.testng.Assert;
import utilities.APIUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;


public class Exercise1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String getAllPosts = "https://jsonplaceholder.typicode.com/posts";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(getAllPosts))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertTrue(String.valueOf(response.statusCode()).equals("200"));


        ObjectMapper mapper = new ObjectMapper();

        List<Post> post = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
        });

        List<Post> sortedById = post
                .stream()
                .sorted((ob1, ob2) -> ob1.getId().compareTo(ob2.getId()))
                .collect(Collectors.toList());


        Assert.assertTrue(APIUtils.isPostsSortedByID(post, sortedById));

    }
}
