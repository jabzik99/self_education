package level_2;

import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Exercise3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String getPost150 = "https://jsonplaceholder.typicode.com/posts/150";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(getPost150))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.body(), "{}");
    }
}
