package by.a1qa.vkapi.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {

    private static final By PROFILE_HEADER_LOCATOR = By.className("ProfileHeader");
    private static final By SHOW_NEXT_COMMENT_BUTTON_LOCATOR = By.xpath("//span[@class='js-replies_next_label']");

    public MyPage() {
        super(PROFILE_HEADER_LOCATOR, "Profile header");
    }

    public void showNextCommentButtonClick() {
        getElementFactory().getButton(SHOW_NEXT_COMMENT_BUTTON_LOCATOR, "Show next button locator").click();
    }

    public PostPage getPostById(int userId, int postId) {
        return new PostPage(userId, postId);
    }
}
