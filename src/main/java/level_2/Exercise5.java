package level_2;

import org.testng.Assert;
import utilities.APIUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Exercise5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String GetUsers = "https://jsonplaceholder.typicode.com/users";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(GetUsers))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(APIUtils.isJSONValid(response.body()));

    }
}
