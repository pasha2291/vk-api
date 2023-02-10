package by.a1qa.vkapi.vksteps;

import by.a1qa.vkapi.constants.Parameters;
import by.a1qa.vkapi.constants.Endpoints;
import by.a1qa.vkapi.models.*;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class CommentSteps extends BaseSteps {

    private final static String PHOTO_OWNER_ID_PHOTO_ID = "photo%d_%d";

    public static Response wallPost(String comment) {
        return  baseRequest()
                .queryParam(Parameters.MESSAGE, comment)
                .get(Endpoints.WALL_POST);
    }

    public static Response wallDeletePost(int commentId) {
        return baseRequest()
                .queryParam(Parameters.POST_ID, commentId)
                .get(Endpoints.WALL_DELETE_POST);
    }

    public static Response wallGetLikes(int commentId) {
        return baseRequest()
                .queryParam(Parameters.POST_ID, commentId)
                .get(Endpoints.WALL_GET_LIKES);
    }

    public static Response wallEditAndAddPicture(int commentId, String editedComment, VkResponse<ArrayList<Photo>> saveWallPhotoResponse) {
        Photo photo = saveWallPhotoResponse.getResponse().get(0);
        String attachment = String.format(PHOTO_OWNER_ID_PHOTO_ID, photo.getOwnerId(), photo.getId());
        return baseRequest()
                .queryParam(Parameters.POST_ID, commentId)
                .queryParam(Parameters.MESSAGE, editedComment)
                .queryParam(Parameters.ATTACHMENTS, attachment)
                .get(Endpoints.WALL_EDIT);
    }

    public static Response wallCreateComment(int commentId, String comment) {
        return baseRequest()
                .queryParam(Parameters.POST_ID, commentId)
                .queryParam(Parameters.MESSAGE, comment)
                .get(Endpoints.WALL_CREATE_COMMENT);
    }
}
