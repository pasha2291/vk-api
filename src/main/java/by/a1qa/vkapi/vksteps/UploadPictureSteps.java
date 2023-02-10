package by.a1qa.vkapi.vksteps;

import by.a1qa.vkapi.constants.Parameters;
import by.a1qa.vkapi.constants.Endpoints;
import by.a1qa.vkapi.utils.logger.CustomFilter;
import by.a1qa.vkapi.utils.logger.CustomLogger;
import by.a1qa.vkapi.models.ServerPhotoHash;
import by.a1qa.vkapi.models.UploadServer;
import by.a1qa.vkapi.models.VkResponse;
import by.a1qa.vkapi.utils.JSONCustomParser;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadPictureSteps extends BaseSteps {

    private static Response getWallUploadServer() {
        CustomLogger.info("UploadPictureSteps.getUploadServer()");
        return baseRequest()
                .get(Endpoints.PHOTOS_GET_WALL_UPLOAD_SERVER);
    }

    private static Response picturePostRequest(String url, String photoPath) {
        CustomLogger.info("UploadPictureSteps.picturePostRequest()");
        return given()
                .filter(new CustomFilter())
                .multiPart(new File(photoPath))
                .post(url);
    }

    private static Response getSaveWallPhoto(int server, String photo, String hash) {
        CustomLogger.info("UploadPictureSteps.getSaveWallPhoto()");
        return baseRequest()
                .queryParam(Parameters.SERVER, server)
                .queryParam(Parameters.PHOTO, photo)
                .queryParam(Parameters.HASH, hash)
                .get(Endpoints.PHOTOS_SAVE_WALL_PHOTO);
    }

    public static Response postPhotoOnWall(String photoFilePath) {
        Response getUploadServer = UploadPictureSteps.getWallUploadServer();
        VkResponse<UploadServer> uploadServerResponse = ResponseSteps.getObject(getUploadServer, new TypeReference<VkResponse<UploadServer>>() {});
        Response picturePostResponse = UploadPictureSteps.picturePostRequest(uploadServerResponse.getResponse().getUploadUrl(), photoFilePath);
        String jsonObject = ResponseSteps.getJsonStringFromResponse(picturePostResponse);
        ServerPhotoHash serverPhotoHash = JSONCustomParser.getObjectFromString(jsonObject, ServerPhotoHash.class);
        return UploadPictureSteps.getSaveWallPhoto(serverPhotoHash.getServer(), serverPhotoHash.getPhoto(), serverPhotoHash.getHash());
    }
}
