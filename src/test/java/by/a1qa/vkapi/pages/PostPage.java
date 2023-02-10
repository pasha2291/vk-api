package by.a1qa.vkapi.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class PostPage extends Form {

    private static final String USER_POST_DYNAMIC_LOCATOR = "//div[@id='post%d_%d']";
    private static final String USER_POST_TEXT_DYNAMIC_LOCATOR = "%s//div[@class='wall_post_text']";
    private static final String USER_POST_LIKE_BUTTON_DYNAMIC_LOCATOR = "%s//div[contains(@class, 'PostButtonReactionsContainer')]";
    private static final String POST_WITH_PHOTO_ID_DYNAMIC_LOCATOR = "%s//a[contains(@href, '%d')]";
    private static final String POST_COMMENTS_DYNAMIC_LOCATOR = "%s//div[@class='wall_reply_text']";
    private final String INITIAL_POST_LOCATOR;
    private final int USER_ID;
    private final int POST_ID;

    public PostPage(int userId, int postId) {
        super(By.xpath(String.format(USER_POST_DYNAMIC_LOCATOR, userId, postId)), "User post on the wall");
        this.USER_ID = userId;
        this.POST_ID = postId;
        this.INITIAL_POST_LOCATOR = String.format(USER_POST_DYNAMIC_LOCATOR, USER_ID, POST_ID);
    }

    private void waitForPostDisplayedCondition(boolean expectedCondition) {
        AqualityServices.getConditionalWait().waitFor(() -> this.state().isDisplayed() == expectedCondition);
    }

    public boolean isPostDisplayed() {
        waitForPostDisplayedCondition(Boolean.TRUE);
        return this.state().isDisplayed();
    }

    public boolean isPostNotDisplayed() {
        waitForPostDisplayedCondition(Boolean.FALSE);
        return this.state().isDisplayed();
    }

    public String getText() {
        return getElementFactory().getLabel(By.xpath(String.format(USER_POST_TEXT_DYNAMIC_LOCATOR, INITIAL_POST_LOCATOR)),
                "User post with text label").getText();
    }

    public void putLike() {
        getElementFactory().getButton(By.xpath(String.format(USER_POST_LIKE_BUTTON_DYNAMIC_LOCATOR, INITIAL_POST_LOCATOR)),
                "User post like button").click();
    }

    public boolean isContainPictureById(int pictureId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_WITH_PHOTO_ID_DYNAMIC_LOCATOR, INITIAL_POST_LOCATOR, pictureId)),
                "Post from user with picture").state().isDisplayed();
    }

    public List<String> getCommentsToThisPost() {
        AqualityServices.getConditionalWait().waitFor(() -> !getElementFactory().findElements(By.xpath(String.format(POST_COMMENTS_DYNAMIC_LOCATOR, INITIAL_POST_LOCATOR)), ILabel.class).isEmpty());
        List<ILabel> commentLabels = getElementFactory().findElements(By.xpath(String.format(POST_COMMENTS_DYNAMIC_LOCATOR, INITIAL_POST_LOCATOR)), ILabel.class);
        List<String> comments = new ArrayList<>();
        for(ILabel current : commentLabels){
            comments.add(current.getText());
        }
        return comments;
    }
}
