package models;

import lombok.Data;

@Data
public class Post {
    Integer userId;
    Integer id;
    String title;
    String body;

    @Override
    public String toString() {
        return "Post [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
    }
}
