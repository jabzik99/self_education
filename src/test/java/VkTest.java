import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataReader;
import utilities.ImagesCompareUtil;
import utilities.PropertiesUtil;
import utilities.VkApiUtils;
import vk.MyPage;
import vk.VkLoginPage;
import vk.VkNavigationPanelForm;
import vk.models.*;

import java.io.File;

import static utilities.VkApiUtils.*;

public class VkTest {

    private static final String PEPE_FROG_IMAGE_PATH = "src/test/resources/data/pepe.jpg";
    private static final String URL = PropertiesUtil.getEnvironment("environment.vk");

    @Test
    public void vkTest() {
        //1 step
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(URL);
        VkLoginPage vkLoginPage = new VkLoginPage();
        Assert.assertTrue(vkLoginPage.state().waitForDisplayed(), "Vk Login page should be opened");

        //2 step
        vkLoginPage.loginAsDefaultUser();

        //3 step
        VkNavigationPanelForm navigation = new VkNavigationPanelForm();
        navigation.openMyPageLink();

        //4 step
        String randomTextForPost = RandomStringUtils.randomAlphabetic(8);
        Post post = DataReader.gePost(postWallPostTo(randomTextForPost));
        int post_id = post.getResponse().getPost_id();

        //5 step
        MyPage myPage = new MyPage();
        String userName = myPage.getUserName();
        Assert.assertTrue(myPage.isPostContainsTextAndUserName(post_id, userName, randomTextForPost));

        //6 step
        WallUploadServer wallUploadServer = DataReader.getWallUploadServerModel(postWallUploadServer());
        String upload_url = wallUploadServer.getResponse().getUpload_url();

        Photo photo = DataReader.getPhotoModel(VkApiUtils.postPhotoToServer(new File(PEPE_FROG_IMAGE_PATH), upload_url));

        String photoId = VkApiUtils.getIdPhotoFromServer(photo.getPhoto(), photo.getServer(), photo.getHash());
        Media media = DataReader.getMediaModel(photoId);

        int mediaId = media.getResponse().get(0).getId();
        postWallEdit(post_id, mediaId);

        // step 7
        Assert.assertFalse(
                myPage.isPostTextEqualsToFollowing(post_id, randomTextForPost),
                "Text should not match"
        );

        String imageUrl = myPage.getImageUrlByPostId(post_id);
        Double differencePercentage = ImagesCompareUtil.compareImagesAndGetDifferencePercentage(PEPE_FROG_IMAGE_PATH, imageUrl);
        Assert.assertEquals(differencePercentage, 0.0, 0.2);

        //step 8
        VkApiUtils.wallAddComment(post_id);

        //step 9
        Assert.assertTrue(
                myPage.isCommendAddedByUser(userName, post_id),
                String.format("Comment should be added from %s user", userName)
        );

        //step 10
        myPage.setLikeToPost(post_id);

        //step 11
        String likeBody = VkApiUtils.wallGetLikes(post_id);
        Like like = DataReader.getLikeModel(likeBody);
        int userId = like.getResponse().getUsers().get(0).getUid();
        Assert.assertEquals(
                userId,
                Integer.valueOf(OWNER_ID).intValue(),
                "Owner IDs must match"
        );

        //step 12
        wallDelete(post_id);

        //step 13
        Assert.assertTrue(
                myPage.waitForPostNotDisplayed(post_id),
                String.format("Post with %d id should not displayed", post_id)
        );
    }
}