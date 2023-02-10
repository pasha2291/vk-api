package by.a1qa.vkapi.data;

import by.a1qa.vkapi.constants.FilePaths;
import by.a1qa.vkapi.utils.JSONCustomParser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigDataManager {

    private static final String BASE_URL = "baseUrl";
    private static final String BASE_REQUEST_URL = "baseRequestUrl";
    private static final String API_VERSION = "apiVersion";

    public static String getURL() {
        return JSONCustomParser.parseElementByName(FilePaths.CONFIG_FILE_PATH, BASE_URL);
    }

    public static String getBaseRequestURL() {
        return JSONCustomParser.parseElementByName(FilePaths.CONFIG_FILE_PATH, BASE_REQUEST_URL);
    }

    public static String getApiVersion() {
        return JSONCustomParser.parseElementByName(FilePaths.CONFIG_FILE_PATH, API_VERSION);
    }
}
