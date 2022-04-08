package vk.models;

import lombok.Data;
@Data
public class Post {
    Response response;

    @Data
    public static class Response {
        int post_id;
    }
}
