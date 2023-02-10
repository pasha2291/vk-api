package by.a1qa.vkapi.vksteps;

import by.a1qa.vkapi.constants.Parameters;
import by.a1qa.vkapi.data.ConfigDataManager;
import by.a1qa.vkapi.data.TestDataManager;
import by.a1qa.vkapi.utils.logger.CustomFilter;
import by.a1qa.vkapi.utils.logger.CustomLogger;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseSteps {

    private final static String BASE_URL = ConfigDataManager.getBaseRequestURL();

    protected static RequestSpecification baseRequest() {
        CustomLogger.info(String.format("BaseSteps.baseRequest() : %s", BASE_URL));
        return given()
                .filter(new CustomFilter())
                .baseUri(BASE_URL)
                .queryParam(Parameters.ACCESS_TOKEN, TestDataManager.getUserToken())
                .queryParam(Parameters.V, ConfigDataManager.getApiVersion())
                .contentType(ContentType.JSON);
    }
}
