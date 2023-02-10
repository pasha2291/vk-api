package by.a1qa.vkapi.utils;

import by.a1qa.vkapi.utils.logger.CustomLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

@UtilityClass
public class JSONCustomParser {

    private static JSONParser parser = new JSONParser();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String parseElementByName(String path, String elementName) {
        CustomLogger.info(String.format("JSONCustomParser.parseElementByName() : %s : %s", path, elementName));
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
        return (String) jsonObject.get(elementName);
    }

    @SneakyThrows
    public static <T> T getObjectFromString(String jsonObject, TypeReference<T> tClass) {
        CustomLogger.info("JSONCustomParser.getObjectFromString()");
        return objectMapper.readValue(jsonObject, tClass);
    }

    @SneakyThrows
    public static <T> T getObjectFromString(String jsonObject, Class<T> tClass) {
        CustomLogger.info("JSONCustomParser.getObjectFromString()");
        return objectMapper.readValue(jsonObject, tClass);
    }
}
