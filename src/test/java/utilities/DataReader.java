package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.experimental.UtilityClass;
import vk.models.*;

@UtilityClass
public class DataReader {

    public Post gePost(String body) {
        return JsonObjectMapper.mapToObject(
                body,
                new TypeReference<Post>() {
                }
        );
    }

    public Media getMediaModel(String body) {
        return JsonObjectMapper.mapToObject(
                body,
                new TypeReference<Media>() {
                }
        );
    }

    public Like getLikeModel(String body) {
        return JsonObjectMapper.mapToObject(
                body,
                new TypeReference<Like>() {
                }
        );
    }

    public WallUploadServer getWallUploadServerModel(String body) {
        return JsonObjectMapper.mapToObject(
                body,
                new TypeReference<WallUploadServer>() {
                }
        );
    }

    public Photo getPhotoModel(String body) {
        return JsonObjectMapper.mapToObject(
                body,
                new TypeReference<Photo>() {
                }
        );
    }
}
