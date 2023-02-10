package by.a1qa.vkapi.vksteps;

import by.a1qa.vkapi.utils.logger.CustomLogger;
import by.a1qa.vkapi.utils.JSONCustomParser;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseSteps {

    public static <T> T getObject(Response response, TypeReference<T> tClass) {
        CustomLogger.info(String.format("ResponseSteps.getObject() : %s", tClass));
        return JSONCustomParser.getObjectFromString(response.body().asString(), tClass);
    }

    public static String getJsonStringFromResponse(Response response) {
        return response.jsonPath().prettify();
    }
}
