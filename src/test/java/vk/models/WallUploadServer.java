package vk.models;

import lombok.Data;

@Data
public class WallUploadServer {

    Response response;

    @Data
    public static class Response {
        int album_id;
        String upload_url;
        int user_id;
    }
}
