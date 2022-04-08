package vk;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utilities.RegexUtil;

public class MyPage extends Form {

    public static String USER_NAME_POST_LOCATOR = "//*[contains(@data-post-id, '%d')]//a[@class='author']";
    public static String POST_TEXT_LOCATOR = "//*[contains(@data-post-id, '%d')]//*[@class='wall_text']";

    public MyPage() {
        super(By.xpath("//*[@id='page_body']//*[@id='profile_short']"), "My page");
    }

    private IElement getPost(int postId) {
        return getElementFactory()
                .getLabel(By.xpath(String.format(POST_TEXT_LOCATOR, postId)), "Post");
    }

    private ILink getShowCommentLink(int postId) {
        return getPost(postId)
                .findChildElement(By.xpath("/following-sibling::div[@class='replies']" +
                "//a[contains(@href, 'wall')]/span[@class='js-replies_next_label']"), ElementType.LINK);
    }

    public boolean waitForPostNotDisplayed(int postId) {
        return getPost(postId).state().waitForNotDisplayed();
    }

    public void setLikeToPost(int postId) {
        getPost(postId)
                .findChildElement(By.xpath("/following-sibling::div[contains(@class, 'like')]" +
                "//div[@data-section-ref='reactions-button-container']"), ElementType.BUTTON)
                .click();
    }

    private String getReplyAuthorName(int postId) {
        return getPost(postId)
                .findChildElement(By.xpath("/following-sibling::div[@class='replies']//div[@class='reply_author']"), ElementType.LABEL)
                .getText();
    }

    public String getUserName() {
        return getElementFactory()
                .getLabel(By.xpath("//h1"), "User name")
                .getText();
    }

    public boolean isCommendAddedByUser(String user, int postId) {
        getShowCommentLink(postId).click();
        return getReplyAuthorName(postId).contains(user);
    }

    public boolean isPostContainsTextAndUserName(int postId, String username, String text) {
        return isPostUserNameEqualsToFollowing(postId, username) && isPostTextEqualsToFollowing(postId, text);
    }

    private boolean isPostUserNameEqualsToFollowing(int postId, String username) {
        return getElementFactory()
                .getLabel(By.xpath(String.format(USER_NAME_POST_LOCATOR, postId)), "User name")
                .getText()
                .equalsIgnoreCase(username);
    }

    public boolean isPostTextEqualsToFollowing(int postId, String text) {
        return getPost(postId)
                .getText()
                .equals(text);
    }

    public String getImageUrlByPostId(int postId) {
        return RegexUtil.extractFirstUrl(
                getPost(postId)
                        .findChildElement(By.xpath("//a"), ElementType.LINK)
                        .getCssValue("background-image")
        );
    }

}
