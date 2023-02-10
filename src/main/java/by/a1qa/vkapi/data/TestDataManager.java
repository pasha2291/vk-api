package by.a1qa.vkapi.data;

import by.a1qa.vkapi.constants.FilePaths;
import by.a1qa.vkapi.utils.JSONCustomParser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataManager {

    private static final String USER_ID = "userID";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_TOKEN = "token";

    public static int getUserID() {
        return Integer.parseInt(JSONCustomParser.parseElementByName(FilePaths.CREDENTIALS_FILE_PATH, USER_ID));
    }

    public static String getUserPhone() {
        return JSONCustomParser.parseElementByName(FilePaths.CREDENTIALS_FILE_PATH, LOGIN);
    }

    public static String getUserEmail() {
        return JSONCustomParser.parseElementByName(FilePaths.CREDENTIALS_FILE_PATH, PASSWORD);
    }

    public static String getUserToken() {
        return JSONCustomParser.parseElementByName(FilePaths.CREDENTIALS_FILE_PATH, USER_TOKEN);
    }
}
