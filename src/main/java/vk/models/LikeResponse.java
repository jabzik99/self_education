package vk.models;

import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {
    private int count;
    private List<LikeUsers> users;
}