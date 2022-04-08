package vk.models;

import lombok.Data;

import java.util.List;

@Data
public class Like {
    Response response;
    @Data
    public static class Response {
        int count;
        List<Users> users;
        @Data
        public static class Users {
            int uid;
            int copied;
        }
    }
}
