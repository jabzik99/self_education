package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;

import static enums.VkMethods.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class VkApiUtils {
    private static final String HOST = "https://api.vk.com/method";
    private static final String TOKEN = PropertiesUtil.getVkTestData("vktestdata.token");
    private static final String VERSION = "5.131";
    public static final String OWNER_ID = PropertiesUtil.getVkTestData("vktestdata.owner_id");

    public static String postWallUploadServer() {
        return given()
                .baseUri(HOST)
                .basePath(GET_WALL_UPLOAD_SERVER.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static String postWallPostTo(String message) {
        return given()
                .baseUri(HOST)
                .basePath(WALL_POST.getMethodName())
                .queryParam("message", message)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static String postPhotoToServer(File file, String serverForUploadPhoto) {
        return given()
                .multiPart(file)
                .when().post(serverForUploadPhoto)
                .then().statusCode(SC_OK)
                .extract().body().asString();
    }

    public static String getIdPhotoFromServer(String serverPhoto, int serverId, String serverHash) {
        return given()
                .baseUri(HOST)
                .basePath(SAVE_WALL_PHOTO.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("server", serverId)
                .queryParam("photo", serverPhoto)
                .queryParam("hash", serverHash)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static void postWallEdit(int postId, int mediaId) {
        given()
                .baseUri(HOST)
                .basePath(WALL_EDIT.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("post_id", postId)
                .queryParam("message", RandomStringUtils.randomAlphabetic(6) + " - edited")
                .queryParam("attachments", "photo" + OWNER_ID + "_" + mediaId)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static void wallAddComment(int postId) {
        given()
                .baseUri(HOST)
                .basePath(WALL_CREATE_COMMENT.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("post_id", postId)
                .queryParam("message", RandomStringUtils.randomAlphabetic(6) + " - COMMENT")
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static String wallGetLikes(int postId) {
        return given()
                .baseUri(HOST)
                .basePath(WALL_GET_LIKES.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("post_id", postId)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static void wallDelete(int postId) {
        given()
                .baseUri(HOST)
                .basePath(WALL_DELETE.getMethodName())
                .queryParam("group_id", OWNER_ID)
                .queryParam("post_id", postId)
                .queryParam("access_token", TOKEN)
                .queryParam("v", VERSION)
                .when().post()
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }
}
