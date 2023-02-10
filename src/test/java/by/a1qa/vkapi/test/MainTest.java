package by.a1qa.vkapi.test;

import by.a1qa.vkapi.constants.FilePaths;
import by.a1qa.vkapi.data.TestDataManager;
import by.a1qa.vkapi.utils.logger.CustomLogger;
import by.a1qa.vkapi.models.*;
import by.a1qa.vkapi.pages.LogInPage;
import by.a1qa.vkapi.pages.MyPage;
import by.a1qa.vkapi.pages.PostPage;
import by.a1qa.vkapi.utils.RandomStringUtil;
import by.a1qa.vkapi.vksteps.CommentSteps;
import by.a1qa.vkapi.vksteps.ResponseSteps;
import by.a1qa.vkapi.vksteps.UploadPictureSteps;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class MainTest extends BaseTest {

    @Test
    public void runMainTestScenario() {
        CustomLogger.info("Start of main test scenario.");

        LogInPage logInPage = new LogInPage();
        logInPage.logIn(TestDataManager.getUserPhone(), TestDataManager.getUserEmail());

        MyPage myPage = logInPage.myPageButtonClick();

        CustomLogger.info("Assert that my page is opened.");
        Assert.assertTrue(myPage.state().isDisplayed(), "My page should be opened!");

        String randomText = RandomStringUtil.getRandomText();
        Response response = CommentSteps.wallPost(randomText);

        int userId = TestDataManager.getUserID();
        int postId = ResponseSteps.getObject(response, new TypeReference<VkResponse<Post>>() {}).getResponse().getPostId();

        PostPage postPage = myPage.getPostById(userId, postId);

        CustomLogger.info("Assert that new post has appeared on the wall.");
        Assert.assertTrue(postPage.isPostDisplayed(), "New message should be on the wall!");

        String edition = "%s_EDITED";
        String editedRandomText = String.format(edition, randomText);
        VkResponse<ArrayList<Photo>> saveWallPhotoResponse = ResponseSteps.getObject(UploadPictureSteps.postPhotoOnWall(FilePaths.PICTURE_FILE_PATH),
                new TypeReference<VkResponse<ArrayList<Photo>>>() {});
        CommentSteps.wallEditAndAddPicture(postId, editedRandomText, saveWallPhotoResponse);

        CustomLogger.info("Assert that post was edited and picture has appeared on the wall.");
        Assert.assertEquals(postPage.getText(), editedRandomText, "The post should be edited!");
        int photoID = saveWallPhotoResponse.getResponse().get(0).getId();
        Assert.assertTrue(postPage.isContainPictureById(photoID), "Picture should be on the wall!");

        String randomComment = RandomStringUtil.getRandomText();
        CommentSteps.wallCreateComment(postId, randomComment);
        myPage.showNextCommentButtonClick();

        CustomLogger.info("Assert that comment from specific user has appeared on the wall.");
        Assert.assertTrue(postPage.getCommentsToThisPost().contains(randomComment), "Comment from specific user should be on the wall!");

        postPage.putLike();

        VkResponse<UserList> getUserLikesResponse = ResponseSteps.getObject(CommentSteps.wallGetLikes(postId), new TypeReference<VkResponse<UserList>>() {});
        User expectedUser = new User();
        expectedUser.setUid(userId);

        CustomLogger.info("Assert that specific comment has like from specific user.");
        Assert.assertTrue(getUserLikesResponse.getResponse().getUsers().contains(expectedUser), "Comment should obtain a like from specific user!");

        CommentSteps.wallDeletePost(postId);

        CustomLogger.info("Assert that post has been deleted.");
        Assert.assertFalse(postPage.isPostNotDisplayed(), "The post should not be on the wall!");

        CustomLogger.info("End of main test scenario.");
    }
}
