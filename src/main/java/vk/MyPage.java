package vk;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utilities.RegexUtil;

public class MyPage extends Form {

    private static final String USER_NAME_POST_LOCATOR = "//*[contains(@data-post-id,'%d')]//a[@class='author']";
    private static final String POST_TEXT_LOCATOR = "//*[contains(@data-post-id,'%d')]//*[@class='wall_text']";
    private static final String POST_COMMENT_LINK_LOCATOR = "/following-sibling::div[@class='replies']//a[contains(@href,'wall')]/span[@class='js-replies_next_label']";
    private static final String POST_LIKE_LOCATOR = "/following-sibling::div[contains(@class,'like')]//div[@data-section-ref='reactions-button-container']";
    private static final String POST_REPLY_AUTHOR_LOCATOR = "/following-sibling::div[@class='replies']//div[@class='reply_author']";
    private static final String LINK_LOCATOR = "//a";

    private final ILabel userNameLbl = getElementFactory().getLabel(By.xpath("//h1[@class='page_name']"), "User name");

    public MyPage() {
        super(By.xpath("//*[@id='page_body']//*[@id='profile_short']"), "My page");
    }

    private IElement getPost(int postId) {
        return getElementFactory()
                .getLabel(By.xpath(String.format(POST_TEXT_LOCATOR, postId)), "Post");
    }

    private ILink getShowCommentLink(int postId) {
        return getPost(postId)
                .findChildElement(By.xpath(POST_COMMENT_LINK_LOCATOR), ElementType.LINK);
    }

    public boolean waitForPostNotDisplayed(int postId) {
        return getPost(postId).state().waitForNotDisplayed();
    }

    public void setLikeToPost(int postId) {
        getPost(postId)
                .findChildElement(By.xpath(POST_LIKE_LOCATOR), ElementType.BUTTON)
                .click();
    }

    private String getReplyAuthorName(int postId) {
        return getPost(postId)
                .findChildElement(By.xpath(POST_REPLY_AUTHOR_LOCATOR), ElementType.LABEL)
                .getText();
    }

    public String getUserName() {
        return userNameLbl.getText();
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
                        .findChildElement(By.xpath(LINK_LOCATOR), ElementType.LINK)
                        .getCssValue("background-image")
        );
    }

}
