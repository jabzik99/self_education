package utilities;

import models.Post;
import org.apache.commons.collections.ListUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class APIUtils {

    public static HttpResponse<String> getResponseFromGetRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url))
                .header("accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> getResponseFromPostRequest(String url, Map<Object, Object> data) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static boolean isPostsSortedByID(List<Post> list, List<Post> sortedList) {
        return ListUtils.isEqualList(
                list
                        .stream()
                        .map(Post::getId)
                        .collect(toList()),
                sortedList
                        .stream()
                        .map(Post::getId)
                        .collect(toList()));
    }

    public static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static JSONObject getJsonObjectForGivenKey(String jsonArrayStr, int key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return jsonArray.optJSONObject(key);
    }
}
