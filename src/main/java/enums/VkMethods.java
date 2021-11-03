package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VkMethods {
    WALL_POST("wall.post"),
    GET_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    SAVE_WALL_PHOTO("photos.saveWallPhoto"),
    WALL_EDIT("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    WALL_GET_LIKES("wall.getLikes"),
    WALL_DELETE("wall.delete");

    @Getter
    private final String methodName;
}
