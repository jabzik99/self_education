package vk.models;

import lombok.Data;

import java.util.List;

@Data
public class Media {

    List<Response> response;
    @Data
    public static class Response {
        int album_id;
        int date;
        int id;
        int owner_id;
        String access_key;
    }
}
